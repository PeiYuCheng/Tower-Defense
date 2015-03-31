package critterModels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import map.EasyMap;

/**
 * <h3>
 * The Medium Critter Class</h3>
 * <p>
 * This critter subclass holds all the methods and properties of <b>Medium</b>
 * critter. This critter class holds all the specific properties as static final
 * properties. This ensures cleaner code structure and no magic numbers
 * anywhere. These properties are now inherent of <b>Medium</b> critter. This
 * class also holds the position of the critter type in the parameter of the
 * constructor, in order to be able to place it on the map.
 * </p>
 * 
 * @author Justin Asfour
 * @version 1.0
 */

public class MediumCritter extends Critter {
	
	// final constants
	private static final int REWARD = 50;
	private static final int MAX_HEALTH = 150;
	private static final int DAMAGING_POWER = 5;
	private static final int MOVING_SPEED = 7;
	private static final Dimension SIZE = new Dimension(10, 10);
	private static final Color COLOUR = Color.ORANGE;

	public MediumCritter(int startX, int startY) {
		super(MAX_HEALTH, MOVING_SPEED, DAMAGING_POWER, REWARD);
		setPosX(startX);
		setPosY(startY);
		setSize(SIZE);
		setColour(COLOUR);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.fillRect(getPosX(), getPosY(), SIZE.width, SIZE.height);
		g.setColor(COLOUR);
	}
}
