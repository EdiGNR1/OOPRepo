package it.polito.oop.milliways;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;

public class Party {

   Map<Race, Integer> composition ;
   Map<String, Integer> compagni= new TreeMap<String, Integer>();
   Map<String, Race> razze = new TreeMap<String, Race>();
	
   protected Map<Race, Integer> getComposition() {
		return composition;
		
	}
   
   Party() {
		composition = new HashMap<>();
	}


	public void addCompanions(Race race, int num) {
		
		compagni.put(race.getName(), num);
		razze.put(race.getName(), race);		
		composition.put(race, num);
		
	}

	public int getNum() {
		int i=0;
		for(int r : compagni.values()) {
			i+=r;
		}
		return i;


	}

	public int getNum(Race race) {
		return compagni.get(race.getName());
		
	}

	public List<String> getRequirements() {
		
		List<String> req = new ArrayList<String>();
		
		for(String r : compagni.keySet()) {
			for(String s : razze.get(r).getRequirements()) {
			if(!req.contains(s)) {
				req.add(s);
			 }
			}
				
			
		}
		
		Collections.sort(req);
		
		return req;

	}
	
	public Map<String,Integer> getDescription(){
		return compagni;
	 
	}
	
	public Map<String, Race> getRazze() {
		return razze;
	}


	public void setRazze(Map<String, Race> razze) {
		this.razze = razze;
	}



	public Map<String, Integer> getCompagni() {
		return compagni;
	}


	public void setCompagni(Map<String, Integer> compagni) {
		this.compagni = compagni;
	}
	
	@Override
	public String toString() {
		return "Party [composition=" + composition + "]";
	}

	public void setComposition(Map<Race, Integer> composition) {
		this.composition = composition;
	}
	
}

	

