package supermarket;

import java.util.*;

public class Scontrino {

	Integer id ;
	boolean b = true;
	Carta c=null;
	List<Prodotto> prodotti = new ArrayList<>();
	int scontoCarta=0;
	double totale =0;

	public Scontrino(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}

	public Carta getC() {
		return c;
	}

	public void setC(Carta c) {
		this.c = c;
	}

	public void addProdotto(Prodotto prodotto) {
		prodotti.add(prodotto);
		
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	public int getScontoCarta() {
		return scontoCarta;
	}

	public void setScontoCarta(int scontoCarta) {
		this.scontoCarta = scontoCarta;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public void inserTotale(double d) {
		totale=d-scontoCarta;
		
	}
	

	
	
	
}
