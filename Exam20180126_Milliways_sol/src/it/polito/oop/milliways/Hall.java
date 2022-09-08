package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hall {
	
	int id;
	List<String> servizi = new ArrayList<String>();
	List<Party> gruppi = new ArrayList<Party>();
	
	 Hall(int id) {
		 this.id=id;
		}

		public int getId() {
			return id;
			
		}

		public void addFacility(String facility) throws MilliwaysException {
			if(servizi.contains(facility)) {
				throw new MilliwaysException();
			}
			
			servizi.add(facility);
		
		}

		public List<String> getFacilities() {
			
			List<String> res = new ArrayList<String>(servizi);
			Collections.sort(res);
			
			return res;
			
		}
		
		int getNumFacilities(){
			return servizi.size();
		   
		}

	
		public boolean isSuitable(Party party) {
			
			boolean b = true;
			
			for(Race r : party.getRazze().values()) {
				for(String s : r.getRequirements() ) {
					if(!servizi.contains(s)) {
						b=false;
					}
				}
				
			}
			return b;

		}

		@Override
		public String toString() {
			return "Hall [id=" + id + ", facilities=" + getFacilities() + "]";
		}

		public void addGruppo(Party party) {
			gruppi.add(party);
			
		}

		public List<Party> getGruppi() {
			return gruppi;
		}

		public void setGruppi(List<Party> gruppi) {
			this.gruppi = gruppi;
		}
		
		

	
}

