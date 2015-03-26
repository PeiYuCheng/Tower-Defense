package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseListener;



import javax.swing.JPanel;

public class SideMenu extends JPanel {
	
	public SideMenu(){
		setBackground(Color.black);
        setPreferredSize(new Dimension(Application.SCREEN_WIDTH/4, Application.SCREEN_HEIGHT));
        setLocation(new Point(Application.SCREEN_WIDTH - Application.SCREEN_WIDTH/4, Application.SCREEN_HEIGHT));
        setDoubleBuffered(true);
        setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
	}

}
