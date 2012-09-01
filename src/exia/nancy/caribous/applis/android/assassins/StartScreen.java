package exia.nancy.caribous.applis.android.assassins;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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

				return new AuthentificationHelper()
						.authenticate(
								((EditText) this.screen
										.findViewById(R.id.userNameTexBox))
										.getText().toString(),
								((EditText) this.screen
										.findViewById(R.id.passwordTextBox))
										.getText().toString(),
								((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE))
										.getDeviceId());
			}

			protected void onPostExecute(Boolean result) {
				if (result) {
					handle.post(new Runnable() {

						public void run() {
							Intent i = new Intent(screen, MainActivity.class);

							i.putExtra("Username", ((EditText) screen
									.findViewById(R.id.userNameTexBox))
									.getText().toString());

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
