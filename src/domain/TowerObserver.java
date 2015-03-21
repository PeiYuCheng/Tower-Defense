package domain;
import java.util.Observable;
import java.util.Observer;

import towerModels.Tower;

public class TowerObserver implements Observer {

	private Tower observed_tower;
	
	public TowerObserver (Tower tower) {
		observed_tower = tower;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Tower stats:");
		System.out.println("Tower position: x=" + observed_tower.getXPosition() + " y=" + observed_tower.getYPosition());
		System.out.println("Level: " + observed_tower.getUpgradeLevel());
		System.out.println("Power: " + observed_tower.getPower());
		System.out.println("Range: " + observed_tower.getRange());
		System.out.println("Rate of fire: " + observed_tower.getRateOfFire());
		System.out.println("Sell value: " + observed_tower.getRefundValue());
		System.out.println("Attack mode: " + observed_tower.getAttackMode().toString());
		System.out.println("Has pyro damage: " + observed_tower.hasPyroDamage());
		System.out.println("Has slow damage: " + observed_tower.hasSlowDamage());
		System.out.println("Is active: " + observed_tower.isActive());
		System.out.println("---");
	}

}
