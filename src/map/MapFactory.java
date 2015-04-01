package map;


public class MapFactory {
	private MapFactory() {
		
	}
	
	public static synchronized MapFactory getUniqueInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new MapFactory();
		}
		return uniqueInstance;
	}
	
	public Map createMap(int flag, int width, int height) {
		mapType = flag;
		if (mapType == 0){
			return new CustomMap(Map.boundNumber(width, 0, 15), Map.boundNumber(height, 0, 15));
		}
		else if (mapType == 1){
			return new EasyMap(15,15);
		}
		else{
			return new HardMap(15,15);
		}
	}
	
	int mapType;
	
	private static MapFactory uniqueInstance = null; 
	
}
