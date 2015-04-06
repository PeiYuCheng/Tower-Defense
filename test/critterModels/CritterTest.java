package critterModels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;
import java.util.Queue;

import map.Cell;
import map.Map;
import map.MapFactory;

import org.junit.Test;

import domain.CritterWaveFactory;

public class CritterTest {

	// Class to be tested

	Critter testRegular = new RegularCritter(0, 0);
	int waveNumber = 10;
	int regularHealth = testRegular.getHealth();
	int towerDamage = 10;
	Map gameMap = MapFactory.getUniqueInstance().createMap(1, 0, 0);
	ArrayList<Cell> walkingPath = gameMap.getPath();

	CritterWaveFactory wave = CritterWaveFactory.getInstance();
	Queue<Critter> critterOnMap = wave.createWave(gameMap);

	// Unit test to check if the critter's health actually goes down
	@Test
	public void healthGoesDownAfterCritterTakesDamage_test() {
		testRegular.damageCritter(towerDamage, false, false);
		assertNotEquals("Critter took damage", regularHealth,
				testRegular.getHealth());
	}

	@Test
	public void critterWaveReferencing_test() {
		for (int i = 0; i < waveNumber; i++) {
			wave.setupNextWave();
		}
		Critter test1 = wave.dispatchOneCritter();
		Critter test2 = wave.dispatchOneCritter();
		assertNotSame(test1, test2);
	}

	@Test
	public void critterWalking_test() {
		while (!testRegular.hasReachedExit()) {
			testRegular.startWalking();
		}
		assertEquals(testRegular.getCell_position(), gameMap.getExitCell().getPosition());
	}
}

