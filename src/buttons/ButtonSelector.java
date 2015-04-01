package buttons;

import java.io.Serializable;

import map.CellSelector;

public class ButtonSelector implements Serializable{

	private static ButtonSelector cell_button;
	private Button selectedButton;
	private boolean sellTowerSelected;
	private boolean upgradeTowerSelected;
	private boolean start_wave;
	private boolean start_game;
	private int map_type;
	
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
			selectedButton = null;
		}
		CellSelector.getInstance().deselectSelectedCell();
	}

	public void setSelectedButton(Button selectedButton) {
		this.selectedButton = selectedButton;
	}
	
	public Button getSelectedButton() {
		return selectedButton;
	}
	
	public void setSellTowerSelected(boolean sellTower) {
		sellTowerSelected = sellTower;
	}
	
	public boolean isSellTowerSelected() {
		return sellTowerSelected;
	}
	
	public void setUpgradeTowerSelected(boolean upgradeTower) {
		upgradeTowerSelected = upgradeTower;
	}
	
	public boolean isUpgradeTowerSelected() {
		return upgradeTowerSelected;
	}

	public void setStartWave(boolean start_wave) {
		this.start_wave = start_wave;
	}
	
	public boolean isStartWave() {
		return start_wave;
	}

	public int getMapType() {
		return map_type;
	}

	public void setMapType(int map_type) {
		this.map_type = map_type;
	}

	public boolean isStartGame() {
		return start_game;
	}

	public void setStartGame(boolean start_game) {
		this.start_game = start_game;
	}
	
}
