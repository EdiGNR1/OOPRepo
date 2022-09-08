package fit;

import java.util.*;

public class Palestra {
	
	String name;
	List<String> slotsOccupati = new ArrayList<String>();
	List<Lesson> lezioni = new ArrayList<Lesson>();
	Map<String,Lesson> slotLezioni = new TreeMap<String,Lesson>();
	int numLezioni;

	public Palestra(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getSlotsOccupati() {
		return slotsOccupati;
	}

	public void setSlotsOccupati(List<String> slotsOccupati) {
		this.slotsOccupati = slotsOccupati;
	}

	public void addSlot(String s) {
		slotsOccupati.add(s);
	}

	public void addLesson(Lesson l) {
		
		lezioni.add(l);
	}

	public void addSlotLesson(Lesson l, String s) {
		slotLezioni.put(s, l);
	}

	public List<Lesson> getLezioni() {
		return lezioni;
	}

	public void setLezioni(List<Lesson> lezioni) {
		this.lezioni = lezioni;
	}

	public Map<String, Lesson> getSlotLezioni() {
		return slotLezioni;
	}

	public void setSlotLezioni(Map<String, Lesson> slotLezioni) {
		this.slotLezioni = slotLezioni;
	}

	public int getNumLezioni() {
		return lezioni.size();
	}

	public void setNumLezioni(int numLezioni) {
		this.numLezioni = numLezioni;
	}
	

}
