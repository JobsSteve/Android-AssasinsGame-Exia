package exia.nancy.caribous.applis.android.assassins;

import metier.all_purpose.LocationHelper;
import metier.all_purpose.LocationObject;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SignUpActivity extends Activity {

	Location location;
	Handler handle;

	private final int PICK_IMAGE = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		handle = new Handler();

		new AsyncTask<Activity, String, LocationObject>() {

			@Override
			protected LocationObject doInBackground(Activity... params) {
				return new LocationHelper().getLocations(params[0]);
			}

			@Override
			protected void onPostExecute(LocationObject result) {
				CustomLocationRunnableClass runnab = new CustomLocationRunnableClass();

				runnab.setPays(result.getCountry());
				runnab.setVille(result.getCity());

				handle.post(runnab);

			};

		}.execute(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_sign_up, menu);
		return true;
	}

	public void finishRegistration(View view) {
		Intent intent = new Intent(view.getContext(), StartScreen.class);

		startActivity(intent);

		finish();
	}

	public void selectPhoto(View view) {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);

		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PICK_IMAGE && data != null && data.getData() != null) {
			Uri _uri = data.getData();
			((ImageButton) findViewById(R.id.photo_textedit)).setImageURI(_uri);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private class CustomLocationRunnableClass implements Runnable {

		private String pays;
		private String ville;

		public void setPays(String pays) {
			this.pays = pays;
		}

		public void setVille(String ville) {
			this.ville = ville;
		}

		public void run() {
			((EditText) findViewById(R.id.location_text_edit)).setText(ville);
			((EditText) findViewById(R.id.pays_text_edit)).setText(pays);
		}

	}
}
