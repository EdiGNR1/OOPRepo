package travelPortal;

import java.util.*;

public class Agenzia {
	
	List<String> attivita = new ArrayList<>();
	String name;
	List<Proposta> proposte = new ArrayList<>();
	List<AttivitaProposta> attivitaProp = new ArrayList<>();
	
	public Agenzia(String name) {
		super();
		this.name = name;
	}

	public List<String> getAttivita() {
		return attivita;
	}

	public void setAttivita(List<String> attivita) {
		this.attivita = attivita;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addAttivita(List<String> lista) {
		attivita.addAll(lista);
		
	}

	public void addProposta(Proposta p) {
		proposte.add(p);
		
	}

	public List<Proposta> getProposte() {
		return proposte;
	}

	public void setProposte(List<Proposta> proposte) {
		this.proposte = proposte;
	}

	
	public List<AttivitaProposta> getAttivitaProp() {
		return attivitaProp;
	}

	public void setAttivitaProp(List<AttivitaProposta> attivitaProp) {
		this.attivitaProp = attivitaProp;
	}

	public void addAttivitaProposta(AttivitaProposta p) {
		attivitaProp.add(p);
		
	}

	
	
	

}
