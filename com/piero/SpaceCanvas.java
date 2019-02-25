package com.piero;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SpaceCanvas extends Canvas {
	
	BufferedImage img;
	int width;
	int height;
	
	SpaceCanvas(int width, int height) {
		this.width = width;
		this.height = height;
		var r = new Res();
		img = r.loadImage();
		img = img.getSubimage(0, 0, width, height);
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
		
		g.drawImage(img, 0, 0, width, height, null);
		
		this.drawGrid(g);
		
		g.setColor(Color.green);
		g.drawRect(0, 0, 50, 50);
		g.setColor(Color.gray);
		g.fillOval(10, 10, 30, 30);
		g.setColor(Color.gray);
		g.fillOval(51, 1, 48, 48);
	}
}
