package exia.nancy.caribous.applis.android.assassins.metier.server_interacts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import metier.all_purpose.PageLoaderHelper;

public class AuthentificationHelper {

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
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
