package domain;

import java.util.*;

import map.Map;
import critterModels.*;

/**
 * <h3>
 * The Critter Engine Class</h3>
 * <p>
 * This class encapsulates all the methods relating solely to the creation of
 * waves of critters that will be spawned on the map. This class takes advantage
 * of the singleton design pattern and the factory design pattern
 * </p>
 * 
 * @author Justin Asfour
 * @version 1.1
 */
public class CritterWaveFactory {

	private static CritterWaveFactory critter_factory;

	public static final int FINAL_WAVE = 10;
	private Queue<Critter> group;
	private int waveNumber;
	private int amountOfSmallCritters;
	private int amountOfMediumCritters;
	private int amountOfLargeCritters;

	// Implementing the singleton pattern as well
	private CritterWaveFactory() {
		waveNumber = 0;
	}

	public static CritterWaveFactory getInstance() {
		if (critter_factory == null)
			critter_factory = new CritterWaveFactory();
		return critter_factory;
	}

	/**
	 * The method creates a group of <b>Critter</b>. It implements the Singleton
	 * Design Pattern. The number of critters in each group changes based on
	 * each wave. As the player progresses through the game, the number of
	 * critters and the type that get dispatched changes instead of the strength
	 * of the critters. This is how difficulty is increased with each wave.
	 * Final wave is against <b>BossCritter</b>.
	 * 
	 * @param map
	 *            This parameter will give the critters an idea of where they
	 *            are allowing them to move along the path
	 */
	public Queue<Critter> createWave(Map map) {
		group = new LinkedList<Critter>();
		
		Critter regularCritter;
		Critter mediumCritter;
		Critter largeCritter;
		Critter bossCritter;

		if (waveNumber == FINAL_WAVE) {
			bossCritter = new BossCritter(0, 0);
			group.add(bossCritter);
		} else {
			for (int i = 0; i < amountOfSmallCritters; i++) {
				regularCritter = new RegularCritter(0, 0);
				group.add(regularCritter);
			}
			for (int i = 0; i < amountOfMediumCritters; i++) {
				mediumCritter = new MediumCritter(0, 0);
				group.add(mediumCritter);
			}
			for (int i = 0; i < amountOfLargeCritters; i++) {
				largeCritter = new LargeCritter(0, 0);
				group.add(largeCritter);
			}
		}

		for (Critter critter : group) {
			critter.setMapKnownToCritters(map);
		}

		// randomize the order of critters
		// randomize the way critters are stored in the list
		// each wave is different that way
		Collections.shuffle((List<?>) group, new Random(System.nanoTime()));

		return group;
	}

	/**
	 * This method is used to simply restart the wave back to zero
	 */
	public void restartWaves() {
		waveNumber = 0;
		amountOfSmallCritters = 0;
		amountOfMediumCritters = 0;
		amountOfLargeCritters = 0;
	}

	/**
	 * Sets the amount of <b>RegularCritter</b> that will spawn in the wave
	 * 
	 * @param waveNumber
	 * @return number of Regular Critters that will be in the wave
	 */
	public int countAmountOfSmallCritters(int waveNumber) {

		if (waveNumber <= FINAL_WAVE / 3) {
			return waveNumber * 2;
		} else if (waveNumber <= (FINAL_WAVE * 2) / 3) {
			return (((FINAL_WAVE * 4) / 3) - (waveNumber * 2));
		} else if (waveNumber < FINAL_WAVE) {
			return 0;
		} else {
			return 0;
		}

	}

	/**
	 * Sets the amount of <b>MediumCritter</b> that will spawn in the wave
	 * 
	 * @param waveNumber
	 * @return number of Medium Critters that will be in the wave
	 */
	public int countAmountOfMediumCritters(int waveNumber) {

		if (waveNumber <= FINAL_WAVE / 3) {
			return 0;
		} else if (waveNumber <= (FINAL_WAVE * 2) / 3) {
			return (waveNumber * 2 - (FINAL_WAVE * 2) / 3);
		} else if (waveNumber < FINAL_WAVE) {
			return ((FINAL_WAVE * 4) / 3) - waveNumber;
		} else {
			return 0;
		}

	}

	/**
	 * Sets the amount of <b>LargeCritter</b> that will spawn in the wave
	 * 
	 * @param waveNumber
	 * @return number of Large Critters that will be in the wave
	 */
	public int countAmountOfLargeCritters(int waveNumber) {

		if (waveNumber <= FINAL_WAVE / 3) {
			return 0;
		} else if (waveNumber <= (FINAL_WAVE * 2) / 3) {
			return 0;
		} else if (waveNumber < FINAL_WAVE) {
			return (waveNumber - (FINAL_WAVE * 2) / 3) * 2;
		} else {
			return 0;
		}

	}

	/**
	 * This method sets up the next wave of critters
	 */
	public void setupNextWave() {
		waveNumber++;
		amountOfSmallCritters = countAmountOfSmallCritters(waveNumber);
		amountOfMediumCritters = countAmountOfMediumCritters(waveNumber);
		amountOfLargeCritters = countAmountOfLargeCritters(waveNumber);
	}

	/**
	 * This method is a helper method to allow one critter to spawn at a time
	 * 
	 * @return the critter at the head of the queue
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
