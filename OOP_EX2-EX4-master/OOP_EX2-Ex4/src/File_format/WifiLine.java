package File_format;

import java.util.Arrays;
import java.util.LinkedList;

public class WifiLine {
	
	private String Time;
	private String ID;
	private  double[] geo = new double [3];
	private	LinkedList<String>  SSID = new LinkedList<String>();
	private LinkedList<String> MAC= new LinkedList<String>();
	private LinkedList<Integer>  Frequncy= new LinkedList<Integer>();
	private LinkedList<Integer> Signal= new LinkedList<Integer>();
	
	/**
	 * this class represent a "wifi line" - at most 10 spots that are sort by signal
	 * @param Time is the time of the scan
	 * @param ID is the name of device that scan the spots
	 * @param lat is latitude of the spot
	 * @param lon is longitude of the spot
	 * @param alt is the altitude of the spot 
	 */
	public WifiLine(String Time , String ID, double lat , double lon , double alt ){
		this.Time= Time;
		this.ID=ID;
		this.geo[0]=lat;
		this.geo[1]=lon;
		this.geo[2]=alt;
	
	}

	/**
	 * add ssid of spots to the line - at most 10 spots
	 * @param ssid name of the spot
	 */
	public void addSSID(String ssid){
		if(SSID.size()<11)
			SSID.add(ssid);
		}
		
	/**
	 * add the mac of the spots to the line - at most 10 spots
	 * @param mac is a unique identifier assigned to network interfaces
	 */
		public void addMAC(String mac){
			if(MAC.size()<11)
				MAC.add(mac);
			}
			
			public void addFrequncy(int frequncy){
				if(Frequncy.size()<11)
					Frequncy.add(frequncy);
				}
		
			public void addSignal(int signal){
				if(Signal.size()<11)
					Signal.add(signal);
				}


			/**
			 * this method return the line of spots in string format
			 */
			public String toString() {
			String str="";
			int i;

				for ( i = 0; i < SSID.size()-1; i++) {
				
					str=str + SSID.get(i)+",";
					str=str + MAC.get(i)+",";
					str=str + String.valueOf(Frequncy.get(i))+",";
					str=str + String.valueOf(Signal.get(i))+",";
				
				}
				str = str  +SSID.get(i)+"," +MAC.get(i)+","+String.valueOf(Frequncy.get(i))+","+String.valueOf(Signal.get(i));
				//return  Time + "," + ID + "," + String.valueOf(geo[0]) + "," + String.valueOf(geo[1]) + "," + String.valueOf(geo[2]) + "," + str;
		
			return  Time + "," + ID + "," + String.valueOf(geo[0]) + "," + String.valueOf(geo[1]) + "," + String.valueOf(geo[2]) +  " ," +str;	
			}


			public String getTime() {
				return Time;
			}


			public String getID() {
				return ID;
			}


			public double[] getGeo() {
				return geo;
			}


			public LinkedList<String> getSSID() {
				return SSID;
			}


			public LinkedList<String> getMAC() {
				return MAC;
			}


			public LinkedList<Integer> getFrequncy() {
				return Frequncy;
			}


			public LinkedList<Integer> getSignal() {
				return Signal;
			}


		

		
		
	
	
	
	
}
