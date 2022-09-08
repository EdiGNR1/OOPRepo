package cooperativeMgmt;

public class Prodotto implements Comparable<Prodotto>{
	
	String id;
	int prezzo;
	
	
	
	public Prodotto(String id, int prezzo) {
		super();
		this.id = id;
		this.prezzo = prezzo;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}


	@Override
	public int compareTo(Prodotto o) {
		// TODO Auto-generated method stub
		return this.id.compareTo(o.getId());
	}
	
	

}
