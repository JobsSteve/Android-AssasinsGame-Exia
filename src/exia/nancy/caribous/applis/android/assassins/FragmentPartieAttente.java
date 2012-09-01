package exia.nancy.caribous.applis.android.assassins;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FragmentPartieAttente extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_partie_attente);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_partie_attente, menu);
        return true;
    }
}
