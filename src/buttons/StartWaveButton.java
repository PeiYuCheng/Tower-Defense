package buttons;

import java.awt.Color;
import java.awt.Graphics;

public class StartWaveButton extends ClickButton {

	public StartWaveButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void drawButton(Graphics g) {
		
		selectColor(g, BUTTON_COLOR);
		g.fillRect(position.x, position.y, size.width, size.height);
		g.setColor(Color.white);
		g.drawRect(position.x, position.y, size.width, size.height);
		g.drawString("Start Wave", position.x, position.y + 45);
	}
	
	@Override
	protected void OnRelease() {
		button_selector.setStartWave(true);
		setSelected(false);
	}

}
