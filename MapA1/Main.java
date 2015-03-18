
public class Main {

	public static void main(String args[]){
		int mapWidth = 8;
		int mapHeight = 8;
		Map MyMap = new Map(mapWidth, mapHeight);
		Cell aCell;
		
		for(int i=0; i<mapHeight; i++){
			for(int j=0; j<mapWidth; j++){
				aCell = new SceneryCell(i,j);
				MyMap.addGridCell(aCell);
			}
		}
		
		// set the path
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
		MyMap.setExitCell(aCell);
		
		// Check Validaty
		boolean valid = MyMap.validate();
		
		if(valid){
			System.out.println("Valid map");
		} else {
			System.out.println("Invalid map");
		}
	}
}
