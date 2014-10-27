package cs.ualberta.octoaskt12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class CreateQuestionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_question);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_question, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == android.R.id.home) {
			onBackPressed();
		}
		return super.onOptionsItemSelected(item);
	}

	// Cancel the submission of a question
	public void cancelQuestionAction(View v) {
		onBackPressed();
	}

	public void submitQuestionAction(View v) {
		EditText titleEditText = (EditText) findViewById(R.id.questionTitleText);
		EditText bodyEditText = (EditText) findViewById(R.id.questionBodyText);
		String questionTitle = titleEditText.getText().toString();
		String questionBody = bodyEditText.getText().toString();
		User user = UserArrayList.getCurrentUser();
		Question question = new Question(questionTitle, questionBody, user);
		QuestionsController.addQuestion(question);
		onBackPressed();
	}
}
