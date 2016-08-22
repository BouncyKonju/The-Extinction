package com.konju.extinction.graphics;

import com.konju.extinction.system.GeneralImageReader;

public class Sprite {
	
	public static final Sprite GRASS = new Sprite(16, "res/grass.png");
	public static final Sprite BRICK = new Sprite(16, "res/brick.png");
	public static final Sprite WATER = new Sprite(16, "res/water.png");
	public static final Sprite SSAND = new Sprite(16, "res/sand.png");
	public static final Sprite SNULL = new Sprite(16, 0xff00ff);
	
	int width;
	int[] pixels;
	
	public Sprite(int width, String path) {
		this.width = width;
		
		pixels = GeneralImageReader.readImage(path);
	}
	
	public Sprite(int width, int color) {
		this.width = width;
		
		pixels = new int[width * width];
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}

}
