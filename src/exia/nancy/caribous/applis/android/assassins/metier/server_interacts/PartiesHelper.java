package exia.nancy.caribous.applis.android.assassins.metier.server_interacts;

import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import metier.all_purpose.JSONDateConverter;
import metier.all_purpose.PageLoaderHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Partie;

public class PartiesHelper {

	public Partie[] getNewGames() {

		URL url;
		ArrayList<Partie> listOfParties = new ArrayList<Partie>();

		try {
			url = new URL("http://192.168.1.5:8080/page/select/parties.aspx");
			String response = new PageLoaderHelper().getResponseFromUrl(url);

			Document doc;
			DocumentBuilderFactory bdf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = bdf.newDocumentBuilder();

			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(response));
			doc = db.parse(inputSource);

			JSONArray partiesArray = new JSONArray(doc
					.getElementsByTagName("div").item(0).getTextContent());

			for (int i = 0; i < partiesArray.length(); i++) {
				listOfParties.add(convertJSONToPartie(partiesArray
						.getJSONObject(i)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listOfParties.toArray(new Partie[listOfParties.size()]);
	}

	private Partie convertJSONToPartie(JSONObject currJsonPartie) {
		Partie tempElement = new Partie();

		try {
			JSONDateConverter jdc = new JSONDateConverter();
			tempElement
					.set_description(currJsonPartie.getString("description"));
			tempElement.set_endDate(jdc
					.convertJSONDateFormatToJavaDate(currJsonPartie
							.getString("dateFin")));
			tempElement.set_id(currJsonPartie.getInt("id"));
			tempElement.set_maxPlayers(currJsonPartie
					.getInt("nombreParticipantMaximum"));
			if (!currJsonPartie.isNull("nombreEquipe"))
				tempElement.set_nbTeams(currJsonPartie.getInt("nombreEquipe"));
			tempElement.set_price_contract(currJsonPartie
					.getDouble("prixContrat"));
			tempElement.set_startDate(jdc
					.convertJSONDateFormatToJavaDate(currJsonPartie
							.getString("dateDebut")));
			tempElement.set_title(currJsonPartie.getString("titre"));
			if (!currJsonPartie.isNull("tagTwitter"))
				tempElement.set_twitter_hashtag(currJsonPartie
						.getString("tagTwitter"));
			tempElement.set_visibility(currJsonPartie.getBoolean("prive"));
			tempElement.set_zone("zone");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return tempElement;
	}

}
