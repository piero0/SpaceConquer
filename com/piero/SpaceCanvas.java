package com.piero;

import java.awt.Canvas;
import java.awt.Point;
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

class Cursor {
	boolean hasSrcCursor;
	Point srcCursorPos, dstCursorPos;
	GameInfo gameInfo;
	
	Cursor(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
		hasSrcCursor = false;
		srcCursorPos = new Point(-1, -1);
		dstCursorPos = new Point(-1, -1);
	}
	
	void drawCursor(Graphics g) {
		g.setColor(Color.green);
		g.drawRect((int)srcCursorPos.getX(), (int)srcCursorPos.getY(), Res.tileSize, Res.tileSize);
		
		if(!hasSrcCursor) {
			g.setColor(Color.red);
			g.drawRect((int)dstCursorPos.getX(), (int)dstCursorPos.getY(), Res.tileSize, Res.tileSize);
		}
	}
	
	void setSrcCursor(Point loc, Planet p) {
		srcCursorPos.setLocation(loc);
		gameInfo.updateInfo("Src", p);
		gameInfo.updateInfo("Dst", null);
	}
	
	void setDstCursor(Point loc, Planet p) {
		dstCursorPos.setLocation(loc);
		gameInfo.updateInfo("Dst", p);
	}
	
	Point pixelsToGridPosition(Point p) {
		return new Point((int)p.getX() / Res.tileSize * Res.tileSize, 
				(int)p.getY() / Res.tileSize * Res.tileSize);
	}
	
	void update(Point p) {
		var loc = this.pixelsToGridPosition(p);
		var planet = gameInfo.getPlanetMan().getPlanetOnXY(loc);
		
		if(planet != null) {
			if(hasSrcCursor == false) {
				hasSrcCursor = true;
			} else if(!loc.equals(srcCursorPos)) {
				hasSrcCursor = loc.equals(dstCursorPos);
			}			
			
			if(hasSrcCursor == true) {
				this.setSrcCursor(loc, planet);
			} else {
				this.setDstCursor(loc, planet);
			}
		}
	}
}
