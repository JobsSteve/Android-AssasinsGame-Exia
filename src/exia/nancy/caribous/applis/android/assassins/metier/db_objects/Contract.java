package exia.nancy.caribous.applis.android.assassins.metier.db_objects;

public class Contract {

	private Player target;
	private int id;
	private int idPartie;

	public Player getTarget() {
		return target;
	}

	public void setTarget(Player target) {
		this.target = target;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPartie() {
		return idPartie;
	}

	public void setIdPartie(int idPartie) {
		this.idPartie = idPartie;
	}

}
