package pathFinding;

import map.Map;

import org.newdawn.slick.util.pathfinding.AStarHeuristic;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class Heuristic implements AStarHeuristic{

	private static Map gameMap;
	@Override
	public float getCost(TileBasedMap map, Mover mover, int x, int y, int tx,
			int ty) {
		// TODO Auto-generated method stub
		return 0;
	}

}
