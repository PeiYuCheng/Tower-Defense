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
		
	Tower regularTower;
	Tower splashTower;
	Tower radialTower;
	Map map;
	ArrayList<Critter> wave;
			
	@Before
	public void setUp() throws Exception {
		regularTower = TowerFactory.getInstance().getNewRegularTower();
		splashTower = TowerFactory.getInstance().getNewAreaOfEffectTower();
		radialTower = TowerFactory.getInstance().getNewRadialTower();
		regularTower.placeTower(new SceneryCell(1, 0, 8, 8), true);
		splashTower.placeTower(new SceneryCell(1, 1, 8, 8), true);
		radialTower.placeTower(new SceneryCell(1, 1, 8, 8), true);
		map = MapFactory.getUniqueInstance().createMap(1, 0, 0);
		wave = new ArrayList<>();
		wave.add(new RegularCritter(0,0));
		wave.add(new LargeCritter(0,0));
		wave.add(new MediumCritter(0,0));
	}
	
	// Tests the regular tower, different tower AI's and if critters get damaged after firing
	// within range
	@Test
	public void testTowerAIandDamaging() {
		
		int prehealth;
		int prehealth_2;
		regularTower.setAllCrittersOnMap(wave);
		
		regularTower.setAttackMode(AI_TYPE.FOLLOW);
		prehealth = wave.get(0).getHealth();
		regularTower.fire();
		assertNotEquals("Follow not working: " + prehealth, wave.get(0).getHealth(), prehealth);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wave.get(0).setCell_position(new Point(1,1));
		prehealth = wave.get(0).getHealth();
		regularTower.fire();
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
		regularTower.fire();
		assertEquals("Follow not working: " + prehealth, wave.get(0).getHealth(), prehealth);
		assertNotEquals("Follow not working: " + prehealth, wave.get(1).getHealth(), prehealth);
		wave.get(0).setCell_position(new Point(0,0));
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		regularTower.setAttackMode(AI_TYPE.BACK);
		prehealth = wave.get(2).getHealth();
		regularTower.fire();
		assertNotEquals("Back targeting not working: " + prehealth, wave.get(2).getHealth(), prehealth);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		regularTower.setAttackMode(AI_TYPE.FRONT);
		prehealth = wave.get(0).getHealth();
		regularTower.fire();
		assertNotEquals("Front targeting not working: " + prehealth, wave.get(0).getHealth(), prehealth);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		regularTower.setAttackMode(AI_TYPE.WEAKEST);
		prehealth = wave.get(0).getHealth();
		regularTower.fire();
		assertNotEquals("Weakest targeting not working: " + prehealth, wave.get(0).getHealth(), prehealth);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		regularTower.setAttackMode(AI_TYPE.STRONGEST);
		prehealth = wave.get(1).getHealth();
		regularTower.fire();
		assertNotEquals("Strongest targeting not working: " + prehealth, wave.get(1).getHealth(), prehealth);

	}
	
	@Test
	public void testSplashTower() {
		int prehealth[] = new int[3];
		int posthealth[] = new int[3];
		wave.get(0).setCell_position(new Point(0,0));
		wave.get(1).setCell_position(new Point(1,0));
		wave.get(2).setCell_position(new Point(2,0));
		splashTower.setAllCrittersOnMap(wave);
		
		for (int i = 0; i < prehealth.length; i++) {
			prehealth[i] = wave.get(i).getHealth();
		}
		
		splashTower.fire();
		
		for (int i = 0; i < posthealth.length; i++) {
			posthealth[i] = wave.get(i).getHealth();
		}
		
		assertNotEquals("All in range not working.", prehealth, posthealth);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		wave.get(1).setCell_position(new Point(1,3));
		
		for (int i = 0; i < prehealth.length; i++) {
			prehealth[i] = wave.get(i).getHealth();
		}
		
		splashTower.fire();
		
		for (int i = 0; i < posthealth.length; i++) {
			posthealth[i] = wave.get(i).getHealth();
		}
		
		assertNotEquals("In range not damaged.", prehealth[0], posthealth[0]);
		assertNotEquals("Out of range damaged.", prehealth[2], posthealth[2]);
		assertEquals("In range not damaged.", prehealth[1], posthealth[1]);
	}
	
	@Test
	public void testRadialTower() {
		int prehealth[] = new int[3];
		int posthealth[] = new int[3];
		wave.get(0).setCell_position(new Point(1,2));
		wave.get(1).setCell_position(new Point(2,1));
		wave.get(2).setCell_position(new Point(2,2));
		radialTower.setAllCrittersOnMap(wave);
		
		for (int i = 0; i < prehealth.length; i++) {
			prehealth[i] = wave.get(i).getHealth();
		}
		
		radialTower.fire();
		
		for (int i = 0; i < posthealth.length; i++) {
			posthealth[i] = wave.get(i).getHealth();
		}
		
		assertNotEquals("In range not damaged.", prehealth[0], posthealth[0]);
		assertNotEquals("Out of range damaged.", prehealth[1], posthealth[1]);
		assertEquals("In range not damaged.", prehealth[2], posthealth[2]);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
}
