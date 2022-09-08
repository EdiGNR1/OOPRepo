package travelPortal;

import java.util.*;

public class Attivita {
	
	String name;
	List<Agenzia> agenzie = new ArrayList<>();
	List<String> agenzieStr = new ArrayList<>();

	public Attivita(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addAgenzia(Agenzia agenzia) {
		agenzie.add(agenzia);
		
	}

	public List<Agenzia> getAgenzie() {
		return agenzie;
	}

	public void setAgenzie(List<Agenzia> agenzie) {
		this.agenzie = agenzie;
	}

	public void addAgenziaStr(String name2) {
		agenzieStr.add(name2);
		
	}

	public List<String> getAgenzieStr() {
		 Collections.sort( agenzieStr);
		return agenzieStr;
	}

	public void setAgenzieStr(List<String> agenzieStr) {
		this.agenzieStr = agenzieStr;
	}
	
	

}
