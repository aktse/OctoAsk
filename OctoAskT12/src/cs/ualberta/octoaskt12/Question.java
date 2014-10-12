package cs.ualberta.octoaskt12;

import java.util.ArrayList;


public class Question {
	private String questionTitle = "";
	private String questionBody = "";
	private int upvote = 0;
	
	// A question contains answers, and replies
	protected ArrayList<Answer> answers = new ArrayList<Answer>();
	protected ArrayList<Reply> replies = new ArrayList<Reply>();
	
	// get the title of the question
	public String getQuestionTitle() {
		return questionTitle;
	}
	// set the title of the question
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	// get the body of the question
	public String getQuestionBody() {
		return questionBody;
	}
	
	// set the body of the question
	public void setQuestionBody(String questionBody) {
		this.questionBody = questionBody;
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
