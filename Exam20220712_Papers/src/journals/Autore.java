package journals;

import java.util.*;

public class Autore {
	
	String name;
	Map< String,Giornale> articoli = new TreeMap<>();
	double impactFactor;
	int numArticoli;

	public Autore(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Giornale> getArticoli() {
		return articoli;
	}

	public void setArticoli(Map<String, Giornale> articoli) {
		this.articoli = articoli;
	}

	public void addArticolo(Giornale j, String paperTitle) {
		articoli.put(paperTitle,j);
		
	}

	

	public double getImpactFactor() {
		return articoli.values().stream()
		.mapToDouble(Giornale::getImpactFactor).sum();
	}

	public int getNumArticoli() {
		return articoli.size();
	}

	public void setNumArticoli(int numArticoli) {
		this.numArticoli = numArticoli;
	}

	
	
	

}
