package cs.ualberta.octoaskt12;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Question {

	private String question;
	private int upvotes;
	private int answers;
	private int replies;
	private GregorianCalendar dateCreated;
	
	public Question(String string) {
		this.question = string;
		this.dateCreated = new GregorianCalendar();
	}

	public String getString() {
		return this.question;
	}
	
	public void incrementAnswers() {
		answers++;
	}
	
	public int getAnswers(){
		return answers;
	}
	
	public int getVotes(){
		return upvotes;
	}
	
	public void incrementVotes(){
		upvotes++;
	}
	
	public int getReplies() {
		return replies;
	}
	
	public void incrementReplies(){
		replies++;
	}
	
	public GregorianCalendar getTime(){	
		return dateCreated;
	}
}
