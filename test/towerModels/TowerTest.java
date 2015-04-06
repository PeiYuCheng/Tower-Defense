package towerModels;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Queue;

import map.Map;
import map.MapFactory;

import org.junit.Test;

import critterModels.Critter;
import domain.CritterWaveFactory;
import domain.TowerFactory;


public class TowerTest {	
	
	Map map = MapFactory.getUniqueInstance().createMap(2, 0, 0);
	CritterWaveFactory wave = CritterWaveFactory.getInstance();
	ArrayList<Critter> buffer = new ArrayList<Critter>();
	Tower tower = TowerFactory.getInstance().getNewRegularTower();
	
	@Test
	public void towerHitCritter_test() {
		wave.setupNextWave();
		Queue<Critter> critterOnMap = wave.createWave(map);
		while (!critterOnMap.isEmpty())
			buffer.add(critterOnMap.poll());
		int maxHealth = buffer.get(0).getHealth();
		tower.activateTower(true);
		tower.detectCritterTargets(buffer);
		tower.fire();

		assertNotEquals(buffer.get(0).getHealth(), maxHealth, 0);
	}
	
	@Test
	public void towerUpgrade_test() {
		assertTrue(tower.upgradeTower());
	}
}
