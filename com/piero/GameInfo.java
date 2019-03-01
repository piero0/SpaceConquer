package com.piero;

import java.util.HashMap;

import javax.swing.JLabel;

public class GameInfo {
	private static GameInfo me;
	private PlanetManager planetMan;
	private LabelUpdater labelsMan;
	
	private GameInfo() {
		planetMan = new PlanetManager();
		labelsMan = new LabelUpdater();
	}
	
	static GameInfo getInstance() {
		if(me == null) {
			me = new GameInfo();
		}
		return me;
	}
	
	String getPlanetDesc(Planet p) {
		String txt = " None"; 
		if(p == null) return txt;
		txt = " Name: " + p.getName()
			+ ", Production: " + p.getProduction()
			+ ", Available ships: " + p.getShipsNum()
			+ ", Owner: " + p.getOwner();
		return txt;
	}
	
	void updateInfo(String prefix, Planet p) {
		labelsMan.update(prefix, prefix + this.getPlanetDesc(p));
	}
	
	PlanetManager getPlanetMan() {
		return planetMan;
	}
	
	LabelUpdater getLabelUpdater() {
		return labelsMan;
	}
}

class LabelUpdater {
	HashMap<String, JLabel> labels;
	
	LabelUpdater() {
		labels = new HashMap<>();
	}
	
	void register(String Name, JLabel label) {
		labels.put(Name, label);
	}
	
	void update(String name, String text) {
		var label = labels.get(name);
		if(label != null) label.setText(text);
	}
}
