package it.polito.oop.elective;

import static java.util.Comparator.reverseOrder;

import java.util.*;
import java.util.stream.Collectors;

public class Corso {
	
	String name;
	int availablePositions;
	Map<Integer, Long> corsoComePreferenza = new TreeMap<Integer, Long>();
	int numOccupati;
	List<Studente> registrati = new ArrayList<Studente>();
	List<String> registratiId = new ArrayList<String>();
	
	public Corso(String name, int availablePositions) {
		
		this.name = name;
		this.availablePositions = availablePositions;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAvailablePositions() {
		return availablePositions;
	}
	public void setAvailablePositions(int availablePositions) {
		this.availablePositions = availablePositions;
	}

	public void addNumPosition(int i) {
		
		if(!corsoComePreferenza.containsKey(i)) {
			corsoComePreferenza.put(i, (long) 1);	
		}else {
			Long temp=corsoComePreferenza.get(i);
			corsoComePreferenza.put(i, temp+1);
		}
		
	}

	public Map<Integer, Long> getCorsoComePreferenza() {
		return corsoComePreferenza;
	}

	public void setCorsoComePreferenza(Map<Integer, Long> corsoComePreferenza) {
		this.corsoComePreferenza = corsoComePreferenza;
	}

	public int getNumOccupati() {
		return numOccupati;
	}

	public void setNumOccupati(int numOccupati) {
		this.numOccupati = numOccupati;
	}

	public List<Studente> getRegistrati() {
		return registrati;
	}

	public void setRegistrati(List<Studente> registrati) {
		this.registrati = registrati;
	}

	public void addStudente(Studente s) {
		registrati.add(s);
		numOccupati++;
	}

	public List<String> getRegistratiId() {
		
		List<Studente> registratiOrdinati = registrati.stream()
	   .sorted(Comparator.comparing(Studente::getGradeAverage, reverseOrder()))
	   .collect(Collectors.toList());
	   
		for(Studente s : registratiOrdinati) {
			registratiId.add(s.getId());
		}
		
		
		
		return registratiId;
	}

	public void setRegistratiId(List<String> registratiId) {
		this.registratiId = registratiId;
	}
	
	
	
	

}
