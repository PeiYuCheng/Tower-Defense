package buttons;

public class ToggleButton extends Button {

	private ButtonSelector button_selector;
	
	public ToggleButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		button_selector = ButtonSelector.getInstance();
	}
	
	@Override
	protected void OnClick() {
		if (isSelected()) {
			button_selector.deselectSelected();
			button_selector.setSelectedButton(null);
		}
		else {
			button_selector.deselectSelected();
			button_selector.setSelectedButton(this);
			setSelected(true);
		}
	}
	
}
