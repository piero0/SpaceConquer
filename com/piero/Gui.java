package com.piero;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JScrollPane;

public class Gui implements ActionListener {
	int width = 530,
		height = 700,
		canvasWidth = 500,
		canvasHeight = 500,
		infoHeight = 200,
		cmdHeight = 200;
	final String windowTitle = "SpaceConquer";
	
	JEditorPane infoBox;
	JFrame MainFrame;
	SpaceCanvas canvas;
	JScrollPane textScroll;
	
	int j;
	
	Gui() {
		j = 0;
	}
	
	void setupMainFrame(JComponent topBox) {
		MainFrame = new JFrame(windowTitle);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.setResizable(false);
		//MainFrame.setLayout(new FlowLayout());
		MainFrame.setSize(width, height);
		MainFrame.setMaximumSize(new Dimension(width, height));
		
		
		MainFrame.add(topBox);
		MainFrame.setVisible(true);		
	}
	
	void setupCanvas() {
		canvas = new SpaceCanvas(canvasWidth, canvasHeight);
		canvas.setMaximumSize(new Dimension(canvasWidth, canvasHeight));
		canvas.setSize(new Dimension(canvasWidth, canvasHeight));
		canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
	}
	
	Box setupControlPanel() {
		var cmds = Box.createHorizontalBox();
		
		JLabel lblPlanetName = new JLabel(" /0 ");
		//JLabel lblSpace = new JLabel("                           ");
		JTextField txtSpaceshipNum = new JTextField(5);
		txtSpaceshipNum.setMaximumSize(new Dimension(50, 50));
		JButton btnSend = new JButton("Send");
		btnSend.setActionCommand("send");
		btnSend.addActionListener(this);
		
		JButton btnNextTurn = new JButton("Next turn");
		btnNextTurn.setActionCommand("next");
		btnNextTurn.addActionListener(this);
		cmds.add(txtSpaceshipNum);
		cmds.add(lblPlanetName);
		cmds.add(btnSend);
		//cmds.add(lblSpace);
		cmds.add(Box.createHorizontalGlue());
		cmds.add(btnNextTurn);
		//cmds.setMaximumSize(new Dimension(width, 100));
		return cmds;
	}
	
	JComponent setupTextBox() {
		infoBox = new JEditorPane();
		infoBox.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		
		infoBox.setEditable(false);
		String txt = "Hello <span color='green'>world</span><br>Second line<br>third<br>fourth<br>fifth<br>sixth";
		txt += "<br>7<br>8<br>9<br>10<br>11";
		infoBox.setText(txt);

		textScroll = new JScrollPane(infoBox);
		//textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		return textScroll;
	}
	
	void drawGUI() {

		
		var box = Box.createVerticalBox();
		//box.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.setupCanvas();
		box.add(canvas);
		
		var tmpvbox = Box.createVerticalBox();
		
		var tmpbox1 = Box.createHorizontalBox();
		JLabel srcstat = new JLabel("Src Name: XYZ, Production: XX, Available ships: YY, Owner: Cluthlu");
		tmpbox1.add(srcstat);
		tmpbox1.add(Box.createHorizontalGlue());
		
		var tmpbox2 = Box.createHorizontalBox();
		JLabel dststat = new JLabel("Dst Name: XYZ, Production: XX, Available ships: YY, Owner: Cluthlu");
		tmpbox2.add(dststat);
		tmpbox2.add(Box.createHorizontalGlue());
		
		tmpvbox.add(tmpbox1);
		tmpvbox.add(tmpbox2);
		
		box.add(tmpvbox);
		
		box.add(this.setupControlPanel());
		
		box.add(this.setupTextBox());
		
		
		var pn = new JPanel();
		pn.setLayout(new BorderLayout());
		pn.add(box);
		
		this.setupMainFrame(pn);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("send")) {
			//infoBox.insert(String.valueOf(++j) + "th line of text\n", 0);
			//infoBox.setCaretPosition(0);
		} else if(e.getActionCommand().equals("next")) {
			JOptionPane.showMessageDialog(null, "Sure you are?");
		}
	}
}
