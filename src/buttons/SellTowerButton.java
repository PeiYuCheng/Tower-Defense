package buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;

import towerModels.AreaOfEffectTower;
import towerModels.Tower;

public class SellTowerButton extends ClickButton {

	public SellTowerButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}

	@Override
	public void drawButton(Graphics g) {
		
		selectColor(g, BUTTON_COLOR);
		g.fillRect(position.x, position.y, size.width, size.height);
		g.setColor(Color.white);
		g.drawRect(position.x, position.y, size.width, size.height);
		g.drawString("Sell Tower", position.x, position.y + 45);
		g.setColor(Color.GRAY);
		g.fillOval(position.x + 2, position.y + 2, size.width - 4, size.height - 4);
	}
		
	@Override
	protected void OnRelease() {
		button_selector.setSellTowerSelected(true);
		setSelected(false);
	}
	
	
}
