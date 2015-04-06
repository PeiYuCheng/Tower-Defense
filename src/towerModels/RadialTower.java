package towerModels;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import critterModels.Critter;

/**
 * The tower that shoots radially outward and inflicts piercing damage.
 * @author Jeffrey Kirman(260493368)
 *
 */
public class RadialTower extends Tower implements Serializable {

	public static final int COST = 7;
	private static final int REFUND_VALUE = 4;
	private static final int RANGE = 4;
	private static final int POWER = 4;
	private static final int RATE_OF_FIRE = 1;
	
	private ArrayList<Critter> radial_targets;
	private ArrayList<Integer> x_coordinate_target_points;
	private ArrayList<Integer> y_coordinate_target_points;
	
	public RadialTower() {
		super(COST, REFUND_VALUE, RANGE, POWER, RATE_OF_FIRE, AI_TYPE.RADIAL);

		radial_targets = new ArrayList<>();
		x_coordinate_target_points = new ArrayList<>();
		y_coordinate_target_points = new ArrayList<>();
		
	}
	
	/**
	 * Finds and lists all the points that would hit a critter if they were at that coordinate.
	 */
	private void findRadialTargetPoints() {
		
		for (int i = -getRange(); i <= getRange(); i++) {
			x_coordinate_target_points.add(0 + getXPosition());
			y_coordinate_target_points.add(i + getYPosition());
			
			x_coordinate_target_points.add(i + getXPosition());
			y_coordinate_target_points.add(0 + getYPosition());
			
			x_coordinate_target_points.add(i + getXPosition());
			y_coordinate_target_points.add(i + getYPosition());
			
			x_coordinate_target_points.add(i + getXPosition());
			y_coordinate_target_points.add(-i + getYPosition());
		}
		
	}
	
	/**
	 * Find all the critters in range of the tower that would be damaged if the tower shoots.
	 * @param critters_in_range A list of all the critters in the range of the tower.
	 */
	private void findRadialTargets(ArrayList<Critter> critters_in_range) {
		
		Critter current_critter;
		ArrayList<Critter> radial_targets = new ArrayList<>();
		boolean x_match, y_match;
		
		if (critters_in_range == null) {
			return;
		}
		
		findRadialTargetPoints();
		for (int i = 0; i < critters_in_range.size(); i++) {
			current_critter = critters_in_range.get(i);
			x_match = false;
			y_match = false;
			for (int j = 0; j < x_coordinate_target_points.size(); j++) {
				x_match = (current_critter.getCell_position().x == x_coordinate_target_points.get(j).intValue());
				y_match = (current_critter.getCell_position().y == y_coordinate_target_points.get(j).intValue());
				
				if (x_match && y_match) {
					radial_targets.add(current_critter);
					break;
				}
			}
		}
		this.radial_targets = radial_targets;
	}
	
	/**
	 * Fires radially and hits any critters in its radial path.
	 * @return True if the tower fires.
	 */
	@Override
	public boolean fire() {
		
		int amount_of_damage;

		amount_of_damage = this.getPower()*this.getUpgradeLevel();
		detectCritterTargets(getAllCrittersOnMap());
		findRadialTargets(this.getCrittersInRange());
		
		// TODO invoke the damage method of the critter
		if (canFire() && radial_targets != null) {
			for (int i = 0; i < radial_targets.size(); i++) {
				radial_targets.get(i).damageCritter(amount_of_damage, hasPyroDamage(), hasSlowDamage());
			}
			setTimeOfLastFire(System.currentTimeMillis());
			return true;
		}
		return false;
	}
	
	@Override
	public void drawTower(Graphics g) {
		if (getUpgradeLevel() == 1) {
			g.drawImage(images.radialTowerLevelOne.getScaledInstance(40, 40, 0), 1, 1, null);
		}
		else if (getUpgradeLevel() == 2) {
			g.drawImage(images.radialTowerLevelTwo.getScaledInstance(40, 40, 0), 1, 1, null);
		}
		else {
			g.drawImage(images.radialTowerLevelThree.getScaledInstance(40, 40, 0), 1, 1, null);
		}
		super.drawTower(g);
	}

}
