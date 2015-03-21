package MapA1.src;


public abstract class Cell implements CellInterface{

	protected int x;
	protected int y;

	public void setX(int posX) { x = posX; }
	public void setY(int posY) { y = posY; }
	public int getX() { return x; }
	public int getY() { return y; }
}
