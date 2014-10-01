package yicheng.letsdolunch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class TextFileReader {
	private final String fileName;
	private ArrayList<ArrayList<Place>> mapPlacesList;
	private ArrayList<Place> avoidPlacesList;
	private ArrayList<Place> peggyStartingPlacesList;
	private ArrayList<Place> samStartingPlacesList;
	private HashMap<String, Place> placeMap;

	public TextFileReader(String fileName){
		this.fileName = fileName;
		this.mapPlacesList = new ArrayList<ArrayList<Place>>();
		this.avoidPlacesList = new ArrayList<Place>();
		this.peggyStartingPlacesList = new ArrayList<Place>();
		this.samStartingPlacesList = new ArrayList<Place>();
		this.placeMap = new HashMap<String, Place>();
	}

	public void readTextFile() throws IOException{

		FileInputStream fstream = new FileInputStream(this.fileName);
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		String currentLine = "";

		while ((strLine = bufferReader.readLine()) != null)   {

			strLine = strLine.trim();

			if(strLine.equals("Map:")){
				currentLine = "map";
			}
			else if (strLine.equals("Avoid:")){
				currentLine = "avoid";
			}
			else if (strLine.equals("Peggy:")){
				currentLine = "peggy";
			}
			else if (strLine.equals("Sam:")){
				currentLine = "sam";
			}
			else {
				if (currentLine.equals("map")){
					String[] currentMapArray = strLine.split(" ");
					ArrayList<Place> tempList = new ArrayList<Place>();
					for (String s : currentMapArray){			
						if (!placeMap.containsKey(s)){
							Place newPlace = new Place(s);
							placeMap.put(s, newPlace);
							tempList.add(newPlace);
						}
						else{
							tempList.add(placeMap.get(s));
						}
					}
					mapPlacesList.add(tempList);

				}
				else if (currentLine.equals("avoid")){
					String[] avoidPlacesArray = strLine.split(" ");
					for (String s : avoidPlacesArray){
						if (placeMap.get(s) != null){
							avoidPlacesList.add(placeMap.get(s));
						}
					}
				}
				else if (currentLine.equals("peggy")){
					String[] peggyPlacesArray = strLine.split(" ");
					for (String s : peggyPlacesArray){
						peggyStartingPlacesList.add(placeMap.get(s));
					}
				}
				else {
					String[] samPlacesArray = strLine.split(" ");
					for (String s : samPlacesArray){
						samStartingPlacesList.add(placeMap.get(s));
					}
				}
			}
		}

		bufferReader.close();
	}

	public String getFileName(){
		return this.fileName;
	}

	public ArrayList<ArrayList<Place>> getMapPlacesList(){
		return this.mapPlacesList;
	}

	public ArrayList<Place> getAvoidPlacesList(){
		return this.avoidPlacesList;
	}

	public ArrayList<Place> getPeggyStartingPlacesList(){
		return this.peggyStartingPlacesList;
	}

	public ArrayList<Place> getSamStartingPlacesList(){
		return this.samStartingPlacesList;
	}



}
