package MapA1.src;


public class PathCell extends Cell{

	public PathCell(int posX, int posY){
		x = posX;
		y = posY;
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
