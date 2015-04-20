package controllers;

import java.io.Serializable;

import buttons.CustomButton;

/**
 * This class handles all the selections of buttons in the game.
 * @author Jeffrey
 *
 */
public class ButtonSelector implements Serializable{
	
	private static ButtonSelector button_selector;
	private CustomButton selectedButton;
	private int map_type;
	
	// Each variable name indicates the operation that has been selected if true.
	private boolean sellTowerSelected;
	private boolean upgradeTowerSelected;
	private boolean start_wave;
	private boolean start_game;
	private boolean validate_and_save;
	private boolean toggleAttackMode;
	private boolean showNextWaveInfo;
	private boolean restartGame;
	private boolean saveGame;
	
	private ButtonSelector() {
	}
	
	public static ButtonSelector getInstance() {
		if (button_selector == null) {
			button_selector = new ButtonSelector();
		}
		return button_selector;
	}
	
	/**
	 * Deselects the currently selected button and deselects the currently selected cell.
	 */
	public void deselectSelected() {
		if (selectedButton != null) {
			selectedButton.setSelected(false);
			selectedButton = null;
		}
		CellSelector.getInstance().deselectSelectedCell();
	}

	public void setSelectedButton(CustomButton selectedButton) {
		this.selectedButton = selectedButton;
	}
	
	public CustomButton getSelectedButton() {
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

	public boolean isValidateAndSave() {
		return validate_and_save;
	}

	public void setValidateAndSave(boolean validate_and_save) {
		this.validate_and_save = validate_and_save;
	}

	public boolean isToggleAttackMode() {
		return toggleAttackMode;
	}

	public void setToggleAttackMode(boolean toggleAttackMode) {
		this.toggleAttackMode = toggleAttackMode;
	}

	public boolean isShowNextWaveInfo() {
		return showNextWaveInfo;
	}

	public void setShowNextWaveInfo(boolean showNextWaveInfo) {
		this.showNextWaveInfo = showNextWaveInfo;
	}

	public boolean isRestartGame() {
		return restartGame;
	}

	public void setRestartGame(boolean restartGame) {
		this.restartGame = restartGame;
	}

	public boolean isSaveGame() {
		return saveGame;
	}

	public void setSaveGame(boolean saveGame) {
		this.saveGame = saveGame;
	}
	
}
