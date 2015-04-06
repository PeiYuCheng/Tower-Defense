package map;

import img.Images;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;


public class PathCell extends Cell implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
