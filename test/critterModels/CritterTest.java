package critterModels;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.CritterFactory;

public class CritterTest {

	// Class to be tested
	Critter testRegular = new RegularCritter(0, 0);
	CritterFactory wave = new CritterFactory(10);
	int regularHealth = testRegular.getHealth();
	int towerDamage = 10;

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

}
