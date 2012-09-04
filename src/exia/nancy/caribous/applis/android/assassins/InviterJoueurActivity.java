package exia.nancy.caribous.applis.android.assassins;

import java.util.ArrayList;
import java.util.List;

import exia.nancy.caribous.applis.android.assassins.metier.db_objects.ObjetFromShop;
import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Player;
import exia.nancy.caribous.applis.android.assassins.metier.server_interacts.PlayerHelper;
import exia.nancy.caribous.applis.android.assassins.metier.server_interacts.ShopHelper;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class InviterJoueurActivity extends Activity {
	
	private JoueurInviteListAdapter listPAAdapter;
	private ListView listPartie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inviter_joueur);
        
      	System.out.println("mmm");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_inviter_joueur, menu);
        return true;
    }
    
    
    
    public void onClickSearch(View v)
    {
    
        EditText txtSearch = (EditText) findViewById(R.id.pseudoSearch);
         
        new DownloadPlayers().execute(txtSearch.getText().toString());
        // ArrayAdapter adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, listPA);
     
         
         ((ListView)this.findViewById(R.id.listViewInviteJoueur)).setAdapter(listPAAdapter);
    }
    
    private class DownloadPlayers extends AsyncTask<String, Integer, ArrayList<Player>> 
    {
        protected ArrayList<Player> doInBackground(String... params) 
        {
        	PlayerHelper phelper = new PlayerHelper();
            return phelper.getPlayers(params[0]);
        }

        protected void onProgressUpdate(Integer... progress) 
        {
           
        }

        protected void onPostExecute(ArrayList<Player> result) 
        {
        	  listPAAdapter = new JoueurInviteListAdapter(getApplicationContext(),0, result);
              
              ((ListView)findViewById(R.id.listViewInviteJoueur)).setAdapter(listPAAdapter);
        }
    }
}
