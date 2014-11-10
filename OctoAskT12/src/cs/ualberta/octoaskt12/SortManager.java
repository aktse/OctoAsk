package cs.ualberta.octoaskt12;

import java.util.Collections;
import java.util.Comparator;

public class SortManager {

	QuestionArrayList sortedQuestionsArrayList = null;

	public QuestionArrayList SortByDate(QuestionArrayList questionsArrayList) {

		sortedQuestionsArrayList = questionsArrayList;

		Collections.sort(sortedQuestionsArrayList.getQuestions(),
				new Comparator<Question>() {

					@Override
					public int compare(Question question1, Question question2) {

						return question2.getTime().compareTo(
								question1.getTime());
					}
				});

		return sortedQuestionsArrayList;
	}

	public QuestionArrayList SortByVotes(QuestionArrayList questionsArrayList) {

		sortedQuestionsArrayList = questionsArrayList;

		Collections.sort(sortedQuestionsArrayList.getQuestions(),
				new Comparator<Question>() {

					@Override
					public int compare(Question question1, Question question2) {

						return question2.getVotes() - question1.getVotes();
					}
				});

		return sortedQuestionsArrayList;
	}

	public QuestionArrayList SortByImages(QuestionArrayList questionsArrayList) {

		sortedQuestionsArrayList = questionsArrayList;

		Collections.sort(sortedQuestionsArrayList.getQuestions(),
				new Comparator<Question>() {

					@Override
					public int compare(Question question1, Question question2) {

						return question1.imageExists()
								- question2.imageExists();
					}
				});

		return sortedQuestionsArrayList;
	}

}
