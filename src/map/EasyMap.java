package map;

import java.io.Serializable;
import java.util.ArrayList;

public class EasyMap extends Map implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EasyMap(int w, int h){
		Width = w;
		Height = h;
		Grid = new Cell[Width][Height];
		path = new ArrayList<Cell>();
		
		createGeneric();
	}
	
	public void createGeneric() {

				//FixedMap MyMap = new FixedMap(mapWidth, mapHeight);
				Cell aCell;
				
				//set every cell to SceneryCell first
				for(int i=0; i<Width; i++){
					for(int j=0; j<Height; j++){
						aCell = new SceneryCell(i,j, Width, Height);
						this.addGridCell(aCell);
					}
				}
				
				// user set the path
				// set the selected cells to PathCell
				// set the EntryCell chosen by user to EntryCell
				aCell = new PathCell(0,5, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				this.setEntryCell(aCell);
				
				aCell = new PathCell(1,5, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,5, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);

				//
				aCell = new PathCell(2,6, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,7, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,8, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,9, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,10, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(3,10, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(4,10, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,10, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,9, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,8, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,7, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,6, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,5, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,4, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(6,4, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(7,4, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,4, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,5, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,6, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,7, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,8, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,9, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,10, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(9,10, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(10,10, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,10, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,9, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,8, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,7, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,6, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,5, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,4, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);

				aCell = new PathCell(12,4, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,4, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,5, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,6, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,7, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,8, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(14,8, Width, Height);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				// set this ExitCell chosen by user to ExitCell
				this.setExitCell(aCell);
				
				sortPathCells();
	}
}