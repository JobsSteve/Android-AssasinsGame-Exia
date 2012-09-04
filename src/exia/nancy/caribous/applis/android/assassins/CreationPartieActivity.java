package exia.nancy.caribous.applis.android.assassins;

import java.util.ArrayList;

import exia.nancy.caribous.applis.android.assassins.metier.db_objects.ObjetFromShop;
import exia.nancy.caribous.applis.android.assassins.metier.server_interacts.PartiesHelper;
import exia.nancy.caribous.applis.android.assassins.metier.server_interacts.ShopHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class CreationPartieActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_partie);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_creation_partie, menu);
        return true;
    }
    
    public void onClickCreate(View v) 
    {
    	new CreateGame().execute();
        
    }
    
    
    private class CreateGame extends AsyncTask<Integer, Integer, Boolean> 
    {
        protected Boolean doInBackground(Integer... params) 
        {
        	PartiesHelper ph = new PartiesHelper();
        	ph.createPartie(true, true, "tamere", "casse les burnes", "5", "8", "5", "06/06/06");
        	return true;
        }

        protected void onProgressUpdate(Integer... progress) 
        {
           
        }

        protected void onPostExecute(boolean result) 
        {
        	  
        }
    }
    
    
    
}
