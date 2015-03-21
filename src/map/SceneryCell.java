package map;

import java.awt.Color;

public class SceneryCell extends Cell{

	private static final Color SCENERY_CELL_COLOR = Color.green;
	
	public SceneryCell(int posX, int posY){
		super(posX, posY, SCENERY_CELL_COLOR);
	}
	
	@Override
	public void printType(){
		System.out.print("s");
	}
	
	@Override
	public boolean canPlaceTower(){
		return true;
	}
	
	@Override
	public boolean canPlaceCritter(){
		return false;
	}
}