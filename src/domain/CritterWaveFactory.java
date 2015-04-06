package domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import map.Map;
import critterModels.BossCritter;
import critterModels.Critter;
import critterModels.LargeCritter;
import critterModels.MediumCritter;
import critterModels.RegularCritter;

/**
 * <h3>
 * The Critter Engine Class</h3>
 * <p>
 * This class encapsulates all the methods relating to the critters. These
 * methods are related to the game logic related to them and their placement.
 * </p>
 * 
 * @author Justin Asfour
 * @version 1.0
 */
public class CritterWaveFactory implements Serializable{

	private static CritterWaveFactory critter_factory;
	
	public static final int FINAL_WAVE = 50;
	private Queue<Critter> group;
	private int waveNumber;
	private int amountOfSmallCritters;
	private int amountOfMediumCritters;
	private int amountOfLargeCritters;

	private CritterWaveFactory() {
		waveNumber = 0;
	}
	
	public static CritterWaveFactory getInstance() {
		if (critter_factory == null)
			critter_factory = new CritterWaveFactory();
		return critter_factory;
	}

	/**
	 * The method creates a group of <b>Critter</b>. 
	 * It implements the Singleton Design Pattern. The number of critters in
	 * each group changes based on each wave. As the player progresses through
	 * the game, the number of critters and the type that get dispatched changes
	 * instead of the strength of the critters. This is how difficulty is
	 * increased with each wave. Final wave is against <b>Boss</b>.
	 * 
	 * @param waveNumber This parameter will change whenever the Player finishes a wave
	 */
	public Queue<Critter> createWave(Map map) {
		group = new LinkedList<Critter>();
		// TODO: add real start position for boss and regular
		Critter regularCritter;
		Critter mediumCritter;
		Critter largeCritter;
		Critter bossCritter;

		if (waveNumber == FINAL_WAVE) {
			bossCritter = new BossCritter(0, 0);
			group.add(bossCritter);
		}
		else {
			for (int i = 0; i < amountOfSmallCritters; i++) {
				regularCritter = new RegularCritter(0, 0);
				group.add(regularCritter);
			}
			for (int i = 0; i < amountOfMediumCritters; i++) {
				mediumCritter = new MediumCritter(0, 0);
				group.add(mediumCritter);
			}
			for (int i = 0; i < amountOfLargeCritters; i++) {
				largeCritter = new LargeCritter(0,0);
				group.add(largeCritter);
			}
		}
		
		for (Critter critter : group) {
			critter.setMapKnownToCritters(map);
		}
		
		Collections.shuffle((List<?>) group, new Random(System.nanoTime()));
		
		return group;
	}
	
	public void restartWaves() {
		waveNumber = 0;
		amountOfSmallCritters = 0;
		amountOfMediumCritters = 0;
		amountOfLargeCritters = 0;
	}
	
	public int countAmountOfSmallCritters(int waveNumber) {
		if (waveNumber <= 40) {
			return waveNumber*2;
		}
		else if (waveNumber < 50) {
			return waveNumber;
		}
		else {
			return 0;
		}
	}
	
	public int countAmountOfMediumCritters(int waveNumber) {
		if (waveNumber > 10) {
			if (waveNumber <= 20) {
				return waveNumber - 10;
			}
			else if (waveNumber <= 40) {
				return waveNumber - 15;
			}
			else if (waveNumber < 50) {
				return waveNumber - 25;
			}
			else {
				return 0;
			}
		}
		return 0;
	}
	
	public int countAmountOfLargeCritters(int waveNumber) {
		if (waveNumber > 20) {
			if (waveNumber <= 30) {
				return waveNumber - 20;
			}
			else if (waveNumber <= 40) {
				return waveNumber - 15;
			}
			else if (waveNumber < 50) {
				return waveNumber - 10;
			}
			else {
				return 0;
			}
		}
		return 0;
	}
	
	public void setupNextWave() {
		waveNumber++;
		amountOfSmallCritters = countAmountOfSmallCritters(waveNumber);
		amountOfMediumCritters = countAmountOfMediumCritters(waveNumber);
		amountOfLargeCritters = countAmountOfLargeCritters(waveNumber);
	}
	
	/**
	 * This method empties the CritterWaveFactory. Its a helper method for the next
	 * one which is <b>dispatchAllCritter()</b>.
	 * 
	 * @return Critter at head of queue
	 */
	public Critter dispatchOneCritter() {
		return group.poll();
	}

	// /////////////////////
	// Getters & Setters //
	// /////////////////////
	public Queue<Critter> getGroup() {
		return group;
	}

	public void setGroup(Queue<Critter> group) {
		this.group = group;
	}

	public int getWaveNumber() {
		return waveNumber;
	}

	public void setWaveNumber(int waveNumber) {
		this.waveNumber = waveNumber;
	}

	public int getAmountOfSmallCritters() {
		return amountOfSmallCritters;
	}

	public void setAmountOfSmallCritters(int amountOfSmallCritters) {
		this.amountOfSmallCritters = amountOfSmallCritters;
	}

	public int getAmountOfMediumCritters() {
		return amountOfMediumCritters;
	}

	public void setAmountOfMediumCritters(int amountOfMediumCritters) {
		this.amountOfMediumCritters = amountOfMediumCritters;
	}

	public int getAmountOfLargeCritters() {
		return amountOfLargeCritters;
	}

	public void setAmountOfLargeCritters(int amountOfLargeCritters) {
		this.amountOfLargeCritters = amountOfLargeCritters;
	}
}
