package com.konju.extinction.system;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	public static final int K_W = KeyEvent.VK_W;
	public static final int K_A = KeyEvent.VK_A;
	public static final int K_S = KeyEvent.VK_S;
	public static final int K_D = KeyEvent.VK_D;
	public static final int K_ESC = KeyEvent.VK_ESCAPE;
	
	public static boolean[] keys = new boolean[1024];

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
