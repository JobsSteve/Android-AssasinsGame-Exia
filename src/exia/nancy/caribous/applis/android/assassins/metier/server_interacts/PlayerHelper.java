package exia.nancy.caribous.applis.android.assassins.metier.server_interacts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import metier.all_purpose.HTMLParser;
import metier.all_purpose.PageLoaderHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Partie;
import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Player;

public class PlayerHelper {
	
	
	public ArrayList<Player> getPlayers(String recherche)
	{
		URL url;
		ArrayList<Player> listOfPlayers = new ArrayList<Player>();
		try 
		{
			HTMLParser htmlp = new HTMLParser();
			url = new URL(PageLoaderHelper.SERVER_URL_AND_PORT + "/page/select/joueurRecherche.aspx?recherche="+ recherche);
			
			String response = new PageLoaderHelper().getResponseFromUrl(url);
			
			Document doc = htmlp.parseSource(response);
			
			JSONArray joueurArray = new JSONArray(doc.getElementsByTagName("div").item(0).getTextContent());
			
			for (int i = 0; i < joueurArray.length(); i++) 
			{
				listOfPlayers.add(setWithJSONObject(joueurArray.getJSONObject(i)));
			}
			
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return listOfPlayers;
		
	}

	public Player setWithJSONObject(JSONObject obj) {
		Player resultPlayer = new Player();
		try {

			if (!obj.isNull("biographie"))
				resultPlayer.setBiographie(obj.getString("biographie"));

			if (!obj.isNull("id"))
				resultPlayer.setId(obj.getInt("id"));

			if (!obj.isNull("mail"))
				resultPlayer.setMail(obj.getString("mail"));

			if (!obj.isNull("nom"))
				resultPlayer.setNom(obj.getString("nom"));

			if (!obj.isNull("numero"))
				resultPlayer.setNumero(obj.getString("numero"));

			if (!obj.isNull("pays"))
				resultPlayer.setPays(obj.getString("pays"));

			if (!obj.isNull("prenom"))
				resultPlayer.setPrenom(obj.getString("prenom"));

			if (!obj.isNull("photo"))
				resultPlayer.setPhoto(new URL(obj.getString("photo")));

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return resultPlayer;
	}

}
