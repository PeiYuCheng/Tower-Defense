package domain;

import towerModels.*;

public class TowerFactory {

	private TowerFactory tower_factory;
	
	private TowerFactory() {
		
	}
	
	public TowerFactory getInstance() {
		if (tower_factory == null) {
			tower_factory = new TowerFactory();
		}
		return tower_factory;
	}
	
	public Tower getNewRegularTower() {
		return new RegularTower();
	}
	
	public Tower getNewAreaOfEffectTower() {
		return new AreaOfEffectTower();
	}
	
	public Tower getNewRadialTower() {
		return new RadialTower();
	}
	
}
