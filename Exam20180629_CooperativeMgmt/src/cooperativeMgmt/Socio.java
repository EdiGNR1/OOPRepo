package cooperativeMgmt;

import java.util.*;

public class Socio {
	
	String name;
	
	List<Campagna> campagne = new ArrayList<>();
	List<Integer> versamenti = new ArrayList<>();


	public Socio(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Campagna> getCampagne() {
		return campagne;
	}

	public void setCampagne(List<Campagna> campagne) {
		this.campagne = campagne;
	}

	public void addCampagna(Campagna campagna) {
		campagne.add(campagna);
	}

	public List<Integer> getVersamenti() {
		return versamenti;
	}

	public void setVersamenti(List<Integer> versamenti) {
		this.versamenti = versamenti;
	}

	public void addPagamento(int amount) {
		versamenti.add(amount);
	}
	
	

}
