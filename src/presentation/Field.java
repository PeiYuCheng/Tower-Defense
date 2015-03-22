package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class Field extends JPanel {
	
	public Field(MouseListener ml){
		setBackground(Color.white);
        setPreferredSize(new Dimension(Application.SCREEN_WIDTH, Application.SCREEN_HEIGHT));
        setDoubleBuffered(true);
        setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
        
        
        addMouseListener(ml);
	}
	

}