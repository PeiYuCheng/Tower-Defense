package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JPanel;

public class MainMenu extends JPanel{
	
	public MainMenu() {
		setBackground(Color.BLACK);
        setPreferredSize(new Dimension(Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT));
        setLocation(new Point(0, 0));
        setDoubleBuffered(true);
        setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
	}
}
