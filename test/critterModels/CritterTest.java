package critterModels;

import static org.junit.Assert.*;

import org.junit.Test;

public class CritterTest {
	
	//Class to be tested
	Critter testRegular = new RegularCritter(0, 0);
	int regularHealth = testRegular.getHealth();
	
	@Test
	public void healthGoesDownAfterCritterTakesDamage_test() {
		testRegular.damageCritter(10, false, false);
		assertNotEquals("Critter took damage", regularHealth, testRegular.getHealth());
	}
}
