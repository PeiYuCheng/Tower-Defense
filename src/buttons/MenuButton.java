package buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * Special button that is used to go to the main menu.
 * @author Jeffrey
 *
 */
public class MenuButton extends ClickButton implements Serializable {

	public MenuButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawButton(Graphics g) {
		
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		int stringWidth = g.getFontMetrics().stringWidth("much menu");
		g.drawString("much menu", (size.width - stringWidth) / 2, (size.height-1)/2 + 6);
		super.drawButton(g);
		
	}
	
	@Override
	protected void OnRelease() {
		button_selector.setRestartGame(true);
		setSelected(false);
	}
}
