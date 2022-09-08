package fit;

import java.util.*;

public class Lesson {
	
	Palestra gym; 
    String activity; 
    int maxattendees;
    int postiOccupati;
    String slot;
    List<String> istruttori = new ArrayList<>();
    List<Cliente> clienti = new ArrayList<>();
    String istruttoreAssegnato="";
   
    public Lesson(Palestra gym, String activity, int maxattendees, String slot, List<String> istruttori) {
		super();
		this.gym = gym;
		this.activity = activity;
		this.maxattendees = maxattendees;
		this.slot=slot;
		this.istruttori = istruttori;
	}

	public Palestra getGym() {
		return gym;
	}

	public void setGym(Palestra gym) {
		this.gym = gym;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public int getMaxattendees() {
		return maxattendees;
	}

	public void setMaxattendees(int maxattendees) {
		this.maxattendees = maxattendees;
	}

	public List<String> getIstruttori() {
		return istruttori;
	}

	public void setIstruttori(List<String> istruttori) {
		this.istruttori = istruttori;
	}

	public List<Cliente> getClienti() {
		return clienti;
	}

	public void setClienti(List<Cliente> clienti) {
		this.clienti = clienti;
	}

	public int getPostiOccupati() {
		return postiOccupati;
	}

	public void setPostiOccupati(int postiOccupati) {
		this.postiOccupati = postiOccupati;
	}

	public void addCliente(Cliente c) {
		clienti.add(c);
		
	}

	public void changeOccupati() {
		postiOccupati++;
	}

	public String getIstruttoreAssegnato() {
		return istruttoreAssegnato;
	}

	public void setIstruttoreAssegnato(String istruttoreAssegnato) {
		this.istruttoreAssegnato = istruttoreAssegnato;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	
    

    
}
