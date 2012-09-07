package exia.nancy.caribous.applis.android.assassins;

import java.util.ArrayList;
import java.util.List;

import exia.nancy.caribous.applis.android.assassins.metier.db_objects.Player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class JoueurInviteListAdapter extends BaseAdapter
{
	 private Context mContext;
    private ArrayList<Player> listPA = new ArrayList<Player>();
    private LayoutInflater mInflater;
    private int resourceId;
    
    public JoueurInviteListAdapter(Context context, int resourceId, ArrayList<Player> listPA) 
    {
        this.mContext = context;
        this.listPA = listPA;
        this.resourceId = resourceId;
        
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
}

    public int getCount() 
    {
        return listPA.size();
    }

	public Player getItem(int position) 
	{
	        return listPA.get(position);
	}
	
	public long getItemId(int position) 
	{
	        return position;
	}


	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View view;
        TextView pseudoPerso;
        Button btnClose;
        
        if(convertView == null)
        {
   	 		view = mInflater.inflate(R.layout.fragment_invite_joueur, parent, false);
   	 		pseudoPerso = (TextView) view.findViewById(R.id.pseudoPerso);
 
   	 		btnClose = (Button) view.findViewById(R.id.btnInvit);
   	 		
   	 		pseudoPerso.setText(this.getItem(position).getPrenom() + " " + this.getItem(position).getNom());
       	        	 
        }
        else
        {
       	 view = convertView;
        }
        
		
		
		return view;
	}

}