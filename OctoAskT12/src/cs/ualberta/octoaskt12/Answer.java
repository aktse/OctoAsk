package cs.ualberta.octoaskt12;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Answer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5590593466133278091L;
	private String answerBody;
	// user who answered the question
	private User user;
	// number of up votes on this  answer
	private int numVotes;
	// the replies for this answer
	private ArrayList<Reply> replies = new ArrayList<Reply>();
	// the date the answer was created
	private GregorianCalendar dateCreated;
	
	// constructor
	public Answer(String answerBody, User user) {
		// set the answer body
		this.answerBody = answerBody;
		// set the date the answer was created
		this.dateCreated = new GregorianCalendar();
		// set the user who answered the question
		this.user = user;
	}
	
	/***************************************************************************
 	* This method concerns the body of the answer.
 	***************************************************************************/
	// get the answer body
	public String getBody() {
		return answerBody;
	}
	
	// set the answer body
	public void setBody(String answerBody) {
		this.answerBody = answerBody;
	}
	
	/***************************************************************************
 	* These methods are responsible for getting the up votes of the answer and
	* incrementing up vote whenever a user votes up an answer.
 	***************************************************************************/
	
	// get the number of up votes for this answer
	public int getVotes() {
		return numVotes;
	}
	
	// increment the up votes for an answer
	public void incrementVotes() {
		numVotes++;
	}
	
	/***************************************************************************
 	*These methods concerns the replies associated with the answer.
 	***************************************************************************/
	// get the number of replies the answer has.
	public int getNumReplies() {
		return replies.size();
	}
	// get all the replies for this answer, in the form of an array
	public ArrayList<Reply> getReplies() {
		return replies;
	}
	// add a reply to the reply array list
	public void addReply(Reply reply) {
		replies.add(reply);
	}
	
	/***************************************************************************
 	* Methods which get the user's name that answered the question and the date
 	* the answer was created.
 	***************************************************************************/
	// get the user that created the answer
	public String getUser() {
		return user.getName();
	}
	// get the date the question was created
	public GregorianCalendar getTime(){	
		return dateCreated;
	}
	
}
