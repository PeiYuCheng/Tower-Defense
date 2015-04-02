package towerModels;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Queue;

import map.Map;
import map.MapFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import critterModels.Critter;
import domain.CritterWaveFactory;
import domain.TowerFactory;


public class TowerTest {
	
	Tower tower;
	Map map;
	ArrayList<Critter> wave;
			
	@Before
	public void setUp() throws Exception {
		tower = TowerFactory.getInstance().getNewRegularTower();
		map = MapFactory.getUniqueInstance().createMap(1, 0, 0);
		wave = (ArrayList<Critter>) CritterWaveFactory.getInstance().createWave(2, map);
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void towerHitCritter() {
		while(!wave.peek().isDead()) {
			tower.activateTower(true);
			tower.detectCritterTargets(buffer);
			tower.fire();
		}
		assertTrue(wave.peek().isDead());
	}
}
