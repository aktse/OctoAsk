package cs.ualberta.octoaskt12;

import java.util.ArrayList;

public class QuestionArrayList {
	
	private ArrayList<Question> questions = new ArrayList<Question>();
	
	public QuestionArrayList() {
		
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	public void addQuestion(Question question) {
		this.questions.add(question);
	}
	
	public int getSize() {
		return questions.size();
	}
	
	public void clear() {
		questions.clear();
	}
	
	public Question get(int i) {
		return questions.get(i);
	}
	
	public void add(Question question) {
		questions.add(question);
	}
}
