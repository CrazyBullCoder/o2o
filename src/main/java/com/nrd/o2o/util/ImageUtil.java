package com.nrd.o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	public static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();

	public static String gererateThumbnail(File shopImg, String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(shopImg);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		// File dest = new File("E:\\Pictures\\upload\\item\\shop\\37");
		File waterMark = new File(basePath + "\\watermark.jpg");
		try {
			Thumbnails.of(shopImg).size(160, 160).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(waterMark), 0.5f)
			.outputQuality(0.8).toFile(dest);
			
			/*
			 * Thumbnails.of(thumbnail).size(160, 160) .watermark(Positions.BOTTOM_RIGHT,
			 * ImageIO.read(new
			 * File("E:\\eclipse-workspace\\o2o\\src\\main\\resources\\watermark.jpg")),
			 * 0.5f) .outputQuality(0.8).toFile(new File("E:\\Pictures\\1_1.jpg"));
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return relativeAddr;
	}

	/**
	 * 创建目标路径所涉及到的目录，即/home/work/nrd/xxx.jpg 那么home work nrd 这三个文件夹都得自动创建
	 * 
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取输入文件流的扩展名
	 * 
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
	private static String getFileExtension(File shopImg) {
		String originalFilename = shopImg.getName();
		return originalFilename.substring(originalFilename.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
	 * 
	 * @return
	 */
	public static String getRandomFileName() {
		// 获取五位随机数
		int rannum = r.nextInt(89999) + 10000;
		String nowTimerStr = sDateFormat.format(new Date());
		return nowTimerStr + rannum;
	}

	public static void main(String[] args) throws IOException {

		Thumbnails.of(new File("E:\\Pictures\\1.jpg")).size(160, 160)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "\\watermark.jpg")), 0.5f)
				.outputQuality(0.8).toFile(new File("E:\\Pictures\\1_1.jpg"));
	}
	public static String gererateThumbnail(InputStream thumbnailInputStream, String fileName, String targetAddr) {

		String realFileName = getRandomFileName();
		String extension = getFileExtension(fileName);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		// File dest = new File("E:\\Pictures\\upload\\item\\shop\\37");
		File waterMark = new File(basePath + "\\watermark.jpg");
		try {
			Thumbnails.of(thumbnailInputStream).size(160, 160).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(waterMark), 0.5f)
					.outputQuality(0.8).toFile(dest);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return relativeAddr;
	}
	public static void deleteFileOrPath(String stroePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath()+stroePath);
		if (fileOrPath.exists()) {
			if (fileOrPath.isDirectory()) {
				File[] files = fileOrPath.listFiles();
				for (File file : files) {
					file.delete();
				}
			}
			fileOrPath.delete();
		}
	}
}
