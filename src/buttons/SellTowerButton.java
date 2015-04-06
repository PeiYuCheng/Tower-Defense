package buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class SellTowerButton extends ClickButton implements Serializable {

	public SellTowerButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}

	@Override
	public void drawButton(Graphics g) {
		
		g.drawImage(images.sellIcon.getScaledInstance(size.width, size.height, 0), 0, 0, null);
		super.drawButton(g);
		
	}
		
	@Override
	protected void OnRelease() {
		button_selector.setSellTowerSelected(true);
		setSelected(false);
	}
	
	
}
