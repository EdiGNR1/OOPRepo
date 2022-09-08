package jobOffers;

import java.util.*;
import java.util.stream.Collectors;

public class Posizione {
	
	String name;
	Map<String, Integer> skillLivello = new TreeMap<>();
	List<Candidato> candidati = new ArrayList<>();
	List<String> candidatiSTR = new ArrayList<>();
	
	public Posizione(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Integer> getSkillLivello() {
		return skillLivello;
	}
	public void setSkillLivello(Map<String, Integer> skillLivello) {
		this.skillLivello = skillLivello;
	}
	public void addMappa(Map<String, Integer> cl) {
	    skillLivello = cl;
		
	}
	public void addCandidato(Candidato candidato) {
		candidati.add(candidato);
		
	}
	public List<Candidato> getCandidati() {
		return candidati;
	}
	public void setCandidati(List<Candidato> candidati) {
		this.candidati = candidati;
	}
	public List<String> getCandidatiSTR() {
		return candidati.stream().map(c->c.getName()).sorted().collect(Collectors.toList());
	}
	public void setCandidatiSTR(List<String> candidatiSTR) {
		this.candidatiSTR = candidatiSTR;
	}
	
	

}
