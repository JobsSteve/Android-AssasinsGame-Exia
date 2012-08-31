package exia.nancy.caribous.applis.android.assassins;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import metier.all_purpose.PageLoaderHelper;
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

		String username = ((EditText) findViewById(R.id.userNameTexBox))
				.getText().toString();
		String password = ((EditText) findViewById(R.id.passwordTextBox))
				.getText().toString();

		new AsyncTask<String, String, String>() {

			@Override
			protected String doInBackground(String... arg0) {

				try {
					HashMap<String, String> argumentsOfRequest = new HashMap<String, String>();

					argumentsOfRequest.put("pseudo", arg0[0]);
					argumentsOfRequest.put("mdp", arg0[1]);
					argumentsOfRequest
							.put("id",
									((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE))
											.getDeviceId());

					new PageLoaderHelper().sendPostDataToUrl(new URL(
							PageLoaderHelper.SERVER_URL_AND_PORT
									+ "/page/create/connexion.aspx"),
							argumentsOfRequest);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Intent i = new Intent(view.getContext(), MainActivity.class);
				// i.putExtra("Username", arg0[0]);

				// startActivity(i);
				// finish();

				return null;

			}

		}.execute(username, password);

	}

	public void signUpFunction(View view) {

		Intent i = new Intent(view.getContext(), SignUpActivity.class);

		startActivity(i);
		finish();
	}
}
