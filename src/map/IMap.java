package map;

import javax.swing.JComponent;

public interface IMap {
	public JComponent getComponent(int x, int y);
	public int getMapWidth();
	public int getMapHeight();
	public void setEntryCell(Cell aCell);
	public Cell getEntryCell();
	public void setExitCell(Cell aCell);
	public Cell getExitCell();
	public void addGridCell(Cell aCell);	
	public void addPathCell(Cell aCell);	
	public boolean validate();
	
}
