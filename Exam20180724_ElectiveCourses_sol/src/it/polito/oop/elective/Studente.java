package it.polito.oop.elective;

import java.util.ArrayList;
import java.util.List;

public class Studente {
	
	String id;
	double gradeAverage;
	int scelta =0;
	List<String> insegnamenti = new ArrayList<String>();
	
	public Studente(String id, double gradeAverage) {
		super();
		this.id = id;
		this.gradeAverage = gradeAverage;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getGradeAverage() {
		return gradeAverage;
	}
	public void setGradeAverage(double gradeAverage) {
		this.gradeAverage = gradeAverage;
	}

	public void addInsegnamenti(List<String> courses) {
		insegnamenti.addAll(courses);
		
	}

	public List<String> getInsegnamenti() {
		return insegnamenti;
	}

	public void setInsegnamenti(List<String> insegnamenti) {
		this.insegnamenti = insegnamenti;
	}

	public void addSceltaInCuiEfinito(int i) {
		this.scelta=i;
		
	}

	public int getScelta() {
		return scelta;
	}
	
	
	

}
