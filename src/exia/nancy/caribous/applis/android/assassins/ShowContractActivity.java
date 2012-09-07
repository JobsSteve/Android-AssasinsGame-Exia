package exia.nancy.caribous.applis.android.assassins;

import metier.all_purpose.PreferenceKeys;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class ShowContractActivity extends FragmentActivity {

	private static final int LAYOUT_ID = 51884962;

	int idPartie;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		this.idPartie = getIntent().getExtras().getInt("partieId");

		ShowContractInfo ppl = new ShowContractInfo();

		ppl.setIdPartie(idPartie);

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		ppl.setIdJoueur(prefs.getInt(PreferenceKeys.USER_ID, 0));

		FrameLayout frame = new FrameLayout(this);
		frame.setId(LAYOUT_ID);
		setContentView(frame, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));

		FragmentTransaction transac = getSupportFragmentManager()
				.beginTransaction();
		transac.add(LAYOUT_ID, ppl);
		transac.commit();
	}

	@Override
	public View onCreateView(View parent, String name, Context context,
			AttributeSet attrs) {
		return super.onCreateView(parent, name, context, attrs);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_show_contract, menu);
		return true;
	}
}
