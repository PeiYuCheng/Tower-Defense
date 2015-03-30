package critterModels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

import javax.imageio.ImageIO;

import map.Cell;
import map.Map;
import domain.Player;

/**
 * <h3>
 * The Critters SuperClass</h3>
 * <p>
 * This class is the Critters superclass implementing all the general methods
 * found in each critter subclass. This simplifies the code style and takes
 * advantage of <b>information expert</b> and <b>polymorphism</b>.
 * </p>
 * 
 * <p>
 * Each critter has a certain amount of <b>health</b> and moves at a certain
 * <b>speed</b> along the map. Based on our game's <b>Business Rules</b> there
 * are only going to be four types of critters: <b>Regular</b>, <b>Medium</b>,
 * <b>Large</b>, and <b>Boss</b>.
 * </p>
 * 
 * @author Justin Asfour
 * @version 1.0
 */

public abstract class Critter extends Observable {

	private int health;
	private int movingSpeed;
	private int damagingPower;
	private int reward;
	private boolean critterSpawned;
	private Point position;
	private Dimension size;
	private boolean damagePlayer;
	private Color colour;
	private Map mapKnownToCritters;
	private List<Cell> pathToWalk;

	public Critter(int health, int movingSpeed, int damagingPower, int reward) {
		this.health = health;
		this.movingSpeed = movingSpeed;
		this.damagingPower = damagingPower;
		this.reward = reward;
		this.damagePlayer = false;
		this.critterSpawned = true;
		this.position = new Point();
		this.size = new Dimension();
		this.mapKnownToCritters = Map.createGeneric();
		this.pathToWalk = (ArrayList<Cell>) mapKnownToCritters.path.clone();
	}

	// //////////////////////
	// Behavioral Methods //
	// //////////////////////

	/**
	 * This method keeps track of whether the critter is dead or not
	 * 
	 * @return <b>true</b> if critter's health goes to 0, indicating that the
	 *         critter is dead gives the player a reward based on the killed
	 *         critter.
	 */
	public boolean isDead() {
		// TODO: reward player with reward of appropriate kill and add animation
		if (getHealth() <= 0)
			return true;
		return false;
	}

	/**
	 * This method rewards player for killing a critter
	 * 
	 * @param p
	 *            which is of the Player Class
	 */

	// TODO: Implement later in game controller
	// public void reward(Player p) {
	// if (isDead())
	// p.cash += reward;
	// }

	/**
	 * This method indicates that the critter is hit.
	 * 
	 * @return <b>true</b> if the critter is hit reduces the critter health
	 *         appropriately based on the tower's power.
	 */
	// public boolean isHit() {
	// // TODO: check if tower hit critter then reduce health appropriately and
	// // add animation to hit
	// if (Tower.ATTACK) {
	// health -= Tower.DAMAGE;
	// setChanged();
	// notifyObservers();
	// return true;
	// } else
	// return false;
	// }

	/**
	 * This method checks if the critter has reached the exit and then reduces
	 * the player's health appropriately.
	 * 
	 * @param p
	 *            of Player class
	 */
	// TODO: Implement later in game controller
	public void damage(Player p) {
		p.changeLives(-damagingPower);
	}

	/**
	 * This method is used to damage Critter by taking in 3 parameters the
	 * tower's attack, and which negative effect will hurt it. The effect is a
	 * boolean which is changed in the game controller for a certain time.
	 * 
	 * @param amountOfDamageTaken
	 *            : The damage that is taken from the tower
	 * @param negativePowerEffect
	 *            : The boolean value that will define a change in the critter's
	 *            power
	 * @param negativeSpeedEffect
	 *            : The boolean value that will define a slowdown in the
	 *            critter's movement
	 */
	public void damageCritter(int amountOfDamageTaken,
			boolean negativePowerEffect, boolean negativeSpeedEffect) {

		this.health -= amountOfDamageTaken;
		int currentSpeed = this.getMovingSpeed();

		if (negativeSpeedEffect)
			this.movingSpeed = currentSpeed - 10;
		else
			this.movingSpeed = currentSpeed;

		setChanged();
		notifyObservers();
	}

	/**
	 * Abstract method created to provide generic drawing of each type of
	 * critter
	 * 
	 * @param g
	 */
	public abstract void draw(Graphics g);

	/**
	 * This method deals with the path finding algorithm to make sure that the
	 * Critters make it from the beginning to the end. We will be implementing
	 * the A* algorithm to make sure of this
	 */
	public void startWalking() {
		// TODO: implement path finding algorithm to get the critters to walk
		// along path

		ListIterator<Cell> iterator = pathToWalk.listIterator();

		if (iterator.hasNext()) {
			// Acquire the current path cell and the next one
			// in order for the critter to move from one to the other
			Cell currentCellOnPath = iterator.next();
			Cell nextCellOnPath = iterator.next();
			int firstItemInList = 0;

			int currentX = (int) (currentCellOnPath.getPixelPosition().getX() + currentCellOnPath.getCellSize().getWidth() / 2);
			int nextX = (int) (nextCellOnPath.getPixelPosition().getX() + nextCellOnPath.getCellSize().getWidth() / 2);
			int currentY = (int) (currentCellOnPath.getPixelPosition().getY() + currentCellOnPath.getCellSize().getHeight() / 2);
			int nextY = (int) (nextCellOnPath.getPixelPosition().getY() + nextCellOnPath.getCellSize().getHeight() / 2);			
			
			// Critter starts on the current cell
			if (critterSpawned) {
				position.setLocation(currentX, currentY);
				critterSpawned = false;
			}

			if (currentX == nextX)
				if (nextY - currentY < 0)
					position.y -= movingSpeed;
				else
					position.y += movingSpeed;
			else if (currentY == nextY)
				position.x += movingSpeed;
			
			// Once the critter reaches the next cell
			// the current cell is removed from the list
			if (position.x == nextX && position.y == nextY) {
				pathToWalk.remove(firstItemInList);
			}

			// Critter has reached last tile => attack player
		} else {
			System.out.println("Critter has reached the end");
			this.damagePlayer = true;
		}
		setChanged();
		notifyObservers();
	}

	// /////////////////////
	// Getters & Setters //
	// /////////////////////

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMovingSpeed() {
		return movingSpeed;
	}

	public void setMovingSpeed(int movingSpeed) {
		this.movingSpeed = movingSpeed;
	}

	public int getDamagingPower() {
		return damagingPower;
	}

	public void setDamagingPower(int damagingPower) {
		this.damagingPower = damagingPower;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public int getPosX() {
		return position.x;
	}

	public void setPosX(int posX) {
		this.position.x = posX;
	}

	public int getPosY() {
		return position.y;
	}

	public void setPosY(int posY) {
		this.position.y = posY;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	public boolean isDamagePlayer() {
		return damagePlayer;
	}

	public void setDamagePlayer(boolean damagePlayer) {
		this.damagePlayer = damagePlayer;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
}
