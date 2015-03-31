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
		if (mapType == 0 && width<15 && height <15){
			return new CustomMap(width,height);
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
