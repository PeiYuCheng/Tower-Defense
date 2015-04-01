package buttons;

import java.io.Serializable;

public class ClickButton extends Button implements Serializable {

	public ClickButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}
	
	@Override
	protected void OnPress() {
		setSelected(true);
	}
	
	@Override
	protected void OnRelease() {
		//TODO Write action in here (subclasses)
		setSelected(false);
	}
	
	@Override
	protected void OnExit() {
		setHovered(false);
		setSelected(false);
	}

}
