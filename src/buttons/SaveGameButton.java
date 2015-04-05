package buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

public class SaveGameButton extends ClickButton implements Serializable {

	public SaveGameButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawButton(Graphics g) {
		
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		g.drawString("very save", 8, (size.height-1)/2 + 6);
		super.drawButton(g);
		
	}
	
	@Override
	protected void OnRelease() {
		button_selector.setSaveGame(true);
		setSelected(false);
	}
}
