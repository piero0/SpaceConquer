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
	GameInfo gameInfo;
	Cursor cursor;
	BufferedImage background, planet;
	int width;
	int height;
	
	SpaceCanvas(int width, int height) {
		this.width = width;
		this.height = height;
		
		background = Res.loadImage("/resources/background.jpg");
		background = background.getSubimage(0, 0, width, height);
		gameInfo = GameInfo.getInstance();
		planetMan = gameInfo.getPlanetMan();
		
		this.addMouseListener(this);
		
		cursor = new Cursor(gameInfo);
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
		planetMan.drawPlanets(g);
		
		this.drawGrid(g);
		cursor.drawCursor(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			cursor.update(new Point(e.getX(), e.getY()));	
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

class Point {
	int x,y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	boolean equals(Point p) {
		return p.x == x && p.y == y;
	}
	
	void set(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	int getX() {return x;}
	int getY() {return y;}
}

class Cursor {
	boolean hasDst;
	Point src, dst;
	GameInfo gameInfo;
	
	Cursor(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
		hasDst = false;
		src = new Point(0, 0);
		dst = new Point(0, 0);
	}
	
	void drawCursor(Graphics g) {
		g.setColor(Color.green);
		g.drawRect(src.getX(), src.getY(), Res.tileSize, Res.tileSize);
		
		if(hasDst) {
			g.setColor(Color.red);
			g.drawRect(dst.getX(), dst.getY(), Res.tileSize, Res.tileSize);
		}
	}
	
	void update(Point p) {
		var u = new Point(
				p.getX() / Res.tileSize * Res.tileSize, 
				p.getY() / Res.tileSize * Res.tileSize);
		
		var planet = gameInfo.getPlanetMan().getPlanetFromXY(u);
		
		if(planet != null) {
			if(!u.equals(src)) {
				if(!hasDst) {
					dst.set(u);
					hasDst = true;
					gameInfo.setDstPlanet(planet);
				} else {
					hasDst = false;
					src.set(u);
					gameInfo.setSrcPlanet(planet);
				}
			}
		}
	}
}
