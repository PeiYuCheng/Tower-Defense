package critterModels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;

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

public class MediumCritter extends Critter implements Serializable {
	
	// final constants
	private static final int REWARD = 3;
	private static final int MAX_HEALTH = 350;
	private static final int DAMAGING_POWER = 2;
	private static final int MOVING_SPEED = 9;
	private static final Dimension SIZE = new Dimension(30, 30);
	private static final Color COLOUR = Color.ORANGE;

	public MediumCritter(int startX, int startY) {
		super(MAX_HEALTH, MOVING_SPEED, DAMAGING_POWER, REWARD);
		setPosX(startX);
		setPosY(startY);
		setSize(SIZE);
		setColour(COLOUR);
	}
	
	@Override
	public void drawCritter(Graphics g) {
		// Critter movement during game loop
		
		switch(direction) {
		case UP:
			g.drawImage(images.mediumCritterUp.getScaledInstance(18, 29, 0), 5, 0, null);
			break;
		case DOWN:
			g.drawImage(images.mediumCritterDown.getScaledInstance(18, 29, 0), 5, 0, null);
			break;
		case LEFT:
			g.drawImage(images.mediumCritterLeft.getScaledInstance(29, 18, 0), 0, 5, null);
			break;
		default:
			g.drawImage(images.mediumCritterRight.getScaledInstance(29, 18, 0), 0, 5, null);
			break;
		}
	}

}
