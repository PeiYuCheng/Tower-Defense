package domain;

import java.io.Serializable;

import towerModels.AreaOfEffectTower;
import towerModels.RadialTower;
import towerModels.RegularTower;
import towerModels.Tower;

public class TowerFactory implements Serializable{

	private static TowerFactory tower_factory;
	
	private TowerFactory() {
		
	}
	
	public static TowerFactory getInstance() {
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
