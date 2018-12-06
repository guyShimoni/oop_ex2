package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Style;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;

public class Csv2Kml {
	
	private fileMaker csvFile;
	
	
	private double lat ;
	private double lon;


	private double alt;
	private String MAC;
	private	String ssid1;
	private	String signal;
	private String time;
	private ArrayList<WifiLine> list;
	private Kml kml = new Kml();
	private Document doc;
	private Folder folder;
	private String kmlFilePath;
	private ArrayList<WifiLine> temp = new  ArrayList<WifiLine>() ;
	 /**
	  * this class get csv folder and turn him to kml file
	  * @param csvFolderPath is the path to the csv foler - all csv files in the folder must be in "wiglewifi 1.4" format 
	  * @param kmlFilePath is the path to the new kml file must end with ".kml"
	  */
	
public	Csv2Kml(String csvFilePath ,String kmlFilePath ){
	
	this.csvFile  = new fileMaker(csvFilePath,false);
	
	this.doc= kml.createAndSetDocument().withOpen(true);
	// create a Folder
			folder = doc.createAndAddFolder();
			folder.withName("networks").withOpen(true);
			list=csvFile.getWifilist(); 
			
			this.kmlFilePath=kmlFilePath;
	
}
/**
 * this function make 1 placemark of the strongest spot in one line
 * @param longitude of spot in one time
 * @param latitude of spot in one time
 * @param altitude of spot in one time
 * @param ssid1 is the name of the strongest spot
 * @param MAC the mac of the strongest spot
 * @param signal is the value of "how much the spot is strong"
 */
private static void createPlacemarkWithChart(Document document, Folder folder, double longitude, double latitude, double altitude,
    String ssid1, String MAC ,String signal, String Time){
timeStempMaker tsm = new timeStempMaker(Time);
String time = tsm.toString();
	
	
	Style style = document.createAndAddStyle();
	style.withId( ssid1); // set the stylename to use this style from the placemark
	    

	Placemark placemark = folder.createAndAddPlacemark();
	// use the style for each continent

	
	
	placemark.withName(ssid1)
	    .withStyleUrl(ssid1).withDescription("<p>MAC:" + MAC + "<p>signal: "+ signal  + "<p>lon: " + longitude + "<p>lat: " + latitude + "<p>alt: " +  altitude + "<p>time: " +  time  )
	 
	    .createAndSetLookAt().withLongitude(longitude).withLatitude(latitude).withAltitude(altitude);

	placemark.createAndSetTimeStamp().setWhen(time);
	placemark.createAndSetPoint().addToCoordinates(longitude, latitude,altitude); // set coordinates
	 
}



/**
 * this void function generate the new kml file and export him to his new place (the path)
 */
public boolean crateAndExport(){
	// create Placemark elements
				for (int i = 0; i <csvFile.getWifilist().size(); i++) {
					lat = (csvFile.getWifilist().get(i).getGeo()[0]);
					lon =  (csvFile.getWifilist().get(i).getGeo()[1]);
					alt =  (csvFile.getWifilist().get(i).getGeo()[2]);
					ssid1 =(csvFile.getWifilist().get(i).getSSID().get(0));
					MAC= (csvFile.getWifilist().get(i).getMAC().get(0));
					time= (csvFile.getWifilist().get(i).getTime());
					signal=Integer.toString((csvFile.getWifilist().get(i).getSignal().get(0)));
					createPlacemarkWithChart(doc, folder, lon, lat,alt, ssid1,MAC,signal,time);
				}
				if (!kmlFilePath.contains(".kml")) System.out.println("not kml file");
				else{
				try {
					
					kml.marshal(new File(kmlFilePath));
					System.out.println("file export completed!");
					return true;
				} 
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				return false;

}


	
}




