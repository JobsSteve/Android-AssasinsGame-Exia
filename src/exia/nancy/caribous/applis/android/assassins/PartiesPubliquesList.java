package exia.nancy.caribous.applis.android.assassins;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Partie;
import exia.nancy.caribous.applis.android.assassins.metier.server_interacts.PartiesHelper;

public class PartiesPubliquesList extends Fragment {

	private int fromItemNum = 0;

	private Handler handler;

	// TODO AsyncTask pour récupérer les prochaines parties publiques non
	// commencées
	// AsyncTask<Params, Progress, Result>
	public class GetGamesAsyncTask extends AsyncTask<Integer, String, Partie[]> {

		@Override
		protected Partie[] doInBackground(Integer... params) {
			handler.post(new Runnable() {

				public void run() {
					ProgressBar pb = ((ProgressBar) getActivity().findViewById(
							R.id.progressBar));
					pb.setVisibility(View.VISIBLE);
					View loadmorebutton = getActivity().findViewById(
							R.id.load_more);
					loadmorebutton.setVisibility(View.GONE);
				}
			});

			Partie[] parties = new PartiesHelper().getNewGames();

			fromItemNum += 20;
			return parties;
		}

		protected void onPostExecute(Partie[] result) {
			// On les ajoute au Table Layout
			try {
				customPartieRunnable cpr = new customPartieRunnable();
				cpr.setResult(result);
				handler.post(cpr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

	}

	private class customPartieRunnable implements Runnable {

		private Partie[] result;

		public void run() {
			TableLayout tabLay = (TableLayout) getActivity().findViewById(
					R.id.table_layout);

			for (int i = 0; i < result.length; i++) {
				getLayoutInflater(new Bundle()).inflate(
						R.layout.partie_afficher_single, tabLay);
				View newTabView = tabLay.getChildAt(tabLay.getChildCount() - 1);

				TextView nameOfGame = (TextView) newTabView
						.findViewById(R.id.name_of_game);
				nameOfGame.setText(result[i].get_title());

				TextView nb_of_players = (TextView) newTabView
						.findViewById(R.id.nb_of_players);
				nb_of_players.setText(result[i].get_maxPlayers() + " Players");

				TextView dateStart = (TextView) newTabView
						.findViewById(R.id.date_debut);
				dateStart.setText(DateFormat.format("yy/MM/dd hh:mm",
						result[i].get_startDate()));

				TextView dateEnd = (TextView) newTabView
						.findViewById(R.id.date_fin);
				dateEnd.setText(DateFormat.format("yy/MM/dd hh:mm",
						result[i].get_endDate()));

			}
			// On dismiss la progressbar
			ProgressBar pb = ((ProgressBar) getActivity().findViewById(
					R.id.progressBar));
			tabLay.removeView(pb);
			tabLay.addView(pb);
			pb.setVisibility(View.GONE);
			ImageButton imgBut = (ImageButton) getActivity().findViewById(
					R.id.load_more);
			tabLay.removeView(imgBut);
			tabLay.addView(imgBut);
			imgBut.setVisibility(View.VISIBLE);
		}

		public void setResult(Partie[] res) {
			this.result = res;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.handler = new Handler();
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.partie_liste_fragment, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		new GetGamesAsyncTask().execute(0);

		super.onViewCreated(view, savedInstanceState);
	}

	public void loadMore() {
		new GetGamesAsyncTask().execute(0);
	}

}