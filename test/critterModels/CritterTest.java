package critterModels;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import map.Cell;
import map.Map;

import org.junit.Test;

import domain.CritterWaveFactory;

public class CritterTest {

	// Class to be tested

	Critter testRegular = new RegularCritter(0, 0);
	int waveNumber = 10;
	int regularHealth = testRegular.getHealth();
	int towerDamage = 10;
	Map gameMap = Map.createGeneric();
	ArrayList<Cell> walkingPath = gameMap.path;

	CritterWaveFactory wave = CritterWaveFactory.getInstance();
	Queue<Critter> critterOnMap = wave.createWave(waveNumber);

	// Unit test to check if the critter's health actually goes down
	@Test
	public void healthGoesDownAfterCritterTakesDamage_test() {
		testRegular.damageCritter(towerDamage, false, false);
		assertNotEquals("Critter took damage", regularHealth,
				testRegular.getHealth());
	}

	@Test
	public void critterWaveReferencing_test() {
		Critter test1 = wave.dispatchOneCritter();
		Critter test2 = wave.dispatchOneCritter();
		assertNotSame(test1, test2);
	}

	@Test
	public void critterWalking_test() {
		while (testRegular.getPosition().getLocation() != gameMap.ExitCell.getPosition()) {
			testRegular.startWalking();
		}
		assertEquals(testRegular.getPosition(), gameMap.ExitCell.getPosition());
	}
}
