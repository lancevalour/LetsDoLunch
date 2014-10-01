package yicheng.letsdolunch;

import java.io.IOException;
import java.util.ArrayList;


public class Test {

	public static void main(String[] args){

		TextFileReader textFileReader = new TextFileReader("C:\\Users\\Yicheng\\Desktop\\text.txt");

		try {
			textFileReader.readTextFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MeetingPlaceFinder meetingPlaceFinder = new MeetingPlaceFinder(
				PlaceGraph.createPlaceGraph(textFileReader.getMapPlacesList()), 
				textFileReader.getAvoidPlacesList(),
				textFileReader.getPeggyStartingPlacesList(), 
				textFileReader.getSamStartingPlacesList());

		ArrayList<Place> meetingPlaces = meetingPlaceFinder.findMeetingPlaces();
		System.out.println(meetingPlaces);

		TextFileWriter textFileWriter = new TextFileWriter("C:\\Users\\Yicheng\\Desktop\\textOutput.txt", meetingPlaces);

		try {
			textFileWriter.writeTextFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




}
