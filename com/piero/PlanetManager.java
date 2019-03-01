package com.piero;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PlanetManager {
	ArrayList<Planet> planets;
	int colisions;
	
	PlanetManager() {
		planets = new ArrayList<>();
		colisions = 0;
	}
	
	Integer[] shuffle(int n) {
		Integer[] inp = new Integer[n];
		Arrays.setAll(inp, x -> x);
		var l = Arrays.asList(inp);
		Collections.shuffle(l);
		return l.toArray(inp);
	}
	
	Planet getPlanetOnXY(Point pp) {
		for (var p: planets) {
			if(p.isOn(pp))
				return p;
		}
		return null;
	}
	
	void generatePlanets() {
		planets.clear();
		
		var img = Res.loadImage("/resources/planet02.png");
		int[] xy;
		int planetsNum = 15;
		int maxIdx = 100;

		Integer[] indxs = this.shuffle(maxIdx);
		
		for (int i = 0; i < planetsNum; i++) {
			xy = this.tileNumToXY(indxs[i]);
			var p = new Planet(xy[0], xy[1], img);
			p.setName("AlphaBeta" + i);
			planets.add(p);
		}
		
	}
	
	int[] tileNumToXY(int num) {
		int[] xy = {num / 10 * Res.tileSize, num % 10 * Res.tileSize};
		return xy;
	}
	
	void drawPlanets(Graphics g) {
		for(var p: planets) {
			p.draw(g);
		}
	}
}

class Planet {
	private int production, ships;
	private BufferedImage image;
	private String owner;
	private String name;
	private Point xy;
	
	Planet(int x, int y, BufferedImage img) {
		xy = new Point();
		xy.setLocation(x, y);
		image = img;
		owner = "Whoever";
		name = "AlphaBeta";
		production = 5;
		ships = 10;
	}
	
	void setName(String name) { this.name = name; }
	String getName() { return name; }
	String getOwner() { return owner; }
	int getProduction() { return production; }
	int getShipsNum() { return ships; }
	
	boolean isOn(Point p) {
		return xy.equals(p);
	}
	
	void draw(Graphics g) {
		g.drawImage(image, (int)xy.getX()+1, (int)xy.getY()+1, Res.tileSize, Res.tileSize, null);
	}
}