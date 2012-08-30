package exia.nancy.caribous.applis.android.assassins;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class PartiesEnAttenteActivity extends FragmentActivity {

	Handler handle;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		handle = new Handler();

		setContentView(R.layout.parties_en_attente_layout);

		super.onCreate(arg0);
	}

	@Override
	public View onCreateView(View parent, String name, Context context,
			AttributeSet attrs) {
		// TODO Auto-generated method stub

		new AsyncTask<String, String, String>() {

			@Override
			protected String doInBackground(String... params) {
				// TODO réupérer les parties en attente pour l'user en cours
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "";
			}

			protected void onPostExecute(String result) {
				handle.post(new Runnable() {

					@Override
					public void run() {
						ViewGroup tabView = (ViewGroup) findViewById(R.id.tab_view_layout);
						for (int i = 0; i < 20; i++) {
							getLayoutInflater().inflate(
									R.layout.partie_attente_detail, tabView);
						}
					}
				});
			};

		}.execute("");

		return super.onCreateView(parent, name, context, attrs);
	}
}
