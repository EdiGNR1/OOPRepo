package fit;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


public class Fit {
    
    public static int MONDAY    = 1;
    public static int TUESDAY   = 2;
    public static int WEDNESDAY = 3;
    public static int THURSDAY  = 4;
    public static int FRIDAY    = 5;
    public static int SATURDAY  = 6;
    public static int SUNDAY    = 7;
    
	public Fit() {
	
	}
	
	Map<String,Palestra> palestre = new TreeMap<>();
	List<Lesson> lezioni = new ArrayList<Lesson>();
	Map<Integer,Cliente> clienti = new TreeMap<>();
	int cont =0;
	
	// R1 
	
	public void addGymn (String name) throws FitException {
		
		if(palestre.containsKey(name)) {
			throw new FitException();
		}
		
		palestre.put(name, new Palestra(name));
	
	}
	
	public int getNumGymns() {
		return palestre.size();
		
	}
	
	//R2

	public void addLessons (String gymnname, 
	                        String activity, 
	                        int maxattendees, 
	                        String slots, 
	                        String ...allowedinstructors) throws FitException{
	
		if(!palestre.containsKey(gymnname)) {
			throw new FitException();
		}
		
		Palestra p = palestre.get(gymnname);
		
		String [] slot = slots.split(",");
		
		for(String s : slot) {
			int day = Integer.parseInt(s.split("[.]")[0]);
			int hour = Integer.parseInt(s.split("[.]")[1]);
			
			if(day<1 || day>7 || hour<7 || hour>20) {
				throw new FitException();
			}
			
			if(p.getSlotsOccupati().contains(s)) {
				throw new FitException();
			}
			
		}
		
		List<String> lista2 = new ArrayList<String>();
		
		for(String s : allowedinstructors) {
			lista2.add(s);
		}
		
		for(String s : slot) {
			p.addSlot(s);
			Lesson l = new Lesson(p, activity, maxattendees, s, lista2);
		    lezioni.add(l);	
		    p.addLesson(l);	
		    p.addSlotLesson(l,s);		
		}
		
		
		
	}
	
	//R3
	public int addCustomer(String name) {
		cont++;
		clienti.put(cont, new Cliente(cont, name));
		return cont;
		
	}
	
	public String getCustomer (int customerid) throws FitException{
		
		if(!clienti.containsKey(customerid)) {
			throw new FitException();
		}
		
		return clienti.get(customerid).getName();
	    
		
	}
	
	//R4
	
	public void placeReservation(int customerid, String gymnname, int day, int slot) throws FitException{
		if(!clienti.containsKey(customerid)) {
			throw new FitException();
		}
		if(!palestre.containsKey(gymnname)) {
			throw new FitException();
		}
		if(day<1 || day>7 || slot<8 || slot>20) {
			throw new FitException();
		}
		
		Palestra p = palestre.get(gymnname);
		
		if(!p.getSlotLezioni().keySet().contains(day+"."+slot)) {
			throw new FitException();
		}
		
		Cliente c = clienti.get(customerid);
		String slotStringa = day+"."+slot;
		Lesson l =p.getSlotLezioni().get(slotStringa);
		
		if(l.getPostiOccupati()<l.getMaxattendees() && (!l.getClienti().contains(c))) {
			//System.out.println("entrato");
			l.addCliente(c);
			c.addLesson(l);
			//System.out.println(c.getLezioni().size());
			l.changeOccupati();
		}else {
			throw new FitException();
		}
       
	}
	
	public int getNumLessons(int customerid) {
		//System.out.println(clienti.get(customerid).getI());
		return clienti.get(customerid).getLezioni().size();
		
	
	}
	
	
	//R5
	
	public void addLessonGiven (String gymnname, int day, int slot, String instructor) throws FitException{
		if(!palestre.containsKey(gymnname)) {
			throw new FitException();
		}
		if(day<1 || day>7 || slot<8 || slot>20) {
			throw new FitException();
		}
		if(!palestre.get(gymnname).getSlotLezioni().keySet().contains(day+"."+slot)) {
			throw new FitException();
		}
		if(!palestre.get(gymnname).getSlotLezioni().get(day+"."+slot).getIstruttori().contains(instructor)) {
			throw new FitException();
		}
		
		
		
		Lesson l=  palestre.get(gymnname).getSlotLezioni().get(day+"."+slot);
		l.setIstruttoreAssegnato(instructor);
		//System.out.println(l.getIstruttoreAssegnato());

	}
	
	public int getNumLessonsGiven (String gymnname, String instructor) throws FitException {
		if(!palestre.containsKey(gymnname)) {
			throw new FitException();
		}
		
		System.out.println(lezioni.size());
		
		return (int) palestre.get(gymnname).getLezioni().stream()
	   .filter(l->l.getIstruttoreAssegnato().compareTo(instructor)==0)
	   .count();
		
		
	}
	//R6
	
	public String mostActiveGymn() {
		
		Palestra p = palestre.values().stream()
		.max(Comparator.comparing(Palestra::getNumLezioni))
		.orElse(null);
		
		return p.getName();
		
		
	}
	
	public Map<String, Integer> totalLessonsPerGymn() {
		
		
		return palestre.values().stream()
		.collect(Collectors.toMap(Palestra::getName, Palestra::getNumLezioni));	
		
		
	}
	
	public SortedMap<Integer, List<String>> slotsPerNofParticipants(String gymnname) throws FitException{
		return palestre.get(gymnname).getLezioni().stream()
	.collect(Collectors.groupingBy(Lesson::getPostiOccupati, TreeMap::new, mapping(Lesson::getSlot, toList())));
	
	}
	

	
	
	
	


}
