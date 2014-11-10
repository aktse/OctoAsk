package cs.ualberta.octoaskt12.test;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import cs.ualberta.octoaskt12.Answer;
import cs.ualberta.octoaskt12.CustomImage;
import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.User;
import junit.framework.TestCase;

public class AddAnswersAndRepliesTests extends TestCase {

	// test, add images to questions
	public void testAddImagesToQuestions() {
		QuestionArrayList question_list = new QuestionArrayList();
		User mock_user = new User("Chris");
		ArrayList<Bitmap> comparableImageList = new ArrayList<Bitmap>();

		for (int i = 0; i < 20; i++) {
			Question temp_question = new Question("Q " + i, "Body " + i,
					mock_user);
			Bitmap temp_image = Bitmap.createBitmap(50, 50, Config.RGB_565);
			temp_question.setImage(temp_image);
			comparableImageList.add(temp_image);
			question_list.addQuestion(temp_question);
		}

		// test images exist and are correct
		for (int i = 0; i < 20; i++) {
			boolean imageExists = false;
			if (question_list.get(i).imageExists() == 1) {
				imageExists = true;
			}
			assertTrue(imageExists);
			assertEquals(question_list.get(i).getQuestionImage(),
					comparableImageList.get(i));
		}
	}

	// test, add images to questions
	public void testAddImagesToAnswers() {
		ArrayList<Answer> answer_list = new ArrayList<Answer>();
		User mock_user = new User("Chris");
		ArrayList<Bitmap> comparableImageList = new ArrayList<Bitmap>();

		for (int i = 0; i < 20; i++) {
			String str = "Body";
			Answer temp_answer = new Answer(str + i, mock_user);
			Bitmap temp_image = Bitmap.createBitmap(50, 50, Config.RGB_565);
			temp_answer.setImage(temp_image);
			comparableImageList.add(temp_image);
			answer_list.add(temp_answer);
		}

		// test images exist and are correct
		for (int i = 0; i < 20; i++) {
			boolean imageExists = false;
			if (answer_list.get(i).imageExists() == 1) {
				imageExists = true;
			}
			assertTrue(imageExists);
			assertEquals(answer_list.get(i).getAnswerImage(),
					comparableImageList.get(i));
		}
	}
}
