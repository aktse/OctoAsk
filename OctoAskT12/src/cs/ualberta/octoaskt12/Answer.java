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
	private User user;
	private int numVotes;
	private ArrayList<Reply> replies = new ArrayList<Reply>();
	private GregorianCalendar dateCreated;
	
	public Answer(String answerBody, User user) {
		this.answerBody = answerBody;
		this.dateCreated = new GregorianCalendar();
		this.user = user;
	}
	
	/***************************************************************************
 	* Text
 	***************************************************************************/
	
	public String getBody() {
		return answerBody;
	}
	
	public void setBody(String answerBody) {
		this.answerBody = answerBody;
	}
	
	/***************************************************************************
 	* Votes
 	***************************************************************************/
	
	public int getVotes() {
		return numVotes;
	}
	
	public void incrementVotes() {
		numVotes++;
	}
	
	/***************************************************************************
 	* Replies
 	***************************************************************************/
	
	public int getNumReplies() {
		return replies.size();
	}
	
	public ArrayList<Reply> getReplies() {
		return replies;
	}
	
	public void addReply(Reply reply) {
		replies.add(reply);
	}
	
	/***************************************************************************
 	* Misc
 	***************************************************************************/
	
	public String getUser() {
		return user.getName();
	}
	
	public GregorianCalendar getTime(){	
		return dateCreated;
	}
	
}
