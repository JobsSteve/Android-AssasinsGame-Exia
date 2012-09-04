package exia.nancy.caribous.applis.android.assassins;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Contract;

public class ShowContractInfo extends Fragment {

	Contract target;

	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.contract_fragment, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		this.view = view;

		setInfosWithCurrContract();
	}

	public void setTarget(Contract target) {
		this.target = target;
	}

	private void setInfosWithCurrContract() {
		((TextView) view.findViewById(R.id.target_pseudo)).setText(target
				.getTarget().getNom() + " " + target.getTarget().getPrenom());
		((TextView) view.findViewById(R.id.biography_textview)).setText(target
				.getTarget().getBiographie());
	}
}
