package towerModels;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Queue;

import map.Map;
import map.MapFactory;
import map.SceneryCell;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import towerModels.Tower.AI_TYPE;
import critterModels.Critter;
import critterModels.LargeCritter;
import critterModels.MediumCritter;
import critterModels.RegularCritter;
import domain.CritterWaveFactory;
import domain.TowerFactory;


public class TowerTest {
	
	Tower tower;
	Map map;
	ArrayList<Critter> wave;
			
	@Before
	public void setUp() throws Exception {
		tower = TowerFactory.getInstance().getNewRegularTower();
		tower.placeTower(new SceneryCell(1, 0, 8, 8), true);
		map = MapFactory.getUniqueInstance().createMap(1, 0, 0);
		wave = new ArrayList<>();
		wave.add(new RegularCritter(0,0));
		wave.add(new LargeCritter(0,0));
		wave.add(new MediumCritter(0,0));
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testTowerAIandDamaging() {
		
		int prehealth;
		int prehealth_2;
		tower.setAllCrittersOnMap(wave);
		
		tower.setAttackMode(AI_TYPE.FOLLOW);
		prehealth = wave.get(0).getHealth();
		tower.fire();
		assertNotEquals("Follow not working: " + prehealth, wave.get(0).getHealth(), prehealth);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wave.get(0).setCell_position(new Point(1,1));
		prehealth = wave.get(0).getHealth();
		tower.fire();
		assertNotEquals("Follow not working: " + prehealth, wave.get(0).getHealth(), prehealth);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wave.get(0).setCell_position(new Point(10,10));
		prehealth = wave.get(0).getHealth();
		prehealth_2 = wave.get(1).getHealth();
		tower.fire();
		assertEquals("Follow not working: " + prehealth, wave.get(0).getHealth(), prehealth);
		assertNotEquals("Follow not working: " + prehealth, wave.get(1).getHealth(), prehealth);
		wave.get(0).setCell_position(new Point(0,0));
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tower.setAttackMode(AI_TYPE.BACK);
		prehealth = wave.get(2).getHealth();
		tower.fire();
		assertNotEquals("Back targeting not working: " + prehealth, wave.get(2).getHealth(), prehealth);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tower.setAttackMode(AI_TYPE.FRONT);
		prehealth = wave.get(0).getHealth();
		tower.fire();
		assertNotEquals("Front targeting not working: " + prehealth, wave.get(0).getHealth(), prehealth);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tower.setAttackMode(AI_TYPE.WEAKEST);
		prehealth = wave.get(0).getHealth();
		tower.fire();
		assertNotEquals("Weakest targeting not working: " + prehealth, wave.get(0).getHealth(), prehealth);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tower.setAttackMode(AI_TYPE.STRONGEST);
		prehealth = wave.get(1).getHealth();
		tower.fire();
		assertNotEquals("Strongest targeting not working: " + prehealth, wave.get(1).getHealth(), prehealth);

	}
}
