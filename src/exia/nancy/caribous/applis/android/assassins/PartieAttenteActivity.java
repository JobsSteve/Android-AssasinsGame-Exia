package exia.nancy.caribous.applis.android.assassins;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PartieAttenteActivity extends Activity {

	private PartieAttenteListAdapter listPAAdapter;
	private ListView listPartie;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_partie_attente);
        
         
         List<Integer> listPA = new ArrayList<Integer>();
         listPA.add(1);
         listPA.add(2);
         listPA.add(5);
         listPA.add(7);
         
         
          
          listPAAdapter = new PartieAttenteListAdapter(getApplicationContext(),0, listPA);
          
         // ArrayAdapter adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, listPA);
      
          
          ((ListView)this.findViewById(R.id.listViewPartieAttente)).setAdapter(listPAAdapter);
        

       
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      
    	
    	
    	getMenuInflater().inflate(R.menu.activity_partie_attente, menu);
        
        
        return true;
    }
}
