package yicheng.letsdolunch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class MeetingPlaceFinder {
	private final PlaceGraph placeGraph;
	private final ArrayList<Place> avoidPlacesList;
	private final ArrayList<Place> personOneStartingPlacesList;
	private final ArrayList<Place> personTwoStartingPlacesList;
	private HashSet<Place> pathSet;

	public MeetingPlaceFinder(PlaceGraph placeGraph, 
			ArrayList<Place> avoidPlacesList, 
			ArrayList<Place> personOneStartingPlacesList, 
			ArrayList<Place> personTwoStartingPlacesList){

		this.placeGraph = placeGraph;
		this.avoidPlacesList = avoidPlacesList;
		this.personOneStartingPlacesList = personOneStartingPlacesList;
		this.personTwoStartingPlacesList = personTwoStartingPlacesList;

	}

	public PlaceGraph getPlaceGraph(){
		return this.placeGraph;
	}

	public ArrayList<Place> getAvoidPlacesList(){
		return this.avoidPlacesList;
	}

	public ArrayList<Place> getPersonOneStartingPlacesList(){
		return this.personOneStartingPlacesList;
	}

	public ArrayList<Place> getPersonTwoStartingPlacesList(){
		return this.personTwoStartingPlacesList;
	}

	public ArrayList<Place> findMeetingPlaces(){
		this.pathSet = new HashSet<Place>();

		for (Place personeOneStartingPlace : this.personOneStartingPlacesList){
			ArrayList<Place> visitedPlaces = new ArrayList<Place>();
			visitedPlaces.add(personeOneStartingPlace);
			for (Place personeTwoStartingPlace : this.personTwoStartingPlacesList){
				searchPath(this.placeGraph, visitedPlaces, personeTwoStartingPlace);
			}
		}

		ArrayList<Place> pathList = new ArrayList<Place>(pathSet);

		Collections.sort(pathList, new Comparator<Place>(){

			@Override
			public int compare(Place place1, Place place2) {
				// TODO Auto-generated method stub
				if (place1.getPlaceName().compareTo(place2.getPlaceName()) > 1){
					return 1;
				}
				else if (place1.getPlaceName().compareTo(place2.getPlaceName()) < 1){
					return -1;
				}
				else {
					return 0;
				}
			}

		});


		return pathList;
	}

	public void searchPath(PlaceGraph placeGraph, ArrayList<Place> visitedPlaces, Place endPlace){
		ArrayList<Place> places = placeGraph.getAdjacentPlaces(visitedPlaces.get(visitedPlaces.size() - 1));

		for (Place place : places){
			if (visitedPlaces.contains(place)){
				continue;
			}
			if (place.equals(endPlace)){
				visitedPlaces.add(place);
				addPath(visitedPlaces);
				visitedPlaces.remove(visitedPlaces.size() - 1);
				break;
			}
		}

		for (Place place : places){
			if (visitedPlaces.contains(place) || place.equals(endPlace) || isAvoidPlace(place)){
				continue;
			}

			visitedPlaces.add(place);
			searchPath(placeGraph, visitedPlaces, endPlace);
			visitedPlaces.remove(visitedPlaces.size() - 1);

		}
	}

	private boolean isAvoidPlace(Place place){
		for (Place avoidPlace : this.avoidPlacesList){
			if (avoidPlace.equals(place)){
				return true;
			}
		}

		return false;
	}

	private void addPath(ArrayList<Place> visitedPlaces){
		for (Place place : visitedPlaces){
			pathSet.add(place);
		}
	}

}
