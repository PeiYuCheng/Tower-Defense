package map;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MapTests {

	MapFactory map_factory = MapFactory.getUniqueInstance();
	Map map = map_factory.createMap(0, 5, 5);
	
	@Test
	public void testValidMap() {
		
		Cell[][] Grid = map.Grid;
		CustomMap custom_map = (CustomMap)map;
		ArrayList<Cell> right_path = new ArrayList<>();
		boolean validated;
		
		custom_map.toggleCell(Grid[1][3]);
		custom_map.toggleCell(Grid[1][4]);
		custom_map.toggleCell(Grid[3][4]);
		custom_map.toggleCell(Grid[4][3]);
		custom_map.toggleCell(Grid[2][4]);
		custom_map.toggleCell(Grid[1][2]);
		custom_map.toggleCell(Grid[3][3]);
		custom_map.toggleCell(Grid[0][2]);
		
		right_path.add(Grid[0][2]);
		right_path.add(Grid[1][2]);
		right_path.add(Grid[1][3]);
		right_path.add(Grid[1][4]);
		right_path.add(Grid[2][4]);
		right_path.add(Grid[3][4]);
		right_path.add(Grid[3][3]);
		right_path.add(Grid[4][3]);
		
		custom_map.findAndSetEntryAndExitCells();
		assertEquals("Entry cell not right.", Grid[0][2], custom_map.EntryCell);
		assertEquals("Exit cell not right.", Grid[4][3], custom_map.ExitCell);
		
		validated = custom_map.validate();
		
		assertEquals("Path list wrong.", custom_map.getPath(), right_path);
		assertEquals("Validation wrong.", validated, true);
		
	}
	
	@Test
	public void testInvalidMap() {
		
		Cell[][] Grid = map.Grid;
		CustomMap custom_map = (CustomMap)map;
		boolean validated;
		
		custom_map.toggleCell(Grid[1][3]);
		custom_map.toggleCell(Grid[1][4]);
		custom_map.toggleCell(Grid[3][4]);
		custom_map.toggleCell(Grid[2][2]);
		custom_map.toggleCell(Grid[4][3]);
		custom_map.toggleCell(Grid[2][4]);
		custom_map.toggleCell(Grid[1][2]);
		custom_map.toggleCell(Grid[3][3]);
		custom_map.toggleCell(Grid[0][2]);
		
		custom_map.findAndSetEntryAndExitCells();
		assertEquals("Entry cell not right.", Grid[0][2], custom_map.EntryCell);
		assertEquals("Exit cell not right.", Grid[4][3], custom_map.ExitCell);
		
		validated = custom_map.validate();
		
		assertEquals("Validation wrong.", validated, false);
		
	}
	
}
