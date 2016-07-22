package com.konju.extinction.graphics;

import java.util.Random;

public class Renderer {

	public int[] pixels;
	
	int width, height;
	
	public Renderer(int width, int height, int[] pixels) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
		clear();
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xff00ff;
		}
	}
	
	public void tRender() {
		Random r = new Random();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = r.nextInt();
			}
		}
	}
	
}
