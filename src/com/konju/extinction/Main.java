package com.konju.extinction;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.konju.extinction.graphics.Renderer;
import com.konju.extinction.system.Keyboard;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final int width = 960, height = 540;
	
	private Thread t;
	private JFrame window;
	
	BufferedImage img;
	int[] pixels;
	
	Renderer r;
	Keyboard k;
	
	private boolean running;
	
	public Main() {
		Dimension size = new Dimension(width, height);
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		
		r = new Renderer(width, height, pixels);
		k = new Keyboard();
		
		window = new JFrame();
		window.setTitle("The Extinction");
		window.pack();
		window.setSize(size);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		addKeyListener(k);
		
		setPreferredSize(size);
	}
	
	public void start() {
		t = new Thread(this, "Extinction");
		t.start();
		running = true;
	}
	
	public void stop() {
		running = false;
		try {
			t.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		requestFocus();
		while (running) {
			update();
			render();
		}
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		r.clear();
		r.tRender();
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(img, 0, 0, width, height, null);
		
		g.dispose();
		bs.show();
	}

	private void update() {
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.window.add(m);
		m.window.setVisible(true);
		
		m.start();
	}

}
