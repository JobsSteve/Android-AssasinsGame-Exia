package exia.nancy.caribous.applis.android.assassins;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;

public class SignUpActivity extends Activity {

	Location location;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		// Location automatique
		LocationManager loma = (LocationManager) getSystemService(LOCATION_SERVICE);

		Criteria criteria = new Criteria();
		criteria.setSpeedAccuracy(Criteria.NO_REQUIREMENT);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		criteria.setAltitudeRequired(false);
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		String provider = loma.getBestProvider(criteria, false);
		location = loma.getLastKnownLocation(provider);

		new AsyncTask<String, String, String>() {

			@Override
			protected String doInBackground(String... params) {
				try {
					URL url = new URL(
							"http://maps.google.com/maps/geo?output=xml&oe=utf-8&ll="
									+ location.getLatitude() + ","
									+ location.getLongitude() + "&key="
									+ R.string.google_maps_api_key);
					URLConnection urlconn = url.openConnection();
					Reader is = new InputStreamReader(new BufferedInputStream(
							urlconn.getInputStream()), "UTF-8");
					StringBuilder stringBuild = new StringBuilder();
					
					char[] buffer = new char[65535];
					
					int read = is.read(buffer);
					while (read > 0) {
						stringBuild.append(buffer);
						read = is.read(buffer);
					}
					stringBuild.append(buffer);
					
					return stringBuild.toString();
				} catch (Exception e) {
					// Gotta catch 'em all
					e.printStackTrace();
				}
				return "";
			}

		}.execute("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_sign_up, menu);
		return true;
	}
}
