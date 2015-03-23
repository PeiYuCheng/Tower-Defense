package critterModels;

import java.util.Observable;

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
	private int posX, posY;

	public Critter(int health, int movingSpeed, int damagingPower, int reward) {
		this.health = health;
		this.movingSpeed = movingSpeed;
		this.damagingPower = damagingPower;
		this.reward = reward;
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
	 * This method indicates that the critter has reached the exit.
	 * 
	 * @return <b>true</b> if the critter has made it to the exit.
	 */
	public boolean hasReachedExit() {
		// TODO: check if critter has reached exit tile.
//		if (posX == Tile.END_TILE[0] && posY == Tile.END_TILE[1])
			return true;
//		else
//			return false;
	}

	/**
	 * This method checks if the critter has reached the exit and then reduces
	 * the player's health appropriately.
	 * @param p of Player class
	 */
	// TODO: Implement later in game controller
	public void damage(Player p) {
		if (hasReachedExit())
			p.setLives(p.getLives() - damagingPower);
		// TODO: Player.Health -= DAMAGING_POWER;
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
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
