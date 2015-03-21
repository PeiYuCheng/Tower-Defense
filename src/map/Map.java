package map;

import java.util.ArrayList;

public class Map {
	
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
}
