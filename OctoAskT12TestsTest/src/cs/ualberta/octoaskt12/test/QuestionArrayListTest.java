package cs.ualberta.octoaskt12.test;

import cs.ualberta.octoaskt12.Answer;
import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.Reply;
import cs.ualberta.octoaskt12.User;
import junit.framework.TestCase;

public class QuestionArrayListTest extends TestCase {
	
	public void testAddQuestion() {
		Question q1 = new Question("sup bruh","neel",new User("ivan burrito"));
		QuestionArrayList questions = new QuestionArrayList();
		questions.addQuestion(q1);
		assertTrue("question not added to question list", questions.getQuestions().contains(q1));
		Question q2 = new Question("301","tatta", new User("4chuan"));
		questions.addQuestion(q2);
		assertTrue("question not added to question list", questions.getQuestions().contains(q2));
	}

	public void testAddAnswer() {
		Question q1 = new Question("sup bruh", "neel", new User("ivan burrito"));
		QuestionArrayList questions = new QuestionArrayList();
		questions.addQuestion(q1);
		
		Answer a1 = new Answer("some more stuff", new User("aoifhaoiehfoi"));
		q1.addAnswer(a1);
		assertTrue("answer not added to answer list", q1.getAnswers().contains(a1));
	
	}
	
	public void testAddReply() {
		Question q1 = new Question("sup bruh", "neel", new User("ivan burrito"));
		QuestionArrayList questions = new QuestionArrayList();
		questions.addQuestion(q1);
		
		Reply r1 = new Reply("reply 1", new User("ivan"));
		q1.addReply(r1);
		assertTrue("reply not added to question", q1.getReplies().contains(r1));
		Answer a1 = new Answer("some more stuff", new User("aoifhaoiehfoi"));
		q1.addAnswer(a1);
		
		int aIndex =  q1.getAnswers().indexOf(a1);
//		ArrayList<Question> answers = q1.getAnswers();
//		get(answers.indexOf(a1));
		Reply r2 = new Reply("reply 1", new User("ivan"));
		q1.getAnswers().get(aIndex).addReply(r2);
		
		assertTrue("reply not added to answer", q1.getAnswers().get(aIndex).getReplies().contains(r2));
		
	}
	
}
