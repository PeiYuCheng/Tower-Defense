package towerModels;

import java.util.ArrayList;

import critterModels.Critter;

/**
 * Targeting strategy that targets the critter closest to the exit.
 * @author Jeffrey
 *
 */
public class TowerTargetingFront implements ITowerTargetingStrategy {

	private ArrayList<Critter> target_critters;
	
	public TowerTargetingFront(ArrayList<Critter> target_critters) {
		this.target_critters = target_critters;
	}


	@Override
	public Critter chooseTargetCritter() {
		return target_critters.get(0);
	}
		
}
