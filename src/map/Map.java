package map;

import java.util.ArrayList;

import javax.swing.JComponent;

public abstract class Map {
	
	protected int Width;
	protected int Height;
	protected Cell[][] Grid; // 2 dimensional array
	protected Cell EntryCell;
	protected Cell ExitCell;
	protected ArrayList<Cell> path;


	
	public void setEntryCell(Cell aCell){
		EntryCell = aCell;
	}
	public Cell getEntryCell() {
		return EntryCell;
	}

	public Cell getExitCell() {
		return ExitCell;
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

	public ArrayList<Cell> getPath() {
		return path;
	}
	
	public JComponent getComponent(int x, int y) {
		return Grid[x][y].getComponent();
	}

	public int getMapWidth() {
		return Width;
	}

	public int getMapHeight() {
		return Height;
	}
	public boolean validate(){
		boolean valid = true;
		
		//check if the grid completely filled with cells
		for(int i=0; i<Width; i++){
			for(int j=0; j<Height; j++){				
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
	
	protected void sortPathCells() {
		
		//ArrayList<Cell> path = new ArrayList<>();
		Cell lastCell = null;
		Cell currentCell;
		Cell[] nextCell = new Cell[4];
		
		currentCell = EntryCell;
		path.add(EntryCell);
		
		while(currentCell != null) {
			
			nextCell[0] = Grid[boundNumber(currentCell.position.x - 1, 0, Grid.length - 1)][boundNumber(currentCell.position.y, 0, Grid.length - 1)];
			nextCell[1] = Grid[boundNumber(currentCell.position.x + 1, 0, Grid.length - 1)][boundNumber(currentCell.position.y, 0, Grid.length - 1)];
			nextCell[2] = Grid[boundNumber(currentCell.position.x, 0, Grid.length - 1)][boundNumber(currentCell.position.y - 1, 0, Grid.length - 1)];
			nextCell[3] = Grid[boundNumber(currentCell.position.x, 0, Grid.length - 1)][boundNumber(currentCell.position.y + 1, 0, Grid.length - 1)];
			
			for (int i = 0; i < nextCell.length; i++) {
				if (nextCell[i] != currentCell && nextCell[i] != lastCell && nextCell[i] instanceof PathCell) {
					lastCell = currentCell;
					currentCell = nextCell[i];
					path.add(currentCell);
					break;
				}
				if (i == 3) {
					currentCell = null;
				}
			}
		}
		
		//this.path = path;
		
		
	}
	
	private int boundNumber(int number, int lowerBound, int upperBound) {
		if (number < lowerBound) {
			return lowerBound;
		}
		else if (number > upperBound) {
			return upperBound;
		}
		else {
			return number;
		}
	}
	
}
