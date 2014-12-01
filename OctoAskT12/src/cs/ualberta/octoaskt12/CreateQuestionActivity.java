package cs.ualberta.octoaskt12;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateQuestionActivity extends Activity {
	Uri imageFileUri;
	private final int GEO_ACTIVITY_REQUEST_CODE = 6969;

	double latitude;
	double longitude;
	String locality;

	private final int CAMERA_ACTIVITY_REQUEST_CODE = 12345;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_question);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		ImageButton button = (ImageButton) findViewById(R.id.question_ImageButton);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				takePicture();

			}
		});
	}

	public void takePicture() {

		String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/MyCameraTest";
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdir();
		}

		String imagePathAndFileName = path + File.separator
				+ String.valueOf(System.currentTimeMillis()) + ".jpg";

		File imageFile = new File(imagePathAndFileName);
		imageFileUri = Uri.fromFile(imageFile);

		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Intent getPictureIntent = new Intent(Intent.ACTION_PICK,
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		startActivityForResult(getPictureIntent, CAMERA_ACTIVITY_REQUEST_CODE);

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == GEO_ACTIVITY_REQUEST_CODE) {

			latitude = data.getExtras().getDouble("Latitude");
			longitude = data.getExtras().getDouble("Longitude");
			locality = data.getExtras().getString("Locality");
			Toast.makeText(
					getApplicationContext(),
					locality + " " + Double.toString(longitude) + " "
							+ Double.toString(latitude), Toast.LENGTH_SHORT)
					.show();

		}

		if (requestCode == CAMERA_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				/*
				 * partial inspiration from
				 * http://viralpatel.net/blogs/pick-image
				 * -from-galary-android-app/ but mostly from the Android API
				 * Developer Guides
				 */
				Uri image = data.getData();
				String[] filePath = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(image, filePath,
						null, null, null);
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePath[0]);
				String picturePath = cursor.getString(columnIndex);
				ImageView iv = (ImageView) findViewById(R.id.question_ImageView);
				Bitmap bm = BitmapFactory.decodeFile(picturePath);
				int bitmapSize = bm.getByteCount();
				if (bitmapSize > 1200000) {
					Toast.makeText(this, "Picture is too big!",
							Toast.LENGTH_SHORT).show();
				} else {
					iv.setImageBitmap(bm);
				}
			}
		}
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

	public void addGeo(View v) {

		Intent intent = new Intent(CreateQuestionActivity.this, GeoAct.class);

		startActivityForResult(intent, GEO_ACTIVITY_REQUEST_CODE);

	}

	public void submitQuestionAction(View v) {
		EditText titleEditText = (EditText) findViewById(R.id.questionTitleText);
		EditText bodyEditText = (EditText) findViewById(R.id.questionBodyText);
		String questionTitle = titleEditText.getText().toString();
		String questionBody = bodyEditText.getText().toString();
		User user = UserController.getCurrentUser();
		Question question = new Question(questionTitle, questionBody, user);

		question.setLocation(locality);
		question.setLongitude(longitude);
		question.setLatitude(latitude);

		Toast.makeText(
				getApplicationContext(),
				locality + " " + Double.toString(longitude) + " "
						+ Double.toString(latitude), Toast.LENGTH_SHORT).show();

		ImageView iv = (ImageView) findViewById(R.id.question_ImageView);
		if (iv.getDrawable() != null) {
			Bitmap picture = ((BitmapDrawable) iv.getDrawable()).getBitmap();
			if (picture != null) {
				question.setImage(picture);
			}
		}

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();

		// have connection
		if (ni != null) {

			QuestionsController.addQuestion(question);
			Toast.makeText(getBaseContext(),
					"Connected to internet,  question added.",
					Toast.LENGTH_SHORT).show();

		}
		// no connection
		else {
			QuestionsCacheManager qcm = new QuestionsCacheManager(
					getApplicationContext());
			qcm.loadQuestions();
			qcm.addQuestion(question);
			qcm.clear();
			qcm.saveQuestions();

			Toast.makeText(getBaseContext(),
					"No onnection found,  question cached.", Toast.LENGTH_SHORT)
					.show();
		}

		onBackPressed();
		System.out.println("returning to main activity");
	}
}
