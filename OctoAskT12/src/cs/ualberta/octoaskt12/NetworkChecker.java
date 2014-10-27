package cs.ualberta.octoaskt12;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkChecker extends MainActivity{
	//private Context context;
	
	// checks for the availability of the network connection
	// returns a boolean
	private Boolean isAvailable()
	{
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		
		if (ni == null)
			return false;
		else
		{
			return true;
		}
	}
}