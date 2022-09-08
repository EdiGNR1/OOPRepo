package supermarket;

import java.util.*;

public class Acquisto {
	
	Carta CardCode;
	String cliente;
	int pointsRedeemed;
	List<Prodotto> prodotti = new ArrayList<>();
	double tot;
	double scontoTot;
	Set<String> cat= new TreeSet<>();
	
	
	public Acquisto(Carta cardCode, int pointsRedeemed, List<Prodotto> prodotti) {
		super();
		CardCode = cardCode;
		this.pointsRedeemed = pointsRedeemed;
		this.prodotti = prodotti;
	}
	public Carta getCardCode() {
		return CardCode;
	}
	public void setCardCode(Carta cardCode) {
		CardCode = cardCode;
	}
	public int getPointsRedeemed() {
		return pointsRedeemed;
	}
	public void setPointsRedeemed(int pointsRedeemed) {
		this.pointsRedeemed = pointsRedeemed;
	}
	public List<Prodotto> getProdotti() {
		return prodotti;
	}
	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}
	public double getTot() {
		return tot;
	}
	public void setTot(double tot) {
		this.tot = tot;
	}
	public double getScontoTot() {
		return scontoTot;
	}
	public void setScontoTot(double scontoTot) {
		this.scontoTot = scontoTot;
	}
	public String getCliente() {
		return CardCode.getName();
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Set<String> getCat() {
		
		Set<String> temp = new TreeSet<>();
		for(Prodotto p: prodotti) {
			temp.add(p.getC().getCategoryName());
		}
		
		return temp;
	}
	public void setCat(Set<String> cat) {
		
		this.cat = cat;
	}
	
	

}
