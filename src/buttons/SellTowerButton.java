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
		
//		selectColor(g, REGULAR_BUTTON_COLOR);
//		g.fillRect(position.x, position.y, size.width, size.height);
//		g.setColor(Color.white);
//		g.drawRect(position.x, position.y, size.width, size.height);
//		g.drawString("Sell Tower", position.x, position.y + 45);
//		g.setColor(Color.GRAY);
//		g.fillOval(position.x + 2, position.y + 2, size.width - 4, size.height - 4);
	}
		
	@Override
	protected void OnRelease() {
		button_selector.setSellTowerSelected(true);
		setSelected(false);
	}
	
	
}
