package exia.nancy.caribous.applis.android.assassins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Partie;

public class PartieFragment extends Fragment {

	Partie boundPartie;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		boundPartie = getArguments().getParcelable("Partie");

		return inflater.inflate(R.layout.partie_fragment, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		updateInfosOnPageWithPartie(view);
	}

	private void updateInfosOnPageWithPartie(View viewParent) {
		((TextView) viewParent.findViewById(R.id.game_name_id))
				.setText(boundPartie.get_title());
		((TextView) viewParent.findViewById(R.id.game_description_textView))
				.setText(boundPartie.get_description());
		((TextView) viewParent.findViewById(R.id.game_info))
				.setText(boundPartie.get_maxPlayers() + " players Max");
		((TextView) viewParent.findViewById(R.id.nb_participants))
				.setText("TO COMPLETE");
		((TextView) viewParent.findViewById(R.id.twitter_tag))
				.setText(boundPartie.get_twitter_hashtag());
		((TextView) viewParent.findViewById(R.id.survivors_left))
				.setText("XX Remaining");
		((TextView) viewParent.findViewById(R.id.nb_teams)).setText(Integer
				.toString(boundPartie.get_nbTeams()));
		if (boundPartie.get_visibility() == null) {
			((TextView) viewParent.findViewById(R.id.partie_privee_textview))
					.setVisibility(View.VISIBLE);
		} else if (boundPartie.get_visibility() != Partie.VISIBILITY_PRIVATE) {
			((TextView) viewParent.findViewById(R.id.partie_privee_textview))
					.setVisibility(View.INVISIBLE);
		}
	}

	public void buttonCurrContractPress(View view) {
		// On appelle le détail du contrat
		Intent intent = new Intent(view.getContext(), ShowContractInfo.class);

		startActivity(intent);
	}

}
