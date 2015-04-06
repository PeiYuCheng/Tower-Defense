package towerModels;

import java.util.ArrayList;

import javax.swing.JComponent;

import towerModels.Tower.AI_TYPE;
import critterModels.Critter;
import map.Cell;

public interface ITower {

	public int actualPower();
	public int actualRange();
	public int actualRateOfFire();
	public int getPower();
	public int getRange();
	public int getRateOfFire();
	public int getUpgradeLevel();
	public int getCost();
	public int getUpgradeCost();
	public int getRefundValue();
	public boolean isActive();
	public void placeTower(Cell cell, boolean activate);
	public boolean upgradeTower();
	public void activateTower(boolean activate);
	public void toggleTowerAI();
	public boolean fire();
	public Critter getCurrentTargetCritter();
	public void setPyroDamage(boolean pyro_damage);
	public boolean hasPyroDamage();
	public void setSlowDamage(boolean slow_damage);
	public boolean hasSlowDamage();
	public AI_TYPE getAttackMode();
	public int getXPosition();
	public int getYPosition();
	public void setAllCrittersOnMap(ArrayList<Critter> all_critters);
	public JComponent getComponent();
	public boolean isTowerSelected();
	public void setTowerSelected(boolean towerSelected);
	
}
