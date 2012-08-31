package exia.nancy.caribous.applis.android.assassins;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ShopFragment extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_shop);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_shop, menu);
        return true;
    }
}
