package cs.ualberta.octoaskt12;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2652571757539537096L;
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
