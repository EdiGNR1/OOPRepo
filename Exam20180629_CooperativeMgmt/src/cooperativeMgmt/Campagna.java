package cooperativeMgmt;

import java.util.*;

public class Campagna {
	
	String id;
	List<Socio> soci = new ArrayList<>();

	List<Prodotto> prodotti = new ArrayList<>();
	
	public Campagna(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	public void addProd(List<Prodotto> prod) {
		prodotti.addAll(prod);
	}

	public List<Socio> getSoci() {
		return soci;
	}

	public void setSoci(List<Socio> soci) {
		this.soci = soci;
	}

	public void addSocio(Socio socio) {
		soci.add(socio);
	}
	
	

}
