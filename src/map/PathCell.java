package map;

import img.Images;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;


public class PathCell extends Cell implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final boolean SELECTABLE = false;
	
	public PathCell(int posX, int posY, int mapWidth, int mapHeight){
		super(posX, posY, SELECTABLE, mapWidth, mapHeight);
		Random r = new Random();
		setCellColor(new Color(200 + r.nextInt(20), 160 + r.nextInt(20) ,50 + r.nextInt(20)));
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
