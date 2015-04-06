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
		
//		selectColor(g, REGULAR_BUTTON_COLOR);
//		g.fillRect(0, 0, size.width, size.height);
//		g.setColor(Color.white);
//		g.drawRect(0, 0, size.width, size.height);
//		g.drawString("Radial", 0, 0 + 45);
//		g.drawString("$" + RadialTower.COST, 0, 60);
//		g.setColor(RadialTower.TOWER_COLOR);
//		g.fillOval(2, 2, size.width - 4, size.height - 4);
	}
	
	@Override
	public Tower getNewTower() {
		return tower_factory.getNewRadialTower();
	}

}
