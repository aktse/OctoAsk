package cs.ualberta.octoaskt12;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionArrayList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1069372161964111294L;
	private ArrayList<Question> questions = new ArrayList<Question>();
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	public void addQuestion(Question question) {
		this.questions.add(question);
	}
	
	public Question getQuestion(int position) {
		return getQuestions().get(position);
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

	public ArrayList<Question> searchQuestion(String searchTerm) {
		return null;
		// TODO Auto-generated method stub
		
	}
}
