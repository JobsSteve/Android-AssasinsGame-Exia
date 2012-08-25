package exia.nancy.caribous.applis.android.assassins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class StartScreen extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_start_screen, menu);
		return true;
	}

	public void logInFunction(View view) {
		String username = ((EditText) findViewById(R.id.userNameTexBox))
				.getText().toString();
		// String password =
		// ((EditText)findViewById(R.id.passwordTextBox)).getText().toString();

		// TODO:Pour la version finale, penser à check la validité du combo en
		// DB

		Intent i = new Intent(view.getContext(), MainActivity.class);
		i.putExtra("Username", username);

		startActivity(i);
	}
	
	public void signUpFunction(View view){
		
		Intent i = new Intent(view.getContext(), SignUpActivity.class);
		
		startActivity(i);
	}
}
