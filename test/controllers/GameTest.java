package controllers;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class GameTest {

	GameController game = new GameController();
	GameController loadedGameState = new GameController();
	
	@Test
	public void saveMapFileExist_test() {
		game.saveMap("Tree");
		
		File gameSave = new File("src/savedMaps/Tree.txt");
		assertTrue(gameSave.exists());
	}
	
	@Test
	public void loadMapFile_Test() {
		game.loadMap(new File("src/savedMaps/Tree.txt"));
	}
}
