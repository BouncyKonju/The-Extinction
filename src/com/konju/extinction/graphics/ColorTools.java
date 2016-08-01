package com.konju.extinction.graphics;

public class ColorTools {
	
	private ColorTools() {}
	
	@Deprecated
	public static int getAlpha(int col) {
		return (col & 0xff000000) >> 24;
	}
	
	public static int getRed(int col) {
		return (col & 0xff0000) >> 16;
	}
	
	public static int getGreen(int col) {
		return (col & 0xff00) >> 8;
	}

	public static int getBlue(int col) {
		return (col & 0xff);
	}

}
