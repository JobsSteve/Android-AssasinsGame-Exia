package exia.nancy.caribous.applis.android.assassins;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TableLayout;

public class PartiesPubliquesList extends Fragment {

	private int fromItemNum = 0;
	
	private Handler handler;

	// TODO AsyncTask pour récupérer les prochaines parties publiques non
	// commencées
	// AsyncTask<Params, Progress, Result>
	public class GetGamesAsyncTask extends AsyncTask<Integer, String, String[]> {

		@Override
		protected String[] doInBackground(Integer... params) {
			// On load la liste des 20 prochaines parties libres
			try {
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						ProgressBar pb = ((ProgressBar) getActivity().findViewById(
								R.id.progressBar));
						pb.setVisibility(View.VISIBLE);
						View loadmorebutton = getActivity()
								.findViewById(R.id.load_more);
						loadmorebutton.setVisibility(View.GONE);
					}
				});
				Thread.sleep(5000);
				fromItemNum += 20;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String[] result) {
			// On les ajoute au Table Layout
			try {
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						TableLayout tabLay = (TableLayout) getActivity().findViewById(
								R.id.table_layout);
						
						for (int i = 0; i < 20; i++) {
							getLayoutInflater(new Bundle()).inflate(
									R.layout.partie_afficher_single, tabLay);
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
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

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
	
	public void loadMore(){
		new GetGamesAsyncTask().execute(0);
	}

}