package exia.nancy.caribous.applis.android.assassins;

import metier.all_purpose.LocationHelper;
import metier.all_purpose.LocationObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Player;

public class SignUpActivity extends Activity {

	Location location;
	Handler handle;

	protected ProgressDialog mProgressDialog;

	private final int PICK_IMAGE = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		handle = new Handler();

		new AsyncTask<Activity, String, LocationObject>() {

			@Override
			protected LocationObject doInBackground(Activity... params) {
				return new LocationHelper().getLocations(params[0]);
			}

			@Override
			protected void onPostExecute(LocationObject result) {
				CustomLocationRunnableClass runnab = new CustomLocationRunnableClass();

				runnab.setPays(result.getCountry());
				runnab.setVille(result.getCity());

				handle.post(runnab);

			};

		}.execute(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_sign_up, menu);
		return true;
	}

	public void finishRegistration(View view) {

		mProgressDialog = ProgressDialog.show(this, "Registration en cours",
				"Création de votre compte utilisateur", true);

		Player joujou = new Player();
		joujou.setPrenom(((EditText) findViewById(R.id.prenom_textedit))
				.getText().toString());
		joujou.setNom(((EditText) findViewById(R.id.nomTextEdit)).getText()
				.toString());
		joujou.setBiographie(((EditText) findViewById(R.id.bio_text_edit))
				.getText().toString());
		joujou.setMail(((EditText) findViewById(R.id.choix_mail_textedit))
				.getText().toString());
		joujou.setNumero((((EditText) findViewById(R.id.numero_text_edit))
				.getText().toString()));
		joujou.setPays((((EditText) findViewById(R.id.pays_text_edit))
				.getText().toString()));
		joujou.setVille((((EditText) findViewById(R.id.location_text_edit))
				.getText().toString()));

		Registrer reg = new Registrer();
		reg.joujou = joujou;
		reg.pass = (((EditText) findViewById(R.id.password_textedit)).getText()
				.toString());

		new Thread(reg).start();

	}

	private void onFinishCalc() {

		mProgressDialog.dismiss();

		Intent intent = new Intent(
				findViewById(R.id.button_finish_registration).getContext(),
				StartScreen.class);

		startActivity(intent);

		finish();
	}

	public void selectPhoto(View view) {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);

		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PICK_IMAGE && data != null && data.getData() != null) {
			Uri _uri = data.getData();
			((ImageButton) findViewById(R.id.photo_textedit)).setImageURI(_uri);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private class CustomLocationRunnableClass implements Runnable {

		private String pays;
		private String ville;

		public void setPays(String pays) {
			this.pays = pays;
		}

		public void setVille(String ville) {
			this.ville = ville;
		}

		public void run() {
			((EditText) findViewById(R.id.location_text_edit)).setText(ville);
			((EditText) findViewById(R.id.pays_text_edit)).setText(pays);
		}

	}

	private class Registrer implements Runnable {

		private Player joujou;

		private String pass;

		public void run() {
			joujou.RegisterInDb(pass);
			onFinishCalc();
		}

	}
}
