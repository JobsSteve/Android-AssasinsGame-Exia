package exia.nancy.caribous.applis.android.assassins;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Partie;

public class DescriptionPartieActivity extends FragmentActivity {

	public static final int LAYOUT_ID = 5214865;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		Partie toSynchro = getIntent().getExtras().getParcelable("Partie");

		DescriptionPartie ppl = new DescriptionPartie();

		ppl.setPartie(toSynchro);

		FrameLayout frame = new FrameLayout(this);
		frame.setId(LAYOUT_ID);
		setContentView(frame, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));

		FragmentTransaction transac = getSupportFragmentManager()
				.beginTransaction();
		transac.add(LAYOUT_ID, ppl);
		transac.commit();
	}

}
