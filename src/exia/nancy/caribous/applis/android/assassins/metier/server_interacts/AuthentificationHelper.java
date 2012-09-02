package exia.nancy.caribous.applis.android.assassins.metier.server_interacts;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import metier.all_purpose.PageLoaderHelper;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Player;

public class AuthentificationHelper {

	private String token;

	private Player currPlayer;

	public boolean authenticate(String username, String password,
			String appareilId) {

		HashMap<String, String> argumentsOfRequest = new HashMap<String, String>();

		argumentsOfRequest.put("pseudo", username);
		argumentsOfRequest.put("mdp", password);
		argumentsOfRequest.put("id", appareilId);

		try {
			String serverResponse = new PageLoaderHelper().sendPostDataToUrl(
					new URL(PageLoaderHelper.SERVER_URL_AND_PORT
							+ "/page/create/connexion.aspx"),
					argumentsOfRequest);

			Document doc;
			DocumentBuilderFactory bdf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = bdf.newDocumentBuilder();

			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(serverResponse));
			doc = db.parse(inputSource);

			doc.getElementsByTagName("div");

			String divContent = doc.getElementsByTagName("div").item(0)
					.getTextContent().trim();

			if (divContent.compareToIgnoreCase("error") == 0
					|| divContent.compareToIgnoreCase("vide") == 0) {
				return false;
			} else {
				String[] parts = divContent.split("\\{");
				PlayerHelper phelp = new PlayerHelper();
				currPlayer = phelp.setWithJSONObject(new JSONObject("{"
						+ parts[1]));
				token = parts[0];
				return true;
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

		return false;
	}

	public String getToken() {
		return token;
	}

	public Player getCurrPlayer() {
		return currPlayer;
	}
}
