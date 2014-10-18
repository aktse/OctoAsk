package cs.ualberta.octoaskt12;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2652571757539537096L;
	private String name;
	// questions asked by the user
	private QuestionArrayList questions = new QuestionArrayList();
	// favourite questions by the user
	private QuestionArrayList favouriteQuestions = new QuestionArrayList();
	
	// constructor
	public User(String name) {
		// set the name of the user
		this.name = name;
	}
	
	// get the name of the user
	public String getName() {
		return name;
	}
	
	// get the user's questions, in the form of an ArrayList
	public ArrayList<Question> getUserQuestions() {
		return questions.getQuestions();
		
	}
	
	// get the user's favourited questions
	public ArrayList<Question> getFavouriteQuestions() {
		return favouriteQuestions.getQuestions();
	}

	public void addQuestion(Question question) {
		// TODO Auto-generated method stub
		
	}

}
