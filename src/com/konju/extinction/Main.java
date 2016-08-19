package com.konju.extinction;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.konju.extinction.graphics.Camera;
import com.konju.extinction.graphics.Map;
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
	Camera c;
	Map test;
	
	private boolean running;
	
	public Main() {
		Dimension size = new Dimension(width, height);
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		
		c = new Camera(0, 0);
		r = new Renderer(width, height, pixels, c);
		k = new Keyboard();
		test = new Map("res/map.png");
		
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
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1_000_000_000.0 / 60.0;
		double delta = 0;
		int fps = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1) {
				update();
				delta--;
			}
			render();
			fps++;
			
			while (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + fps);
				fps = 0;
			}
		}
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		r.clear();
		//r.tRender();
		test.render(r);
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(img, 0, 0, width, height, null);
		
		g.dispose();
		bs.show();
	}

	private void update() {
		if (Keyboard.keys[Keyboard.K_W]) { 
			c.move(0, -5);
		}
		if (Keyboard.keys[Keyboard.K_A]) {
			c.move(-5, 0);
		}
		if (Keyboard.keys[Keyboard.K_S]) {
			c.move(0, 5);
		}
		if (Keyboard.keys[Keyboard.K_D]) {
			c.move(5, 0);
		}
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.window.add(m);
		m.window.setVisible(true);
		
		m.start();
	}

}
