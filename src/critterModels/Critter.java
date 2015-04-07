package critterModels;

import img.Images;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

import javax.swing.JComponent;

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
	private int max_health;
	private int movingSpeed;
	private int damagingPower;
	private int reward;
	protected boolean critterSpawned;
	protected Point pixel_position;
	protected Point cell_position;
	private Dimension size;
	private boolean damagePlayer;
	private Color colour;
	private Map mapKnownToCritters;
	protected List<Cell> pathToWalk;
	private JComponent critter_component;
	private JComponent healthbar_component;
	private boolean deployed;
	protected DIRECTION direction;
	public static final long DEPLOY_TIME = 1000;
	public static final int HEALTH_BAR_WIDTH = 20;
	public static final int HEALTH_BAR_HEIGHT = 5;
	public static enum DIRECTION {UP, DOWN, LEFT, RIGHT};
	protected Images images;

	public Critter(int health, int movingSpeed, int damagingPower, int reward) {
		this.health = health;
		this.max_health = health;
		this.movingSpeed = movingSpeed;
		this.damagingPower = damagingPower;
		this.reward = reward;
		this.damagePlayer = false;
		this.critterSpawned = true;
		this.pixel_position = new Point();
		this.cell_position = new Point();
		this.pathToWalk = new ArrayList<>();
		this.size = new Dimension();
		images = Images.getUniqueInstance();
		critter_component = new JComponent() {
			@Override
			protected void paintComponent(Graphics g) {
				setBounds(getTruePixelPosition().x, getTruePixelPosition().y, size.width, size.height);
				drawCritter(g);
				super.paintComponent(g);
			}
		};
		healthbar_component = new JComponent() {
			@Override
			protected void paintComponent(Graphics g) {
				setBounds(getTruePixelPosition().x - HEALTH_BAR_WIDTH/2 + size.width/2, getTruePixelPosition().y - 15, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
				drawHealthBar(g);
				super.paintComponent(g);
			}
		};
		
	}

	// //////////////////////
	// Behavioral Methods //
	// //////////////////////

	protected void drawHealthBar(Graphics g) {
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, HEALTH_BAR_WIDTH*health/max_health, HEALTH_BAR_HEIGHT);
		g.setColor(Color.black);
		g.drawRect(0, 0, HEALTH_BAR_WIDTH*health/max_health, HEALTH_BAR_HEIGHT);
		
	}

	protected void drawCritter(Graphics g) {

		g.setColor(Color.black);
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
	 * Indicates whether the <b>Critter</b> has made it to the exit
	 * @return <b>true</b> if reached exit cell else <b>false</b>
	 */
	public boolean hasReachedExit() {
		if (pathToWalk.size() == 1)
			return true;
		return false;
	}
	
	/**
	 * Sets the movement boolean fields for direction
	 * @param direction
	 */
	public void setMovement(DIRECTION direction) {
		this.direction = direction;
	}

	/**
	 * This method deals with the path finding algorithm to make sure that the
	 * Critters make it from the beginning to the end. 
	 */
	public void startWalking() {

		ListIterator<Cell> iterator = pathToWalk.listIterator();

		Cell currentCellOnPath = iterator.next();
		
		int currentPixelPositionX = (int) (currentCellOnPath.getPixelPosition().getX() + currentCellOnPath.getCellSize().getWidth() / 2);
		int currentPixelPositionY = (int) (currentCellOnPath.getPixelPosition().getY() + currentCellOnPath.getCellSize().getHeight() / 2);
		int currentCellPositionX = currentCellOnPath.getX();
		int currentCellPositionY = currentCellOnPath.getY();
		
		int firstItemInList = 0;

		if (iterator.hasNext()) {
			// Acquire the current path cell and the next one
			// in order for the critter to move from one to the other
			Cell nextCellOnPath = iterator.next();
			
			int nextPixelPositionX = (int) (nextCellOnPath.getPixelPosition().getX() + nextCellOnPath.getCellSize().getWidth() / 2);
			int nextPixelPositionY = (int) (nextCellOnPath.getPixelPosition().getY() + nextCellOnPath.getCellSize().getHeight() / 2);			
			int nextCellPositionY = nextCellOnPath.getY();
			int nextCellPositionX = nextCellOnPath.getX();
			
			if (iterator.hasNext()) {

				// Critter spawns on starting cell
				if (critterSpawned) {
					setMovement(DIRECTION.RIGHT);
					pixel_position.setLocation(currentPixelPositionX, currentPixelPositionY);
					cell_position.setLocation(currentCellPositionX, currentCellPositionY);
					critterSpawned = false;
				}

				// Critter movement during game loop
				if (currentPixelPositionX == nextPixelPositionX) {
					if (nextPixelPositionY - currentPixelPositionY < 0) {
						// Move Up
						setMovement(DIRECTION.UP);
						pixel_position.y -= movingSpeed;
						if (pixel_position.y - nextPixelPositionY < 0)
							pixel_position.setLocation(nextPixelPositionX, nextPixelPositionY);
					}
					else {
						//Move Down
						setMovement(DIRECTION.DOWN);
						pixel_position.y += movingSpeed;
						if (pixel_position.y - nextPixelPositionY > 0)
							pixel_position.setLocation(nextPixelPositionX, nextPixelPositionY);
					}	
				}
				else if (currentPixelPositionY == nextPixelPositionY) {
					if (nextPixelPositionX - currentPixelPositionX > 0) {
						// Move Right
						setMovement(DIRECTION.RIGHT);
						pixel_position.x += movingSpeed;
						if (pixel_position.x - nextPixelPositionX  > 0)
							pixel_position.setLocation(nextPixelPositionX, nextPixelPositionY);
					}
					else {
						// Move Left
						setMovement(DIRECTION.LEFT);
						pixel_position.x -= movingSpeed;
						if (pixel_position.x - nextPixelPositionX  < 0)
							pixel_position.setLocation(nextPixelPositionX, nextPixelPositionY);
					}
				}

				// Once the critter reaches the next cell
				// the current cell is removed from the list
				if (pixel_position.x == nextPixelPositionX && pixel_position.y == nextPixelPositionY) {
					cell_position.setLocation(nextCellPositionX, nextCellPositionY);
					pathToWalk.remove(firstItemInList);
				}
			} else {
				/*
				 * This ensures that the critter keeps moving into the exit cell
				 * and gets deleted once it reaches it.
				 */
				if (direction == DIRECTION.RIGHT) {
					if (nextPixelPositionY - currentPixelPositionY == 0) {
						setMovement(DIRECTION.RIGHT);
						pixel_position.x += movingSpeed;
					} else if (nextPixelPositionY - currentPixelPositionY < 0) {
						setMovement(DIRECTION.UP);
						pixel_position.y -= movingSpeed;
					} else {
						setMovement(DIRECTION.DOWN);
						pixel_position.y += movingSpeed;
					}
				}	
				else if (direction == DIRECTION.LEFT) {
					if (nextPixelPositionY - currentPixelPositionY == 0) {
						setMovement(DIRECTION.LEFT);
						pixel_position.x -= movingSpeed;
					} else if (nextPixelPositionY - currentPixelPositionY < 0) {
						setMovement(DIRECTION.UP);
						pixel_position.y -= movingSpeed;
					} else {
						setMovement(DIRECTION.DOWN);
						pixel_position.y += movingSpeed;
					}
				}
				else if (direction == DIRECTION.UP) {
					if (nextPixelPositionX - currentPixelPositionX == 0) {
						setMovement(DIRECTION.UP);
						pixel_position.y -= movingSpeed;
					} else if (nextPixelPositionX - currentPixelPositionX > 0) {
						setMovement(DIRECTION.RIGHT);
						pixel_position.x += movingSpeed;
					} else {
						setMovement(DIRECTION.LEFT);
						pixel_position.x -= movingSpeed;
					}
				}
				else {
					if (nextPixelPositionX - currentPixelPositionX == 0) {
						setMovement(DIRECTION.DOWN);
						pixel_position.y += movingSpeed;
					} else if (nextPixelPositionX - currentPixelPositionX > 0) {
						setMovement(DIRECTION.RIGHT);
						pixel_position.x += movingSpeed;
					} else {
						setMovement(DIRECTION.LEFT);
						pixel_position.x -= movingSpeed;
					}
				}

				// Critter reaches the exit cell
				if (pixel_position.x >= nextPixelPositionX && pixel_position.y >= nextPixelPositionY && (direction == DIRECTION.RIGHT || direction == DIRECTION.DOWN)) {
					pathToWalk.remove(firstItemInList);
				}
				else if (pixel_position.x <= nextPixelPositionX && pixel_position.y <= nextPixelPositionY && (direction == DIRECTION.UP || direction == DIRECTION.LEFT)) {
					pathToWalk.remove(firstItemInList);
				}
			}
		}
		critter_component.setBounds(getTruePixelPosition().x, getTruePixelPosition().y, size.width, size.height);
		healthbar_component.setBounds(getTruePixelPosition().x - HEALTH_BAR_WIDTH/2 + size.width/2, getTruePixelPosition().y - 15, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
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

	public JComponent getCritterComponent() {
		return critter_component;
	}
	
	public JComponent getHealthbarComponent() {
		return healthbar_component;
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

	public boolean isDeployed() {
		return deployed;
	}

	public void setDeployed(boolean deployed) {
		this.deployed = deployed;
	}
	
	public void setMapKnownToCritters (Map map) {
		mapKnownToCritters = map;
		pathToWalk.addAll(map.getPath());
	}

	public Point getTruePixelPosition() {
		return new Point(pixel_position.x - 15, pixel_position.y - 15);
	}
}
