package metier.all_purpose;

import java.io.StringReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.app.PendingIntent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import exia.nancy.caribous.applis.android.assassins.R;

public class LocationHelper {

	public LocationObject getLocations(Activity calledFromActivity) {
		LocationObject locationResult = new LocationObject();

		try {
			// Location automatique
			LocationManager loma = (LocationManager) calledFromActivity
					.getSystemService(Activity.LOCATION_SERVICE);

			Criteria criteria = new Criteria();
			criteria.setSpeedAccuracy(Criteria.NO_REQUIREMENT);
			criteria.setPowerRequirement(Criteria.POWER_LOW);
			criteria.setAltitudeRequired(false);
			criteria.setAccuracy(Criteria.ACCURACY_COARSE);
			String provider = loma.getBestProvider(criteria, false);

			loma.requestSingleUpdate(criteria, PendingIntent.getBroadcast(
					calledFromActivity.getApplicationContext(), 0,
					calledFromActivity.getIntent(),
					PendingIntent.FLAG_UPDATE_CURRENT));

			Location location = loma.getLastKnownLocation(provider);

			URL url = new URL(
					"http://maps.google.com/maps/geo?output=xml&oe=utf-8&ll="
							+ location.getLatitude() + ","
							+ location.getLongitude() + "&key="
							+ R.string.google_maps_api_key);

			String result = new PageLoaderHelper().getResponseFromUrl(url);

			Document doc;
			DocumentBuilderFactory bdf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = bdf.newDocumentBuilder();

			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(result));
			doc = db.parse(inputSource);

			NodeList nodes = doc.getElementsByTagName("LocalityName");

			if (nodes.getLength() > 0) {
				locationResult.setCity(nodes.item(0).getTextContent());
			}

			nodes = doc.getElementsByTagName("CountryName");

			if (nodes.getLength() > 0) {
				locationResult.setCountry(nodes.item(0).getTextContent());
			}

		} catch (Exception e) {
			// Gotta catch 'em all
			e.printStackTrace();
		}
		return locationResult;
	}

}
