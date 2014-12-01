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
	
	public void addToFront(Question question) {
		this.questions.add(0,question);
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

	public boolean has(Question question) {
		return questions.contains(question);
	}
	
	public ArrayList<Question> searchQuestion(String searchTerm) {
		ArrayList<Question> arrayListQuestions = new ArrayList<Question>();
		for (Question question: this.questions){
			if (question.getBody().equals(searchTerm)) {
				arrayListQuestions.add(question);
			}
		}
		
		return arrayListQuestions;		
	}
	
	// the function below will be used by ReadLater class
	public int remove(Question question)
	{
		if (questions.contains(question))
		{
			questions.remove(question);
			
			// returns 1 if question successfully removed
			return 1;
		}
		else
		{
			// returns 0 if question does not exist in favorite
			return 0;
		}
	}
	
	public int searchQuestionIndexById(String questionId)
	{
		int count = 0;
		for (Question question : questions)
		{
			if (question.getId().equals(questionId))
			{
				break;
			}
		}
		
		return count;
	}
	
	public void removeQuestionByIndex(int index)
	{
		questions.remove(index);
	}
	
	public int getIndexById(String ID)
	{
		boolean found = false;
		int index = 0;
		for (Question question : this.questions)
		{
			if (question.getId().equals(ID))
			{
				found = true;
				break;
			}
			index += 1;
		}
		if (found == true)
		{
			return index;
		}
		else
		{
			return -1;
		}
	}
}
