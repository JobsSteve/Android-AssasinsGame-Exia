package exia.nancy.caribous.applis.android.assassins;

import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class SearchResults extends FragmentActivity {

	public static final int LAYOUT_ID = 5214865;

	private PartiesPubliquesList ppl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		Bundle b = getIntent().getExtras();

		ppl = new PartiesPubliquesList();

		ppl.setSearch(true);

		HashMap<String, String> paramsFromBundle = new HashMap<String, String>();

		paramsFromBundle.put(GameSearchActivity.PAYS,
				b.getString(GameSearchActivity.PAYS));
		paramsFromBundle.put(GameSearchActivity.VILLE,
				b.getString(GameSearchActivity.VILLE));
		paramsFromBundle.put(GameSearchActivity.RADIO_SELECTED,
				Integer.toString(b.getInt(GameSearchActivity.RADIO_SELECTED)));

		ppl.setParamsFromBundle(paramsFromBundle);

		FrameLayout frame = new FrameLayout(this);
		frame.setId(LAYOUT_ID);
		setContentView(frame, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));

		FragmentTransaction transac = getSupportFragmentManager()
				.beginTransaction();
		transac.add(LAYOUT_ID, ppl);
		transac.commit();
	}

	public void loadMore(View view) {
		ppl.loadMore();
	}

	public void afficherDescriptionButtonPress(View view) {
		ppl.afficherDescriptionButtonPress(view);
	}

}
