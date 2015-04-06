package map;

import java.io.Serializable;
import java.util.ArrayList;

public class HardMap extends Map implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HardMap(int w, int h) {
		Width = w;
		Height = h;
		Grid = new Cell[Width][Height];
		path = new ArrayList<Cell>();

		createGeneric();
	}

	public void createGeneric() {

		// FixedMap MyMap = new FixedMap(mapWidth, mapHeight);
		Cell aCell;

		// set every cell to SceneryCell first
		for (int i = 0; i < Width; i++) {
			for (int j = 0; j < Height; j++) {
				aCell = new SceneryCell(i, j);
				this.addGridCell(aCell);
			}
		}

		// set the selected cells to PathCell
		// set the EntryCell chosen by user to EntryCell
		aCell = new PathCell(0, 6);
		this.addGridCell(aCell);
		this.addPathCell(aCell);
		this.setEntryCell(aCell);

		aCell = new PathCell(1, 6);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(2, 6);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(3, 6);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(3, 7);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(3, 8);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(4, 8);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(5, 8);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(6, 8);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(7, 8);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(7, 7);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(7, 6);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(8, 6);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(9, 6);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(10, 6);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(11, 6);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(11, 7);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(11, 8);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(12, 8);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(13, 8);
		this.addGridCell(aCell);
		this.addPathCell(aCell);

		aCell = new PathCell(14, 8);
		this.addGridCell(aCell);
		this.addPathCell(aCell);
		// set this ExitCell chosen by user to ExitCell
		this.setExitCell(aCell);

		sortPathCells();
	}

}
