package yicheng.letsdolunch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class TextFileWriter {
	private final String fileName;
	private final ArrayList<Place> meetingPlaces;

	public TextFileWriter(String fileName, ArrayList<Place> meetingPlaces){
		this.fileName = fileName;
		this.meetingPlaces = meetingPlaces;
	}

	public String getFileName(){
		return this.fileName;
	}

	public ArrayList<Place> getMeetingPlaces(){
		return this.meetingPlaces;
	}
	
	public void writeTextFile() throws IOException{
		File writeFile = new File(this.fileName);
		FileOutputStream outputStream = new FileOutputStream(writeFile);

		BufferedWriter bufferWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

		for (Place place : this.meetingPlaces){
			bufferWriter.write(place.getPlaceName());
			bufferWriter.newLine();
		}

		bufferWriter.close();

	}
}
