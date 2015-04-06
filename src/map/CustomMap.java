package map;

import java.io.Serializable;
import java.util.ArrayList;

import controllers.CellSelector;

public class CustomMap extends Map implements Serializable {

	public CustomMap(int w, int h) {
		Width = w;
		Height = h;
		Grid = new Cell[Width][Height];
		path = new ArrayList<Cell>();

		createGeneric();
	}
	
	public Cell toggleCell(Cell cell) {
		if (cell instanceof SceneryCell) {
			Grid[cell.getX()][cell.getY()] = new PathCell(cell.getX(), cell.getY());
			Grid[cell.getX()][cell.getY()].customMapModeOn();
			return Grid[cell.getX()][cell.getY()];
		}
		else {
			Grid[cell.getX()][cell.getY()] = new SceneryCell(cell.getX(), cell.getY());
			return Grid[cell.getX()][cell.getY()];
		}
	}
	
	public void createGeneric() {

		Cell aCell = null;
		CellSelector cell_selector = CellSelector.getInstance();

		// set every cell to SceneryCell first
		for (int i = 0; i < Width; i++) {
			for (int j = 0; j < Height; j++) {
				aCell = new SceneryCell(i, j);
				this.addGridCell(aCell);
			}
		}

		if (path.isEmpty()) {
			if (aCell.isSelected()) {
				cell_selector.setSelectedCell(aCell);
				aCell.toggleSelection();
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				this.setEntryCell(aCell);
			}
		} else {
			if (aCell.isSelected()) {
				cell_selector.setSelectedCell(aCell);
				aCell.toggleSelection();
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				this.setEntryCell(aCell);
			}
		}

		sortPathCells();
	}

}
