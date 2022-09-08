package carShare;

public class Car {
	
	Parking parking;
	String licencePlate;
	double minRate;
	double kmRate;
	int prenotata=0;
	Subscriber iscritto;
	boolean nelParcheggio = true;
	
	
	public Car(Parking parking, String licencePlate, double minRate, double kmRate) {


		this.parking = parking;
		this.licencePlate = licencePlate;
		this.minRate = minRate;
		this.kmRate = kmRate;
	}
	public Parking getParking() {
		return parking;
	}
	public void setParking(Parking parking) {
		this.parking = parking;
	}
	public String getLicencePlate() {
		return licencePlate;
	}
	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}
	public double getMinRate() {
		return minRate;
	}
	public void setMinRate(double minRate) {
		this.minRate = minRate;
	}
	public double getKmRate() {
		return kmRate;
	}
	public void setKmRate(double kmRate) {
		this.kmRate = kmRate;
	}
	public int isPrenotata() {
		return prenotata;
	}
	public void setPrenotata(int prenotata) {
		this.prenotata = prenotata;
	}
	public Subscriber getIscritto() {
		return iscritto;
	}
	public void setIscritto(Subscriber iscritto) {
		this.iscritto = iscritto;
	}
	public boolean isNelParcheggio() {
		return nelParcheggio;
	}
	public void setNelParcheggio(boolean nelParcheggio) {
		this.nelParcheggio = nelParcheggio;
	}
	public int getPrenotata() {
		return prenotata;
	}
	
	
	
	
	

}
