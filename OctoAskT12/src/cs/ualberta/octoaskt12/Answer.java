package cs.ualberta.octoaskt12;

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
	}
	
}
