package buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import towerModels.RadialTower;
import towerModels.Tower;

public class BuyRadialTowerButton extends ToggleButton implements Serializable {

	public BuyRadialTowerButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}

	@Override
	public void drawButton(Graphics g) {
		
		g.drawImage(images.radialTowerLevelOne.getScaledInstance(size.width, size.height, 0), 0, 0, null);
		super.drawButton(g);
		
	}
	
	@Override
	public Tower getNewTower() {
		return tower_factory.getNewRadialTower();
	}

}
