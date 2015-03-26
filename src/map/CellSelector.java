package map;

public class CellSelector {

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
		}
	}

	public void setSelectedCell(Cell selectedCell) {
		this.selectedCell = selectedCell;
	}
	
}
