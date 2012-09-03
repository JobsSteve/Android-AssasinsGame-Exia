package exia.nancy.caribous.applis.android.assassins.metier.db_objects;

public class ObjetFromShop 
{
	
	private int _id;
	private String _description;
	private Double _prix;
	private boolean _consomable;
	
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String get_description() {
		return _description;
	}
	public void set_description(String _description) {
		this._description = _description;
	}
	public Double get_prix() {
		return _prix;
	}
	public void set_prix(Double _prix) {
		this._prix = _prix;
	}
	public boolean is_consomable() {
		return _consomable;
	}
	public void set_consomable(boolean _consomable) {
		this._consomable = _consomable;
	}
	
	

}
