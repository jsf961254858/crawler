package com.ocr;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.jhlabs.image.ScaleFilter;
import com.ocr.svm.svm_predict;
import com.ocr.svm.svm_train;

public class ImagePreProcess5 {

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

	/**
	 * 获取Svm输入格式的数据，即trains中data.txt
	 * 把每张图片转换成libsvm的data格式    label index[i]:value[i]
	 * 2 1:0 2:0 3:0 4:0 5:0 6:0 7:0 8:0 9:0 10:0 11:0 12:0 13:0 14:1 15:1 16:0 17:0 18:0 19:0 20:1 21:1 .....
	 * 3 1:0 2:0 3:0 4:0 5:1 6:0 7:0 8:0 9:0 10:0 11:0 12:1 13:0 14:0 15:0 16:0 17:0 18:1 19:1 20:1 21:1 .....
	 * 前面的label就是对应的图片的数字，index[i]表示第i个像素，value[i]表示第i个像素的值，像素为黑是value[i]为1，白则为0(更合理的方法好像是黑为0.999，白为0.001)
	 * @throws Exception
	 */
	public static void getSvmdata() throws Exception {
		File dir = new File("train5");
		File dataFile = new File("train5\\data.txt");
		FileOutputStream fs = new FileOutputStream(dataFile);
		File[] files = dir.listFiles();
		for (File file : files) {
			if (!file.getName().endsWith(".jpg"))
				continue;
			BufferedImage imgdest = ImageIO.read(file);
			fs.write((file.getName().charAt(0) + " ").getBytes());
			int index = 1;
			for (int x = 0; x < imgdest.getWidth(); ++x) {
				for (int y = 0; y < imgdest.getHeight(); ++y) {
					fs.write((index++ + ":" + isBlack(imgdest.getRGB(x, y)) + " ").getBytes());
				}
			}
			fs.write("\r\n".getBytes());
		}
		fs.close();
	}

	/**
	 * 训练数据模型
	 * 把每张图片转换成libsvm的data格式    label index[i]:value[i]
	 * 2 1:0 2:0 3:0 4:0 5:0 6:0 7:0 8:0 9:0 10:0 11:0 12:0 13:0 14:1 15:1 16:0 17:0 18:0 19:0 20:1 21:1 .....
	 * 3 1:0 2:0 3:0 4:0 5:1 6:0 7:0 8:0 9:0 10:0 11:0 12:1 13:0 14:0 15:0 16:0 17:0 18:1 19:1 20:1 21:1 .....
	 * 前面的label就是对应的图片的数字，index[i]表示第i个像素，value[i]表示第i个像素的值，像素为黑是value[i]为1，白则为0(更合理的方法好像是黑为0.999，白为0.001)
	 * @throws Exception
	 */
	public static void scaleTraindata() throws Exception {
		
		File dir = new File("temp5");
		File dataFile = new File("train5\\data.txt");  //训练数据集
		FileOutputStream fs = new FileOutputStream(dataFile);
		File[] files = dir.listFiles();
		for (File file : files) {
			BufferedImage img = ImageIO.read(file);  //读取训练数据图片
			ScaleFilter sf = new ScaleFilter(16, 16); //将图片统一大小16*16
			BufferedImage imgdest = new BufferedImage(16, 16, img.getType());
			imgdest = sf.filter(img, imgdest);
			
			//ImageIO.write(imgdest, "JPG", new File("train5\\" + file.getName()));  //保存统一大小16*16的图片
			
			/*
			 * 将训练数据集写入data.txt中
			 */
			fs.write((file.getName().charAt(0) + " ").getBytes());
			int index = 1;
			for (int x = 0; x < imgdest.getWidth(); ++x) {
				for (int y = 0; y < imgdest.getHeight(); ++y) {
					fs.write((index++ + ":" + isBlack(imgdest.getRGB(x, y)) + " ").getBytes());
				}
			}
			fs.write("\r\n".getBytes());
		}
		fs.close();

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		/* 1、数据预处理
		 * 去除背景噪音   切割文件 同ImagePreProcess4
		 * 把训练的图片缩放成16*16的大小。
		 * 将切割的图片存入在temp5中
		 */
		//scaleTraindata();
		
		/*
		 * 2、训练数据转换为svm输入的格式
		 * 把每张图片转换成libsvm的data格式    label index[i]:value[i]
		 * 2 1:0 2:0 3:0 4:0 5:0 6:0 7:0 8:0 9:0 10:0 11:0 12:0 13:0 14:1 15:1 16:0 17:0 18:0 19:0 20:1 21:1 .....
		 * 3 1:0 2:0 3:0 4:0 5:1 6:0 7:0 8:0 9:0 10:0 11:0 12:1 13:0 14:0 15:0 16:0 17:0 18:1 19:1 20:1 21:1 .....
		 * 前面的label就是对应的图片的数字，index[i]表示第i个像素，value[i]表示第i个像素的值，像素为黑是value[i]为1，白则为0(更合理的方法好像是黑为0.999，白为0.001)
		 */
		//getSvmdata();
		
		/*
		 * 3、建立svm分类模型
		 * 输入训练集的绝对路径train5\\data.txt
		 * 在train5目录下得到data.txt.model
		 */
		//svm_train train = new svm_train();
		//train.run(new String[]{new File("train5\\data.txt").getAbsolutePath()});
		
		/*
		 * 4、模型检测
		 * args[0] 为需要预测的数据集 格式和训练集一样 label indexi:valuei
		 * args[1] 为第3步中得到的模型
		 * args[2] 识别结果就在output.txt里面了
		 * 并在控制台得到准确率
		 * Accuracy = 100.0% (200/200) (classification)
		 */
		/*svm_predict.main(new String[] {
				new File("train5\\data.txt").getAbsolutePath(),  
				new File("train5\\data.txt.model").getAbsolutePath(),
				"train5\\output.txt" });*/
		
		/*
		 * 5、模型使用
		 * 模型使用同步骤4一样，只是args[0]中label值随意给一个，在args[2]中会得到正确的结果
		 */
		
		
	}
}