package controllers;

import java.awt.Color;
import java.awt.Graphics;

import map.Cell;

public class Button {

	protected static final Color BUTTON_COLOR = Color.black;
	protected int button_height;
	protected int button_width;
	protected int button_spacing;
	protected int x_position;
	protected int y_position;
	private boolean selected;
	
	public Button(int posX, int posY, int width, int height, int spacing) {
		this.x_position = posX;
		this.y_position = posY;
		button_height = height;
		button_width = width;
		button_spacing = spacing;
	}
	
	public void drawCell(Graphics g) {
		
		if (isSelected()) {
			g.setColor(Color.magenta);
		}
		else{
			g.setColor(BUTTON_COLOR);
		}
		g.fillRect(x_position, y_position, button_width, button_height);
		g.setColor(Color.black);
		g.drawRect(x_position, y_position, button_width, button_height);
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
		if (checkInRange(xPos, (x_position + button_spacing/2)) && checkInRange(yPos, (y_position + button_spacing/2))) {
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
		if ((position_to_check > cell_centre - (button_spacing/2)) && (position_to_check < cell_centre + (button_spacing/2))) {
			return true;
		}
		else {
			return false;
		}
	}
	

	
}
