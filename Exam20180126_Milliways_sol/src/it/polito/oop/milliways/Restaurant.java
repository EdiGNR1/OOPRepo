package it.polito.oop.milliways;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.HashMap;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import static java.util.Comparator.*;

public class Restaurant {
	
	Map<String, Race> razze = new TreeMap<String, Race>();
	List<Party> parties = new ArrayList<Party>();
	Map<Integer, Hall> halls = new HashMap<Integer, Hall>();
	List<Hall> hallList = new ArrayList<Hall>();

	public Restaurant() {
		
	}
	
	public Race defineRace(String name) throws MilliwaysException{
		
		if(razze.containsKey(name)) {
			throw new MilliwaysException();
		}
		
		Race r = new Race(name);
		razze.put(name, r);
		
		
		return r;
	}
	
	public Party createParty() {
		
		Party p = new Party();
		parties.add(p);
		return p;
	    
	}
	
	public Hall defineHall(int id) throws MilliwaysException{
		if(halls.containsKey(id)) {
			throw new MilliwaysException();
		}
		
		halls.put(id,new Hall(id));
		hallList.add(new Hall(id));
		return halls.get(id);
	  
	}

	public List<Hall> getHallList() {
		//System.out.println(hallList);
		return hallList;
		
	}

	public Hall seat(Party party, Hall hall) throws MilliwaysException {
		
		if(hall.isSuitable(party)==false) {
			throw new MilliwaysException();
			
			
		}
		hall.addGruppo(party);
		return hall;
		
	}

	public Hall seat(Party party) throws MilliwaysException {
		
		boolean trovato = false;
		Hall hall = null;
		
		for(Hall h : halls.values()) {
			if(trovato == false && h.isSuitable(party)==true) {
				trovato = true;
				hall=h;
			}
		}
		
		if(trovato == false) {
			throw new MilliwaysException();
		}
		
		hall.addGruppo(party);
		
		return hall;
		
	}

	public Map<Race, Integer> statComposition() {
		
		
		Map<Race, Integer> res = new TreeMap<Race, Integer>();
		
		for(Hall h : halls.values()) {
			for(Party p : h.getGruppi()) {
				for(Race r : p.getComposition().keySet()) {
					
					if(res.containsKey(r)) {
						int temp= res.get(r)+p.getComposition().get(r);
						res.put(r, temp);
						
					}else {
						res.put(r, p.getComposition().get(r));
					}
					
				}
			}
		}
		
		
		
		
		return res;
		}

	public List<String> statFacility() {
		return  halls.values().stream()
				.flatMap(h -> h.getFacilities().stream())
				.collect(Collectors.groupingBy(	x -> x, 
				Collectors.counting())).entrySet().stream()
				.sorted(comparing(Map.Entry::getKey))
				.sorted(comparing(Map.Entry::getValue, reverseOrder()))
				.map(Entry::getKey).collect(Collectors.toList());
	
	}
	
	public Map<Integer,List<Integer>> statHalls() {
		return halls.values().stream().sorted(Comparator.comparingInt(Hall::getId))
        .collect(groupingBy(Hall::getNumFacilities, TreeMap::new, mapping(Hall::getId, toList()) ));

	}

}
