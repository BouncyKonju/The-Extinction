package com.konju.extinction.system;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageReader {
	
	private ImageReader() {}
	
	public static int[] readImage(String path) {
		int[] result;
		try {
			BufferedImage image = ImageIO.read(new File(path));
			result = new int[image.getWidth() * image.getHeight()];
			image.getRGB(0, 0, image.getWidth(), image.getHeight(), result, 0, image.getWidth());
		}
		catch (IOException e) {
			e.printStackTrace();
			result = new int[1];
		}
		return result;
	}

}
