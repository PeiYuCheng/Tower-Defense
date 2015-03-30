package critterModels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import javax.imageio.ImageIO;

import map.Cell;
import map.EasyMap;
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

public abstract class Critter extends Observable{

	private int health;
	private int movingSpeed;
	private int damagingPower;
	private int reward;
	private Point position;
	private Dimension size;
	private boolean damagePlayer;
	private Color colour;
	private EasyMap mapKnownToCritters;
	private ArrayList<Cell> pathToWalk;

	public Critter(int health, int movingSpeed, int damagingPower, int reward) {
		this.health = health;
		this.movingSpeed = movingSpeed;
		this.damagingPower = damagingPower;
		this.reward = reward;
		this.damagePlayer = false;
		this.position = new Point();
		this.size = new Dimension();
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
	 * @param p which is of the Player Class
	 */

	// TODO: Implement later in game controller
//	public void reward(Player p) {
//		if (isDead())
//			p.cash += reward;
//	}

	/**
	 * This method indicates that the critter is hit.
	 * 
	 * @return <b>true</b> if the critter is hit reduces the critter health
	 *         appropriately based on the tower's power.
	 */
//	public boolean isHit() {
//		// TODO: check if tower hit critter then reduce health appropriately and
//		// add animation to hit
//		if (Tower.ATTACK) {
//			health -= Tower.DAMAGE;
//			setChanged();
//			notifyObservers();
//			return true;
//		} else
//			return false;
//	}

	/**
	 * This method checks if the critter has reached the exit and then reduces
	 * the player's health appropriately.
	 * @param p of Player class
	 */
	// TODO: Implement later in game controller
	public void damage(Player p) {
		p.changeLives(-damagingPower);
	}
	
	/**
	 * This method is used to damage Critter by taking in 3 parameters the tower's attack, 
	 * and which negative effect will hurt it. The effect is a boolean which is changed in the game 
	 * controller for a certain time.
	 * 
	 * @param amountOfDamageTaken: The damage that is taken from the tower
	 * @param negativePowerEffect: The boolean value that will define a change in the critter's power
	 * @param negativeSpeedEffect: The boolean value that will define a slowdown in the critter's movement
	 */
	public void damageCritter(int amountOfDamageTaken, boolean negativePowerEffect, boolean negativeSpeedEffect) {

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
	public void startWalking(EasyMap gameMap) {
		// TODO: implement path finding algorithm to get the critters to walk
		// along path
		
		this.mapKnownToCritters = gameMap;
		pathToWalk = mapKnownToCritters.path;
		
		Iterator<Cell> iterator = pathToWalk.iterator();
		
		if (iterator.hasNext()) {
			Cell currentCellOnPath = iterator.next();
			iterator.remove();
			Cell nextCellOnPath = iterator.next();
			iterator.remove();

			int currentX = currentCellOnPath.getX();
			int nextX = nextCellOnPath.getX();
			int currentY = currentCellOnPath.getY();
			int nextY = nextCellOnPath.getY();

			// Critter starts moving on the first tile
			position.setLocation(currentX, currentY);

			if (currentX == nextX)
				position.y += movingSpeed;
			else if (currentY == nextY)
				position.x += movingSpeed;

		// Critter has reached last tile => attack player
		} else 
			System.out.println("Critter has reached the end");
			this.damagePlayer = true;
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
