package buttons;

import java.awt.Graphics;

public class AttackModeButton extends ClickButton {

	public AttackModeButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void drawButton(Graphics g) {
		
		g.drawImage(images.attackModeIcon.getScaledInstance(size.width, size.height, 0), 0, 0, null);
		super.drawButton(g);

	}
		
	@Override
	protected void OnRelease() {
		button_selector.setToggleAttackMode(true);
		setSelected(false);
	}
}
