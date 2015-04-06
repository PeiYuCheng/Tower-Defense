package buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

import controllers.GameController;

public class StartLoadedMapButton extends ClickButton implements Serializable{
String cardLayout = GameController.CARD_MAIN_GAME;
	
	public StartLoadedMapButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}

	@Override
	protected void OnRelease() {
		button_selector.setMapType(GameController.LOAD_MAP);
		button_selector.setStartGame(true);
		setSelected(false);
	}
	
	@Override
	public void drawButton(Graphics g) {
		
		int fontsize = 24;
		String message = "so load map";
		int stringWidth;
		
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, fontsize));
		stringWidth = g.getFontMetrics().stringWidth(message);
		g.drawString(message, (size.width - stringWidth) / 2, size.height/2 + 8);

	}
}
