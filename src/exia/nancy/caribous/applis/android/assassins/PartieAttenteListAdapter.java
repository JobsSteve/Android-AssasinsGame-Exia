package exia.nancy.caribous.applis.android.assassins;



import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class PartieAttenteListAdapter extends BaseAdapter
{
	 private Context mContext;
     private List<Integer> listPA = new ArrayList<Integer>();
     private LayoutInflater mInflater;
     private int resourceId;
     
     public PartieAttenteListAdapter(Context context, int resourceId, List<Integer> listPA) {
         this.mContext = context;
         this.listPA = listPA;
         this.resourceId = resourceId;
         
         mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 }

     public int getCount() {
         return listPA.size();
 }

 public Integer getItem(int position) {
         return listPA.get(position);
 }

 public long getItemId(int position) {
         return position;
 }




	public View getView(int position, View convertView, ViewGroup parent) 
	{
		
		 View view;
         TextView nomPartie;
         TextView nbParticipant;
         TextView datePartie;
         Button btnClose;
         
         
         if(convertView == null)
         {
        	 view = mInflater.inflate(R.layout.fragment_partie_attente, parent, false);
        	 nomPartie = (TextView) view.findViewById(R.id.nomPartie);
        	 nbParticipant = (TextView) view.findViewById(R.id.nbParticipants);
        	 datePartie = (TextView) view.findViewById(R.id.datePartie);
        	 btnClose = (Button) view.findViewById(R.id.btnClose);
        	 
        	 nbParticipant.setText("3");
        	 
         }
         else
         {
        	 view = convertView;
         }
         
		
		
		return view;
	}
	
	
	
}
