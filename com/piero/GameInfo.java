package com.piero;

import javax.swing.JLabel;

public class GameInfo {
	private static GameInfo me;
	private PlanetManager planetMan;
	private Planet srcPlanet, dstPlanet;
	private boolean hasSrcPlanet, hasDstPlanet;
	
	private JLabel srcInfo, dstInfo;
	
	private GameInfo() {
		planetMan = new PlanetManager();
	}
	
	static GameInfo getInstance() {
		if(me == null) {
			me = new GameInfo();
		}
		return me;
	}
	
	void setSrcLabel(JLabel srcLabel) {
		srcInfo = srcLabel;
	}
	
	void setDstLabel(JLabel dstLabel) {
		dstInfo = dstLabel;
	}
	
	void setSrcPlanet(Planet p) {
		hasSrcPlanet = true;
		srcPlanet = p;
		
		if(srcInfo != null) {
			srcInfo.setText(this.getPlanetDesc("Src"));
		}
	}
	
	void setDstPlanet(Planet p) {
		hasDstPlanet = true;
		srcPlanet = p;
		
		if(dstInfo != null) {
			dstInfo.setText(this.getPlanetDesc("Dst"));
		}
	}
	
	String getPlanetDesc(String prefix) {
		//"Src Name: XYZ, Production: XX, Available ships: YY, Owner: Cluthlu"
		String txt = prefix + ": None";
		if(hasSrcPlanet) {
			txt = prefix + " Name: " + srcPlanet.getName()
				+ ", Production: " + srcPlanet.getProduction()
				+ ", Available ships: " + srcPlanet.getShipsNum()
				+ ", Owner: " + srcPlanet.getOwner();
			
		}
		return txt;
	}
	
	PlanetManager getPlanetMan() {
		return planetMan;
	}
}
