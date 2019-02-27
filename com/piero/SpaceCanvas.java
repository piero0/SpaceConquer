package com.piero;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class SpaceCanvas extends Canvas implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	PlanetManager planetMan;
	BufferedImage background, planet;
	int width;
	int height;
	int curx, cury;
	
	SpaceCanvas(int width, int height) {
		this.width = width;
		this.height = height;
		
		background = Res.loadImage("/resources/background.jpg");
		background = background.getSubimage(0, 0, width, height);
		planet = Res.loadImage("/resources/planet02.png");
		planetMan = new PlanetManager();
		
		this.addMouseListener(this);
		
		curx = 0; cury = 0;
	}
	
	void initPlanets() {
		planetMan.generatePlanets();
		this.repaint();
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
		g.drawRect(curx, cury, Res.tileSize, Res.tileSize);
		
		planetMan.drawPlanets(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			curx = e.getX() / Res.tileSize * Res.tileSize;
			cury = e.getY() / Res.tileSize * Res.tileSize;
			//System.out.println(String.valueOf(curx) + ":" + String.valueOf(cury));
			this.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
