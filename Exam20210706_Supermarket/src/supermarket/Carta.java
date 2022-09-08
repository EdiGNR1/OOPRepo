package supermarket;

public class Carta {
	
	int id;
	String name;
	String dateOfBirth;
	int punti=0;
	int puntiTuttiAcquisti=0;
	
	
	public Carta(int id, String name, String dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti = punti;
	}
	public void addPunti(int round) {
		punti+=round;
		
	}
	public void removePunti(int pointsRedeemed) {
		punti-=pointsRedeemed;
		
	}
	public int getPuntiTuttiAcquisti() {
		return puntiTuttiAcquisti;
	}
	public void setPuntiTuttiAcquisti(int puntiTuttiAcquisti) {
		this.puntiTuttiAcquisti = puntiTuttiAcquisti;
	}
	public void addTuttiPunti(int tot) {
		puntiTuttiAcquisti+=tot;
		
	}
	
	

}
