package controllers;

import java.awt.Color;
import java.awt.Graphics;

import towerModels.RegularTower;

public class BuyRegularTowerButton extends Button {


	
	public BuyRegularTowerButton(int posX, int posY, int width, int height,
			int spacing) {
		super(posX, posY, width, height, spacing);
	}

	@Override
	public void drawCell(Graphics g) {
		
		if (isSelected()) {
			g.setColor(Color.magenta);
		}
		else{
			g.setColor(BUTTON_COLOR);
		}
		g.fillRect(x_position, y_position, button_width, button_height);
		g.setColor(Color.white);
		g.drawRect(x_position, y_position, button_width, button_height);
		g.drawString("Regular", x_position, y_position + 45);
		g.drawString("$" + RegularTower.COST, x_position, y_position + 60);
		g.setColor(RegularTower.TOWER_COLOR);
		g.fillOval(x_position + 2, y_position + 2, button_width - 4, button_height - 4);
	}

}
