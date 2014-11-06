package cs.ualberta.octoaskt12;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateReplyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_reply);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intent = getIntent();
		final String extraKey = intent.getStringExtra("replyFor");
		Button submitReply = (Button) findViewById(R.id.submitReplyButton);
		submitReply.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				EditText replyBodyText = (EditText) findViewById(R.id.replyBodyText);
				String replyBody = replyBodyText.getText().toString();
				// user wants to reply to an answer
				
				if (extraKey.equals("0")){
					AnswerHolder answerHolder = AnswerHolder.getInstance();
					Answer answer = answerHolder.getAnswer();
					answer.addReply(new Reply(replyBody, UserArrayList.getCurrentUser()));		
				}
				if (extraKey.equals("1")){
					QuestionHolder questionHolder = QuestionHolder.getInstance();
					Question question = questionHolder.getQuestion();
					question.addReply(new Reply(replyBody, UserArrayList.getCurrentUser()));
				}
				onBackPressed();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_reply, menu);
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
		return super.onOptionsItemSelected(item);
	}
	
	
	
}
