package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseListener;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class Field extends JPanel {
	
	public Field(){
		setBackground(Color.white);
        setPreferredSize(new Dimension(3*Application.SCREEN_WIDTH/4, Application.SCREEN_HEIGHT));
        setDoubleBuffered(true);
        setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
	}
	

}