package buttons;

import java.awt.Color;
import java.awt.Graphics;

import towerModels.AreaOfEffectTower;

public class UpgradeButton extends ClickButton {

	public UpgradeButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}
	
	@Override
	public void drawButton(Graphics g) {
		
		selectColor(g, BUTTON_COLOR);
		g.fillRect(position.x, position.y, size.width, size.height);
		g.setColor(Color.white);
		g.drawRect(position.x, position.y, size.width, size.height);
		g.drawString("Upgrade", position.x, position.y + 45);
//		g.drawString("$" + AreaOfEffectTower.COST, position.x, position.y + 60);
		
	}

	@Override
	protected void OnRelease() {
		button_selector.setUpgradeTowerSelected(true);
		setSelected(false);
	}
	

}
