package towerModels;
import java.awt.Color;
import java.util.ArrayList;

import critterModels.Critter;

/**
 * The tower that shoots at a target and damages an area around said target.
 * @author Jeffrey Kirman (260493368)
 *
 */
public class AreaOfEffectTower extends Tower {

	private static final int COST = 70;
	private static final int REFUND_VALUE = 40;
	private static final int RANGE = 3;
	private static final int POWER = 3;
	private static final int RATE_OF_FIRE = 1;
	private static final Color TOWER_COLOR = Color.orange;
	
	private final int AREA_OF_EFFECT = 2;
	private ArrayList<Critter> area_of_effect_targets;
	
	public AreaOfEffectTower() {
		super(COST, REFUND_VALUE, RANGE, POWER, RATE_OF_FIRE, AI_TYPE.FOLLOW, TOWER_COLOR);
		area_of_effect_targets = new ArrayList<>();
	}
	
	/**
	 * Finds the list of critters that would be affected by an area of effect shot.
	 * @param all_critters The list of all the critters on the map.
	 */
	private void findAreaOfEffectTargets(ArrayList<Critter> all_critters) {
		Critter current_target_critter = this.getCurrentTargetCritter();
		area_of_effect_targets = this.detectCritters(all_critters, current_target_critter.getPosX(),
				current_target_critter.getPosY(), AREA_OF_EFFECT);		
	}
		
	/**
	 * Deals damage to all the critters within range of a shot.
	 * @return True if the tower fires.
	 */
	@Override
	public boolean fire() {
		
		int amount_of_damage;

		amount_of_damage = this.getPower()*this.getUpgradeLevel();
		detectCritterTargets(getAllCrittersOnMap());
		findAreaOfEffectTargets(this.getAllCrittersOnMap());
		
		// TODO invoke the damage method of the critter
		if (canFire() && this.getCurrentTargetCritter() != null) {
			for (int i = 0; i < area_of_effect_targets.size(); i++) {
				area_of_effect_targets.get(i).damageCritter(amount_of_damage, hasPyroDamage(), hasSlowDamage());
			}
			setTimeOfLastFire(System.currentTimeMillis());
			return true;
		}
		return false;
		
	}

}
