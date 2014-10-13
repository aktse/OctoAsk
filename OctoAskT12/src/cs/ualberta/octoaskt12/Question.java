package cs.ualberta.octoaskt12;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8648225441126206210L;
	private String questionTitle;
	private String questionBody;
	private int numVotes;
	private GregorianCalendar dateCreated;
	private User user;
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	private ArrayList<Reply> replies = new ArrayList<Reply>();
	
	public Question(String questionTitle, String questionBody, User user) {
		this.questionTitle = questionTitle;
		this.questionBody = questionBody;
		this.dateCreated = new GregorianCalendar();
		this.user = user;
	}
	
	/***************************************************************************
 	* Text
 	***************************************************************************/
	
	public void setTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	
	public String getTitle() {
		return questionTitle;
	}
	
	public void setBody(String questionBody) {
		this.questionBody = questionBody;
	}
	
	public String getBody() {
		return questionBody;
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
 	* Answers
 	***************************************************************************/
	
	public int getNumAnswers() {
		return answers.size();
	}
	
	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	
	public void addAnswer(Answer answer) {
		answers.add(answer);
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
	
	public GregorianCalendar getTime() {
		return dateCreated;
	}

	
	
}
