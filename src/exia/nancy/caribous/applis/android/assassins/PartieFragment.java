package exia.nancy.caribous.applis.android.assassins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PartieFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.partie_fragment, container, false);
	}

	public void setContent() {
		// Appeler avec un objet partie et set le contenu du fragment
	}
	
	public void buttonCurrContractPress(View view){
		// On appelle le détail du contrat
		Intent intent = new Intent(view.getContext(), ShowContractInfo.class);
		
		startActivity(intent);
	}

}
