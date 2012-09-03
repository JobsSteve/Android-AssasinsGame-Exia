package exia.nancy.caribous.applis.android.assassins.metier.server_interacts;

import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import metier.all_purpose.PageLoaderHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import exia.nancy.caribous.applis.android.assassins.metier.db_objects.ObjetFromShop;

public class ShopHelper 
{
	public ArrayList<ObjetFromShop> getObjets()
	{
		URL url;
		ArrayList<ObjetFromShop> listOfObjets = new ArrayList<ObjetFromShop>();
		
		try 
		{
			url = new URL(PageLoaderHelper.SERVER_URL_AND_PORT + "/page/select/objets.aspx");
			String response = new PageLoaderHelper().getResponseFromUrl(url);

			Document doc;
			DocumentBuilderFactory bdf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = bdf.newDocumentBuilder();

			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(response));
			doc = db.parse(inputSource);

			JSONArray objetsArray = new JSONArray(doc.getElementsByTagName("div").item(0).getTextContent());

			for (int i = 0; i < objetsArray.length(); i++) 
			{
				listOfObjets.add(convertJSONToObjet(objetsArray.getJSONObject(i)));
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return listOfObjets;
	}
	
	private ObjetFromShop convertJSONToObjet(JSONObject currJsonPartie) 
	{
		ObjetFromShop tempElement = new ObjetFromShop();

		try 
		{
			tempElement.set_id(currJsonPartie.getInt("id"));
			tempElement.set_description(currJsonPartie.getString("description"));
			if (!currJsonPartie.isNull("prix"))
				tempElement.set_prix(currJsonPartie.getDouble("prix"));
			tempElement.set_consomable(currJsonPartie.getBoolean("consommable"));
			
		

		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}

		return tempElement;
	}

}
