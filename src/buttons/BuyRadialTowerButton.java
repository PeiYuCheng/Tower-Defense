package buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;

import towerModels.RadialTower;

public class BuyRadialTowerButton extends ToggleButton {

	public BuyRadialTowerButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}

	@Override
	public void drawButton(Graphics g) {
		
		selectColor(g, BUTTON_COLOR);
		g.fillRect(position.x, position.y, size.width, size.height);
		g.setColor(Color.white);
		g.drawRect(position.x, position.y, size.width, size.height);
		g.drawString("Radial", position.x, position.y + 45);
		g.drawString("$" + RadialTower.COST, position.x, position.y + 60);
		g.setColor(RadialTower.TOWER_COLOR);
		g.fillOval(position.x + 2, position.y + 2, size.width - 4, size.height - 4);
	}

}
