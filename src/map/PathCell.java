package map;

import java.awt.Color;


public class PathCell extends Cell {
	
	private static final Color PATH_CELL_COLOR = Color.orange;
	private static final boolean SELECTABLE = false;
	
	public PathCell(int posX, int posY){
		super(posX, posY, PATH_CELL_COLOR, SELECTABLE);
	}
	
	@Override
	public void printType(){
		System.out.print("P");
	}
	
	@Override
	public boolean canPlaceTower(){
		return false;
	}
	
	@Override
	public boolean canPlaceCritter(){
		return true;
	}
}
