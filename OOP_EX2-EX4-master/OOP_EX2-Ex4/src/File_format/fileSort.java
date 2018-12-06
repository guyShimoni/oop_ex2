package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class fileSort {
private String fileName;
private String [][] info;
private String DeviceName="";
private ArrayList<WifiLine>  Stringlist = new ArrayList<WifiLine>();
/**
 * this class gets 1 csv file in wiggle wifi format and sort him (Columns) by the requst of "matala0"  and  by signal and time (rows) and putting it into list of wifiline(line of spots) 
 * @param fileName gets csv file in wiggle format -else the class do nothing
 */
public  fileSort(String fileName){
	if(!fileName.contains(".csv")) System.out.println("not csv file");
	else{
try {
	
			this.fileName=fileName;
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String str;
			str =br.readLine(); //check if file not empty
		
if (str.contains("WigleWifi-1.4")){		

			////give number of WIFI////////////
			int numNetworks;
			for( numNetworks=0; str!=null; numNetworks=numNetworks+1)	
				str = br.readLine();
		
			numNetworks=numNetworks-2; /// number of rowos - titles
			///////////////////////////////
			br.close();
			fr.close();
			///////////make arr of info/////////
			
		this.info = new String [numNetworks][10]; ////  wifi lines, 10 rows of title
}
	}
catch(NullPointerException ex) {
	System.out.println("not in 'wigle 1.4' format");
}
catch(IOException ex) {
	System.out.print("Error reading file\n" + ex);
	System.exit(2);
	}
	}
	}
	
/**
 * sort the file  (Columns) by the requst of "matala0"  and  by signal and time (rows) and putting it into list of wifiline(line of spots) 
 */
public void sort (){
		// try read from the file	
	

	try {
		String str;
	
		FileReader newFr = new FileReader(fileName);
	
		BufferedReader newBr = new BufferedReader(newFr);
	
		str=newBr.readLine();
		str=str.replaceAll(",","=");
		String []  temp = str.split("=");
		DeviceName=temp[4];
		
		newBr.readLine();
	
		//////////////////////////////////////////////////
		///put info in matrix////////
		str=newBr.readLine();
	
		for (int j = 0; j < info.length; j++) {
			info[j]=str.split(",");
	
			str=newBr.readLine();
		
		}
		
		////////////////////////////////
		/////sort by signal//////
		int start,finish;
		for (int i = 0; i < groups(info).length-1; i++) {
			start=groups(info)[i];
			finish=groups(info)[i+1];
			sortBySignal(info,start,finish);
		}
		//print(info);
		//////////////////////////////////

		
		
		
	
		//////////////////////////////////////////////
////////////////////////////////
		for (int i = 0; i < groups(info).length-1; i++) {
			start=groups(info)[i];
			finish=groups(info)[i+1];
			
			while(finish-start>10) finish--;

			WifiLine newLine =new WifiLine(info[start][3],DeviceName,Double.parseDouble(info[start][6]),Double.parseDouble(info[start][7]),Double.parseDouble(info[start][8]));
	
			
			
			
			for (int h = start; h < finish; h++) {
				newLine.addSSID(info[h][1]);
				
				newLine.addMAC(info[h][0]);
				newLine.addFrequncy(Integer.parseInt(info[h][4]));
				newLine.addSignal(Integer.parseInt(info[h][5]));
		
				
			}
	
			Stringlist.add((newLine));
			
			
			
		}
		
		
		


		
		
		//printLinkedList(Stringlist);
////////////////////////////////
////////////////////////////////
		
		}
	catch(NullPointerException ex) {
	
		System.out.println("you have csv file not in 'wigle 1.4' format,file making continue...");
		
	}
		catch(IOException ex) {
		System.out.print("Error reading file\n" + ex);
		//System.exit(2);
		}
		}

	private void swapRows (String [] a ,String [] b ){
	int size = a.length; //same size a,b
		String [] temp = new String[size];
		for (int i = 0; i < size; i++) {
			temp[i]=a[i];
		}
		for (int i = 0; i < size; i++) {
			a[i]=b[i];
		}
		for (int i = 0; i < size; i++) {
			b[i]=temp[i];
		}
		
		
	}
	
	
	

	

	private  int countSameTime(String [][] arr,int group){
		int count = 0;
		String time = arr[group][3]; /// 3 is the Time column
		for (int i = 0; i < arr.length; i++) {
			
			if (time.equals(arr[i][3])) count++;	
			
		}
		return count;
	}

	private void sortBySignal(String[][] arr,int start , int finish){ ///selction sort
		  int n = finish;
		  
	        
	        for (int i = start; i < n-1; i++)
	        {
	           
	            int min_idx = i;
	            for (int j = i+1; j < n; j++)
	                if (Integer.parseInt(arr[j][5]) > Integer.parseInt(arr[min_idx][5]))
	                    min_idx = j;
	 
	            swapRows(arr[min_idx],arr[i]);
	            
	        }
	        
	   
	}
	
	private int [] groups(String[][] info){///groups of same time//
		
		int count =countSameTime(info,0);
		int temp =countSameTime(info,0);
		int size=0;
	
		while(count<info.length ){
			temp=countSameTime(info,count);
			count=count+temp;
			size++;
			
			}
		int [] groups = new int [size+2];
		 count =countSameTime(info,0);
		 temp =countSameTime(info,0);
		groups[0]=0;
		groups[1]=count;
		int i=2;
		while(count<info.length ){
			temp=countSameTime(info,count);
			
			count=count+temp;
			groups[i]=count;
			i++;
			}
		return groups;
	}

	public ArrayList<WifiLine> getStringlist() {
		return Stringlist;
	}






}