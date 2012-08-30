package exia.nancy.caribous.applis.android.assassins;

import metier.all_purpose.LocationHelper;
import metier.all_purpose.LocationObject;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

public class GameSearchActivity extends Activity {

	Handler handle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		handle = new Handler();

		super.onCreate(savedInstanceState);

		setContentView(R.layout.recherche_partie_fragment);

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
			((EditText) findViewById(R.id.ville_textedit)).setText(ville);
			((EditText) findViewById(R.id.pays_textedit)).setText(pays);
		}

	}
}
