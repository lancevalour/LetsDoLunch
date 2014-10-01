package yicheng.letsdolunch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PlaceGraph {
	private static HashMap<Place, HashSet<Place>> map;

	public PlaceGraph(){
		map = new HashMap<Place, HashSet<Place>>();	
	}

	public void addPlaceEdge(Place place1, Place place2){
		HashSet<Place> adjacent = map.get(place1);
		if (adjacent == null){
			adjacent = new HashSet<Place>();
			map.put(place1, adjacent);
		}
		adjacent.add(place2);
	}

	public void addTwoWayPlaceNode(Place place1, Place place2){
		addPlaceEdge(place1, place2);
		addPlaceEdge(place2, place1);
	}

	public boolean isTwoPlacesConnected(Place place1, Place place2){
		HashSet<Place> adjacent = map.get(place1);

		if (adjacent == null){
			return false;
		}

		return adjacent.contains(place2);
	}

	public ArrayList<Place> getAdjacentPlaces(Place place){
		HashSet<Place> adjacent = map.get(place);
		if (adjacent == null){
			return new ArrayList<Place>();
		}

		return new ArrayList<Place>(adjacent);

	}

	public static PlaceGraph createPlaceGraph(ArrayList<ArrayList<Place>> mapPlaceList){
		PlaceGraph placeGraph = new PlaceGraph();

		for (ArrayList<Place> list : mapPlaceList){
			placeGraph.addTwoWayPlaceNode(list.get(0), list.get(1));
		}

		return placeGraph;
	}

}
