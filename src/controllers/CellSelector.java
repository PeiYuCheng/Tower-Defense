package controllers;

import java.io.Serializable;

import map.Cell;
import towerModels.Tower;


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
