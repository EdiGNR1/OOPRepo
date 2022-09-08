package supermarket;

import java.util.*;

public class Categoria {
	
	String categoryName;
	Map<String, Double> prodCosto = new TreeMap<>();
	String prodMax;
	int sconto=0;
	List<Integer> sconti = new ArrayList<>();
	public Categoria(String categoryName) {
		super();
		this.categoryName = categoryName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Map<String, Double> getProdCosto() {
		return prodCosto;
	}
	public void setProdCosto(Map<String, Double> prodCosto) {
		this.prodCosto = prodCosto;
	}
	public String getProdMax() {
		return prodCosto.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
	}
	public void setProdMax(String prodMax) {
		this.prodMax = prodMax;
	}
	public int getSconto() {
		return sconto;
	}
	public void setSconto(int sconto) {
		this.sconto = sconto;
	}
	public List<Integer> getSconti() {
		return sconti;
	}
	public void setSconti(List<Integer> sconti) {
		this.sconti = sconti;
	}
	public void addSconto(int percentage) {
		sconti.add(percentage);
	}
	
	

}
