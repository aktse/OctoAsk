package cs.ualberta.octoaskt12;

import java.util.ArrayList;

public class User {
	
	private String name;
	private QuestionArrayList questions = new QuestionArrayList();
	
	public User(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Question> getQuestions() {
		return questions.getQuestions();
	}
	
}
