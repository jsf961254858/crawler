package com.ocr;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

public class ImagePreProcess2 {

	//训练集样本<key,value>=<图片,对应的数字>
	private static Map<BufferedImage, String> trainMap = null;

	public static int isBlack(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() <= 100) {
			return 1;
		}
		return 0;
	}

	public static int isWhite(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() > 100) {
			return 1;
		}
		return 0;
	}

	/**
	 * 去除背景颜色    这个验证码是黑白不用处理
	 * @param picFile 验证码图片所在文件地址
	 * @return img  去除背景颜色的验证码
	 * @throws Exception
	 */
	public static BufferedImage removeBackgroud(String picFile)
			throws Exception {
		BufferedImage img = ImageIO.read(new File(picFile));
		return img;
	}

	/**
	 * 横向截取
	 * @param img 纵向截取的图片
	 * @return  截取完成的图片
	 * @throws Exception
	 */
	public static BufferedImage removeBlank(BufferedImage img) throws Exception {
		int width = img.getWidth();
		int height = img.getHeight();
		int start = 0; //横向截取的起始位置
		int end = 0; //横向截取的结束位置
		
		Label1: for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				if (isWhite(img.getRGB(x, y)) == 1) {
					start = y;
					break Label1;
				}
			}
		}
		
		Label2: for (int y = height - 1; y >= 0; --y) {
			for (int x = 0; x < width; ++x) {
				if (isWhite(img.getRGB(x, y)) == 1) {
					end = y;
					break Label2;
				}
			}
		}
		//横向截取
		return img.getSubimage(0, start, width, end - start + 1);
	}

	/**
	 * 切割图片
	 * 先纵向扫描，很容易分成四部分，再对每一部分横向扫描
	 * @param img 一张完整的验证码图片
	 * @return subImgs 切割完成的验证码
	 * @throws Exception
	 */
	public static List<BufferedImage> splitImage(BufferedImage img)
			throws Exception {
		
		List<BufferedImage> subImgs = new ArrayList<BufferedImage>(); //保存截取段
		int width = img.getWidth();
		int height = img.getHeight();
		
		List<Integer> weightlist = new ArrayList<Integer>();  //在x坐标中 将图片分成4段
		
		//查找x坐标上白色像素个数
		for (int x = 0; x < width; ++x) {
			int count = 0;
			for (int y = 0; y < height; ++y) {
				if (isWhite(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
			weightlist.add(count);
		}
		
		//
		for (int i = 0; i < weightlist.size();) {
			
			int length = 0; //图片x坐标上的像素数
			while (weightlist.get(i++) > 1) {
				length++;
			}
			if (length > 12) {  //length>12的情况 应该是两个数字连在一起了 的情况
				
				subImgs.add(removeBlank(img.getSubimage(i - length - 1, 0, length / 2, height)));
				subImgs.add(removeBlank(img.getSubimage(i - length / 2 - 1, 0, length / 2, height)));
				
			} else if (length > 3) {
				
				BufferedImage subimage = img.getSubimage(i - length - 1, 0, length, height); //纵向截取
				subImgs.add(removeBlank(subimage));
			}
		}
		return subImgs;
	}

	/**
	 * 加载训练样本数据
	 * @return map 将样本数据放入hashmap中  <key,value>=<图片,对应的数字>
	 * @throws Exception
	 */
	public static Map<BufferedImage, String> loadTrainData() throws Exception {
		if (trainMap == null) {
			Map<BufferedImage, String> map = new HashMap<BufferedImage, String>();
			File dir = new File("train2");
			File[] files = dir.listFiles();
			for (File file : files) {
				map.put(ImageIO.read(file), file.getName().charAt(0) + "");
			}
			trainMap = map;
		}
		return trainMap;
	}

	/**
	 * 切割的测试图片和样本数据一一对比
	 * 两张图片中不一样的地方越小说明两张图片越相识
	 * @param img 切割的测试图片
	 * @param map 样本数据\训练数据
	 * @return
	 */
	public static String getSingleCharOcr(BufferedImage img,
			Map<BufferedImage, String> map) {
		String result = "";
		int width = img.getWidth();
		int height = img.getHeight();
		int min = width * height;
		for (BufferedImage bi : map.keySet()) {
			int count = 0;
			int widthmin = width < bi.getWidth() ? width : bi.getWidth();
			int heightmin = height < bi.getHeight() ? height : bi.getHeight();
			Label1: for (int x = 0; x < widthmin; ++x) {
				for (int y = 0; y < heightmin; ++y) {
					if (isWhite(img.getRGB(x, y)) != isWhite(bi.getRGB(x, y))) {
						count++;
						if (count >= min)
							break Label1;
					}
				}
			}
			if (count < min) {
				min = count;
				result = map.get(bi);
			}
		}
		return result;
	}

	/**
	 * 测试验证码
	 * @param file 待测试验证码文件地址
	 * @return result 验证码显示结果
	 * @throws Exception
	 */
	public static String getAllOcr(String file) throws Exception {
		
		//1、图像预处理
		BufferedImage img = removeBackgroud(file); //去除背景颜色
		
		//2、切割图片
		List<BufferedImage> listImg = splitImage(img); //按像素切割
		
		//3、训练
		/*
		 * 通过步骤2中切割的0-9的数字
		 * 直接拿几张图片，包含0-9，每个数字一个样本就可以了，将文件名对应相应的数字
		 */
		
		//4、测试
		Map<BufferedImage, String> map = loadTrainData();//加载训练样本数据
		String result = "";
		for (BufferedImage bi : listImg) {
			result += getSingleCharOcr(bi, map);
		}
		
		ImageIO.write(img, "JPG", new File("result2\\" + result + ".jpg")); //输出结果数据
		return result;
	}

	/**
	 * 下载验证码图片
	 * 地址已经不存在，不过图片已经存储在img2中
	 * http://www.pkland.net/img.php?key=2000
	 */
	public static void downloadImage() {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = null;
		for (int i = 0; i < 30; i++) {
			getMethod = new GetMethod("http://www.pkland.net/img.php?key="
					+ (2000 + i));
			try {
				// 执行getMethod
				int statusCode = httpClient.executeMethod(getMethod);
				if (statusCode != HttpStatus.SC_OK) {
					System.err.println("Method failed: "
							+ getMethod.getStatusLine());
				}
				// 读取内容
				String picName = "img2\\" + i + ".jpg";
				InputStream inputStream = getMethod.getResponseBodyAsStream();
				OutputStream outStream = new FileOutputStream(picName);
				IOUtils.copy(inputStream, outStream);
				outStream.close();
				System.out.println(i + "OK!");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 释放连接
				getMethod.releaseConnection();
			}
		}
	}

	/**
	 * 生成训练集数据
	 * @throws Exception
	 */
	private static int index = 0;
	public static void trainData() throws Exception {
		File dir = new File("temp2");
		File[] files = dir.listFiles();
		for (File file : files) {
			BufferedImage img = removeBackgroud("temp2\\" + file.getName());
			List<BufferedImage> listImg = splitImage(img);
			if (listImg.size() == 4) {
				for (int j = 0; j < listImg.size(); ++j) {
					ImageIO.write(listImg.get(j), "JPG", new File("train2\\"
							+ file.getName().charAt(j) + "-" + (index++)
							+ ".jpg"));
				}
			}
		}
	}

	/**
	 * 字体固定，大小固定，位置不固定的验证码
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		
		//downloadImage(); //下载验证码
		
		//trainData(); //生成训练集
		
		for (int i = 0; i < 30; ++i) {
			String text = getAllOcr("img2\\" + i + ".jpg");
			System.out.println(i + ".jpg = " + text);
		}
	}
	
}
