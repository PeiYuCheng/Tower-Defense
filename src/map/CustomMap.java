package map;

import java.util.ArrayList;

import javax.swing.JComponent;

public class CustomMap extends Map{

	public CustomMap(int w, int h){
		Width = w;
		Height = h;
		Grid = new Cell[Width][Height];
		path = new ArrayList<Cell>();
		
		createGeneric();
	}
	
	public void toggleCell(Cell cell) {
		if (cell instanceof SceneryCell) {
			Grid[cell.getX()][cell.getY()] = new PathCell(cell.getX(), cell.getY());
		}
		else {
			Grid[cell.getX()][cell.getY()] = new SceneryCell(cell.getX(), cell.getY());
		}
	}
	
	public void createGeneric() {

				Cell aCell;
				
				//set every cell to SceneryCell first
				for(int i=0; i<Width; i++){
					for(int j=0; j<Height; j++){
						aCell = new SceneryCell(i,j);
						this.addGridCell(aCell);
					}
				}
/*				
				aCell = new PathCell(0,5);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				this.setEntryCell(aCell);
				
				
				aCell = new PathCell(7,2);
				this.addGridCell(aCell);
				this.addPathCell(aCell);
				// set this ExitCell chosen by user to ExitCell
				this.setExitCell(aCell);
*/	
				sortPathCells();			
	}

}