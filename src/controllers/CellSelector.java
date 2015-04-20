package controllers;

import java.io.Serializable;

import map.Cell;
import towerModels.Tower;

/**
 * This class handles all the selections of cells in the game.
 * @author Jeffrey
 *
 */
public class CellSelector implements Serializable{

	private static CellSelector cell_controller;
	private Cell selectedCell;
	
	private CellSelector() {
	}
	
	public static CellSelector getInstance() {
		if (cell_controller == null) {
			cell_controller = new CellSelector();
		}
		return cell_controller;
	}
	
	/**
	 * Deselects the currently selected cell as well as deselects the tower contained in the
	 * currently selected cell if applicable.
	 */
	public void deselectSelectedCell() {
		if (selectedCell != null) {
			selectedCell.setSelection(false);
			Tower tower;
			tower = this.selectedCell.getTowerInCell();
			if (tower != null) {
				tower.setTowerSelected(false);
			}
			selectedCell = null;
		}
	}

	/**
	 * Selects the currently selected cell as well as Selects the tower contained in the
	 * currently selected cell if applicable.
	 */
	public void setSelectedCell(Cell selectedCell) {
		Tower tower = null;
		if (this.selectedCell != null) {
			tower = this.selectedCell.getTowerInCell();
		}
		if (tower != null) {
			tower.setTowerSelected(false);
		}
		this.selectedCell = selectedCell;
		if (selectedCell != null) {
			tower = selectedCell.getTowerInCell();
			if (tower != null) {
				tower.setTowerSelected(true);
			}
		}
	}
	
	public Cell getSelectedCell() {
		return selectedCell;
	}
	
}
