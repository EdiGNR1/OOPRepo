package carShare;

import java.util.ArrayList;
import java.util.List;

public class Subscriber {
	
	String card;
	boolean giaPrenotazione = false;
	List<String> note = new ArrayList<String>();

	public Subscriber(String card) {
		super();
		this.card = card;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public boolean isGiaPrenotazione() {
		return giaPrenotazione;
	}

	public void setGiaPrenotazione(boolean giaPrenotazione) {
		this.giaPrenotazione = giaPrenotazione;
	}

	public void addNote(String res) {
		note.add(res);
		
	}

	public List<String> getNote() {
		return note;
	}

	public void setNote(List<String> note) {
		this.note = note;
	}
	
	
	
	

}
