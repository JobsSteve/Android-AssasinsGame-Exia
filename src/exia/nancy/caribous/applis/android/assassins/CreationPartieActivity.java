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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
        	EditText txtNompartie = (EditText) findViewById(R.id.nomPartie);
        	EditText txtDescription = (EditText) findViewById(R.id.descriptionPartie);
        	EditText txtNbJoueur = (EditText) findViewById(R.id.nombreDeJoueur);
        	EditText txtDollarContrat = (EditText) findViewById(R.id.dollarContrat);
        	EditText txtnbEquipe = (EditText) findViewById(R.id.nbEquipe);
        	EditText txtdateDebut= (EditText) findViewById(R.id.dateDebut);
        	
        	RadioGroup pubPriv = (RadioGroup) findViewById(R.id.radioGroupPubPriv);
        	RadioGroup soloTeam = (RadioGroup) findViewById(R.id.radioGroupSoloTeam);
        	
        	boolean priveP = true;
        	
        	if (pubPriv.getCheckedRadioButtonId() == R.id.radioPub)
        	{
        		priveP = false;
        	}
        	else if (pubPriv.getCheckedRadioButtonId() == R.id.radioPriv)
        	{
        		priveP = true;
        	}
        	
    		boolean teamP = true;
        	
        	if (soloTeam.getCheckedRadioButtonId() == R.id.radioSolo)
        	{
        		priveP = true;
        	}
        	else if (soloTeam.getCheckedRadioButtonId() == R.id.radioTeam)
        	{
        		priveP = false;
        	}
        	
        	PartiesHelper ph = new PartiesHelper();
        	ph.createPartie(priveP, teamP, txtNompartie.getText().toString(), txtDescription.getText().toString(), 
        			txtNbJoueur.getText().toString(),txtDollarContrat.getText().toString(),txtnbEquipe.getText().toString(), 
        			txtdateDebut.getText().toString());
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
