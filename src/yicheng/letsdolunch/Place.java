package yicheng.letsdolunch;

public class Place {
	private final String placeName;
	
	public Place(String placeName){
		this.placeName = placeName;
	}
	
	public String getPlaceName(){
		return this.placeName;
	}

	public String toString() {
		return this.placeName;
	}

}
