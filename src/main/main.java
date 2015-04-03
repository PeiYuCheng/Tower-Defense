package main;

import presentation.Application;

public class main {
	
	private static Application tower_defense;
	
	/**
	 * This Class serves as an entry point to the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		tower_defense = new Application();
	}
	
	public static void restartGame() {
		tower_defense = new Application();
	}
	
}
