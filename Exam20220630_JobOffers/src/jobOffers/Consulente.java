package jobOffers;

import java.util.ArrayList;
import java.util.List;

public class Consulente {
	String name;
	List<String> capacita = new ArrayList<>();

	public Consulente(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getCapacita() {
		return capacita;
	}
	public void setCapacita(List<String> capacita) {
		this.capacita = capacita;
	}

	public void addCap(List<String> sl) {
		capacita.addAll(sl);
	}
	
	

}
