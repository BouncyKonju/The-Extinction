package com.konju.extinction.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Map {
	
	public Sprite[] data;
	public int width, height;
	
	public Map(String path) {
		
		int[] tmpdata = load(path);
		data = new Sprite[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// 004cff water
				// 009b0c grass
				// 994f00 brick
				if (tmpdata[x + y * width] == 0xff009b0c) data[x + y * width] = Sprite.GRASS;
				else if (tmpdata[x + y * width] == 0xff004cff) data[x + y * width] = Sprite.WATER;
				else if (tmpdata[x + y * width] == 0xff994f00) data[x + y * width] = Sprite.BRICK;
				else data[x + y * width] = Sprite.GRASS;
			}
		}
	}
	
	private int[] load(String path) {
		int[] result;
		try {
			BufferedImage image = ImageIO.read(new File(path));
			int w = image.getWidth();
			int h = image.getHeight();
			this.width = w;
			this.height = h;
			result = new int[w * h];
			image.getRGB(0, 0, w, h, result, 0, w);
		}
		catch (IOException e) {
			e.printStackTrace();
			result = new int[1];
		}
		return result;
	}
	
	public void render(Renderer render) {
		int x0 = render.cam.x >> 4;
		int x1 = (render.cam.x + render.width + Sprite.GRASS.width) >> 4;
		int y0 = render.cam.y >> 4;
		int y1 = (render.cam.y + render.height + Sprite.GRASS.width) >> 4;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				try {
					data[x + y * width].render(x, y, render); 
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
		}
	}

}
