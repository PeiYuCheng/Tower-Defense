package buttons;

import java.awt.Color;
import java.awt.Graphics;

public class ValidateAndSaveButton extends ClickButton {

	public ValidateAndSaveButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}
	
	@Override
	public void drawButton(Graphics g) {
		
		g.drawImage(images.validateIcon.getScaledInstance(size.width, size.height, 0), 0, 0, null);
		super.drawButton(g);
		
	}

	@Override
	public void drawButtonBackground(Graphics g) {

	}
	
	@Override
	protected void OnRelease() {
		button_selector.setValidateAndSave(true);
		setSelected(false);
	}

}
