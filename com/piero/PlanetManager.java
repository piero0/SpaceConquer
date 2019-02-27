package com.piero;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class PlanetManager {
	ArrayList<Planet> planets;
	int colisions;
	
	PlanetManager() {
		planets = new ArrayList<>();
		colisions = 0;
	}
	
	int getRand(Random rnd, int max, int[] prev, int i) {
		int next = 0;
		int maxTries = 10;
		int curTry = 0;
		boolean duplicate = false;
		
		while(curTry < maxTries) {
			next = rnd.nextInt(max);
			for (int j = 0; j < i; j++) {
				if(next == prev[j]) {
					duplicate = true;
					break;
				}
			}
			
			if(duplicate) {
				colisions++;
				curTry++;
				duplicate = false;
			} else {
				prev[i] = next;
				break;
			}
		}
		if(curTry >= maxTries) throw new RuntimeException("Random gen failure");
		return next;
	}
	
	void generatePlanets() {
		planets.clear();
		
		var img = Res.loadImage("/resources/planet02.png");
		int[] xy;
		int tileNum = 0;
		var rnd = new Random();
		
		int size = 10;
		int maxIdx = 100;
		
		int[] indxs = new int[size];
		
		colisions = 0;
		
		for (int i = 0; i < size; i++) {
			tileNum = this.getRand(rnd, maxIdx, indxs, i);
			xy = this.tileNumToXY(tileNum);
			planets.add(new Planet(xy[0], xy[1], img));
		}
		
		System.out.println(colisions);
	}
	
	int[] tileNumToXY(int num) {
		int[] xy = {0, 0};
		
		int y = num / 10;
		int x = num % 10;
		
		xy[0] = x*Res.tileSize;
		xy[1] = y*Res.tileSize;
		
		//System.out.println(String.valueOf(xy[0]) + "-" + String.valueOf(xy[1]));
		
		return xy;
	}
	
	void drawPlanets(Graphics g) {
		for(var p: planets) {
			p.draw(g);
		}
	}
}

class Planet {
	int x, y, production, ships;
	BufferedImage image;
	String owner;
	
	Planet(int x, int y, BufferedImage img) {
		this.x = x;
		this.y = y;
		image = img;
		owner = "Whoever";
		production = 5;
		ships = 10;
	}
	
	void draw(Graphics g) {
		g.drawImage(image, x, y, Res.tileSize, Res.tileSize, null);
	}
}