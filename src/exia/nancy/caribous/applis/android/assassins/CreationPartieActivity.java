package exia.nancy.caribous.applis.android.assassins;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

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
    	/*
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        */
        
        
    }
}
