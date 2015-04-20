package map;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JComponent;

public abstract class Map implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final int MAX_WIDTH = 15;
	public static final int MAX_HEIGHT = 15;
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
		Grid[i][j] = aCell;
	}
	
	public void addPathCell(Cell aCell){
		path.add(aCell);
	}

	public ArrayList<Cell> getPath() {
		return path;
	}
	
	public JComponent getComponent(int x, int y) {
		return Grid[y][x].getComponent();
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
//					Grid[i][j].printType();
//					System.out.print(" ");	
				}
			}
//			System.out.print("\n");
		}
		
		//check if path is continuous and does not branch out
		int previousX = -1;
		int previousY = -1;
		Cell FirstCell;
		Cell LastCell;
		
		if(EntryCell == null || ExitCell == null){
			return false;
		} else {
			if (!validateAndSortPathCells()) {
				return false;
			}
			FirstCell = EntryCell;
			LastCell = ExitCell;
			previousX = FirstCell.getX();
			previousY = FirstCell.getY();
		//see if at least one side the current cell is connected to the previous cell
		//if path has branches,the cell at the end of the branch won't be connected
		//to the next cell, so return false
			for(Cell aCell : path){
				if(aCell.getX() != previousX && aCell.getY() != previousY ){
					return false;
				}
				previousX = aCell.getX();
				previousY = aCell.getY();
			}
		//at the end of the for loop, previousX and Y should be the coordinates of
		//the last cell.
		//check if it is the coordinate of the last cell
			if(previousX != LastCell.getX() || previousY != LastCell.getY()){
				return false;
			}
		}
		
		return valid;
	}
	
	protected boolean validateAndSortPathCells() {
		
		ArrayList<Cell> path = new ArrayList<>();
		int pathCellCount;
		boolean valid = true;
		Cell lastCell = null;
		Cell currentCell;
		Cell[] nextCell = new Cell[4];
		
		currentCell = EntryCell;
		path.add(EntryCell);
		
		while(currentCell != null) {
			
			pathCellCount = 0;
			
			// Obtain all possible next path cells
			nextCell[0] = Grid[boundNumber(currentCell.position.x - 1, 0, Grid.length - 1)][boundNumber(currentCell.position.y, 0, Grid[0].length - 1)];
			nextCell[1] = Grid[boundNumber(currentCell.position.x + 1, 0, Grid.length - 1)][boundNumber(currentCell.position.y, 0, Grid[0].length - 1)];
			nextCell[2] = Grid[boundNumber(currentCell.position.x, 0, Grid.length - 1)][boundNumber(currentCell.position.y - 1, 0, Grid[0].length - 1)];
			nextCell[3] = Grid[boundNumber(currentCell.position.x, 0, Grid.length - 1)][boundNumber(currentCell.position.y + 1, 0, Grid[0].length - 1)];
			
			for (int i = 0; i < nextCell.length; i++) {
				if ((nextCell[i] instanceof PathCell) && (currentCell != nextCell[i])) {
					pathCellCount++;
				}
			}
			
			if (pathCellCount > 2) {
				valid = false;
				break;
			}
			
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
		
		this.path = path;
		return valid;
		
		
	}
	
	public void findAndSetEntryAndExitCells() {
				
		EntryCell = null;
		ExitCell = null;
		ArrayList<Cell> list_of_boundary_cells = new ArrayList<>();
		
		// left edge cells
		for (int i = 0; i < Grid[0].length; i++) {
			list_of_boundary_cells.add(Grid[0][i]);
		}
		
		// bottom edge cells
		for (int i = 0; i < Grid.length; i++) {
			list_of_boundary_cells.add(Grid[i][Grid[0].length - 1]);
		}
		
		// right edge cells
		for (int i = 0; i < Grid[0].length; i++) {
			list_of_boundary_cells.add(Grid[Grid.length - 1][Grid[0].length - 1 - i]);
		}
		
		// top edge cells
		for (int i = 0; i < Grid.length; i++) {
			list_of_boundary_cells.add(Grid[Grid.length - 1 - i][0]);
		}
		
		list_of_boundary_cells.add(Grid[0][0]);
		
		
		// scan all the possible entry and exit cells and set the first found as an entry
		// and the second found as an exit
		for (int i = 1; i < list_of_boundary_cells.size() - 2; i++) {
			if (list_of_boundary_cells.get(i) instanceof PathCell &&
					list_of_boundary_cells.get(i-1) instanceof SceneryCell &&
					list_of_boundary_cells.get(i+1) instanceof SceneryCell) {
				if (EntryCell == null) {
					EntryCell = list_of_boundary_cells.get(i);
				}
				else if (ExitCell == null) {
					ExitCell = list_of_boundary_cells.get(i);
					return;
				}
			}
		}
		
	}
	
	public static int boundNumber(int number, int lowerBound, int upperBound) {
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
	
	public void refreshCellSelectors() {
		for (int i = 0; i < Grid.length; i++) {
			for (int j = 0; j < Grid[0].length; j++) {
				Grid[i][j].updateCellSelector();
			}
		}
	}
	
}
