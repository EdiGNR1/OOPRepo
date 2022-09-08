package jobOffers;

import java.util.*;

public class Candidato {
	String name;
	List<String> capacita = new ArrayList<>();
	List<Posizione> posizioni = new ArrayList<>();
	
	
	
	public Candidato(String name) {
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
	public void addPos(Posizione posizione) {
	  posizioni.add(posizione);
		
	}
	public List<Posizione> getPosizioni() {
		return posizioni;
	}
	public void setPosizioni(List<Posizione> posizioni) {
		this.posizioni = posizioni;
	}
	
	

}
