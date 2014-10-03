package yicheng.letsdolunch;

import java.io.IOException;
import java.util.ArrayList;


public class Test {

	public static void main(String[] args){

		StdInReader stdInReader = new StdInReader();
		try {
			stdInReader.readTextFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		MeetingPlaceFinder meetingPlaceFinder = new MeetingPlaceFinder(
				PlaceGraph.createPlaceGraph(stdInReader.getMapPlacesList()), 
				stdInReader.getAvoidPlacesList(),
				stdInReader.getPeggyStartingPlacesList(), 
				stdInReader.getSamStartingPlacesList());

		ArrayList<Place> meetingPlaces = meetingPlaceFinder.findMeetingPlaces();
		//System.out.println(meetingPlaces);
		for (Place place : meetingPlaces){
			System.out.println(place.getPlaceName());
		}

		
	}




}
