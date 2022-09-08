package cooperativeMgmt;

import java.util.*;

public class Ordine {
	
	String orderId;
	Socio s;
	Campagna c;
	Map<Prodotto,Integer> prodottiQuantita = new TreeMap<>();
	int totOrdine;
	String socioName;
	
	public int getTotOrdine() {
		int tot=0;
		for(Prodotto p: prodottiQuantita.keySet()) {
			tot+=p.getPrezzo()*prodottiQuantita.get(p);
		}
		return tot;
	}
	public void setTotOrdine(int totOrdine) {
		this.totOrdine = totOrdine;
	}
	public Ordine(String orderId, Socio s, Campagna c) {
		super();
		this.orderId = orderId;
		this.s = s;
		this.c = c;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Socio getS() {
		return s;
	}
	public void setS(Socio s) {
		this.s = s;
	}
	public Campagna getC() {
		return c;
	}
	public void setC(Campagna c) {
		this.c = c;
	}
	public Map<Prodotto, Integer> getProdottiQuantita() {
		return prodottiQuantita;
	}
	public void setProdottiQuantita(Map<Prodotto, Integer> prodottiQuantita) {
		this.prodottiQuantita = prodottiQuantita;
	}
	public void addProdQt(Map<Prodotto, Integer> prodQuant) {
		prodottiQuantita = prodQuant;
	}
	public String getSocioName() {
		return s.getName();
	}
	public void setSocioName(String socioName) {
		this.socioName = socioName;
	}
	

}
