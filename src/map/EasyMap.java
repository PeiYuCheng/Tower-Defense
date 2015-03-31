package map;

import java.util.ArrayList;

import javax.swing.JComponent;

public class EasyMap extends Map{
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
						aCell = new SceneryCell(i,j);
						this.addGridCell(aCell);
					}
				}
				
				// user set the path
				// set the selected cells to PathCell
				// set the EntryCell chosen by user to EntryCell
				aCell = new PathCell(0,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				this.setEntryCell(aCell);
				
				aCell = new PathCell(1,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);

				//
				aCell = new PathCell(2,6);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,7);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,8);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,9);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(2,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(3,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(4,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,9);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,8);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,7);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,6);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(5,4);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(6,4);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(7,4);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,4);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,6);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,7);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,8);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,9);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(8,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(9,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(10,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,10);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,9);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,8);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,7);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,6);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(11,4);
				this.addGridCell(aCell);
				this.addPathCell(aCell);

				aCell = new PathCell(12,4);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,4);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,6);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,7);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(13,8);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				
				aCell = new PathCell(14,8);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				// set this ExitCell chosen by user to ExitCell
				this.setExitCell(aCell);
				
				sortPathCells();
	}
}