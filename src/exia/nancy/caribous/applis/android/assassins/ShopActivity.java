package exia.nancy.caribous.applis.android.assassins;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import exia.nancy.caribous.applis.android.assassins.metier.db_objects.ObjetFromShop;
import exia.nancy.caribous.applis.android.assassins.metier.server_interacts.ShopHelper;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class ShopActivity extends Activity 
{

	private ShopListAdapter listPAAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        
        ArrayList<ObjetFromShop> listPA = new ArrayList<ObjetFromShop>();
        
        new DownloadObjets().execute(1);
        
        
        
      
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_shop, menu);
        return true;
    }
    
    
    private class DownloadObjets extends AsyncTask<Integer, Integer, ArrayList<ObjetFromShop>> 
    {
        protected ArrayList<ObjetFromShop> doInBackground(Integer... params) 
        {
        	ShopHelper shopHelp = new ShopHelper();
            return shopHelp.getObjets();
        }

        protected void onProgressUpdate(Integer... progress) 
        {
           
        }

        protected void onPostExecute(ArrayList<ObjetFromShop> result) 
        {
        	  listPAAdapter = new ShopListAdapter(getApplicationContext(),0, result);
              
              ((ListView)findViewById(R.id.listViewObjets)).setAdapter(listPAAdapter);
        }
    }
    
}


