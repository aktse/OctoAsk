package cs.ualberta.octoaskt12;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

import cs.ualberta.octoaskt12.R;

public class GeoAct extends Activity implements LocationListener,
GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener {

	
TextView mDisplayTextView;
	
    LocationRequest mLocationRequest;
    LocationClient mLocationClient;
    
    
	static final int REQUEST_CODE_GPS_APK = 6969;
	
	//contains quality of service

    Location mCurrentLocation;
    
    private Dialog mDialog;
    // Default constructor. Sets the dialog field to null

	protected double longitude;

	protected double latitude;


	
	@Override
	protected void onCreate(Bundle savedinstancestate){
		super.onCreate(savedinstancestate);

		Double locationlatitude;
		Double locationlongitude;
		 String locality;
		
		 //locationlatitude = null;
		 //locationlongitude = null;
		 //locality = "locality";
		
		
		setContentView(R.layout.activity_geo);
		//setContentView(R.activity_geo);

		   /*
         * Create a new location client, using the enclosing class to
         * handle callbacks.
         */
		
		
        mLocationRequest = LocationRequest.create();
        //create default parameters/ block accuracy/
        //mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

		mLocationClient = new LocationClient(this, this, this);
		
	}
	
	/*
    public static class ErrorDialogFragment extends DialogFragment {
        // Global field to contain the error dialog
        private Dialog mDialog;
        // Default constructor. Sets the dialog field to null
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }
        // Set the dialog to display
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }
        // Return a Dialog to the DialogFragment.
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }*/
    
	
	@Override
	protected void onStart(){
        super.onStart();

		/*
	     * Called when the Activity becomes visible.
	     */
        
        //boolean gpsapk = isOk();
        
Log.i("bef","bef");
Log.i("bef","bef");

        
        int googlePayResult = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        
        
Log.i("aft","aft");
Log.i("aft","aft");


        if (googlePayResult == ConnectionResult.SUCCESS ) {
        	
            
        	Log.i("succ","succ");
        	Log.i("succ","succ");
        	
        	
        	mLocationClient.connect();
        	
            
        	Log.i("conn","conn");

        	Log.i("conn","conn");
        	Log.i("conn","conn");
        	Log.i("conn","conn");
        	Log.i("conn","conn");
            //mCurrentLocation = mLocationClient.getLastLocation();
        	
        	
            
        	Log.i("last","last");
        	Log.i("conn","conn");
        	Log.i("conn","conn");
        	Log.i("conn","conn");


        }
        /*
        else if(gpsapk = true){
        	mLocationClient.connect();
            mCurrentLocation = mLocationClient.getLastLocation();

        	
        }*/
        
        else{
			Toast.makeText(this, "Google Play is not Available", Toast.LENGTH_LONG).show();
        }
        
        /*
        Log.i(Boolean.toString(gpsapk == true),Boolean.toString(gpsapk == true));
        Log.i(Boolean.toString(gpsapk == true),Boolean.toString(gpsapk == true));
        Log.i(Boolean.toString(gpsapk == true),Boolean.toString(gpsapk == true));
        Log.i(Boolean.toString(gpsapk == true),Boolean.toString(gpsapk == true));
        Log.i(Boolean.toString(gpsapk == true),Boolean.toString(gpsapk == true));
        Log.i(Boolean.toString(gpsapk == true),Boolean.toString(gpsapk == true));
        Log.i(Boolean.toString(gpsapk == true),Boolean.toString(gpsapk == true));
        Log.i(Boolean.toString(gpsapk == true),Boolean.toString(gpsapk == true));
        Log.i(Boolean.toString(gpsapk == true),Boolean.toString(gpsapk == true));
        Log.i(Boolean.toString(gpsapk == true),Boolean.toString(gpsapk == true));*/
/*
       if(gpsapk==true){
        	mLocationClient.connect();
            mCurrentLocation = mLocationClient.getLastLocation();

        	
        }*/
        
    }

        //mLocationClient.connect();
        //mCurrentLocation = mLocationClient.getLastLocation();

        
	@Override
	protected void onResume(){
		super.onResume();
		
		LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		//longitude = location.getLongitude();
		//latitude = location.getLatitude();
		
		
		//Toast.makeText(this, location.getLatitude() +"," + location.getLongitude()+" : ", Toast.LENGTH_LONG).show();

		Geocoder geodude = new Geocoder(this);
		//List
		try {
			List<Address> geodudelocation = geodude.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
			for(Address geoaddress : geodudelocation){


			//Toast.makeText(this, location.getLatitude() +"," + location.getLongitude()+" : "+ geoaddress.getLocality(), Toast.LENGTH_LONG).show();
			}
			
			double x = locationdifference(location.getLatitude(),location.getLongitude(), 53.522458, -113.623004);
			longitude = location.getLongitude();
			latitude = location.getLatitude();
			//Toast.makeText(this, Double.toString(x), Toast.LENGTH_LONG).show();
			
        	//Log.i(Double.toString(x),Double.toString(x));


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Button next = (Button) findViewById(R.id.button1);
		next.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View view) {
				LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 

		    	Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				String locality = null;

		    
		    	//======
		    	
		        Geocoder coder = new Geocoder(getApplicationContext());
				List<Address> geocodeResults;
				
				try {
					geocodeResults = coder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
					for (Address address: geocodeResults){
						Log.d("Location", location.getLatitude() +"," + location.getLongitude() +" : " +address.getLocality());
						//Toast.makeText(this, location.getLatitude() +"," + location.getLongitude()+" : " +address.getLocality(), Toast.LENGTH_LONG).show();

						Double locationlatitude = location.getLatitude();
						Double locationlongitude = location.getLongitude();
						locality = address.getLocality();
						//TextView mDisplayTextView = (TextView) findViewById(R.id.displayTextViews);
						//String q = location.getLatitude() +"," + location.getLongitude()+" : " +address.getLocality();
						//mDisplayTextView.setText(q);
					}
					}
					catch  (Exception e) { // http://developer.android.com/reference/android/content/IntentSender.html
						// Log the error
						//description of intent not being able to send or unable to process request 
		                e.printStackTrace();
					}
		    	
		    	
		    	//=====

				longitude = location.getLongitude();
				latitude = location.getLatitude();
				
		        //Intent intent = new Intent(getApplicationContext(),CreateQuestionActivity.class);
		        //intent.putExtra("Tag", "Value");
				
		        //Toast.makeText(getApplicationContext(), longitude +"," + latitude+" : " , Toast.LENGTH_LONG).show();
				//Toast.makeText(getApplicationContext(), location.getLatitude() +"," + location.getLongitude()+" : ", Toast.LENGTH_LONG).show();

		        //startActivity(intent);
				
				Intent intent = new Intent();
				intent.putExtra("Latitude", latitude);
				intent.putExtra("Longitude", longitude);
				intent.putExtra("Locality", locality);

			    setResult(REQUEST_CODE_GPS_APK, intent);

				//REQUEST_CODE_GPS_APK
				
		        finish();
		    }
		    
		});
		
		
		
	}
	
	@Override
	protected void onStop(){
			

        mLocationClient.disconnect();
        super.onStop();

	}

	
    /*
     * Called by Location Services if the attempt to
     * Location Services fails.
     */
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		// TODO Auto-generated method stub
		 /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
		
		/*
		if (connectionResult.hasResolution()){
			
			try{
	            connectionResult.startResolutionForResult(
                        this,
                        REQUEST_CODE_GPS_APK);
			}//try
			
			catch  (IntentSender.SendIntentException e) { // http://developer.android.com/reference/android/content/IntentSender.html
				// Log the error
				//description of intent not being able to send or unable to process request 
                e.printStackTrace();
                
            }// catch
			
		}// if hasresolution
		else{
			
			   
             //* If no resolution is available, display a dialog to the
             //* user with the error.
             //
	        Toast.makeText(this, "Well... SHIT..", Toast.LENGTH_SHORT).show();

		}*/
		
	}//onconnectionfailed	
		
	//

	
    /*
     * Called by Location Services when the request to connect the
     * client finishes successfully. At this point, you can
     * request the current location or start periodic updates
     */
	@Override
	public void onConnected(Bundle dataBundle) {
        // Display the connection status
	    //mLocationClient.requestLocationUpdates(mLocationRequest, this);
        mCurrentLocation = mLocationClient.getLastLocation();

        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
        Log.i("oi","oi");
        Log.i("bef","bef");
        Log.i("bef","bef");

        //Toast.makeText(this, (CharSequence) mCurrentLocation,
         //       Toast.LENGTH_SHORT).show();
        

		
	}// on connected

	
	/*
     * Called by Location Services if the connection to the
     * location client drops because of an error.
     */
	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
        Toast.makeText(this, "Disconnected. Please re-connect.",
                Toast.LENGTH_SHORT).show();
        
		
	}// on disconnected
	
	
	/*
	Context classcontext = getApplicationContext();;
	Activity classactivity = this;*/
	

	/*
	public boolean isOk(){
		
		boolean hasAPK = false;
		
		System.out.println("our class context" + classcontext);
		
		int GPSavailability = GooglePlayServicesUtil.isGooglePlayServicesAvailable(classcontext);

		System.out.println("GPS availability" + GPSavailability);

		if(GPSavailability == ConnectionResult.SUCCESS){
			
			System.out.println("inside if statement in isOK method");
			hasAPK = true;
			
			System.out.println("return True");

			return hasAPK;
			
			
		}else{
			
			System.out.println("inside else statement in isOKmethod");
			System.out.println("print classactivity" + classactivity.getClass());
			System.out.println("print classcontext" + classcontext.getClass());


			if(GooglePlayServicesUtil.isUserRecoverableError(GPSavailability)){
			
			//if not working try:
			//parent activity instead of classcontext for second argument
			
			GooglePlayServicesUtil.getErrorDialog(GPSavailability, classactivity, REQUEST_CODE_GPS_APK).show();
			//get error dialog should  which case Google Play services may send a result back to your activity. To handle this result, override the method onActivityResult().
			// you need a result code to identify 	
			
			}else{
				
				Toast.makeText(classcontext, "Please install the the recent Google Play Service",  Toast.LENGTH_SHORT).show();
			}//else2
			
			System.out.println("return False");

			hasAPK = false;
			
			
		}//else1
		
		
		return hasAPK;
	}//is ok */
	
	
	
	//check if gspapk is available
	/*
	 private boolean servicesConnected() {
	        // Check that Google Play services is available
	        int resultCode =
	                GooglePlayServicesUtil.
	                        isGooglePlayServicesAvailable(this);
	        // If Google Play services is available
	        if (ConnectionResult.SUCCESS == resultCode) {
	            // In debug mode, log the status
	            Log.d("Location Updates",
	                    "Google Play services is available.");
	            // Continue
	            return true;
	        // Google Play services was not available for some reason.
	        // resultCode holds the error code.
	        } else {
	            // Get the error dialog from Google Play services
	            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
	                    resultCode,
	                    this,
	                    REQUEST_CODE_GPS_APK);

	            // If Google Play services can provide an error dialog
	            if (errorDialog != null) {
	                // Create a new DialogFragment for the error dialog
	               // ErrorDialogFragment errorFragment =
	                 //       new ErrorDialogFragment();
	                // Set the dialog in the DialogFragment
	                //errorFragment.setDialog(errorDialog);
	                // Show the error dialog in the DialogFragment
	                //errorFragment.show(getSupportFragmentManager(),
	                  //      "Location Updates");
	            }
	            
	            }
	        return false;
	        }*/
	
	
	//you need to turn this into an activity and 
	//have an onactivityresult
	
	
	//http://developer.android.com/training/location/retrieve-current.html
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		System.out.println("inside on activityresult");
		
		switch(requestCode){
		
			case REQUEST_CODE_GPS_APK :
				System.out.println("inside switch in onactivity result, taking in the request code case REQUEST_CODE_GPS_APK");
					if (resultCode == Activity.RESULT_CANCELED){
						Toast.makeText(this, "fix google play services apk", Toast.LENGTH_SHORT).show();
						finish();
						return;
					}//if case rquest_code_gps_apk
					else if (resultCode == Activity.RESULT_OK){
						//go to previous activity through intent  and try the gps
						// no maybe try isok method
						return;

					}//else if
					break;
		
		}// switch(requestcode)
	
	
	}//onActivity Result
	
	//http://stackoverflow.com/questions/2227292/how-to-get-latitude-and-longitude-of-the-mobiledevice-in-android	
	//by Dave Webbfa
	
private final LocationListener locationListener = new LocationListener() {
    public void onLocationChanged(Location location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }
    };
	
	@Override
	public void onLocationChanged(Location location) {
        Geocoder coder = new Geocoder(getApplicationContext());
		List<Address> geocodeResults;
		try {
			geocodeResults = coder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
			for (Address address: geocodeResults){
				Log.d("Location", location.getLatitude() +"," + location.getLongitude() +" : " +address.getLocality());
				//Toast.makeText(this, location.getLatitude() +"," + location.getLongitude()+" : " +address.getLocality(), Toast.LENGTH_LONG).show();

				Double locationlatitude = location.getLatitude();
				Double locationlongitude = location.getLongitude();
				String locality = address.getLocality();
				//TextView mDisplayTextView = (TextView) findViewById(R.id.displayTextViews);
				//String q = location.getLatitude() +"," + location.getLongitude()+" : " +address.getLocality();
				//mDisplayTextView.setText(q);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		Intent intent = new Intent(GeoAct.this,
				CreateQuestionActivity.class);
		startActivity(intent);	*/
	}
	
	//http://rosettacode.org/wiki/Haversine_formula
	//http://stackoverflow.com/questions/17787235/creating-a-method-using-haversine-formula-android-v2/17787472#17787472 by Scott Helme
	//http://stackoverflow.com/questions/22577075/calculating-the-distance-between-two-latitude-and-longitude-points-in-android by David and Emilio
	
	public double locationdifference(double initialLat, double initialLong,
            double finalLat, double finalLong){
	
		Location locationA = new Location("point A");     
		locationA.setLatitude(initialLat); 
		locationA.setLongitude(initialLong);
		
		Location locationB = new Location("point B");
		
		locationB.setLatitude(finalLat); 
		locationB.setLongitude(finalLong);
		
		double distance = locationA.distanceTo(locationB) ;
		
		return distance;
	}
	

}
