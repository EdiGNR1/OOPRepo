package supermarket;

import java.util.*;

public class Prodotto {
	
	String name;
	double price;
	Categoria c;
	List<Integer> discount = new ArrayList<>();
	int maxSconto;
	
	
	public Prodotto(String name, double price, Categoria c) {
		super();
		this.name = name;
		this.price = price;
		this.c = c;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Categoria getC() {
		return c;
	}
	public void setC(Categoria c) {
		this.c = c;
	}


	public List<Integer> getDiscount() {
		return discount;
	}


	public void setDiscount(List<Integer> discount) {
		this.discount = discount;
	}


	public void addSconto(int percentage) {
		if(discount.size()==0) {
			discount.add(0);
		}
		discount.add(percentage);
		
	}


	public int getMaxSconto() {
		return discount.stream().max(Comparator.comparing(Integer::valueOf)).orElse(0);
	}


	public void setMaxSconto(int maxSconto) {
		this.maxSconto = maxSconto;
	}
	
	
	

}
