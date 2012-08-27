package exia.nancy.caribous.applis.android.assassins;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SignUpActivity extends Activity {

	Location location;

	private final int PICK_IMAGE = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		new AsyncTask<String, String, String[]>() {

			@Override
			protected String[] doInBackground(String... params) {

				ArrayList<String> results = new ArrayList<String>();

				try {
					// Location automatique
					LocationManager loma = (LocationManager) getSystemService(LOCATION_SERVICE);

					Criteria criteria = new Criteria();
					criteria.setSpeedAccuracy(Criteria.NO_REQUIREMENT);
					criteria.setPowerRequirement(Criteria.POWER_LOW);
					criteria.setAltitudeRequired(false);
					criteria.setAccuracy(Criteria.ACCURACY_COARSE);
					String provider = loma.getBestProvider(criteria, false);

					loma.requestSingleUpdate(criteria, PendingIntent
							.getBroadcast(getApplicationContext(), 0,
									getIntent(),
									PendingIntent.FLAG_UPDATE_CURRENT));

					location = loma.getLastKnownLocation(provider);

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
						stringBuild.append(buffer, 0, read);
						read = is.read(buffer);
					}

					Document doc;
					DocumentBuilderFactory bdf = DocumentBuilderFactory
							.newInstance();

					DocumentBuilder db = bdf.newDocumentBuilder();

					InputSource inputSource = new InputSource();
					inputSource.setCharacterStream(new StringReader(stringBuild
							.toString()));
					doc = db.parse(inputSource);

					NodeList nodes = doc.getElementsByTagName("LocalityName");

					if (nodes.getLength() > 0) {
						results.add(nodes.item(0).getTextContent());
					}

					nodes = doc.getElementsByTagName("CountryName");

					if (nodes.getLength() > 0) {
						results.add(nodes.item(0).getTextContent());
					}

				} catch (Exception e) {
					// Gotta catch 'em all
					e.printStackTrace();
				}
				return results.toArray(new String[results.size()]);
			}

			@Override
			protected void onPostExecute(String[] result) {
				((EditText) findViewById(R.id.location_text_edit))
						.setText(result[0]);
				((EditText) findViewById(R.id.pays_text_edit))
						.setText(result[1]);
			};

		}.execute("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_sign_up, menu);
		return true;
	}

	public void finishRegistration(View view) {
		Intent intent = new Intent(view.getContext(), StartScreen.class);

		startActivity(intent);

		finish();
	}

	public void selectPhoto(View view) {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);

		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PICK_IMAGE && data != null && data.getData() != null) {
			Uri _uri = data.getData();
			((ImageButton) findViewById(R.id.photo_textedit)).setImageURI(_uri);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
