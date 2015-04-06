package towerModels;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import critterModels.Critter;

/**
 * The tower that shoots at a target and damages an area around said target.
 * @author Jeffrey Kirman (260493368)
 *
 */
public class AreaOfEffectTower extends Tower implements Serializable {

	public static final int COST = 13;
	private static final int REFUND_VALUE = 4;
	private static final int RANGE = 2;
	private static final int POWER = 3;
	private static final int RATE_OF_FIRE = 1;
	
	private final int AREA_OF_EFFECT = 2;
	private ArrayList<Critter> area_of_effect_targets;
	
	public AreaOfEffectTower() {
		super(COST, REFUND_VALUE, RANGE, POWER, RATE_OF_FIRE, AI_TYPE.FOLLOW);
		area_of_effect_targets = new ArrayList<>();
	}
	
	/**
	 * Finds the list of critters that would be affected by an area of effect shot.
	 * @param all_critters The list of all the critters on the map.
	 */
	private void findAreaOfEffectTargets(ArrayList<Critter> all_critters) {
		if (all_critters.isEmpty()) {
			return;
		}
		Critter current_target_critter = this.getCurrentTargetCritter();
		
		if (current_target_critter == null) {
			return;
		}
		area_of_effect_targets = this.detectCritters(all_critters, current_target_critter.getCell_position().x,
				current_target_critter.getCell_position().y, AREA_OF_EFFECT);		
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
		
		if (area_of_effect_targets.isEmpty()) {
			return false;
		}
		
		if (canFire() && this.getCurrentTargetCritter() != null) {
			for (int i = 0; i < area_of_effect_targets.size(); i++) {
				area_of_effect_targets.get(i).damageCritter(amount_of_damage, hasPyroDamage(), hasSlowDamage());
			}
			setTimeOfLastFire(System.currentTimeMillis());
			return true;
		}
		return false;
		
	}
	
	@Override
	public void drawTower(Graphics g) {
		if (getUpgradeLevel() == 1) {
			g.drawImage(images.splashTowerLevelOne.getScaledInstance(40, 40, 0), 0, 0, null);
		}
		else if (getUpgradeLevel() == 2) {
			g.drawImage(images.splashTowerLevelTwo.getScaledInstance(40, 40, 0), 0, 0, null);
		}
		else {
			g.drawImage(images.splashTowerLevelThree.getScaledInstance(40, 40, 0), 0, 0, null);
		}
		super.drawTower(g);
	}

}
