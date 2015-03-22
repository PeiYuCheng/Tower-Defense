package towerModels;

import java.awt.Color;

/**
 * The tower that simply shoots at a target.
 * @author Jeffrey Kirman (260493368)
 *
 */
public class RegularTower extends Tower {

	public static final int COST = 50;
	private static final int REFUND_VALUE = 30;
	private static final int RANGE = 5;
	private static final int POWER = 5;
	private static final int RATE_OF_FIRE = 1;
	public static final Color TOWER_COLOR = Color.blue;
	
	
	public RegularTower() {
		super(COST, REFUND_VALUE, RANGE, POWER, RATE_OF_FIRE, AI_TYPE.FOLLOW, TOWER_COLOR);
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
		// TODO invoke the damage method of the critter
		if (canFire() && this.getCurrentTargetCritter() != null) {
			this.getCurrentTargetCritter().damageCritter(amount_of_damage, hasPyroDamage(), hasSlowDamage());
			setTimeOfLastFire(System.currentTimeMillis());
			return true;
		}
		return false;
	}

}
