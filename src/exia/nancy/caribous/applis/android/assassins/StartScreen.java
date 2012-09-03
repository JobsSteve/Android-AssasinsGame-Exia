package exia.nancy.caribous.applis.android.assassins;

import metier.all_purpose.PreferenceKeys;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import exia.nancy.caribous.applis.android.assassins.metier.server_interacts.AuthentificationHelper;

public class StartScreen extends Activity {

	Handler handle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
		handle = new Handler();
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		if (prefs.contains(PreferenceKeys.AUTH_KEY)) {
			Intent i = new Intent(this, MainActivity.class);

			i.putExtra("Username",
					prefs.getString(PreferenceKeys.USER_NAME, ""));

			// new Thread(){
			// //TODO Search Player Object from server
			// }.start();

			startActivity(i);
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_start_screen, menu);
		return true;
	}

	public void logInFunction(View view) {

		new AsyncTask<StartScreen, String, Boolean>() {

			StartScreen screen;

			@Override
			protected Boolean doInBackground(StartScreen... arg0) {

				this.screen = arg0[0];

				AuthentificationHelper auth = new AuthentificationHelper();

				boolean res = auth
						.authenticate(
								((EditText) this.screen
										.findViewById(R.id.userNameTexBox))
										.getText().toString(),
								((EditText) this.screen
										.findViewById(R.id.passwordTextBox))
										.getText().toString(),
								((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE))
										.getDeviceId());

				if (res) {
					SharedPreferences prefs = PreferenceManager
							.getDefaultSharedPreferences(screen);
					Editor edit = prefs.edit();
					edit.putString(PreferenceKeys.AUTH_KEY, auth.getToken());
					edit.putString(PreferenceKeys.USER_NAME, auth
							.getCurrPlayer().getPrenom()
							+ " "
							+ auth.getCurrPlayer().getNom());
					edit.putInt(PreferenceKeys.USER_ID, auth.getCurrPlayer()
							.getId());
					edit.putString(PreferenceKeys.USER_MAIL, auth
							.getCurrPlayer().getMail());
					edit.putString(PreferenceKeys.USER_PAYS, auth
							.getCurrPlayer().getPays());
					edit.putString(PreferenceKeys.USER_BIO, auth
							.getCurrPlayer().getBiographie());
					edit.putString(PreferenceKeys.USER_NUMERO, auth
							.getCurrPlayer().getNumero());
					if (auth.getCurrPlayer().getPhoto() != null)
						edit.putString(PreferenceKeys.USER_PHOTO_URL, auth
								.getCurrPlayer().getPhoto().getPath());
					edit.commit();
				}

				return res;
			}

			protected void onPostExecute(Boolean result) {
				if (result) {
					handle.post(new Runnable() {

						public void run() {
							Intent i = new Intent(screen, MainActivity.class);

							SharedPreferences prefs = PreferenceManager
									.getDefaultSharedPreferences(screen);

							i.putExtra("Username", prefs.getString(
									PreferenceKeys.USER_NAME, ""));

							screen.startActivity(i);

							screen.finish();
						}
					});
				}
			};

		}.execute(this);

	}

	public void signUpFunction(View view) {

		Intent i = new Intent(view.getContext(), SignUpActivity.class);

		startActivity(i);
		finish();
	}
}
