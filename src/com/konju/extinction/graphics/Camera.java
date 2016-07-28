package com.konju.extinction.graphics;

public class Camera {
	
	public int x, y;
	
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void move(int lengthX, int lengthY) {
		this.x += lengthX;
		this.y += lengthY;
	}

}
