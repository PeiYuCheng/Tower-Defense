package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * JPanel that is to contain all the game entities.
 * @author Jeffrey
 *
 */
public class Field extends JPanel implements Serializable {
	
	private JLayeredPane lp;
	
	public Field(){
		
		setBackground(new Color(20,20,20));
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