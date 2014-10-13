package cs.ualberta.octoaskt12;

import java.util.ArrayList;

public class QuestionArrayList {
	
	private ArrayList<Question> questions = new ArrayList<Question>();
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	public void addQuestion(Question question) {
		this.questions.add(question);
	}
	
}
