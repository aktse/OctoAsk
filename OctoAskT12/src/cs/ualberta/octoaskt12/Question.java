package cs.ualberta.octoaskt12;

<<<<<<< HEAD
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
=======
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
	
>>>>>>> beae88ca4458365291a2f48cac9adabcffe17f16
}
