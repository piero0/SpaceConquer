package com.piero;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class Gui implements ActionListener {
	int width = 520,
		height = 700,
		canvasHeight = 500,
		infoHeight = 50,
		cmdHeight = 200;
	final String windowTitle = "SpaceConquer";
	
	JTextArea infoBox;
	JFrame MainFrame;
	SpaceCanvas c;
	JScrollPane textScroll;
	
	int j;
	
	Gui() {
		j = 0;
	}
	
	void drawGUI() {
		MainFrame = new JFrame(windowTitle);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.setSize(width, height);
		MainFrame.setMaximumSize(new Dimension(width, height));
		MainFrame.setResizable(false);
		
		var pane = MainFrame.getContentPane();
		BoxLayout bl = new BoxLayout(pane, BoxLayout.Y_AXIS);
		pane.setLayout(bl);
		
		//painting part
		c = new SpaceCanvas(500, canvasHeight);

		c.setSize(500, canvasHeight);
		
		pane.add(c);
		
		JPanel tmp = new JPanel();
		JLabel stat = new JLabel("a: - b: - c: - ");
		//stat.setSize(width, 50);
		stat.setAlignmentX(Component.RIGHT_ALIGNMENT);
		stat.setBackground(Color.yellow);
		stat.setOpaque(true);
		//tmp.add(stat);
		pane.add(stat);
		
		//cmds part
		JPanel cmds = new JPanel();
		
		FlowLayout fl = new FlowLayout();
		cmds.setSize(width, cmdHeight);
		cmds.setLayout(fl);
		
		JLabel lblPlanetName = new JLabel("AlphaCentauri");
		JLabel lblSpace = new JLabel("                           ");
		JTextField txtSpaceshipNum = new JTextField(5);
		JButton btnSend = new JButton("Send");
		btnSend.setActionCommand("send");
		btnSend.addActionListener(this);
		
		JButton btnNextTurn = new JButton("Next turn");
		btnNextTurn.setActionCommand("next");
		btnNextTurn.addActionListener(this);
		

		
		cmds.add(lblPlanetName);
		cmds.add(txtSpaceshipNum);
		cmds.add(btnSend);
		cmds.add(lblSpace);
		cmds.add(btnNextTurn);
		pane.add(cmds);
		
		// info part
		infoBox = new JTextArea(10, 1);
		infoBox.setSize(width, infoHeight);

		textScroll = new JScrollPane(infoBox);
		textScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		infoBox.setEditable(false);
		
		pane.add(textScroll);
		
		MainFrame.setVisible(true);		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("send")) {
			infoBox.insert(String.valueOf(++j) + "th line of text\n", 0);
			infoBox.setCaretPosition(0);
		} else if(e.getActionCommand().equals("next")) {
			JOptionPane.showMessageDialog(null, "Sure you are?");
		}
	}
}
