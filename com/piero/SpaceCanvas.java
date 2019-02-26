package com.piero;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SpaceCanvas extends Canvas {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage background, planet;
	int width;
	int height;
	
	SpaceCanvas(int width, int height) {
		this.width = width;
		this.height = height;
		var r = new Res();
		background = r.loadImage("/resources/background.jpg");
		background = background.getSubimage(0, 0, width, height);
		planet = r.loadImage("/resources/planet02.png");
	}
	
	void drawGrid(Graphics g) {
		int maxi = 10, maxj = 10;
		int l = 50;
		g.setColor(Color.gray);
		
		for (int i = 0; i < maxi; i++) {
			for (int j = 0; j < maxj; j++) {
				g.drawRect(i*l, j*l, l, l);
			}
		}
	}
	
	public void paint(Graphics g) {
		
		g.drawImage(background, 0, 0, width, height, null);
		
		this.drawGrid(g);
		
		g.setColor(Color.green);
		g.drawRect(0, 0, 50, 50);
		g.setColor(Color.gray);
		g.fillOval(10, 10, 30, 30);
		
		//g.setColor(Color.gray);
		//g.fillOval(51, 1, 48, 48);
		g.drawImage(planet, 50, 0, 50, 50, null);
	}
}
