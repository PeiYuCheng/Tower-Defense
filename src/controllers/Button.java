package controllers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class Button extends JComponent {

	protected static final Color BUTTON_COLOR = Color.black;
	protected int button_spacing;
	protected Point position;
	protected Dimension size;
	private boolean selected;
	private boolean hovered;
	private MouseMaster mouse_master;
	
	public Button(int posX, int posY, int width, int height) {
		super();
		position = new Point(posX, posY);
		size = new Dimension(width, height);
		setLocation(position);
		setPreferredSize(size);
//		setBorder(BorderFactory.createTitledBorder("Node"));
		
		mouse_master = new MouseMaster();
		addMouseListener(mouse_master);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		setLocation(position);
		drawButton(g);
		super.paintComponent(g);
	}
	
	public void drawButton(Graphics g) {
		
		selectColor(g, BUTTON_COLOR);
		g.fillRect(position.x, position.y, size.width, size.height);
		g.setColor(Color.black);
		g.drawRect(position.x, position.y, size.width, size.height);
		
	}
	
	protected void selectColor(Graphics g, Color button_color) {
		if (selected) {
			g.setColor(Color.magenta);
		}
		else if (hovered) {
			g.setColor(Color.gray);
		}
		else {
			g.setColor(button_color);
		}
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void toggleSelection() {
		if (selected) {
			selected = false;
		}
		else {
			selected = true;
		}
	}
	
	public boolean isHovered() {
		return hovered;
	}

	private class MouseMaster extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
				
			toggleSelection();

		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			hovered = true;
			super.mouseEntered(e);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			hovered = false;
			super.mouseExited(e);
		}
		
		

	}
	
	
}
