package MapA1.src;

public class SceneryCell extends Cell{

	public SceneryCell(int posX, int posY){
		x = posX;
		y = posY;
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