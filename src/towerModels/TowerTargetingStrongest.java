package towerModels;

import java.util.ArrayList;

import critterModels.Critter;

/**
 * Targeting strategy that targets the strongest critter.
 * @author Jeffrey
 *
 */
public class TowerTargetingStrongest implements ITowerTargetingStrategy {

	private ArrayList<Critter> target_critters;
	
	public TowerTargetingStrongest(ArrayList<Critter> target_critters) {
		this.target_critters = target_critters;
	}


	@Override
	public Critter chooseTargetCritter() {
		Critter target_critter;
		target_critter = target_critters.get(0);
		for (Critter critter : target_critters) {
			if (critter.getDamagingPower() > target_critter.getDamagingPower()) {
				target_critter = critter;
			}
		}
		return target_critter;
	}
		
}
