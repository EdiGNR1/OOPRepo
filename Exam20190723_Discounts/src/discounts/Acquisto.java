package discounts;

import java.util.*;

public class Acquisto {
	
	int id;
	Carta carta=null;
	Map<Prodotto, Integer> prodQuantita = new TreeMap<>();
	double totAcquisto;
	double totSconto;
	int totAcquistoArrotondato;
	int cartaId;
	int totScontoArrotondato;
	
	public Acquisto(int id) {
		super();
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Carta getCarta() {
		return carta;
	}
	public void setCarta(Carta carta2) {
		this.carta = carta2;
	}

	public void addProdQt(Map<Prodotto, Integer> prodQt) {
		this.prodQuantita=prodQt;
		
	}

	public Map<Prodotto, Integer> getProdQuantita() {
		return prodQuantita;
	}

	public void setProdQuantita(Map<Prodotto, Integer> prodQuantita) {
		this.prodQuantita = prodQuantita;
	}

	public double getTotAcquisto() {
		
		double tot=0.0;
		for(Prodotto p : prodQuantita.keySet()) {
			
			if(carta!=null) {
				tot+= prodQuantita.get(p)* (p.getPrice()- (p.getPrice()*((double)p.getDiscount()/100)));
			}else {
				tot+= prodQuantita.get(p)* p.getPrice();
			}
			
		}
		
		return tot;
	}

	public void setTotAcquisto(double totAcquisto) {
		this.totAcquisto = totAcquisto;
	}

	public double getTotSconto() {
		
		double tot=0.0;
		for(Prodotto p : prodQuantita.keySet()) {
			
			if(carta!=null) {
				tot+=prodQuantita.get(p)*( p.getPrice()*((double)p.getDiscount()/100));
			}
			
		}
		
		return tot;
	}

	public void setTotSconto(double totSconto) {
		this.totSconto = totSconto;
	}

	public int getTotAcquistoArrotondato() {
		return (int) Math.round(this.getTotAcquisto());
	}

	public void setTotAcquistoArrotondato(int totAcquistoArrotondato) {
		this.totAcquistoArrotondato = totAcquistoArrotondato;
	}

	public int getCartaId() {
		return carta.getId();
	}

	public void setCartaId(int cartaId) {
		this.cartaId = cartaId;
	}

	public int getTotScontoArrotondato() {
		return (int) Math.round(this.getTotSconto());
	}

	public void setTotScontoArrotondato(int totScontoArrotondato) {
		this.totScontoArrotondato = totScontoArrotondato;
	}
	
	
	

}
