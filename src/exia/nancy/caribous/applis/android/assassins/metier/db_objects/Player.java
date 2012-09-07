package exia.nancy.caribous.applis.android.assassins.metier.db_objects;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import metier.all_purpose.PageLoaderHelper;

public class Player {
	private String biographie;
	private int id;
	private String mail;
	private String nom;
	private String numero;
	private String pays;
	private String prenom;
	private URL photo;
	private String ville;

	public String getBiographie() {
		return biographie;
	}

	public void setBiographie(String biographie) {
		this.biographie = biographie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public URL getPhoto() {
		return photo;
	}

	public void setPhoto(URL photo) {
		this.photo = photo;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void RegisterInDb(String password) {

		Map<String, String> arguments = new HashMap<String, String>();

		arguments.put("txtNom", nom);
		arguments.put("txtPrenom", prenom);
		arguments.put("txtVille", ville);
		arguments.put("txtPays", pays);
		arguments.put("txtStatut", "OK");
		arguments.put("txtBiographie", biographie);
		arguments.put("txtMail", mail);
		arguments.put("txtNumero", numero);
		arguments.put("txtMdp", password);

		try {
			new PageLoaderHelper().sendPostDataToUrl(new URL(
					PageLoaderHelper.SERVER_URL_AND_PORT
							+ "/page/create/creerJoueur.aspx"), arguments);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
