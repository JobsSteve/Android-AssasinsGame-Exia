package exia.nancy.caribous.applis.android.assassins;

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
		if (prefs.contains("AuthenticationKeyExiassassins")) {
			Intent i = new Intent(this, MainActivity.class);
			
			i.putExtra("Username", prefs.getString("UsernameExiassassins", ""));
			
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
					edit.putString("AuthenticationKeyExiassassins",
							auth.getToken());
					edit.putString("UsernameExiassassins", auth.getCurrPlayer()
							.getPrenom() + " " + auth.getCurrPlayer().getNom());
					edit.putInt("IdConnectedUserExiassassins", auth
							.getCurrPlayer().getId());
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

							i.putExtra("Username",
									prefs.getString("UsernameExiassassins", ""));

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
