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

import com.jhlabs.image.ScaleFilter;

public class ImagePreProcess4 {

	private static Map<BufferedImage, String> trainMap = null;
	private static int index = 0;

	public static int isBlack(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() <= 300) {
			return 1;
		}
		return 0;
	}

	public static int isWhite(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() > 300) {
			return 1;
		}
		return 0;
	}

	public static int getColorBright(int colorInt) {
		Color color = new Color(colorInt);
		return color.getRed() + color.getGreen() + color.getBlue();

	}

	public static int isBlackOrWhite(int colorInt) {
		if (getColorBright(colorInt) < 30 || getColorBright(colorInt) > 730) {
			return 1;
		}
		return 0;
	}

	/**
	 * 放大看，我们会发现干扰线是纯黑色的，因此去干扰线的方法就有了
	 * 对点color[i][j],如果color[i+1][j],color[i-1][j],color[i][j+1],color[i][j-1]都是纯黑或者纯白色的，
	 * 就认为color[i][j]是干扰，将color[i][j]置为白色。
	 * @param picFile 验证码图片所在文件地址
	 * @return img  去除背景颜色的验证码
	 * @throws Exception
	 */
	public static BufferedImage removeBackgroud(String picFile)
			throws Exception {
		BufferedImage img = ImageIO.read(new File(picFile));
		int width = img.getWidth();
		int height = img.getHeight();
		
		for (int x = 1; x < width - 1; ++x) {
			for (int y = 1; y < height - 1; ++y) {
				if (getColorBright(img.getRGB(x, y)) < 100) {
					if (isBlackOrWhite(img.getRGB(x - 1, y))
							+ isBlackOrWhite(img.getRGB(x + 1, y))
							+ isBlackOrWhite(img.getRGB(x, y - 1))
							+ isBlackOrWhite(img.getRGB(x, y + 1)) == 4) {
						img.setRGB(x, y, Color.WHITE.getRGB());
					}
				}
			}
		}
		
		for (int x = 1; x < width - 1; ++x) {
			for (int y = 1; y < height - 1; ++y) {
				if (getColorBright(img.getRGB(x, y)) < 100) {
					if (isBlackOrWhite(img.getRGB(x - 1, y))
							+ isBlackOrWhite(img.getRGB(x + 1, y))
							+ isBlackOrWhite(img.getRGB(x, y - 1))
							+ isBlackOrWhite(img.getRGB(x, y + 1)) == 4) {
						img.setRGB(x, y, Color.WHITE.getRGB());
					}
				}
			}
		}
		
		img = img.getSubimage(1, 1, img.getWidth() - 2, img.getHeight() - 2);
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
		int start = 0;//横向截取的起始位置
		int end = 0;//横向截取的结束位置
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
		List<BufferedImage> subImgs = new ArrayList<BufferedImage>();
		int width = img.getWidth();
		int height = img.getHeight();
		List<Integer> weightlist = new ArrayList<Integer>(); //在x坐标中 将图片分成4段或者5段
		
		for (int x = 0; x < width; ++x) {
			int count = 0;
			for (int y = 0; y < height; ++y) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
			weightlist.add(count);  
		}
		
		for (int i = 0; i < weightlist.size(); i++) {
			int length = 0;
			while (i < weightlist.size() && weightlist.get(i) > 0) {
				i++;
				length++;
			}
			//纵向截取
			if (length > 18) {  //两个数据连在一起的情况
				subImgs.add(removeBlank(img.getSubimage(i - length, 0,
						length / 2, height)));
				subImgs.add(removeBlank(img.getSubimage(i - length / 2, 0,
						length / 2, height)));
			} else if (length > 5) {
				subImgs.add(removeBlank(img.getSubimage(i - length, 0, length,
						height)));
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
			File dir = new File("train4");
			File[] files = dir.listFiles();
			for (File file : files) {
				//统一大小为16*16的
				map.put(scaleImage(ImageIO.read(file)), file.getName().charAt(0) + "");
			}
			trainMap = map;
		}
		return trainMap;
	}

	public static int getDistance(BufferedImage img, BufferedImage sample) {
		int width = img.getWidth();
		int height = img.getHeight();
		int count = 0;
		int widthmin = width < sample.getWidth() ? width : sample.getWidth();
		int heightmin = height < sample.getHeight() ? height : sample
				.getHeight();
		for (int x = 0; x < widthmin; ++x) {
			for (int y = 0; y < heightmin; ++y) {
				if (isWhite(img.getRGB(x, y)) != isWhite(sample.getRGB(x, y))) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * 根据8的特点判断
	 * 在y轴中心上下2行，x轴0到中心点+2，判断纯黑的像素个数，如果个数小于2不是8
	 * @param img 切割的图片
	 * @return true or false
	 */
	public static boolean isNotEight(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int minCount = width;
		for (int y = height / 2 - 2; y < height / 2 + 2; ++y) {
			int count = 0;
			for (int x = 0; x < width / 2 + 2; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
			minCount = Math.min(count, minCount);
		}
		return minCount < 2;
	}

	/**
	 * 根据3的特点判断
	 * 在y轴中心上下3行，x轴0到中心点+1，判断纯黑的像素个数，如果最小个数大于0不是3
	 * @param img 切割的图片
	 * @return true or false
	 */
	public static boolean isNotThree(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int minCount = width;
		for (int y = height / 2 - 3; y < height / 2 + 3; ++y) {
			int count = 0;
			for (int x = 0; x < width / 2 + 1; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
			minCount = Math.min(count, minCount);
		}
		return minCount > 0;
	}

	/**
	 * 根据5的特点判断
	 * 在y轴0到中心点，x轴 2/3宽到终点，判断纯黑的像素个数，如果最小个数大于0不是5
	 * @param img 切割的图片
	 * @return true or false
	 */
	public static boolean isNotFive(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int minCount = width;
		for (int y = 0; y < height / 3; ++y) {
			int count = 0;
			for (int x = width * 2 / 3; x < width; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
			minCount = Math.min(count, minCount);
		}
		return minCount > 0;
	}

	/**
	 * 切割的测试图片和样本数据一一对比
	 * 两张图片中不一样的地方越小说明两张图片越相识
	 * @param img 切割的测试图片
	 * @param map 样本数据\训练数据
	 * @return
	 */
	public static String getSingleCharOcr(BufferedImage img,
			Map<BufferedImage, String> map) throws Exception {
		String result = "#";
		
		img = scaleImage(img);  //将图片统一为16*16
		
		int width = img.getWidth();
		int height = img.getHeight();
		int min = width * height;
		
		boolean bNotEight = isNotEight(img); //判断img是否是8
		boolean bNotThree = isNotThree(img); //判断img是否是3
		boolean bNotFive = isNotFive(img);   //判断img是否是5
		
		for (BufferedImage bi : map.keySet()) {
			
			/*
			 * 由于3 5 8 三个数字区分错误概率大，所以单独检验一次
			 */
			if (bNotThree && map.get(bi).startsWith("3"))
				continue;
			if (bNotEight && map.get(bi).startsWith("8"))
				continue;
			if (bNotFive && map.get(bi).startsWith("5"))
				continue;
			
			double count1 = getBlackCount(img);
			double count2 = getBlackCount(bi);
			if (Math.abs(count1 - count2) / Math.max(count1, count2) > 0.25) //判断两张图片像素 
				continue;
			
			int count = 0;
			if (width < bi.getWidth() && height < bi.getHeight()) {
				for (int m = 0; m <= bi.getWidth() - width; m++) {
					for (int n = 0; n <= bi.getHeight() - height; n++) {
						Label1: for (int x = m; x < m + width; ++x) {
							for (int y = n; y < n + height; ++y) {
								if (isWhite(img.getRGB(x - m, y - n)) != isWhite(bi.getRGB(x, y))) {
									count++;
									if (count >= min)
										break Label1;
								}
							}
						}
					}
				}
			} else {
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
		BufferedImage img = removeBackgroud(file);
		
		//2、切割图片
		List<BufferedImage> listImg = splitImage(img);
		
		//3、训练
		/*
		 * trainData()
		 */
		
		//4、测试
		Map<BufferedImage, String> map = loadTrainData();
		
		String result = "";
		for (BufferedImage bi : listImg) {
			result += getSingleCharOcr(bi, map);
		}
		//System.out.println(result);
		ImageIO.write(img, "JPG", new File("result4\\" + result + ".jpg"));
		return result;
	}

	public static int getBlackCount(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int count = 0;
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * 下载验证码图片
	 * 地址http://reg.keepc.com/getcode/getCode.php
	 * 地址已经不存在 但是图片已经保存下来再img4中
	 */
	public static void downloadImage() {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(
				"http://reg.keepc.com/getcode/getCode.php");
		for (int i = 0; i < 30; i++) {

			try {
				// 执行getMethod
				int statusCode = httpClient.executeMethod(getMethod);
				if (statusCode != HttpStatus.SC_OK) {
					System.err.println("Method failed: "
							+ getMethod.getStatusLine());
				}
				// 读取内容
				String picName = "img4\\" + i + ".jpg";
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
	 * 将图片过滤成统一大小16*16
	 * @param img 要处理的图片
	 * @return sf.filter(img, imgdest); 大小为16*16的图片
	 */
	public static BufferedImage scaleImage(BufferedImage img)
	{
		ScaleFilter sf = new ScaleFilter(16,16);
		BufferedImage imgdest = new BufferedImage(16,16, img.getType());
		return sf.filter(img, imgdest);
	}
	
	/**
	 * 生成训练集数据
	 * @throws Exception
	 */
	public static void trainData() throws Exception {
		File dir = new File("temp4");
		File[] files = dir.listFiles();
		for (File file : files) {
			//去除背景噪音
			BufferedImage img = removeBackgroud("temp4\\" + file.getName());
			
			//ImageIO.write(img, "JPG", new File("train4\\"+ file.getName() ));
			
			//切割图片
			List<BufferedImage> listImg = splitImage(img);
			if (listImg.size() == 4) {
				for (int j = 0; j < listImg.size(); ++j) {
					ImageIO.write(listImg.get(j), "JPG", new File("train4\\"
							+ file.getName().charAt(j) + "-" + (index++)
							+ ".jpg"));
				}
			}
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		//downloadImage();
		
		//trainData();
		
		for (int i = 0; i < 30; ++i) {
			String text = getAllOcr("img4\\" + i + ".jpg");
			System.out.println(i + ".jpg = " + text);
		}
	}
}
