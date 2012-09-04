package exia.nancy.caribous.applis.android.assassins;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Partie;

public class DescriptionPartie extends Fragment {

	private View topView;

	Partie partie;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_description_partie,
				container, false);
	}

	public void setPartie(Partie part) {
		partie = part;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		topView = view;

		super.onViewCreated(view, savedInstanceState);

		new SetPartie().execute(this.partie);
	}

	private class SetPartie extends AsyncTask<Partie, Integer, Partie> {
		protected Partie doInBackground(Partie... params) {
			return params[0];
		}

		protected void onPostExecute(Partie result) {
			TextView nomPartieT;
			TextView descriptionT;
			TextView nbParticipantsT;
			TextView tagTwitterT;
			TextView nbSurvivantT;
			TextView nbTeamT;
			// TextView inscritT;

			try {

				nomPartieT = (TextView) topView.findViewById(R.id.nomPartie);
				descriptionT = (TextView) topView
						.findViewById(R.id.descriptionPartie);
				nbParticipantsT = (TextView) topView
						.findViewById(R.id.nbParticipant);
				tagTwitterT = (TextView) topView.findViewById(R.id.tagTwitter);
				nbSurvivantT = (TextView) topView
						.findViewById(R.id.nbSurvivant);
				nbTeamT = (TextView) topView.findViewById(R.id.nbTeam);

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
