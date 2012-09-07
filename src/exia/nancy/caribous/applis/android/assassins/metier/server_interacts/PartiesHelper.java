package exia.nancy.caribous.applis.android.assassins.metier.server_interacts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import metier.all_purpose.HTMLParser;
import metier.all_purpose.JSONDateConverter;
import metier.all_purpose.PageLoaderHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Partie;

public class PartiesHelper {

	public Partie[] getNewPublicGames(int fromItemNum) {

		URL url;
		ArrayList<Partie> listOfParties = new ArrayList<Partie>();

		try {
			url = new URL(PageLoaderHelper.SERVER_URL_AND_PORT
					+ "/page/select/partiePublicLimite.aspx?start="
					+ fromItemNum + "&nombre=20");
			String response = new PageLoaderHelper().getResponseFromUrl(url);

			Document doc = new HTMLParser().parseSource(response);

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

	public Partie getPartie(Integer idPartie) {
		URL url;
		Partie PartieJ = new Partie();

		try {
			url = new URL(PageLoaderHelper.SERVER_URL_AND_PORT
					+ "/page/select/partie.aspx?partie=" + idPartie.toString());
			String response = new PageLoaderHelper().getResponseFromUrl(url);

			Document doc = new HTMLParser().parseSource(response);

			JSONObject partiesArray = new JSONObject(doc
					.getElementsByTagName("div").item(0).getTextContent());
			PartieJ = convertJSONToPartie(partiesArray);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return PartieJ;
	}

	public Partie[] getMesParties(int IdJoueur) {

		ArrayList<Partie> listOfParties = new ArrayList<Partie>();

		try {
			URL url = new URL(PageLoaderHelper.SERVER_URL_AND_PORT
					+ "/page/select/parties.aspx");

			String response = new PageLoaderHelper().getResponseFromUrl(url);

			Document doc = new HTMLParser().parseSource(response);

			JSONArray partiesArray = new JSONArray(doc
					.getElementsByTagName("div").item(0).getTextContent());

			for (int i = 0; i < partiesArray.length(); i++) {
				try {
					listOfParties.add(convertJSONToPartie(partiesArray
							.getJSONObject(i)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
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
