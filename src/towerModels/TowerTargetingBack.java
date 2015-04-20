package towerModels;

import java.util.ArrayList;

import critterModels.Critter;

/**
 * Targeting strategy that targets the critter closest to the entrance.
 * @author Jeffrey
 *
 */
public class TowerTargetingBack implements ITowerTargetingStrategy {

	private ArrayList<Critter> target_critters;
	
	public TowerTargetingBack(ArrayList<Critter> target_critters) {
		this.target_critters = target_critters;
	}


	@Override
	public Critter chooseTargetCritter() {
		return target_critters.get(target_critters.size() - 1);
	}
		
}
