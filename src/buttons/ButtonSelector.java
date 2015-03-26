package buttons;

import map.Cell;
import map.CellSelector;

public class ButtonSelector {

	private static ButtonSelector cell_button;
	private Button selectedButton;
	
	private ButtonSelector() {
	}
	
	public static ButtonSelector getInstance() {
		if (cell_button == null) {
			cell_button = new ButtonSelector();
		}
		return cell_button;
	}
	
	public void deselectSelected() {
		if (selectedButton != null) {
			selectedButton.setSelected(false);
		}
		CellSelector.getInstance().deselectSelectedCell();
	}

	public void setSelectedButton(Button selectedButton) {
		this.selectedButton = selectedButton;
	}
	
}