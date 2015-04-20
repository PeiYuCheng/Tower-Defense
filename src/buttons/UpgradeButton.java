package buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * Special button that is used to upgrade a tower.
 * @author Jeffrey
 *
 */
public class UpgradeButton extends ClickButton implements Serializable {

	public UpgradeButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}
	
	@Override
	public void drawButton(Graphics g) {
		
		g.drawImage(images.upgradeIcon.getScaledInstance(size.width, size.height, 0), 0, 0, null);
		super.drawButton(g);
		
	}

	@Override
	protected void OnRelease() {
		button_selector.setUpgradeTowerSelected(true);
		setSelected(false);
	}
	

}
