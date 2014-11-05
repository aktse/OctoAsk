package cs.ualberta.octoaskt12;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
		Button submitReply = (Button) findViewById(R.id.submitReplyButton);
		submitReply.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				processIntent(true);
			}
		});
	}

	public void processIntent(boolean submitPressed) {
		Intent intent = getIntent();
		if (intent != null) {
			try {
				if (submitPressed) {
					EditText replyBodyText = (EditText) findViewById(R.id.replyBodyText);
					String replyBody = replyBodyText.getText().toString();
					int answerPos = intent.getIntExtra("answerPos", -1);
					Intent returnIntent = new Intent();
					returnIntent.putExtra("answerPos", answerPos);
					returnIntent.putExtra("replyBody", replyBody);
					setResult(RESULT_OK, returnIntent);
				}
			} catch (Exception e) {
				setResult(RESULT_CANCELED);
				finish();
			}
		}
		finish();
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
