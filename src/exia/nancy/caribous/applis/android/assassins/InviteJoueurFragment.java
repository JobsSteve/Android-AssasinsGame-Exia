package exia.nancy.caribous.applis.android.assassins;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class InviteJoueurFragment extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_invite_joueur);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_invite_joueur, menu);
        return true;
    }
}
