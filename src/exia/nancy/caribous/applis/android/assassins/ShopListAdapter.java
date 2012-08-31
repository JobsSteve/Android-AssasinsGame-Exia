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


public class ShopListAdapter extends BaseAdapter
{
	private Context mContext;
    private List<Integer> listPA = new ArrayList<Integer>();
    private LayoutInflater mInflater;
    private int resourceId;
    
    public ShopListAdapter(Context context, int resourceId, List<Integer> listPA) {
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
        TextView nomObjet;
        Button btnBuy;
        
        
        if(convertView == null)
        {
       	 view = mInflater.inflate(R.layout.fragment_shop, parent, false);
       	 nomObjet = (TextView) view.findViewById(R.id.nomObjet);
       	 btnBuy = (Button) view.findViewById(R.id.btnBuy);
       	        	 
        }
        else
        {
       	 view = convertView;
        }
        
		
		
		return view;
	}

}
