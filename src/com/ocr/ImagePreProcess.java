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

public class ImagePreProcess {

	/**
	 * 根据 图片的像素值判断是否为白色
	 * @param colorInt 像素值
	 * @return 1 or 0
	 */
	public static int isBlack(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() <= 100) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * 根据 图片的像素值判断是否为白色
	 * @param colorInt 像素值
	 * @return 1 or 0
	 */
	public static int isWhite(int colorInt) {
		Color color = new Color(colorInt);
		//如果color.getRed() + color.getGreen() + color.getBlue() > 100 则为白色
		if (color.getRed() + color.getGreen() + color.getBlue() > 100) {
			return 1;
		}
		return 0;
	}

	/**
	 * 去除背景颜色
	 * @param picFile 验证码图片所在文件地址
	 * @return img  去除背景颜色的验证码
	 * @throws Exception
	 */
	public static BufferedImage removeBackgroud(String picFile)
			throws Exception {
		BufferedImage img = ImageIO.read(new File(picFile));
		int width = img.getWidth();
		int height = img.getHeight();
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				if (isWhite(img.getRGB(x, y)) == 1) {
					img.setRGB(x, y, Color.WHITE.getRGB());
				} else {
					img.setRGB(x, y, Color.BLACK.getRGB());
				}
			}
		}
		return img;
	}

	/**
	 * 切割图片
	 * @param img 一张完整的验证码图片
	 * @return subImgs 切割完成的验证码
	 * @throws Exception
	 */
	public static List<BufferedImage> splitImage(BufferedImage img)
			throws Exception {
		List<BufferedImage> subImgs = new ArrayList<BufferedImage>();
		
		//使用工具window自带的画图  放到到800 添加标尺和网格 这样便于处理
		/*
		 * getSubimage(int x,int y,int w,int h)
		 * x,y为图片左上角起始剪切位置  w,h为剪切图片的大小
		 */
		subImgs.add(img.getSubimage(10, 6, 8, 10));
		subImgs.add(img.getSubimage(19, 6, 8, 10));
		subImgs.add(img.getSubimage(28, 6, 8, 10));
		subImgs.add(img.getSubimage(37, 6, 8, 10));
		return subImgs;
	}

	/**
	 * 加载训练样本数据
	 * @return map 将样本数据放入hashmap中
	 * @throws Exception
	 */
	public static Map<BufferedImage, String> loadTrainData() throws Exception {
		Map<BufferedImage, String> map = new HashMap<BufferedImage, String>();
		File dir = new File("train");
		File[] files = dir.listFiles();
		for (File file : files) {
			map.put(ImageIO.read(file), file.getName().charAt(0) + "");
		}
		return map;
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
			Label1: for (int x = 0; x < width; ++x) {
				for (int y = 0; y < height; ++y) {
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
	 * @param file 待测试的验证码文件地址
	 * @return result 验证码显示结果
	 * @throws Exception
	 */
	public static String getAllOcr(String file) throws Exception {
		
		//1、图像预处理
		BufferedImage img = removeBackgroud(file);   //去除背景颜色
		
		//2、切割图片
		List<BufferedImage> listImg = splitImage(img);  //按像素切割
		
		//3、训练
		/*
		 * 通过步骤2中切割的0-9的数字
		 * 直接拿几张图片，包含0-9，每个数字一个样本就可以了，将文件名对应相应的数字
		 */
		
		//4、测试
		Map<BufferedImage, String> map = loadTrainData(); //加载训练样本数据
		String result = "";
		for (BufferedImage bi : listImg) {
			result += getSingleCharOcr(bi, map);  //将验证码切割的图片和样本数据一一对比
		}
		
		ImageIO.write(img, "JPG", new File("result\\"+result+".jpg")); //将测试结果放入result中
		return result;
	}

	/**
	 * http://www.puke888.com/authimg.php
	 * 下载验证码图片
	 */
	public static void downloadImage() {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(
				"http://www.puke888.com/authimg.php");
		for (int i = 0; i < 30; i++) {
			try {
				// 执行getMethod
				int statusCode = httpClient.executeMethod(getMethod);
				if (statusCode != HttpStatus.SC_OK) {
					System.err.println("Method failed: "
							+ getMethod.getStatusLine());
				}
				// 读取内容
				String picName = "img\\" + i + ".jpg";
				InputStream inputStream = getMethod.getResponseBodyAsStream();
				OutputStream outStream = new FileOutputStream(picName);
				IOUtils.copy(inputStream, outStream);
				outStream.close();
				System.out.println(i+"OK!");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 释放连接
				getMethod.releaseConnection();
			}
		}
	}

	/**
	 * 主函数入口
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		//downloadImage();
		
		/**
		 * 测试验证码结果
		 */
		for (int i = 0; i < 30; ++i) {
			String text = getAllOcr("img\\" + i + ".jpg");
			System.out.println(i + ".jpg = " + text);
		}
	}
}
