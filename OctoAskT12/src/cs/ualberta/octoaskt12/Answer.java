package cs.ualberta.octoaskt12;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Answer {
	private String answer;
	private int upvotes;
	private int replies;
	private GregorianCalendar dateCreated;
	
	public Answer(String string) {
		this.answer = string;
		this.dateCreated = new GregorianCalendar();
	}

	public String getString() {
		return this.answer;
	}
	
	public int getVotes(){
		return upvotes;
	}
	
	public void incrementVotes() {
		upvotes++;
	}
	
	public int getReplies(){
		return replies;
	}
	
	public void incrementReplies() {
		replies++;
	}
	
	public GregorianCalendar getTime(){	
		return dateCreated;
	}
	
}
