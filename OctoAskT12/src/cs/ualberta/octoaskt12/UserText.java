package cs.ualberta.octoaskt12;

import java.util.GregorianCalendar;

public class UserText {

	private String userText;
	private int upvotes = 5;
	private int answers = 3;
	private GregorianCalendar dateCreated;
	
	public UserText(String string){
		this.userText = string;
		this.dateCreated = new GregorianCalendar();
	}
	
	public String getUserText(){
		return this.userText;
	}
	
	public int incrementVotes(){
		upvotes++;
		return upvotes;
	}
	
	public int getVotes(){
		return upvotes;
	}
	
	public int incrementAnswers(){
		answers++;
		return answers;
	}
	
	public int getAnswers(){
		return answers;
	}
	
	public GregorianCalendar getTime(){
		
		return dateCreated;
	}
}
