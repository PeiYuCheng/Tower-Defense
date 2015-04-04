package buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class MenuButton extends ClickButton implements Serializable {

	public MenuButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawButton(Graphics g) {
		
		selectColor(g, BUTTON_COLOR);
		g.fillRect(position.x, position.y, size.width, size.height);
		g.setColor(Color.white);
		g.drawRect(position.x, position.y, size.width, size.height);
		g.drawString("Start Game", position.x, position.y);
	}
	
	@Override
	protected void OnRelease() {
//		button_selector.setStart_game(true);
		setSelected(false);
	}
}