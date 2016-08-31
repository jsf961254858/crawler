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

public class ImagePreProcess3 {

	private static Map<BufferedImage, String> trainMap = null;
	private static int index = 0;

	public static int isBlack(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() <= 100) {
			return 1;
		}
		return 0;
	}

	public static int isWhite(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() > 600) {
			return 1;
		}
		return 0;
	}

	/**
	 * 可以注意到每个验证码数字或字母都是同一颜色，所以把验证码平均分成5份
	 * 计算每个区域的颜色分布，除了白色之外，颜色值最多的就是验证码的颜色
	 * @param picFile 验证码图片所在文件地址
	 * @return img  去除背景颜色的验证码
	 * @throws Exception
	 */
	public static BufferedImage removeBackgroud(String picFile)
			throws Exception {
		BufferedImage img = ImageIO.read(new File(picFile));
		
		img = img.getSubimage(1, 1, img.getWidth() - 2, img.getHeight() - 2); //去除图片的黑色边框
		
		int width = img.getWidth();
		int height = img.getHeight();
		double subWidth = width / 5.0;  //把验证码平均分成5份
		for (int i = 0; i < 5; i++) {
			
			//map中记录除白色以外的每种颜色的像素个数
			Map<Integer, Integer> map = new HashMap<Integer, Integer>(); 
			for (int x = (int) (1 + i * subWidth); x < (i + 1) * subWidth && x < width - 1; ++x) {
				for (int y = 0; y < height; ++y) {
					if (isWhite(img.getRGB(x, y)) == 1)
						continue;
					if (map.containsKey(img.getRGB(x, y))) {
						map.put(img.getRGB(x, y), map.get(img.getRGB(x, y)) + 1);
					} else {
						map.put(img.getRGB(x, y), 1);
					}
				}
			}
			
			//计算map中颜色最多的像素值
			int max = 0;
			int colorMax = 0;
			for (Integer color : map.keySet()) {
				if (max < map.get(color)) {
					max = map.get(color);
					colorMax = color;
				}
			}
			
			/*
			 * 将为colorMax的像素设为黑色
			 * 其他设为白色
			 */
			for (int x = (int) (1 + i * subWidth); x < (i + 1) * subWidth && x < width - 1; ++x) {
				for (int y = 0; y < height; ++y) {
					if (img.getRGB(x, y) != colorMax) {
						img.setRGB(x, y, Color.WHITE.getRGB());
					} else {
						img.setRGB(x, y, Color.BLACK.getRGB());
					}
				}
			}
		}
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
				if (isBlack(img.getRGB(x, y)) == 1) {
					start = y;
					break Label1;
				}
			}
		}
		
		Label2: for (int y = height - 1; y >= 0; --y) {
			for (int x = 0; x < width; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
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
		List<Integer> weightlist = new ArrayList<Integer>(); //在x坐标中 将图片分成4段或者5段
		
		//查找x坐标上黑色像素个数
		for (int x = 0; x < width; ++x) {
			int count = 0;
			for (int y = 0; y < height; ++y) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
			weightlist.add(count);
		}
		
		for (int i = 0; i < weightlist.size();i++) {
			int length = 0;
			while (i < weightlist.size() && weightlist.get(i) > 0) {
				i++;
				length++;
			}
			if (length > 2) {
				//纵向截取
				subImgs.add(removeBlank(img.getSubimage(i - length, 0, length, height)));
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
			File dir = new File("train3");
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
		String result = "#";
		int width = img.getWidth();
		int height = img.getHeight();
		int min = width * height;
		for (BufferedImage bi : map.keySet()) {
			int count = 0;
			
			if (Math.abs(bi.getWidth()-width) > 2 || (Math.abs(bi.getHeight()-height) > 2)) //如果两张图片大小相差大于2个像素 为噪音数据 不识别
				continue;
			
			int widthmin = width < bi.getWidth() ? width : bi.getWidth();
			int heightmin = height < bi.getHeight() ? height : bi.getHeight();
			Label1: for (int x = 0; x < widthmin; ++x) {
				for (int y = 0; y < heightmin; ++y) {
					if (isBlack(img.getRGB(x, y)) != isBlack(bi.getRGB(x, y))) {
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
		List<BufferedImage> listImg = splitImage(img);//按像素切割
		
		//3、训练
		/*
		 * trainData()
		 */
		
		//4、测试
		Map<BufferedImage, String> map = loadTrainData();//加载训练样本数据
		String result = "";
		for (BufferedImage bi : listImg) {
			result += getSingleCharOcr(bi, map);          //图片已经变成了4个数字，所以最后一个不识别
		}
		
		ImageIO.write(img, "JPG", new File("result3\\" + result + ".jpg"));
		return result;
	}

	/**
	 * 下载验证码图片
	 * 地址http://game.tom.com/checkcode.php
	 */
	public static void downloadImage() {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod("http://game.tom.com/checkcode.php");
		for (int i = 0; i < 30; i++) {
			try {
				// 执行getMethod
				int statusCode = httpClient.executeMethod(getMethod);
				if (statusCode != HttpStatus.SC_OK) {
					System.err.println("Method failed: "
							+ getMethod.getStatusLine());
				}
				// 读取内容
				String picName = "img3\\" + i + ".jpg";
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
	public static void trainData() throws Exception {
		File dir = new File("temp3");
		File[] files = dir.listFiles();
		for (File file : files) {
			BufferedImage img = removeBackgroud("temp3\\" + file.getName());
			List<BufferedImage> listImg = splitImage(img);
			if (listImg.size() == 5) {
				for (int j = 0; j < listImg.size(); ++j) {
					ImageIO.write(listImg.get(j), "JPG", new File("train3\\"
							+ file.getName().charAt(j) + "-" + (index++)
							+ ".jpg"));
				}
			}
		}
	}

	/**
	 * 前面的验证码背景都比较简单，用亮度稍微区分一下就可以去掉背景 
	 * 下面这个稍微复杂一点的
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		//downloadImage();
		//trainData();
		
		for (int i = 0; i < 30; ++i) {
			String text = getAllOcr("img3\\" + i + ".jpg");
			System.out.println(i + ".jpg = " + text);
		}
	}
	
}
