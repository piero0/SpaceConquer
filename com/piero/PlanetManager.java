package com.piero;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

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
		int planetsNum = 15;
		int maxIdx = 100;

		Integer[] indxs = this.shuffle(maxIdx);
		
		for (int i = 0; i < planetsNum; i++) {
			xy = this.tileNumToXY(indxs[i]);
			planets.add(new Planet(xy[0], xy[1], img));
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
		g.drawImage(image, x+1, y+1, Res.tileSize, Res.tileSize, null);
	}
}