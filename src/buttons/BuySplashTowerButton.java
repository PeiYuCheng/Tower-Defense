package buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import towerModels.AreaOfEffectTower;
import towerModels.Tower;

/**
 * Special button that is used to buy a splash type tower.
 * @author Jeffrey
 *
 */
public class BuySplashTowerButton extends ToggleButton implements Serializable {


	
	public BuySplashTowerButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}

	@Override
	public void drawButton(Graphics g) {
		
		g.drawImage(images.splashTowerLevelOne.getScaledInstance(size.width, size.height, 0), 0, 0, null);
		super.drawButton(g);
		
	}

	@Override
	public Tower getNewTower() {
		return tower_factory.getNewAreaOfEffectTower();
	}

	
}
