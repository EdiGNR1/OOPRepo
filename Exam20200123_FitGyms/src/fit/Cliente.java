package fit;

import java.util.*;

public class Cliente {
	
	int i;
	String name;
	List<Lesson> lezioni = new ArrayList<>();
	
	public Cliente(int i, String name) {
		super();
		this.i = i;
		this.name = name;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addLesson(Lesson l) {
		lezioni.add(l);
	}
	public List<Lesson> getLezioni() {
		return lezioni;
	}
	public void setLezioni(List<Lesson> lezioni) {
		this.lezioni = lezioni;
	}
	
	

}
