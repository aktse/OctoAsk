package cs.ualberta.octoaskt12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CreateAnswerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_answer);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Button submitButton = (Button) findViewById(R.id.submitAnswerButton);
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				processIntent(true);	
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_answer, menu);
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
	
	public void processIntent(boolean submitPressed) {
		Intent intent = getIntent();
		if (intent != null) {
			try {
				if (submitPressed) {
					EditText answerBodyText = (EditText) findViewById(R.id.answerBodyText);
					String answerBody = answerBodyText.getText().toString();
					Intent returnIntent = new Intent();
					returnIntent.putExtra("answerBody", answerBody);
					setResult(RESULT_OK, returnIntent);
				}
			} catch (Exception e) {
				setResult(RESULT_CANCELED);
				finish();
			}
		}
		finish();
	}
	
//	public void submitAnswer(View v) {
//		EditText answerBodyText = (EditText) findViewById(R.id.questionBodyText);
//		String answerBody = answerBodyText.getText().toString();
//	}
	
	public void cancelAnswer(View v) {
		
	}
}
