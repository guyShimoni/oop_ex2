package File_format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class fileMaker {
////make list of arrays and first line is the title ///////
	private ArrayList<WifiLine>  wifilist = new ArrayList<WifiLine>();
	private  String title;
	private	File[] files;
	
	private File file;
/**
 * this class gets folder of scv files and add them (after sorting) to a list that contains lines of wifi spots
 * @param folderName the path to the folder
 */
	public fileMaker(String folderName,boolean folder){
		
		File file = new File(folderName);
		this.files = file.listFiles();
		
		 String str="";
		 title = "Time,ID,Lat,Lon,Alt"  ;
		
			int j=1;
			for (int i = 5; i < 45; i=i+4) {
				str=str + "SSID" + String.valueOf(j) +",";
				str=str +"MAC" + String.valueOf(j)+",";
				str = str+ "Frequncy" + String.valueOf(j +",");
				str=str+  "Signal" + String.valueOf(j) +",";
				j++;
			
			}
			title=title+ ","+str;
			//this.Stringlist.add(title);
			if (folder)
				add();
			else
				add_one(folderName);
	}

	
	
	/**
	 * the method Going through every csv folder - sort him - and add him to the wifilist
	 */
	
	private void add(){
		for (int i = 0; i < files.length; i++) {
		//	System.out.println(files[i].getPath().replace("\\", "/"));
			String str = files[i].getPath().replace("\\", "/");
		
			if (str.contains(".csv")){
		fileSort newFile = new fileSort(str);	
		newFile.sort();
		ArrayList<WifiLine> newList =(ArrayList<WifiLine>) newFile.getStringlist();
	
			wifilist.addAll(newList);
			}
		}
		
		
	}
	
	private void add_one(String file_name){
		
		//	System.out.println(files[i].getPath().replace("\\", "/"));
			String str = file_name;
			
			if (str.contains(".csv") && str.equals(file_name)){
		fileSort newFile = new fileSort(str);	
		newFile.sort();
		ArrayList<WifiLine> newList =(ArrayList<WifiLine>) newFile.getStringlist();
	
			wifilist.addAll(newList);
			}
		
		
		
	}
	
	

	public ArrayList<WifiLine> getWifilist() {
		return wifilist;
	}

	
	public boolean exportCSV(String Path){
	
		if(!Path.contains(".csv")) {
			System.out.println("not csv file");
			return false;
		}
		else{
		try {
			
			FileWriter fw = new FileWriter(Path);
			PrintWriter outs = new PrintWriter(fw);
			outs.print(title);
			outs.println();
			for (int i = 0; i < wifilist.size(); i++) {
				
					outs.print(wifilist.get(i).toString());
					
				
				outs.println();
			
			}
			System.out.println("File making completed!");
			outs.close();
			fw.close();
			return true;
			}
			catch(IOException ex) {
			System.out.print("Error writing file\n" + ex);
			return false ;
			}
		}
	
		
	}
}
