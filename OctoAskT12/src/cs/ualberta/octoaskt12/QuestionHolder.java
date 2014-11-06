package cs.ualberta.octoaskt12;

// use to pass a specific question around activites
public class QuestionHolder {
	private Question question = null;
	// lazy singleton
	private static QuestionHolder questionHolder = null;
	public static QuestionHolder getInstance(){
		if (questionHolder == null){
			questionHolder = new QuestionHolder();
		}
		return questionHolder;
	}
	
	public Question getQuestion(){
		return question;
	}
	
	public void setQuestion(Question question){
		this.question = question;
	}
}
