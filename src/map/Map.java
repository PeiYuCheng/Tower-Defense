package map;

import java.util.ArrayList;

public class Map{
	
	private int Width;
	private int Height;
	public Cell[][] Grid; // 2 dimensional array
	public Cell EntryCell;
	public Cell ExitCell;
	public ArrayList<Cell> path;

	public Map(int w, int h){
		Width = w;
		Height = h;
		Grid = new Cell[Width][Height];
		path = new ArrayList<Cell>();
	}
	
	public void setEntryCell(Cell aCell){
		EntryCell = aCell;
	}

	public void setExitCell(Cell aCell){
		ExitCell = aCell;
	}
	
	public void addGridCell(Cell aCell){
		int i = aCell.getX();
		int j = aCell.getY();
		Grid[j][i] = aCell;
	}
	
	public void addPathCell(Cell aCell){
		path.add(aCell);
	}
	
	public boolean validate(){
		boolean valid = true;
		
		//check if the grid completely filled with cells
		for(int i=0; i<Height; i++){
			for(int j=0; j<Width; j++){				
				if(Grid[i][j] == null){
					valid = false;
				} else {
					Grid[i][j].printType();
					System.out.print(" ");	
				}
			}
			System.out.print("\n");
		}
		
		//check if path is continuous and does not branch out
		int previousX = -1;
		int previousY = -1;
		Cell FirstCell;
		Cell LastCell;
		
		if(EntryCell == null || ExitCell == null){
			valid = false;
		} else {
			FirstCell = EntryCell;
			LastCell = ExitCell;
			previousX = FirstCell.getX();
			previousY = FirstCell.getY();
		//see if at least one side the current cell is connected to the previous cell
		//if path has branches,the cell at the end of the branch won't be connected
		//to the next cell, so return false
			for(Cell aCell : path){
				if(aCell.getX() != previousX && aCell.getY() != previousY ){
					valid = false;
					break;
				}
				previousX = aCell.getX();
				previousY = aCell.getY();
			}
		//at the end of the for loop, previousX and Y should be the coordinates of
		//the last cell.
		//check if it is the coordinate of the last cell
			if(previousX != LastCell.getX() || previousY != LastCell.getY()){
				valid = false;
			}
		}
		
		return valid;
	}
	
	public static Map createGeneric() {
		//user set width and height of map
				int mapWidth = 8;
				int mapHeight = 8;
				
				Map MyMap = new Map(mapWidth, mapHeight);
				Cell aCell;
				
				//set every cell to SceneryCell first
				for(int i=0; i<mapHeight; i++){
					for(int j=0; j<mapWidth; j++){
						aCell = new SceneryCell(i,j);
						MyMap.addGridCell(aCell);
					}
				}
				
				// user set the path
				// set the selected cells to PathCell
				// set the EntryCell chosen by user to EntryCell
				aCell = new PathCell(0,5);
				MyMap.addGridCell(aCell);
				MyMap.addPathCell(aCell);
				MyMap.setEntryCell(aCell);
				
				aCell = new PathCell(1,5);
				MyMap.addGridCell(aCell);
				MyMap.addPathCell(aCell);
				
				aCell = new PathCell(2,5);
				MyMap.addGridCell(aCell);
				MyMap.addPathCell(aCell);
				
				//add a bad pathCell to test
				aCell = new PathCell(2,6);
				MyMap.addGridCell(aCell);
				MyMap.addPathCell(aCell);
				//
				aCell = new PathCell(3,5);
				MyMap.addGridCell(aCell);
				MyMap.addPathCell(aCell);
				
				aCell = new PathCell(3,4);
				MyMap.addGridCell(aCell);
				MyMap.addPathCell(aCell);
				
				aCell = new PathCell(3,3);
				MyMap.addGridCell(aCell);
				MyMap.addPathCell(aCell);
				
				aCell = new PathCell(3,2);
				MyMap.addGridCell(aCell);
				MyMap.addPathCell(aCell);
				
				aCell = new PathCell(4,2);
				MyMap.addGridCell(aCell);
				MyMap.addPathCell(aCell);
				
				aCell = new PathCell(5,2);
				MyMap.addGridCell(aCell);
				MyMap.addPathCell(aCell);
				
				aCell = new PathCell(6,2);
				MyMap.addGridCell(aCell);
				MyMap.addPathCell(aCell);
				
				aCell = new PathCell(7,2);
				MyMap.addGridCell(aCell);
				MyMap.addPathCell(aCell);
				// set this ExitCell chosen by user to ExitCell
				MyMap.setExitCell(aCell);
				
				return MyMap;
	}	
}