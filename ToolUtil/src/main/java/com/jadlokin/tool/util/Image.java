package com.jadlokin.tool.util;

import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.IOException;

public class Image {
	private BufferedImage bufferedImage;

	private int width;
	private int height;

	public Image(String imagePath) {
		try {
			bufferedImage = ImageIO.read(Image.class.getResourceAsStream(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.notNull(bufferedImage,"获取背景图片失败");
		width=bufferedImage.getWidth(null);//图像宽度
		height=bufferedImage.getHeight(null);//图像高度
	}

	public int[] getData() {
		PixelGrabber pixelGrabber = new PixelGrabber(bufferedImage, 0, 0, -1, -1, true);
		try {
			pixelGrabber.grabPixels();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return (int[]) pixelGrabber.getPixels();
	}

	public Shape toShape() {
		Area area=new Area();
		int[] pixels = getData();
		for(int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (getAlpha(pixels[i * width + j]) > 64) {
					Rectangle rectangle = new Rectangle(j, i, 1, 1);
					area.add(new Area(rectangle));
				}
			}
		}
		return area;
	}

	private static int getAlpha(int pixel) {
		return (pixel >> 24) & 0xff;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage toBufferImage() {
		return bufferedImage;
	}
}
