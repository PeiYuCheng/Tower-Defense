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
	private static final boolean SELECTABLE = true;
	
	public SceneryCell(int posX, int posY, int mapWidth, int mapHeight){
		super(posX, posY, SELECTABLE, mapWidth, mapHeight);
		Random r = new Random();
		setCellColor(new Color(20 + r.nextInt(20), 180 + r.nextInt(20) ,70 + r.nextInt(20)));
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