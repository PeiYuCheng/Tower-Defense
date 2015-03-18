package critter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

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
public class CritterFactory {

	private static final int FINAL_WAVE = 50;
	private Queue<Critter> group;

	/**
	 * The method creates a group of <b>Critter</b>. The number of critters in
	 * each group changes based on each wave. As the player progresses through
	 * the game, the number of critters and the type that get dispatched changes
	 * instead of the strength of the critters. This is how difficulty is
	 * increased with each wave. Final wave is against <b>Boss</b>.
	 * 
	 * @param waveNumber This parameter will change whenever the Player finishes a wave
	 */
	public CritterFactory(int waveNumber) {
		group = new LinkedList<Critter>();
		// TODO: add real start position for boss and regular
		Critter regularCritter = new Regular(0, 0);
		Critter bossCritter = new Boss(0, 0);
		Critter mediumCritter = new Medium(0, 0);
		Critter largeCritter = new Large(0,0);

		switch (waveNumber) {
		case 1:case 2:case 3:case 4:case 5:case 6:case 7:case 8:case 9:case 10:
			for (int i = 0; i < waveNumber * 2; i++) {
				group.add(regularCritter);
			}
			break;
		case 11:case 12:case 13:case 14:case 15:case 16:case 17:case 18:case 19:case 20:
			for (int i = 0; i < waveNumber * 2; i++) {
				group.add(regularCritter);
			}
			for (int i = 0; i < waveNumber - 10; i++) {
				group.add(mediumCritter);
			}
			break;
		case 21:case 22:case 23:case 24:case 25:case 26:case 27:case 28:case 29:case 30:
			for (int i = 0; i < waveNumber * 2; i++) {
				group.add(regularCritter);
			}
			for (int i = 0; i < waveNumber - 15; i++) {
				group.add(mediumCritter);
			}
			for (int i = 0; i < waveNumber - 20; i++) {
				group.add(largeCritter);
			}
			break;
		case 31:case 32:case 33:case 34:case 35:case 36:case 37:case 38:case 39:case 40:
			for (int i = 0; i < waveNumber * 2; i++) {
				group.add(regularCritter);
			}
			for (int i = 0; i < waveNumber - 15; i++) {
				group.add(mediumCritter);
			}
			for (int i = 0; i < waveNumber - 25; i++) {
				group.add(largeCritter);
			}
			break;
		case 41:case 42:case 43:case 44:case 45:case 46:case 47:case 48:case 49:
			for (int i = 0; i < waveNumber; i++) {
				group.add(regularCritter);
			}
			for (int i = 0; i < waveNumber - 35; i++) {
				group.add(mediumCritter);
			}
			for (int i = 0; i < waveNumber - 20; i++) {
				group.add(largeCritter);
			}
			break;
		case 50:
			group.add(bossCritter);
			break;
		}
	}

	/**
	 * This method empties the CritterFactory. Its a helper method for the next
	 * one which is <b>dispatchAllCritter()</b>.
	 * 
	 * @return Critter at head of queue
	 */
	public Critter dispatchOneCritter() {
		return group.poll();
	}

	/**
	 * This method deals with placing the critter on the map every 10
	 * milliseconds and getting them ready to begin walking from start to
	 * finish.
	 */
	// TODO: Every 10 seconds add critters to map tiles array.
	public void dispatchAllCritters() {
		int i = 1;
		int wave = Collections.frequency(group, group.peek()) / 2;
		if (group.peek().getClass().getSimpleName().equals("Boss"))
			wave = FINAL_WAVE;

		while (!group.isEmpty()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// TODO: Do it here
			System.out
					.println("Critter "
							+ i
							+ " of wave "
							+ wave
							+ " is a "
							+ dispatchOneCritter().getClass().getSimpleName()
							+ " Critter and begins walking from start tile to end tile");
			i++;
		}
	}

	/**
	 * This method deals with the path finding algorithm to make sure that the
	 * Critters make it from the beginning to the end. We will be implementing
	 * the A* algorithm to make sure of this
	 */
	public void startWalking() {
		// TODO: implement path finding algorithm to get the critters to walk
		// along path
		dispatchAllCritters();
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
}
