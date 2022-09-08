package travelPortal;

import java.util.*;

public class Proposta {
	
	String code;
	String agency;
	String destination;
	String period;
	int minNP;
	int maxNP;
	int price;
	List<AttivitaProposta> attivitaProposte = new ArrayList<>();
	List<String> partecipanti = new ArrayList<>();
	Map<String, Integer> partecipRate= new TreeMap<>();
	
	public Proposta(String code, String agency, String destination, String period, int minNP, int maxNP, int price) {
		super();
		this.code = code;
		this.agency = agency;
		this.destination = destination;
		this.period = period;
		this.minNP = minNP;
		this.maxNP = maxNP;
		this.price = price;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public int getMinNP() {
		return minNP;
	}
	public void setMinNP(int minNP) {
		this.minNP = minNP;
	}
	public int getMaxNP() {
		return maxNP;
	}
	public void setMaxNP(int maxNP) {
		this.maxNP = maxNP;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<AttivitaProposta> getAttivitaProposte() {
		return attivitaProposte;
	}
	public void setAttivitaProposte(List<AttivitaProposta> attivitaProposte) {
		this.attivitaProposte = attivitaProposte;
	}
	public void addAttivitaProposta(AttivitaProposta p) {
		attivitaProposte.add(p);
		
	}
	public List<String> getPartecipanti() {
		return partecipanti;
	}
	public void setPartecipanti(List<String> partecipanti) {
		this.partecipanti = partecipanti;
	}
	public void addPartecipanti(List<String> res) {
	partecipanti.addAll(res);
		
	}
	public void addPartecRate(String string, Integer rate) {
		partecipRate.put(string, rate);
		
	}
	public Map<String, Integer> getPartecipRate() {
		return partecipRate;
	}
	public void setPartecipRate(Map<String, Integer> partecipRate) {
		this.partecipRate = partecipRate;
	}
	
	

}
