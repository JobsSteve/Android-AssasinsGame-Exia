package exia.nancy.caribous.applis.android.assassins;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.widget.TextView;
import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Partie;
import exia.nancy.caribous.applis.android.assassins.metier.server_interacts.PartiesHelper;

public class DescriptionPartie extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		new DownloadPartie().execute(2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_description_partie, menu);
		return true;
	}

	private class DownloadPartie extends AsyncTask<Integer, Integer, Partie> {
		protected Partie doInBackground(Integer... params) {
			PartiesHelper partieHelp = new PartiesHelper();
			return partieHelp.getPartie(params[0]);
		}

		protected void onPostExecute(Partie result) {
			TextView nomPartieT;
			TextView descriptionT;
			TextView nbParticipantsT;
			TextView tagTwitterT;
			TextView nbSurvivantT;
			TextView nbTeamT;
			TextView inscritT;

			try {

				nomPartieT = (TextView) findViewById(R.id.nomPartie);
				descriptionT = (TextView) findViewById(R.id.descriptionPartie);
				nbParticipantsT = (TextView) findViewById(R.id.nbParticipant);
				tagTwitterT = (TextView) findViewById(R.id.tagTwitter);
				nbSurvivantT = (TextView) findViewById(R.id.nbSurvivant);
				nbTeamT = (TextView) findViewById(R.id.nbTeam);

				// inscritT = (TextView) findViewById(R.id.prixObjet);

				nomPartieT.setText(result.get_title());
				descriptionT.setText(result.get_description());
				nbParticipantsT.setText("/" + result.get_maxPlayers());
				tagTwitterT.setText(result.get_twitter_hashtag());
				nbSurvivantT.setText("nbsurvi");
				nbTeamT.setText(result.get_nbTeams());

				// inscritT.setText("lesinscrits");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
