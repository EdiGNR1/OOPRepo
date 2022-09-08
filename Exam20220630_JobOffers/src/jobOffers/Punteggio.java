package jobOffers;

import java.util.Map;
import java.util.TreeMap;

public class Punteggio {
	
	String consultant;
	String candidate;
	Map<String,Integer> cr = new TreeMap<>();
	public Punteggio(String candidate) {
		
		this.candidate = candidate;
	}
	public String getConsultant() {
		return consultant;
	}
	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}
	public String getCandidate() {
		return candidate;
	}
	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}
	public Map<String, Integer> getCr() {
		return cr;
	}
	public void setCr(Map<String, Integer> cr) {
		this.cr = cr;
	}
	public void addMap(Map<String, Integer> cr2) {
		this.cr=cr2;
	}
	
	

}
