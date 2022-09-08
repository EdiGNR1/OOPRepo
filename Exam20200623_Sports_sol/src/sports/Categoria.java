package sports;

import java.util.*;

public class Categoria {
	
	String name;
	List<String> attivita = new ArrayList<>();
	
	public Categoria(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAttivita() {
		return attivita;
	}

	public void setAttivita(List<String> attivita) {
		this.attivita = attivita;
	}

	public void addAttivita(List<String> res) {
		attivita.addAll(res);
	}
	
	

}
