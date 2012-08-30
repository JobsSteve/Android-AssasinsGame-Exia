package exia.nancy.caribous.applis.android.assassins.metier.db_objects;

import java.util.Date;

public class Partie {

	public final boolean VISIBILITY_PRIVATE = true;
	public final boolean VISIBILITY_PUBLIC = false;

	private Boolean _visibility;
	private int _id;
	private int _maxPlayers;
	private Date _startDate;
	private Date _endDate;
	private String _title;
	private String _description;
	private int _nbTeams;
	private double _price_contract;
	private String _twitter_hashtag;
	private String _zone;

	public Boolean get_visibility() {
		return _visibility;
	}

	public void set_visibility(Boolean _visibility) {
		this._visibility = _visibility;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_maxPlayers() {
		return _maxPlayers;
	}

	public void set_maxPlayers(int _maxPlayers) {
		this._maxPlayers = _maxPlayers;
	}

	public Date get_startDate() {
		return _startDate;
	}

	public void set_startDate(Date _startDate) {
		this._startDate = _startDate;
	}

	public Date get_endDate() {
		return _endDate;
	}

	public void set_endDate(Date _endDate) {
		this._endDate = _endDate;
	}

	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}

	public int get_nbTeams() {
		return _nbTeams;
	}

	public void set_nbTeams(int _nbTeams) {
		this._nbTeams = _nbTeams;
	}

	public double get_price_contract() {
		return _price_contract;
	}

	public void set_price_contract(double _price_contract) {
		this._price_contract = _price_contract;
	}

	public String get_twitter_hashtag() {
		return _twitter_hashtag;
	}

	public void set_twitter_hashtag(String _twitter_hashtag) {
		this._twitter_hashtag = _twitter_hashtag;
	}

	public String get_zone() {
		return _zone;
	}

	public void set_zone(String _zone) {
		this._zone = _zone;
	}
}
