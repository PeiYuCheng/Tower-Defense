package critterModels;

import img.Images;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ListIterator;

import map.Cell;

/**
 * <h3>
 * The Regular Critter Class</h3>
 * <p>
 * This critter subclass holds all the methods and properties of <b>Regular</b>
 * critter. This critter class holds all the specific properties as static final
 * properties. This ensures cleaner code structure and no magic numbers
 * anywhere. These properties are now inherent of <b>Regular</b> critter. This
 * class also holds the position of the critter type in the parameter of the
 * constructor, in order to be able to place it on the map.
 * </p>
 * 
 * @author Justin Asfour
 * @version 1.0
 */

public class RegularCritter extends Critter implements Serializable {

	// final constants
	private static final int REWARD = 10;
	private static final int MAX_HEALTH = 100;
	private static final int DAMAGING_POWER = 1;
	private static final int MOVING_SPEED = 3;
	private static final Dimension SIZE = new Dimension(5, 5);
	private static final Color COLOUR = Color.BLUE;
	private Images img;

	public RegularCritter(int startX, int startY) {
		super(MAX_HEALTH, MOVING_SPEED, DAMAGING_POWER, REWARD);
		setPosX(startX);
		setPosY(startY);
		setSize(SIZE);
		setColour(COLOUR);
		img = new Images();
	}

	@Override
	public void drawCritter(Graphics g) {
		// Critter movement during game loop
		if (isUp()) {
			// Move Up
			g.setColor(COLOUR);
			g.fillRect(super.pixel_position.x, super.pixel_position.y, 10, 30);
		} else if (isDown()) {
			//Move Down
			g.setColor(COLOUR);
			g.fillRect(super.pixel_position.x, super.pixel_position.y, 10, 30);
		} else if (isRight()) {
			// Move Right
			g.setColor(COLOUR);
			g.fillRect(super.pixel_position.x, super.pixel_position.y, 30, 10);
		} else if (isLeft()) {
			// Move Left
			g.setColor(COLOUR);
			g.fillRect(super.pixel_position.x, super.pixel_position.y, 30, 10);
		}
	}
}
