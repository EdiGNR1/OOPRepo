package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Race implements Comparable<Race>{
	
	String name;
	List<String> requisiti = new ArrayList<String>();
	
	Race(String name) throws MilliwaysException {
		this.name=name;
			}
	
	public void addRequirement(String requirement) throws MilliwaysException {
		
		if(requisiti.contains(requirement)) {
			throw new MilliwaysException();
		}else {
			requisiti.add(requirement);
		}
		
	
	}
	
	public List<String> getRequirements() {
		List<String> res = requisiti;
		Collections.sort(res);
		return res;


	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	
		return name;
	}

	@Override
	public int compareTo(Race o) {
		
		if(name.compareTo(o.getName())<0) {
			return -1;
		}else if(name.compareTo(o.getName())>0) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	
	
	
}
