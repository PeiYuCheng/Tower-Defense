package MapA1.src;

public class Main {

	public static void main(String args[]){
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
		
		
		// Check Validaty
		boolean valid = MyMap.validate();
		
		if(valid){
			System.out.println("Valid map");
		} else {
			System.out.println("Invalid map. Path is incomplete or has branches");
		}
		
		//we can access the user determined path through MyMap.path
		System.out.print("the coordinates of the path cells are: \n");
		for(Cell c: MyMap.path)
			System.out.print("{" + c.getX() + "," + c.getY() + "}");
	}
}
