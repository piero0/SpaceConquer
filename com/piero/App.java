package com.piero;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		for(var laf: UIManager.getInstalledLookAndFeels()) {
			System.out.println(laf.getName() + " " + laf.getClassName());
		}
		//"com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
		//"javax.swing.plaf.nimbus.NimbusLookAndFeel"
		//"com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		new Res().main();
		new Gui().drawGUI();
		
	}
}

class Res {
	void main() {
		var r = getClass().getResource("resources/test01");
		var r2 = getClass().getResource("/resources/planet01.png");
		
		if(r != null) { System.out.println(r.getPath()); } else { System.out.println("r is null"); }
		if(r2 != null) { System.out.println(r2.getPath()); } else { System.out.println("r2 is null"); }
	}
	//"/resources/background.jpg"
	public BufferedImage loadImage(String name) {
		var url = getClass().getResource(name);
		BufferedImage img = null;
		try {
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}