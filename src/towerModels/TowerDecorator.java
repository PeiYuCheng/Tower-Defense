package towerModels;

import java.util.ArrayList;

import javax.swing.JComponent;

import towerModels.Tower.AI_TYPE;
import critterModels.Critter;
import map.Cell;

public abstract class TowerDecorator implements ITower {

	protected ITower tower;
	
	public TowerDecorator(ITower tower) {
		this.tower = tower;
	}

	@Override
	public int actualPower() {
		return tower.actualPower();
	}

	@Override
	public int actualRange() {
		return tower.actualRange();
	}

	@Override
	public int actualRateOfFire() {
		return tower.actualRateOfFire();
	}

	@Override
	public int getPower() {
		return tower.getPower();
	}

	@Override
	public int getRange() {
		return tower.getRange();
	}

	@Override
	public int getRateOfFire() {
		return tower.getRateOfFire();
	}

	@Override
	public int getUpgradeLevel() {
		return tower.getUpgradeLevel();
	}

	@Override
	public int getCost() {
		return tower.getCost();
	}

	@Override
	public int getUpgradeCost() {
		return tower.getUpgradeCost();
	}

	@Override
	public int getRefundValue() {
		return tower.getRefundValue();
	}

	@Override
	public boolean isActive() {
		return tower.isActive();
	}

	@Override
	public void placeTower(Cell cell, boolean activate) {
		tower.placeTower(cell, activate);
		
	}

	@Override
	public void activateTower(boolean activate) {
		tower.activateTower(activate);
	}

	@Override
	public void toggleTowerAI() {
		tower.toggleTowerAI();
	}

	@Override
	public boolean fire() {
		return tower.fire();
	}

	@Override
	public boolean upgradeTower() {
		// TODO Auto-generated method stub
		return tower.upgradeTower();
	}

	@Override
	public Critter getCurrentTargetCritter() {
		// TODO Auto-generated method stub
		return tower.getCurrentTargetCritter();
	}

	@Override
	public void setPyroDamage(boolean pyro_damage) {
		tower.setPyroDamage(pyro_damage);
		
	}

	@Override
	public boolean hasPyroDamage() {
		// TODO Auto-generated method stub
		return tower.hasPyroDamage();
	}

	@Override
	public void setSlowDamage(boolean slow_damage) {
		tower.setSlowDamage(slow_damage);
		
	}

	@Override
	public boolean hasSlowDamage() {
		// TODO Auto-generated method stub
		return tower.hasSlowDamage();
	}

	@Override
	public AI_TYPE getAttackMode() {
		// TODO Auto-generated method stub
		return tower.getAttackMode();
	}

	@Override
	public int getXPosition() {
		// TODO Auto-generated method stub
		return tower.getXPosition();
	}

	@Override
	public int getYPosition() {
		// TODO Auto-generated method stub
		return tower.getYPosition();
	}

	@Override
	public void setAllCrittersOnMap(ArrayList<Critter> all_critters) {
		tower.setAllCrittersOnMap(all_critters);
		
	}

	@Override
	public JComponent getComponent() {
		// TODO Auto-generated method stub
		return tower.getComponent();
	}

	@Override
	public boolean isTowerSelected() {
		// TODO Auto-generated method stub
		return tower.isTowerSelected();
	}

	@Override
	public void setTowerSelected(boolean towerSelected) {
		tower.setTowerSelected(towerSelected);
		
	}

}
