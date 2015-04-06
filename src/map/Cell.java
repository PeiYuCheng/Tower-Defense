package map;

import img.Images;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JComponent;

import controllers.CellSelector;
import towerModels.Tower;

public abstract class Cell implements CellInterface, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final int CELL_HEIGHT = 40;
	protected static final int CELL_WIDTH = 40;
	protected static final int CELL_SPACING = 40;
	protected Point position;
	protected Point pixel_position;
	private Color cell_color;
	private boolean selected;
	private boolean selectable;
	private JComponent component;
	private MouseMaster mouse_master;
	private boolean hovered;
	private CellSelector cell_selector;
	private Tower tower_in_cell;
	
	public Cell (int x, int y, boolean selectable, int mapWidth, int mapHeight) {
		
		cell_selector = CellSelector.getInstance();
		
		position = new Point(x,y);
		pixel_position = new Point((((Map.MAX_WIDTH-mapWidth)*CELL_SPACING)/2) + x*CELL_SPACING,(((Map.MAX_HEIGHT-mapHeight)*CELL_SPACING)/2) + y*CELL_SPACING);
		this.selectable = selectable;
		component = new JComponent() {
			@Override
			protected void paintComponent(Graphics g) {
				setBounds(pixel_position.x, pixel_position.y, CELL_WIDTH, CELL_HEIGHT);
				drawCellBackground(g);
				drawCell(g);
				super.paintComponent(g);
			}
		};
		
		component.setBounds(pixel_position.x, pixel_position.y, CELL_WIDTH, CELL_HEIGHT);
		
		mouse_master = new MouseMaster();
		component.addMouseListener(mouse_master);
	}

	public void setX(int posX) { position.x = posX; }
	public void setY(int posY) { position.y = posY; }
	public int getX() { return position.x; }
	public int getY() { return position.y; }
	
	protected void drawCellBackground(Graphics g) {
		g.setColor(cell_color);
		g.fillRect(0, 0, CELL_WIDTH, CELL_HEIGHT);
	}
	
	protected void drawCell(Graphics g) {
		if (selected) {
			g.setColor(new Color(254,140,255,100));
			g.fillRect(0, 0, CELL_WIDTH, CELL_HEIGHT);
		}
		else if (hovered) {
			g.setColor(new Color(0,0,0,50));
			g.fillRect(0, 0, CELL_WIDTH, CELL_HEIGHT);
		}
	}
	
	public JComponent getComponent() {
		return component;
	}

	public void setCellColor(Color color) {
		cell_color = color;
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
	
	public Point getPosition() {
		return position;
	}
	
	public Point getPixelPosition() {
		return pixel_position;
	}
	
	public Dimension getCellSize() {
		return new Dimension(CELL_WIDTH, CELL_HEIGHT);
	}
	
	public Tower getTowerInCell() {
		return tower_in_cell;
	}
	
	public void setTowerInCell(Tower tower) {
		tower_in_cell = tower;
	}
	
	public boolean cellAvailable() {
		return tower_in_cell == null;
	}
	
	@Override
	public String toString() {
		return "{" + position.x + ", " + position.y + "}";
	}
	
	public void customMapModeOn() {
		selectable = true;
	}
	
	public void customMapModeOff() {
		selectable = false;
	}
	
	public void updateCellSelector() {
		cell_selector = CellSelector.getInstance();
	}
	
	private class MouseMaster extends MouseAdapter implements Serializable{
		
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
