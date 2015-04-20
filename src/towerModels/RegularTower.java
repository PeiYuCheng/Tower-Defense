package towerModels;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * The tower that simply shoots at a target.
 * @author Jeffrey Kirman (260493368)
 *
 */
public class RegularTower extends Tower implements Serializable {

	public static final int COST = 9;
	private static final int REFUND_VALUE = 7;
	private static final int RANGE = 1;
	private static final int POWER = 5;
	private static final int RATE_OF_FIRE = 2;
	
	
	public RegularTower() {
		super(COST, REFUND_VALUE, RANGE, POWER, RATE_OF_FIRE, AI_TYPE.FOLLOW);
	}
	
	/**
	 * Fires at a critter if there is a critter in range and invokes the damage critter method.
	 * @return True if the tower fires.
	 */
	@Override
	public boolean fire() {
		
		int amount_of_damage;
		
		detectCritterTargets(getAllCrittersOnMap());
		amount_of_damage = this.getPower()*this.getUpgradeLevel();

		// damages the target critter if the tower can fire
		if (canFire() && this.getCurrentTargetCritter() != null) {
			this.getCurrentTargetCritter().damageCritter(amount_of_damage, hasPyroDamage(), hasSlowDamage());
			setTimeOfLastFire(System.currentTimeMillis());
			return true;
		}
		return false;
	}
	
	@Override
	public void drawTower(Graphics g) {
		if (getUpgradeLevel() == 1) {
			g.drawImage(images.regularTowerLevelOne.getScaledInstance(40, 40, 0), 0, 0, null);
		}
		else if (getUpgradeLevel() == 2) {
			g.drawImage(images.regularTowerLevelTwo.getScaledInstance(40, 40, 0), 0, 0, null);
		}
		else {
			g.drawImage(images.regularTowerLevelThree.getScaledInstance(40, 40, 0), 0, 0, null);
		}
		super.drawTower(g);
	}

	@Override
	public int actualRange() {
		return RANGE*(getUpgradeLevel()+1);
	}
	
}
