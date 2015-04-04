package buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

import towerModels.RadialTower;
import controllers.GameController;

public class StartEasyGameButton extends ClickButton implements Serializable{

	String cardLayout = GameController.CARD_MAIN_GAME;
	
	public StartEasyGameButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}

	@Override
	protected void OnRelease() {
		button_selector.setMapType(GameController.EASY_MAP);
		button_selector.setStartGame(true);
		setSelected(false);
	}
	
	@Override
	public void drawButton(Graphics g) {
		
		int fontsize = 24;
		
		selectColor(g, BUTTON_COLOR);
		g.fillRect(position.x, position.y, size.width, size.height);
		g.setColor(Color.white);
		g.drawRect(position.x, position.y, size.width, size.height);
		
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, fontsize));
		g.drawString("Easy Game", position.x + 16, position.y + size.height/2 + 8);

	}
	
}