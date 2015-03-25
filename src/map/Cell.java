package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import com.sun.javafx.scene.control.SelectedCellsMap;

import map.CellInterface;

public abstract class Cell extends Observable implements CellInterface{

	protected static final int CELL_HEIGHT = 30;
	protected static final int CELL_WIDTH = 30;
	protected static final int CELL_SPACING = 30;
	protected Point position;
	protected Point pixel_position;
	private Color cell_color;
	private boolean selected;
	private boolean selectable;
	private JComponent component;
	private MouseMaster mouse_master;
	private boolean hovered;
	private CellSelector cell_selector;
	
	public Cell (int x, int y, Color color, boolean selectable) {
		
		cell_selector = CellSelector.getInstance();
		
		position = new Point(x,y);
		pixel_position = new Point(x*CELL_SPACING,y*CELL_SPACING);
		cell_color = color;
		this.selectable = selectable;
		component = new JComponent() {
			@Override
			protected void paintComponent(Graphics g) {
				setLocation(pixel_position);
				drawCell(g);
				super.paintComponent(g);
			}
		};
		
		component.setPreferredSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
		component.setLocation(pixel_position);
		
//		component.setBorder(BorderFactory.createTitledBorder("Node"));
		
		mouse_master = new MouseMaster();
		component.addMouseListener(mouse_master);
	}

	public void setX(int posX) { position.x = posX; }
	public void setY(int posY) { position.y = posY; }
	public int getX() { return position.x; }
	public int getY() { return position.y; }
	
	private void drawCell(Graphics g) {
		
		chooseColor(g);
		g.fillRect(position.x*CELL_SPACING, position.y*CELL_SPACING, CELL_WIDTH, CELL_HEIGHT);
		g.setColor(Color.black);
		g.drawRect(position.x*CELL_SPACING, position.y*CELL_SPACING, CELL_WIDTH, CELL_HEIGHT);
	}
	
	public void chooseColor(Graphics g) {
		if (selected) {
			g.setColor(Color.magenta);
		}
		else if (hovered) {
			g.setColor(Color.gray);
		}
		else{
			g.setColor(cell_color);
		}
	}
	
	public JComponent getComponent() {
		return component;
	}

	public boolean isSelected() {
		return selected;
	}
	
	public void toggleSelection() {
		selected = !selected;
	}
	
	public void setSelection(boolean selected) {
		this.selected = selected;
	}
	
	private Cell getThisCell() {
		return this;
	}
	
	private class MouseMaster extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if (selectable) {
				
				if (selected) {
					cell_selector.deselectSelectedCell();
					cell_selector.setSelectedCell(null);
				}
				else {
					cell_selector.deselectSelectedCell();
					cell_selector.setSelectedCell(getThisCell());
					selected = true;
				}

				
			}

		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			
			if (selectable) {

				hovered = true;
			}
			
			super.mouseEntered(e);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {

			if (selectable) {
				hovered = false;
			}
			
			super.mouseExited(e);
		}

	}
	
}
