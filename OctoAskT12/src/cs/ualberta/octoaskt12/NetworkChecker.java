package cs.ualberta.octoaskt12;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkChecker extends CreateQuestionActivity {
	private Context context;

	// checks for the availability of the network connection
	// returns a boolean

	public NetworkChecker(Context context) {
		this.context = context.getApplicationContext();
	}

	public Boolean isAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();

		if (ni == null)
			return false;
		else {
			return true;
		}
	}
}