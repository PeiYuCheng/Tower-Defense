package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.io.Serializable;

import javax.swing.JPanel;

public class MainMenu extends JPanel implements Serializable{
	
	public MainMenu() {
		setBackground(Color.BLACK);
		setLocation(0,0);
        setPreferredSize(new Dimension(Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT));
        setDoubleBuffered(true);
        setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
	}
}
