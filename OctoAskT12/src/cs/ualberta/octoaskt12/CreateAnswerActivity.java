package cs.ualberta.octoaskt12;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateAnswerActivity extends Activity {

    private final int GEO_ACTIVITY_REQUEST_CODE = 6969;

	   double latitude;
       double longitude;
       String locality;
    
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
					returnIntent.putExtra("location", locality);
					returnIntent.putExtra("latitude", latitude);
					returnIntent.putExtra("longitude", longitude);

					setResult(RESULT_OK, returnIntent);
				}
			} catch (Exception e) {
				setResult(RESULT_CANCELED);
				finish();
			}
		}
		finish();
	}

	public void cancelAnswer(View v) {
		onBackPressed();
	}
	
	public void addGeo(View v) {
		
		Intent intent = new Intent(CreateAnswerActivity.this,
				GeoAct.class);
		
		startActivityForResult(intent, GEO_ACTIVITY_REQUEST_CODE);		

}
	

	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(resultCode == GEO_ACTIVITY_REQUEST_CODE){
		
		   latitude  = data.getExtras().getDouble("Latitude");
	       longitude = data.getExtras().getDouble("Longitude");
	       locality  = data.getExtras().getString("Locality");
	       Toast.makeText(getApplicationContext(), locality+" "+Double.toString(longitude)+" "+Double.toString(latitude), Toast.LENGTH_SHORT).show();

			
		}
			
	}
}
