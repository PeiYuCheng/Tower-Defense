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
import javax.swing.BorderFactory;
import javax.swing.JComponent;

import map.Cell;
import map.*;
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
	private Point pixel_position, cell_position;
	private Dimension size;
	private boolean damagePlayer;
	private Color colour;
	private IMap mapKnownToCritters;
	private List<Cell> pathToWalk;
	private JComponent component;

	public Critter(int health, int movingSpeed, int damagingPower, int reward) {
		this.health = health;
		this.movingSpeed = movingSpeed;
		this.damagingPower = damagingPower;
		this.reward = reward;
		this.damagePlayer = false;
		this.critterSpawned = true;
		this.pixel_position = new Point();
		this.cell_position = new Point();
		this.size = new Dimension();
		this.mapKnownToCritters = MapFactory.getUniqueInstance().createMap(1, 0, 0);
		this.pathToWalk = mapKnownToCritters.getPath();		
		component = new JComponent() {
			@Override
			protected void paintComponent(Graphics g) {
				setBorder(BorderFactory.createTitledBorder("Node"));
				setBounds(pixel_position.x, pixel_position.y, size.width, size.height);
				drawCritter(g);
				drawHealthBar(g);
				super.paintComponent(g);
			}
		};
	}

	// //////////////////////
	// Behavioral Methods //
	// //////////////////////

	protected void drawHealthBar(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	protected void drawCritter(Graphics g) {
		
		g.setColor(Color.pink);
		g.fillRect(0, 0, size.width, size.height);
		
	}

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

	public boolean hasReachedExit() {
		if (pathToWalk.size() == 1)
			return true;
		return false;
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
	 * Critters make it from the beginning to the end. 
	 */
	public void startWalking() {

		ListIterator<Cell> iterator = pathToWalk.listIterator();

		Cell currentCellOnPath = iterator.next();
		if (iterator.hasNext()) {
			// Acquire the current path cell and the next one
			// in order for the critter to move from one to the other
			Cell nextCellOnPath = iterator.next();
			if (iterator.hasNext()) {
				int firstItemInList = 0;

				int currentPixelPositionX = (int) (currentCellOnPath.getPixelPosition().getX() + currentCellOnPath.getCellSize().getWidth() / 2);
				int nextPixelPostionX = (int) (nextCellOnPath.getPixelPosition().getX() + nextCellOnPath.getCellSize().getWidth() / 2);
				int currentPixelPositionY = (int) (currentCellOnPath.getPixelPosition().getY() + currentCellOnPath.getCellSize().getHeight() / 2);
				int nextPixelPositionY = (int) (nextCellOnPath.getPixelPosition().getY() + nextCellOnPath.getCellSize().getHeight() / 2);			

				int currentCellPositionX = currentCellOnPath.getX();
				int nextCellPositionX = nextCellOnPath.getX();
				int currentCellPositionY = currentCellOnPath.getY();
				int nextCellPositionY = nextCellOnPath.getY();

				// Critter spawns on starting cell
				if (critterSpawned) {
					pixel_position.setLocation(currentPixelPositionX, currentPixelPositionY);
					cell_position.setLocation(currentCellPositionX, currentCellPositionY);
					critterSpawned = false;
				}

				// Critter movement during game loop
				if (currentPixelPositionX == nextPixelPostionX) {
					if (nextPixelPositionY - currentPixelPositionY < 0) {
						// Move Up
						pixel_position.y -= movingSpeed;
					}
					else {
						//Move Down
						pixel_position.y += movingSpeed;
					}
				}
				else if (currentPixelPositionY == nextPixelPositionY) {
					pixel_position.x += movingSpeed;
				}

				// Once the critter reaches the next cell
				// the current cell is removed from the list
				if (pixel_position.x == nextPixelPostionX && pixel_position.y == nextPixelPositionY) {
					cell_position.setLocation(nextCellPositionX, nextCellPositionY);
					pathToWalk.remove(firstItemInList);
					System.out.println(cell_position.toString());
				}
			} else {
				/*
				 * This is a copy of the top part but it prevents the
				 * NoSuchElementException from occurring. It continues the iteration
				 * until the exit cell is reached
				 */
				int firstItemInList = 0;

				int currentPixelPositionX = (int) (currentCellOnPath.getPixelPosition().getX() + currentCellOnPath.getCellSize().getWidth() / 2);
				int nextPixelPostionX = (int) (nextCellOnPath.getPixelPosition().getX() + nextCellOnPath.getCellSize().getWidth() / 2);
				int currentPixelPositionY = (int) (currentCellOnPath.getPixelPosition().getY() + currentCellOnPath.getCellSize().getHeight() / 2);
				int nextPixelPositionY = (int) (nextCellOnPath.getPixelPosition().getY() + nextCellOnPath.getCellSize().getHeight() / 2);			

				int nextCellPositionX = nextCellOnPath.getX();
				int nextCellPositionY = nextCellOnPath.getY();

				// Critter movement during game loop
				if (currentPixelPositionX == nextPixelPostionX) {
					if (nextPixelPositionY - currentPixelPositionY < 0) {
						// Move Up
						pixel_position.y -= movingSpeed;
					}
					else {
						//Move Down
						pixel_position.y += movingSpeed;
					}
				}
				else if (currentPixelPositionY == nextPixelPositionY) {
					pixel_position.x += movingSpeed;
				}

				// Once the critter reaches the next cell
				// the current cell is removed from the list
				if (pixel_position.x == nextPixelPostionX && pixel_position.y == nextPixelPositionY) {
					cell_position.setLocation(nextCellPositionX, nextCellPositionY);
					pathToWalk.remove(firstItemInList);
					System.out.println(cell_position.toString());
				}

				// Critter has reached last tile => attack player
				if (hasReachedExit()) {
					System.out.println("Critter has reached the end");
					this.damagePlayer = true;
				}
			}
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
		return pixel_position.x;
	}

	public void setPosX(int posX) {
		this.pixel_position.x = posX;
	}

	public int getPosY() {
		return pixel_position.y;
	}

	public void setPosY(int posY) {
		this.pixel_position.y = posY;
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

	public JComponent getComponent() {
		return component;
	}

	public Point getPixel_position() {
		return pixel_position;
	}

	public void setPixel_position(Point pixel_position) {
		this.pixel_position = pixel_position;
	}

	public Point getCell_position() {
		return cell_position;
	}

	public void setCell_position(Point cell_position) {
		this.cell_position = cell_position;

	}
}
