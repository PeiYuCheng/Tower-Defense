package map;

import java.io.Serializable;


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
			selectedCell = null;
		}
	}

	public void setSelectedCell(Cell selectedCell) {
		this.selectedCell = selectedCell;
	}
	
	public Cell getSelectedCell() {
		return selectedCell;
	}
	
}
