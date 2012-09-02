package exia.nancy.caribous.applis.android.assassins.metier.server_interacts;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Player;

public class PlayerHelper {

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
