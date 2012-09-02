package exia.nancy.caribous.applis.android.assassins.metier.db_objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class Partie implements Parcelable {

	public final static boolean VISIBILITY_PRIVATE = true;
	public final static boolean VISIBILITY_PUBLIC = false;

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

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this._description);
		dest.writeString(this._title);
		dest.writeString(this._twitter_hashtag);
		dest.writeString(this._zone);
		dest.writeInt(this._id);
		dest.writeInt(this._maxPlayers);
		dest.writeInt(this._nbTeams);
		dest.writeString(this._endDate.toString());
		dest.writeString(this._startDate.toString());
		dest.writeString(this._visibility.toString());
		dest.writeDouble(this._price_contract);
	}

	public Partie(Parcel in) {
		this._description = in.readString();
		this._title = in.readString();
		this._twitter_hashtag = in.readString();
		this._zone = in.readString();
		this._id = in.readInt();
		this._maxPlayers = in.readInt();
		this._nbTeams = in.readInt();
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss zzz yyyy");
		try {
			this._endDate = format.parse(in.readString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			this._startDate = format.parse(in.readString());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this._visibility = Boolean.parseBoolean(in.readString());
		this._price_contract = in.readDouble();
	}

	public Partie() {

	}

	public static final Parcelable.Creator<Partie> CREATOR = new Parcelable.Creator<Partie>() {

		public Partie createFromParcel(Parcel source) {
			return new Partie(source);
		}

		public Partie[] newArray(int size) {
			return new Partie[size];
		}
	};
}
