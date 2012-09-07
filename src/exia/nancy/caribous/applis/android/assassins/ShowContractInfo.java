package exia.nancy.caribous.applis.android.assassins;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Contract;
import exia.nancy.caribous.applis.android.assassins.metier.server_interacts.ContratHelper;

public class ShowContractInfo extends Fragment {

	Contract target;

	int idPartie = 0;
	int idJoueur = 0;
	View view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		new AsyncTask<String, String, Contract>() {

			@Override
			protected Contract doInBackground(String... params) {

				if (idPartie != 0)
					return new ContratHelper().getContractForPlayerAndGame(
							idJoueur, idPartie);

				return null;
			}

			@Override
			protected void onPostExecute(Contract result) {
				if (idPartie != 0) {
					target = result;
					if (target != null)
						setInfosWithCurrContract();
				}
				super.onPostExecute(result);
			}

		}.execute("");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.contract_fragment, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		this.view = view;

		if (target != null) {
			setInfosWithCurrContract();
		}
	}

	public void setIdPartie(int id) {
		this.idPartie = id;
	}

	public void setIdJoueur(int id) {
		this.idJoueur = id;
	}

	public void setTarget(Contract contract) {
		this.target = contract;
	}

	private void setInfosWithCurrContract() {
		if (target.getTarget() != null) {
			((TextView) view.findViewById(R.id.target_pseudo)).setText(target
					.getTarget().getNom()
					+ " "
					+ target.getTarget().getPrenom());
			((TextView) view.findViewById(R.id.biography_textview))
					.setText(target.getTarget().getBiographie());
		}
	}
}
