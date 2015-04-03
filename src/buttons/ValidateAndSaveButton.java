package buttons;

import java.awt.Color;
import java.awt.Graphics;

public class ValidateAndSaveButton extends ClickButton {

	public ValidateAndSaveButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}
	
	@Override
	public void drawButton(Graphics g) {
		
		selectColor(g, BUTTON_COLOR);
		g.fillRect(position.x, position.y, size.width, size.height);
		g.setColor(Color.white);
		g.drawRect(position.x, position.y, size.width, size.height);
		g.drawString("Validate and Save", position.x, position.y + 45);
//		g.drawString("$" + AreaOfEffectTower.COST, position.x, position.y + 60);
		
	}

	@Override
	protected void OnRelease() {
		button_selector.setValidateAndSave(true);
		setSelected(false);
	}

}
