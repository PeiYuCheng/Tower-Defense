package towerModels;

import java.util.ArrayList;

import critterModels.Critter;

/**
 * Targeting strategy that targets and follows a critter in range of the tower.
 * @author Jeffrey
 *
 */
public class TowerTargetingFollow implements ITowerTargetingStrategy {

	private Critter current_target_critter;
	private ArrayList<Critter> target_critters;
	
	public TowerTargetingFollow(Critter current_target_critter,
			ArrayList<Critter> target_critters) {
		this.current_target_critter = current_target_critter;
		this.target_critters = target_critters;
	}


	@Override
	public Critter chooseTargetCritter() {
		if (!(current_target_critter == null)) {
			if (!target_critters.contains(current_target_critter)) {
				return target_critters.get(0);
			}
			return current_target_critter;
		}
		else {
			return target_critters.get(0);
		}
	}
		
}
