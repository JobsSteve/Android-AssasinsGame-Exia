package exia.nancy.caribous.applis.android.assassins;

import metier.all_purpose.LocationHelper;
import metier.all_purpose.LocationObject;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GameSearchActivity extends Activity {

	public static final String IS_SEARCH = "isSearch";
	public static final String PAYS = "pays";
	public static final String VILLE = "ville";
	public static final String RADIO_SELECTED = "RadioSelected";
	public static final Integer FFA_SELECTED = 2;
	public static final Integer TOUT_SELECTED = 0;
	public static final Integer TEAMS_SELECTED = 1;

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

	public void searchButtonCallback(View view) {
		if (((EditText) findViewById(R.id.ville_textedit)).getText().length() == 0
				&& ((EditText) findViewById(R.id.ville_textedit)).getText()
						.length() == 0) {
			Toast.makeText(
					view.getContext(),
					"Vous devez entrer soit un pays, soit une ville pour lancer la recherche",
					Toast.LENGTH_SHORT).show();
		} else {
			Intent i = new Intent(view.getContext(), SearchResults.class);

			RadioGroup herge = (RadioGroup) findViewById(R.id.radioGroup1);

			int rabuId = herge.getCheckedRadioButtonId();

			RadioButton rabais = (RadioButton) findViewById(rabuId);

			if (rabais.getId() == R.id.tout_radiobutton) {
				i.putExtra(GameSearchActivity.RADIO_SELECTED,
						GameSearchActivity.TOUT_SELECTED);
			} else if (rabais.getId() == R.id.team_radiobutton) {
				i.putExtra(GameSearchActivity.RADIO_SELECTED,
						GameSearchActivity.TEAMS_SELECTED);
			} else if (rabais.getId() == R.id.solo_radiobutton) {
				i.putExtra(GameSearchActivity.RADIO_SELECTED,
						GameSearchActivity.FFA_SELECTED);
			}

			i.putExtra(GameSearchActivity.IS_SEARCH, true);
			i.putExtra(GameSearchActivity.PAYS,
					((EditText) findViewById(R.id.pays_textedit)).getText()
							.toString());
			i.putExtra(GameSearchActivity.VILLE,
					((EditText) findViewById(R.id.ville_textedit)).getText()
							.toString());

			startActivity(i);

			finish();
		}
	}
}
