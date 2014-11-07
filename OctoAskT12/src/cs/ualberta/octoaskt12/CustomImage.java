package cs.ualberta.octoaskt12;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
public class CustomImage extends Activity{
	Uri imageFileUri;
	private Activity mContext;
	private final int CAMERA_ACTIVITY_REQUEST_CODE =  12345;
	
	public void TakePicture(Activity activity){
		this.mContext = activity;
		String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyCameraTest";
		File folder = new File(path);
		if(!folder.exists()){
			folder.mkdir();
		}
		
		String imagePathAndFileName = path + File.separator + 
				String.valueOf(System.currentTimeMillis()) + ".jpg";
		
		File imageFile = new File(imagePathAndFileName);
		imageFileUri = Uri.fromFile(imageFile);
		
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
	    mContext.startActivityForResult(takePictureIntent, CAMERA_ACTIVITY_REQUEST_CODE);
	    
		
		}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		System.exit(10);
		Log.v("sdfsd","sdfsdfsdf");
		Toast.makeText(getBaseContext(), "captured", Toast.LENGTH_SHORT).show();
		if(requestCode == CAMERA_ACTIVITY_REQUEST_CODE){
			if(resultCode == RESULT_OK){
				ImageView iv = (ImageView) findViewById(R.id.question_ImageView);
				iv.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
			}
			else{
				if(resultCode == RESULT_CANCELED){
					// do nothing
				}
			}
		}
			
	}
}
