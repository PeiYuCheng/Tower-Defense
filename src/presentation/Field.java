package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class Field extends JPanel {
	
	private JLayeredPane lp;
	
	public Field(){
		
		setBackground(Color.white);
		setLocation(0, 0);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(3*Application.SCREEN_WIDTH/4, Application.SCREEN_HEIGHT));
		setDoubleBuffered(true);
		setVisible(true);
		this.setFocusable(true);
		this.requestFocus();
		
		lp = new JLayeredPane();
		lp.setLocation(0, 0);
		lp.setPreferredSize(new Dimension(3*Application.SCREEN_WIDTH/4, Application.SCREEN_HEIGHT));
		lp.setVisible(true);
		
		this.add(lp, BorderLayout.CENTER);

	}
	
	public JLayeredPane getLayeredPane() {
		return lp;
	}

}