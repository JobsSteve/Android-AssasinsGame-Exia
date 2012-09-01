package exia.nancy.caribous.applis.android.assassins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Bundle b = getIntent().getExtras();
		((TextView) findViewById(R.id.textView1)).setText(b
				.getString("Username"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void goToPartiesEnCours(View view) {
		Intent i = new Intent(view.getContext(), RandomPagerActivity.class);

		startActivity(i);
	}

	public void goToJoinParties(View view) {
		Intent i = new Intent(view.getContext(), JoinGame.class);

		startActivity(i);
	}

	public void goToCreationPartie(View view) {
		Intent i = new Intent(view.getContext(), CreationPartieActivity.class);
		startActivity(i);
	}

	public void goToActiveContracts(View view) {
		Intent i = new Intent(view.getContext(),
				ListeContratsActifsActivity.class);

		startActivity(i);
	}

	public void goToPartieAttente(View view) {
		Intent i = new Intent(view.getContext(), ShopActivity.class);
		startActivity(i);
	}
}
