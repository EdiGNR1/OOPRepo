package sports;

public class Recensione {
	
	Prodotto p;
	String attivita;
	String userName;
	int numStars;
	String comment;
	public Recensione(Prodotto p, String userName, int numStars, String comment) {
		super();
		this.p = p;
		this.userName = userName;
		this.numStars = numStars;
		this.comment = comment;
	}
	public Prodotto getP() {
		return p;
	}
	public void setP(Prodotto p) {
		this.p = p;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getNumStars() {
		return numStars;
	}
	public void setNumStars(int numStars) {
		this.numStars = numStars;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return numStars+" : "+comment;
	}
	
	

}
