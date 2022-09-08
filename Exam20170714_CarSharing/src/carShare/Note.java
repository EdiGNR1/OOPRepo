package carShare;

import java.util.Comparator;

public class Note  {
	
	
	String id;
	String card;
	String plate;
	String parking;
	int min; 
	int km;
	double addebito;
	
	public Note(String id, String card, String plate, String parking, int min, int km, double addebito) {
		super();
		this.id = id;
		this.card = card;
		this.plate = plate;
		this.parking = parking;
		this.min = min;
		this.km = km;
		this.addebito=addebito;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
	
	
	public double getAddebito() {
		return addebito;
	}
	public void setAddebito(double addebito) {
		this.addebito = addebito;
	}
	
	
	
	

}
