package exia.nancy.caribous.applis.android.assassins.metier.server_interacts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import metier.all_purpose.HTMLParser;
import metier.all_purpose.PageLoaderHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Contract;

public class ContratHelper {

	public Contract[] getContratsEnCoursForPlayer(int playerId) {
		ArrayList<Contract> contracts = new ArrayList<Contract>();
		try {
			String response = new PageLoaderHelper()
					.getResponseFromUrl(new URL(
							PageLoaderHelper.SERVER_URL_AND_PORT
									+ "/page/select/ContratsJoueur.aspx?joueur="
									+ playerId));

			Document doc = new HTMLParser().parseSource(response);

			String content = doc.getElementsByTagName("div").item(0)
					.getTextContent();

			JSONArray arrayOfPlayers = new JSONArray(content);

			PlayerHelper playHel = new PlayerHelper();

			for (int i = 0; i < arrayOfPlayers.length(); i++) {
				Contract tempCon = new Contract();
				tempCon.setId(arrayOfPlayers.getJSONObject(i).getInt(
						"idContrat"));
				tempCon.setTarget(playHel.setWithJSONObject(arrayOfPlayers
						.getJSONObject(i)));
				tempCon.setIdPartie(arrayOfPlayers.getJSONObject(i).getInt(
						"idPartie"));
				contracts.add(tempCon);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return contracts.toArray(new Contract[contracts.size()]);
	}

	public Contract getContractForPlayerAndGame(int playerId, int gameId) {
		Contract tempCon = new Contract();
		try {

			String response = new PageLoaderHelper()
					.getResponseFromUrl(new URL(
							PageLoaderHelper.SERVER_URL_AND_PORT
									+ "/page/select/ContratJoueurPartie.aspx?joueur="
									+ playerId + "&partie=" + gameId));

			Document doc = new HTMLParser().parseSource(response);

			String content = doc.getElementsByTagName("div").item(0)
					.getTextContent();

			JSONArray arrayOfPlayers = new JSONArray(content);

			PlayerHelper playHel = new PlayerHelper();

			// tempCon.setId(arrayOfPlayers.getJSONObject(0).getInt("idContrat"));
			tempCon.setTarget(playHel.setWithJSONObject(arrayOfPlayers
					.getJSONObject(0)));
			// tempCon.setIdPartie(arrayOfPlayers.getJSONObject(0).getInt(
			// "idPartie"));
			tempCon.setIdPartie(gameId);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tempCon;
	}
}
