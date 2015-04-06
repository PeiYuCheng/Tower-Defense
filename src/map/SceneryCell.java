package map;

import img.Images;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;

public class SceneryCell extends Cell implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color SCENERY_CELL_COLOR = new Color(48,173,35);
	private static final boolean SELECTABLE = true;
	
	public SceneryCell(int posX, int posY, int mapWidth, int mapHeight){
		super(posX, posY, SCENERY_CELL_COLOR, SELECTABLE, mapWidth, mapHeight);
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