package cs.ualberta.octoaskt12;

<<<<<<< HEAD
import java.util.ArrayList;


public class Answer {
	private String answerBody = "";
	private int upvote = 0;
	
	// An answer contains replies
	ArrayList<Reply> replies = new ArrayList<Reply>();

	// get the body of the answer
	public String getAnswerBody() {
		return answerBody;
	}
	
	// set the body of the answer
	public void setAnswerBody(String answerBody) {
		this.answerBody = answerBody;
	}
	
	// get the count of the upVote for the answer
	public int getUpVote() {
		return upvote;
	}
	
	// increment the count of upVote for the answer
	public void incrementUpVote() {
		this.upvote++;
=======
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
>>>>>>> beae88ca4458365291a2f48cac9adabcffe17f16
	}
	
}
