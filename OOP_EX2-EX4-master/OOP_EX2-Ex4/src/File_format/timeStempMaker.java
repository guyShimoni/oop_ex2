package File_format;

/**
 *this class turns the string of time in wiglwifi format to google earth format  
 * @author 12345qwe
 *
 */
public class timeStempMaker {

	private String time;
	private String timeStemp="";

	public timeStempMaker (String time){
		this.time= time;
	
		String [] temp = time.split(" ");
		timeStemp=temp[0]+"T"+temp[1]+"Z";
	
	}

	@Override
	public String toString() {
		return timeStemp;
	}
	
	
	
}
