package journals;

import java.util.*;

public class Giornale {
	
	String name;
	double impactFactor;
	Map<String, List<Autore>> articoli = new TreeMap<>();
	int numArticoli;
	
	public Giornale(String name, double impactFactor) {
		super();
		this.name = name;
		this.impactFactor = impactFactor;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getImpactFactor() {
		return impactFactor;
	}
	public void setImpactFactor(double impactFactor) {
		this.impactFactor = impactFactor;
	}

	public Map<String, List<Autore>> getArticoli() {
		return articoli;
	}

	public void setArticoli(Map<String, List<Autore>> articoli) {
		this.articoli = articoli;
	}

	public void addArticoloAutori(String paperTitle, List<Autore> lista) {
		articoli.put(paperTitle, lista);
		
	}

	public int getNumArticoli() {
		return articoli.size();
	}

	@Override
	public String toString() {
		return name+":"+articoli.size();
	}
	
	
	

}
