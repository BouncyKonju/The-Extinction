package com.konju.extinction.graphics;

import java.util.Random;

public class Renderer {

	public int[] pixels;
	
	int width, height;
	Random r = new Random();
	
	protected Camera cam;
	
	private int tiles[];
	
	public Renderer(int width, int height, int[] pixels, Camera camera) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
		this.cam = camera;
		clear();
		
		tiles = new int[8 * 8];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = r.nextInt(0xffffff);
		}
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xff00ff;
		}
	}
	
	public void tRender() {
		for (int y = 0; y < height; y++) {
			int yt = y + cam.y;
			for (int x = 0; x < width; x++) {
				int xt = x + cam.x;
				int tileindex = ((xt >> 4) & 7) + ((yt >> 4) & 7) * 8;
				pixels[x + y * width] = tiles[tileindex];
			}
		}
	}
	
	public void renderSprite(int x, int y, Sprite data) {
		x -= cam.x;
		y -= cam.y;
		for (int y0 = 0; y0 < data.width; y0++) {
			int y1 = y + y0;
			for (int x0 = 0; x0 < data.width; x0++) {
				int x1 = x + x0;
				if (x1 < -data.width || x1 >= width || y1 < 0 || y1 >= width) break;
				if (x1 < 0) x1 = 0;
				pixels[x1 + y1 * width] = data.pixels[x0 + y0 * data.width];
			}
		}
	}
	
}
