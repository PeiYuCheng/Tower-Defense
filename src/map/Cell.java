package map;


public abstract class Cell implements CellInterface{

	private int x;
	private int y;
	
	public Cell (int x, int y) {
		
	}

	public void setX(int posX) { x = posX; }
	public void setY(int posY) { y = posY; }
	public int getX() { return x; }
	public int getY() { return y; }
}
