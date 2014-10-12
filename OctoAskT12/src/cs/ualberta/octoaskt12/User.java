package cs.ualberta.octoaskt12;

import java.util.ArrayList;


public class User {
	private String username = "";
	protected ArrayList<Question> myQuestions = new ArrayList<Question>();
	protected ArrayList<Answer> myAnswers = new ArrayList<Answer>();
	protected ArrayList<Reply> myReplies = new ArrayList<Reply>();
	
	
	public User(String name) {
		super();
		this.username = name;
	}
	
	// get the user's name
	public String getName() {
		return username;
	}
		
	// set the user's name
	public void setName(String name) {
		this.username = name;
	}
	
}
