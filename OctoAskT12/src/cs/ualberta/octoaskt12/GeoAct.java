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
    Location mCurrentLocation;
    private Dialog mDialog;
	protected double longitude;
	protected double latitude;
	
	@Override
	protected void onCreate(Bundle savedinstancestate){
		super.onCreate(savedinstancestate);
		setContentView(R.layout.activity_geo);


		Double locationlatitude;
		Double locationlongitude;
		String locality;
		
		   /*
         * Create a new location client, using the enclosing class to
         * handle callbacks.
         */
		
        mLocationRequest = LocationRequest.create();

		mLocationClient = new LocationClient(this, this, this);
		
	}
	
	@Override
	protected void onStart(){
        super.onStart();

        int googlePayResult = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (googlePayResult == ConnectionResult.SUCCESS ) {
        	
        	mLocationClient.connect();
        	
        }
        
        else{
			Toast.makeText(this, "Google Play is not Available", Toast.LENGTH_LONG).show();
        }
        
    }

        
	@Override
	protected void onResume(){
		super.onResume();
		
		LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		Geocoder geodude = new Geocoder(this);
		//List
		try {
			List<Address> geodudelocation = geodude.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
			for(Address geoaddress : geodudelocation){

			}
			
			double x = locationdifference(location.getLatitude(),location.getLongitude(), 53.522458, -113.623004);
			longitude = location.getLongitude();
			latitude = location.getLatitude();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//BUTTON
		Button next = (Button) findViewById(R.id.button1);
		next.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View view) {
				LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 

		    	Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				String locality = null;

		        Geocoder coder = new Geocoder(getApplicationContext());
				List<Address> geocodeResults;
				
				try {
					geocodeResults = coder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
					for (Address address: geocodeResults){
						
						Double locationlatitude = location.getLatitude();
						Double locationlongitude = location.getLongitude();
						locality = address.getLocality();
					}
					}
					catch  (Exception e) { // http://developer.android.com/reference/android/content/IntentSender.html
						// Log the error
						//description of intent not being able to send or unable to process request 
		                e.printStackTrace();
					}
		    	
				longitude = location.getLongitude();
				latitude = location.getLatitude();

				Intent intent = new Intent();
				intent.putExtra("Latitude", latitude);
				intent.putExtra("Longitude", longitude);
				intent.putExtra("Locality", locality);
			    setResult(REQUEST_CODE_GPS_APK, intent);
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
     * Called by Location Services when the request to connect the
     * client finishes successfully. At this point, you can
     * request the current location or start periodic updates
     */
	@Override
	public void onConnected(Bundle dataBundle) {
        mCurrentLocation = mLocationClient.getLastLocation();

        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
		
	}// on connected

	
	/*
     * Called by Location Services if the connection to the
     * location client drops because of an error.
     */
	@Override
	public void onDisconnected() {
        Toast.makeText(this, "Disconnected. Please re-connect.",
                Toast.LENGTH_SHORT).show();
        
		
	}// on disconnected

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

				Double locationlatitude = location.getLatitude();
				Double locationlongitude = location.getLongitude();
				String locality = address.getLocality();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

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

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		
	}
	

}
