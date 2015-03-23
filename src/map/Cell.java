package map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import map.CellInterface;

public abstract class Cell extends Observable implements CellInterface{

	protected static final int CELL_HEIGHT = 30;
	protected static final int CELL_WIDTH = 30;
	protected static final int CELL_SPACING = 30;
	protected int x_position;
	protected int y_position;
	private Color cell_color;
	private boolean selected;
	
	public Cell (int x, int y, Color color) {
		this.x_position = x;
		this.y_position = y;
		cell_color = color;
	}

	public void setX(int posX) { x_position = posX; }
	public void setY(int posY) { y_position = posY; }
	public int getX() { return x_position; }
	public int getY() { return y_position; }
	
	public void drawCell(Graphics g) {
		
		if (isSelected()) {
			g.setColor(Color.magenta);
		}
		else{
			g.setColor(cell_color);
		}
		g.fillRect(x_position*CELL_SPACING, y_position*CELL_SPACING, CELL_WIDTH, CELL_HEIGHT);
		g.setColor(Color.black);
		g.drawRect(x_position*CELL_SPACING, y_position*CELL_SPACING, CELL_WIDTH, CELL_HEIGHT);
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * Selects a cell if it contains the point used as parameter
	 * @param xPos
	 * @param yPos
	 */
	public void selectCell(int xPos, int yPos) {
		if (checkInRange(xPos, (x_position*CELL_SPACING + CELL_SPACING/2)) && checkInRange(yPos, (y_position*CELL_SPACING + CELL_SPACING/2))) {
			selected = true;
		}
		else {
			selected = false;
		}
	}
	
	/**
	 * Returns true if a position is within the centre of a cell
	 * @param position_to_check
	 * @param cell_centre
	 * @return
	 */
	private boolean checkInRange(int position_to_check, int cell_centre) {
		if ((position_to_check > cell_centre - (CELL_SPACING/2)) && (position_to_check < cell_centre + (CELL_SPACING/2))) {
			return true;
		}
		else {
			return false;
		}
	}
}
