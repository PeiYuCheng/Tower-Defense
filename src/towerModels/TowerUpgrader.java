package towerModels;

public class TowerUpgrader extends TowerDecorator {

	public TowerUpgrader(ITower tower) {
		super(tower);
		tower.upgradeTower();
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
	
}
