package exia.nancy.caribous.applis.android.assassins;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DescriptionPartie extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_partie);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_description_partie, menu);
        return true;
    }
}
