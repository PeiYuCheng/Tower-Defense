package towerModels;
import img.Images;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import javax.swing.JComponent;

import controllers.CellSelector;
import map.Cell;
import critterModels.Critter;

/**
 * Abstract class that describes a generic tower.
 * @author Jeffrey Kirman (260493368)
 *
 */
public abstract class Tower extends Observable implements ITower, Serializable{

	private final int MAX_UPGRADE_LEVEL = 3;
	public enum AI_TYPE {FOLLOW, BACK, FRONT, STRONGEST, WEAKEST, RADIAL};
	private JComponent tower_component;
	protected Images images;
	private boolean towerSelected;
	
	/**
	 * The cost of a tower.
	 */
	private int cost;
	
	/**
	 * The base amount of money returned to the player if the tower is sold.
	 */
	private int refund_value;
	
	/**
	 * The base attack radius (in squares) that the tower can attack.
	 */
	private int range;
	
	/**
	 * The time in milliseconds when the tower last fired.
	 */
	private long time_of_last_fire;
	
	/**
	 * The base value that determines how much damage a single attack does to a critter.
	 */
	private int power;
	
	/**
	 * The base value that determines the amount of attacks the tower can do in 1 second.
	 */
	private int rate_of_fire;
	
	/**
	 * The upgrade level of the tower.
	 */
	private int upgrade_level;
	
	/**
	 * Determines whether the tower delivers pyro damage on an attack.
	 */
	private boolean pyro_damage;
	
	/**
	 * Determines whether the tower delivers slow damage on an attack.
	 */
	private boolean slow_damage;
	
	/**
	 * Indicates which type of attack pattern the tower should follow when attacking.
	 * FOLLOW: Attacks the critter closest to the entrance in range and continues attacking this critter until it is out of range.
	 * BACK_FIRST: Attacks the critter closest to the entrance in range.
	 * FRONT_FIRST: Attacks the critter closest to the exit in range.
	 * RADIAL: Attacks radially (special AI for radial tower).
	 */
	private AI_TYPE attack_mode;
	
	/**
	 * True if the tower is allowed to fire.
	 */
	private boolean active;
	
	/**
	 * The critter that is the current target of the tower.
	 */
	private Critter current_target_critter;
	
	/**
	 * A list of all the critters within the range of the tower.
	 */
	private ArrayList<Critter> critters_in_range;
	
	/**
	 * A list of all the critters on the map.
	 */
	private ArrayList<Critter> all_critters_on_map;
	
	/**
	 * The cell that contains the Tower.
	 */
	private Cell cell;
	
	/**
	 * The x coordinate of the tower.
	 */
	private int x_position;
	
	/**
	 * The y coordinate of the tower.
	 */
	private int y_position;
	
	public Tower(int cost, int refund_value, int range, int power, int rate_of_fire, AI_TYPE attack_mode) {
		
		this.cost = cost;
		this.refund_value = refund_value;
		this.range = range;
		this.power = power;
		this.rate_of_fire = rate_of_fire;
		this.upgrade_level = 1;
		this.pyro_damage = false;
		this.slow_damage = false;
		this.attack_mode = attack_mode;
		this.active = false;
		this.time_of_last_fire = 0;
		images = Images.getUniqueInstance();
		
		tower_component = new JComponent() {
			@Override
			protected void paintComponent(Graphics g) {
				tower_component.setLocation(cell.getPixelPosition());
				tower_component.setPreferredSize(cell.getCellSize());
				drawTower(g);
				super.paintComponent(g);
			}
		};
	}
	
	/**
	 * Getter for the cost of the tower.
	 * @return The cost of the tower.
	 */
	public int getCost() {
		return this.cost;
	}
	
	/**
	 * Getter for the cost to upgrade the tower to the next level.
	 * @return The cost to upgrade the cost of the tower. Returns -1 if the tower cannot be upgraded anymore.
	 */
	public int getUpgradeCost() {
		
		int upgrade_cost;
		upgrade_cost = this.cost/2 + (this.cost*this.upgrade_level)/2;
		
		if (this.upgrade_level <= MAX_UPGRADE_LEVEL) {
			return upgrade_cost;
		}
		else {
			return -1;
		}
	}
	
	/**
	 * Getter for the refund value of the tower.
	 * @return The money that would be returned to the player if the tower is sold.
	 */
	public int getRefundValue() {
		
		int refund_value;
		refund_value = this.refund_value*this.upgrade_level;
		
		return refund_value;
	}
	
	/**
	 * Getter for the rate of fire of the tower.
	 * @return The rate of fire of the tower.
	 */
	public int getRateOfFire() {
		return rate_of_fire;
	}

	/**
	 * Checks if the tower is activated for firing. (Getter for the active attribute.)
	 * @return True if the tower is activated.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Checks for all the critters within a range of a specified point and returns them in a sorted
	 * list where the lowest index represents the critter closest to the entrance of the map.
	 * @param all_critters A sorted list of all the critters with the lowest index pointing to the critter closest to the entrance.
	 * @param x_position The x coordinate around which critters should be checked for.
	 * @param y_position The y coordinate around which critters should be checked for.
	 * @param range The radius within which a critter is considered in range.
	 * @return A sorted list of all critters in range of the tower where the lowest index represents the critter closest to the entrance of the map.
	 */
	protected ArrayList<Critter> detectCritters(ArrayList<Critter> all_critters, int x_position, int y_position, int range) {
		
		ArrayList<Critter> critters_in_range = new ArrayList<>();
		all_critters_on_map = all_critters;
		Critter current_critter;
		double current_distance;
		
		// Adds all the critters in the radius of the range of the tower into a separate ArrayList to be returned by the method
		for (int i = 0; i < all_critters.size(); i++) {
			current_critter = all_critters.get(i);
			current_distance = Math.pow(current_critter.getCell_position().x - x_position, 2) + Math.pow(current_critter.getCell_position().y - y_position, 2);
			if (current_distance <= Math.pow(range, 2)) {
				critters_in_range.add(current_critter);
			}
		}
		
		this.critters_in_range = critters_in_range;
		return critters_in_range;
	}
	
	/**
	 * Checks for all the critters within the range of the tower and returns them in a sorted
	 * list where the lowest index represents the critter closest to the entrance of the map.
	 * @param all_critters A sorted list of all the critters with the lowest index pointing to the critter closest to the entrance.
	 * @return A sorted list of all the critters within the range of the tower with the lowest
	 * index pointing to the critter closest to the entrance.
	 */
	private void detectCrittersInRangeOfTower(ArrayList<Critter> all_critters) {
		detectCritters(all_critters, this.x_position, this.y_position, actualRange());
	}
	
	/**
	 * Sets the position of the tower.
	 * @param x_position The x coordinate of the tower.
	 * @param y_position The y coordinate of the tower.
	 * @param activate A boolean that if true activates the tower for attacking, false by default.
	 */
	public void placeTower(Cell cell, boolean activate) {
		this.cell = cell;
		this.x_position = cell.getX();
		this.y_position = cell.getY();
		active = activate;
		
		cell.setTowerInCell(this);
		
		setChanged();
		notifyObservers();
		
		tower_component.setBounds(cell.getPixelPosition().x, cell.getPixelPosition().y, 
				cell.getCellSize().width, cell.getCellSize().height);
	}
	
	/**
	 * Raises the upgrade level of the tower by 1.
	 * @return True if the tower was upgraded.
	 */
	public boolean upgradeTower() {
		if (upgrade_level < MAX_UPGRADE_LEVEL) {
			upgrade_level++;
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}
	
	/**
	 * Activates or disables the tower to enable firing. (Setter for the active attribute.)
	 * @param activate True activates the tower, false deactivates it.
	 */
	public void activateTower(boolean activate) {
		this.active = activate;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Changes the attack mode of the tower. (Trying to change the attack mode of a radial tower will do nothing.)
	 * @param towerAI The AI_TYPE of the new attack mode of the tower
	 */
	private void changeTowerAI(AI_TYPE towerAI) {
		
		if (this.attack_mode == AI_TYPE.RADIAL) {
			return;
		}
		else {
			this.attack_mode = towerAI;
		}	
		setChanged();
		notifyObservers();
	}
	
	public void toggleTowerAI() {
		
		if (attack_mode.equals(AI_TYPE.RADIAL)) {
			return;
		}
		ArrayList<AI_TYPE> allTypes = new ArrayList<AI_TYPE>(Arrays.asList(AI_TYPE.values()));
		allTypes.remove(AI_TYPE.RADIAL);
		
		for (int i = 0; i < allTypes.size() - 1; i++) {
			if (attack_mode.equals(allTypes.get(i))) {
				changeTowerAI(allTypes.get(i+1));
				return;
			}
		}
		changeTowerAI(allTypes.get(0));


	}
	
	/**
	 * Chooses a target critter based on the attack mode of the tower.
	 * @param target_critters A list of the critters in the range of the tower.
	 */
	protected void chooseTargetCritter(ArrayList<Critter> target_critters) {
		
		if (target_critters.size() == 0) {
			current_target_critter = null;
			return;
		}
		
		ITowerTargetingStrategy targeter;
		
		switch (attack_mode) {
		
		// Only changes target when the current critter is out of range of the tower
		case FOLLOW:
			targeter = new TowerTargetingFollow(current_target_critter, target_critters);
			break;
			
		// Always changes the target to the back critter
		case BACK:
			targeter = new TowerTargetingBack(target_critters);
			break;
			
		// Always changes the target to the front critter
		case FRONT:
			targeter = new TowerTargetingFront(target_critters);
			break;
			
		case RADIAL:
			targeter = null;
			break;
			
		case STRONGEST:
			targeter = new TowerTargetingStrongest(target_critters);
			break;
			
		case WEAKEST:
			targeter = new TowerTargetingWeakest(target_critters);
			break;
			
		default:
			targeter = null;
			break;
		}
		
		if (targeter != null) {
			current_target_critter = targeter.chooseTargetCritter();
		}
		else {
			current_target_critter = null;
		}
		
		
	}
	
	/**
	 * Fires at a critter if there is a critter in range and invokes the damage critter method.
	 * @return True if the tower fires.
	 */
	public boolean fire() {
		
		int amount_of_damage;

		detectCritterTargets(all_critters_on_map);
		amount_of_damage = actualPower();
		if (canFire() && this.getCurrentTargetCritter() != null) {
			current_target_critter.damageCritter(amount_of_damage, pyro_damage, slow_damage);
			time_of_last_fire = System.currentTimeMillis();
			return true;
		}
		return false;
		
	}
	
	/**
	 * Checks to see if the tower is able to fire.
	 * @return True if the tower is able to fire.
	 */
	protected boolean canFire() {
		
		long fire_time = (long)(1000*(1/(double)actualRateOfFire()));
		
		if (System.currentTimeMillis() - time_of_last_fire > fire_time) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * Performs all the tasks required to detect critter targets.
	 */
	protected void detectCritterTargets(ArrayList<Critter> all_critters) {
		detectCrittersInRangeOfTower(all_critters);
		chooseTargetCritter(critters_in_range);
	}

	/**
	 * Getter for the range of the tower.
	 * @return The range of the tower.
	 */
	public int getRange() {
		return range;
	}

	/**
	 * Getter for the power of each shot of the tower.
	 * @return The power of each shot of the tower.
	 */
	public int getPower() {
		return power;
	}

	/**
	 * Getter for the current upgrade level of the tower.
	 * @return The current upgrade level of the tower.
	 */
	public int getUpgradeLevel() {
		return upgrade_level;
	}

	/**
	 * Getter for the current target critter that the tower is focusing on.
	 * @return The current target critter that the tower is focusing on.
	 */
	public Critter getCurrentTargetCritter() {
		return current_target_critter;
	}
	
	/**
	 * Setter for the current target critter that the tower is focusing on.
	 * @param current_target_critter The current target critter that the tower is focusing on.
	 */
	protected void setCurrentTargetCritter(Critter current_target_critter) {
		this.current_target_critter = current_target_critter;
	}

	/**
	 * Setter for pyro damage (damage inflicted over time to a critter).
	 * @param pyro_damage True if the tower inflicts pyro damage. False if not.
	 */
	public void setPyroDamage(boolean pyro_damage) {
		this.pyro_damage = pyro_damage;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Checks if the tower inflicts pyro damage (damage over time to a critter).
	 * @return True if the tower inflicts pyro damage. False if not.
	 */
	public boolean hasPyroDamage() {
		return pyro_damage;
	}

	/**
	 * Setter for slow damage (damage that slows a critter).
	 * @param slow_damage True if the tower inflicts slow damage. False if not.
	 */
	public void setSlowDamage(boolean slow_damage) {
		this.slow_damage = slow_damage;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Checks if the tower inflicts slow damage (damage that slows a critter).
	 * @return True if the tower inflicts slow damage. False if not.
	 */
	public boolean hasSlowDamage() {
		return slow_damage;
	}

	/**
	 * Getter for the current attack mode of the tower.
	 * @return The AI_TYPE enumeration of the current attack mode.
	 */
	public AI_TYPE getAttackMode() {
		return attack_mode;
	}

	/**
	 * Getter for the x coordinate of the tower.
	 * @return The x coordinate of the tower.
	 */
	public int getXPosition() {
		return x_position;
	}

	/**
	 * Getter for the y coordinate of the tower.
	 * @return The y coordinate of the tower.
	 */
	public int getYPosition() {
		return y_position;
	}

	/**
	 * Getter for the time the tower last fired in milliseconds.
	 * @return The time the tower last fired in milliseconds.
	 */
	protected long getTimeOfLastFire() {
		return time_of_last_fire;
	}

	/**
	 * Setter fore the time the tower last fired in milliseconds.
	 * @param time_of_last_fire The time the tower last fired in milliseconds.
	 */
	protected void setTimeOfLastFire(long time_of_last_fire) {
		this.time_of_last_fire = time_of_last_fire;
	}

	/**
	 * Getter for the list of critters in range of the tower.
	 * @return The list of critters in range of the tower.
	 */
	protected ArrayList<Critter> getCrittersInRange() {
		
		return critters_in_range;
	}

	/**
	 * Getter for the list of all the critters on the map.
	 * @return The list of all the critters on the map.
	 */
	protected ArrayList<Critter> getAllCrittersOnMap() {
		return all_critters_on_map;
	}
	
	/**
	 * Setter for the list of all the critters on the map.
	 * @param all_critters The list of all the critters on the map.
	 */
	public void setAllCrittersOnMap(ArrayList<Critter> all_critters) {
		all_critters_on_map = all_critters;
	}


	protected void drawTower(Graphics g) {
		
		if (towerSelected) {
			g.setColor(new Color(254,140,255,100));
			g.fillRect(1, 1, cell.getCellSize().width, cell.getCellSize().height);
		}

	}
	
	public JComponent getComponent() {
		return tower_component;
	}
	
	public int actualPower() {
		return power*upgrade_level;
	}
	
	public int actualRange() {
		return range*upgrade_level;
	}
	
	public int actualRateOfFire() {
		return rate_of_fire*upgrade_level;
	}

	public boolean isTowerSelected() {
		return towerSelected;
	}

	public void setTowerSelected(boolean towerSelected) {
		this.towerSelected = towerSelected;
	}
	
	public void setAttackMode(AI_TYPE attack_mode) {
		this.attack_mode = attack_mode;
	}
	
}
