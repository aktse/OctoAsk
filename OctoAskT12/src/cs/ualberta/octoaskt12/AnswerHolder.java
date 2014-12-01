package cs.ualberta.octoaskt12;

// Placeholder class used to pass a specific answer around activites
// Temporarily holds answers that have a high chance of being used

public class AnswerHolder {
	private Answer answer = null;
	// lazy singleton
	private static AnswerHolder answerHolder = null;

	public static AnswerHolder getInstance() {
		if (answerHolder == null) {
			answerHolder = new AnswerHolder();
		}
		return answerHolder;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
}
