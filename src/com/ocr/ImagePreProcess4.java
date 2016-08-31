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

	public static BufferedImage removeBlank(BufferedImage img) throws Exception {
		int width = img.getWidth();
		int height = img.getHeight();
		int start = 0;
		int end = 0;
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
		return img.getSubimage(0, start, width, end - start + 1);
	}

	public static List<BufferedImage> splitImage(BufferedImage img)
			throws Exception {
		List<BufferedImage> subImgs = new ArrayList<BufferedImage>();
		int width = img.getWidth();
		int height = img.getHeight();
		List<Integer> weightlist = new ArrayList<Integer>();
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
			if (length > 18) {
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

	public static Map<BufferedImage, String> loadTrainData() throws Exception {
		if (trainMap == null) {
			Map<BufferedImage, String> map = new HashMap<BufferedImage, String>();
			File dir = new File("train4");
			File[] files = dir.listFiles();
			for (File file : files) {
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

	public static String getSingleCharOcr(BufferedImage img,
			Map<BufferedImage, String> map) throws Exception {
		String result = "#";
		img = scaleImage(img);
		int width = img.getWidth();
		int height = img.getHeight();
		int min = width * height;
		boolean bNotEight = isNotEight(img);
		boolean bNotThree = isNotThree(img);
		boolean bNotFive = isNotFive(img);
		for (BufferedImage bi : map.keySet()) {
			if (bNotThree && map.get(bi).startsWith("3"))
				continue;
			if (bNotEight && map.get(bi).startsWith("8"))
				continue;
			if (bNotFive && map.get(bi).startsWith("5"))
				continue;
			double count1 = getBlackCount(img);
			double count2 = getBlackCount(bi);
			if (Math.abs(count1 - count2) / Math.max(count1, count2) > 0.25)
				continue;
			int count = 0;
			if (width < bi.getWidth() && height < bi.getHeight()) {
				for (int m = 0; m <= bi.getWidth() - width; m++) {
					for (int n = 0; n <= bi.getHeight() - height; n++) {
						Label1: for (int x = m; x < m + width; ++x) {
							for (int y = n; y < n + height; ++y) {
								if (isWhite(img.getRGB(x - m, y - n)) != isWhite(bi
										.getRGB(x, y))) {
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
				int heightmin = height < bi.getHeight() ? height : bi
						.getHeight();
				Label1: for (int x = 0; x < widthmin; ++x) {
					for (int y = 0; y < heightmin; ++y) {
						if (isWhite(img.getRGB(x, y)) != isWhite(bi
								.getRGB(x, y))) {
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

	public static String getAllOcr(String file) throws Exception {
		BufferedImage img = removeBackgroud(file);
		List<BufferedImage> listImg = splitImage(img);
		Map<BufferedImage, String> map = loadTrainData();
		String result = "";
		for (BufferedImage bi : listImg) {
			result += getSingleCharOcr(bi, map);
		}
		System.out.println(result);
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

	public static BufferedImage scaleImage(BufferedImage img)
	{
		ScaleFilter sf = new ScaleFilter(16,16);
		BufferedImage imgdest = new BufferedImage(16,16, img.getType());
		return sf.filter(img, imgdest);
	}
	
	public static void trainData() throws Exception {
		File dir = new File("temp4");
		File[] files = dir.listFiles();
		for (File file : files) {
			BufferedImage img = removeBackgroud("temp4\\" + file.getName());
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
		
		downloadImage();
		
		trainData();
		
		/*for (int i = 0; i < 30; ++i) {
			String text = getAllOcr("img4\\" + i + ".jpg");
			System.out.println(i + ".jpg = " + text);
		}*/
	}
}
