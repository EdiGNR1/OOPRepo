package carShare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CarService {


	Map<String,Parking> parkings = new TreeMap<String,Parking>();
	Map<String,Car> cars = new TreeMap<String,Car>();
	Map<String,Subscriber> subscribers = new TreeMap<String,Subscriber>(); 
	List<Note> notes = new ArrayList<Note>();
	
	
	public void addParking(String name) throws InvalidName{
		
		if(parkings.containsKey(name)) {
			throw new InvalidName();
		}
		
		parkings.put(name, new Parking(name));


	}

	public void addCar(String parking, String licencePlate, double minRate, double kmRate) throws InvalidName{


		if(!parkings.containsKey(parking)) {
			throw new InvalidName();
		}
		
		if(cars.containsKey(licencePlate)) {
			throw new InvalidName();
		}
		Car c = new Car (parkings.get(parking), licencePlate, minRate, kmRate);
		
		parkings.get(parking).addCar(licencePlate, c);
		cars.put(licencePlate, c);
		c.setPrenotata(0);

	}
		
	public SortedSet<String> getAvailableCars(String parking) throws InvalidName{
		
		if(!parkings.containsKey(parking)) {
			throw new InvalidName();
		}
		
		List<String> res = new ArrayList<String>();
		for(Car c : cars.values()) {
			
			if(c.getParking().getName().compareTo(parking)==0) {
				if(c.isPrenotata()==0) {
			       res.add(c.getLicencePlate());
				}
			}
		}
		//System.out.println(parkings.get(parking).getCars());
		
		Collections.sort(res);
		
		SortedSet<String> ris = new TreeSet<String>(res);
		
		return ris;

		//return parkings.get(parking).getCars().stream().map(c -> (c.getLicencePlate())).collect(Collectors.toCollection(TreeSet::new));
		

    }
	
	public void addSubscriber(String card) throws InvalidName{
		
		if(subscribers.containsKey(card)) {
			throw new InvalidName();
		}
		
		subscribers.put(card, new Subscriber(card));

 
	}
	
	public List<String> getSubscribers(){
		
		List<String> res = new ArrayList<String>(subscribers.keySet());
		Collections.sort(res);
		
		return res;

    }
	
	public String reserve(String card, String parking) throws InvalidName{
		
		if(!subscribers.containsKey(card)) {
			throw new InvalidName();
		}
		if(!parkings.containsKey(parking)) {
			throw new InvalidName();
		}
		
		if(subscribers.get(card).isGiaPrenotazione()==true) {
			return null;
		}
		
		if(parkings.get(parking).almenoUnaLibera()==false) {
			return null;
		}
		
		
		Car c = parkings.get(parking).prenota(subscribers.get(card));
		//System.out.println(c);
		c.setIscritto(subscribers.get(card));
		c.setPrenotata(1);
		subscribers.get(card).setGiaPrenotazione(true);
		
		return c.getLicencePlate();


	}
	
	public String release(String card, String plate) throws InvalidName{
		
		if(!subscribers.containsKey(card)) {
			throw new InvalidName();
		}
		if(!cars.containsKey(plate)) {
			throw new InvalidName();
		}
		
		Car c = cars.get(plate);
		Subscriber i  = subscribers.get(card);
		
		if(c.isPrenotata()==1 && c.getIscritto().getCard().compareTo(card)==0) {
		
		
		c.setPrenotata(0);
		c.setIscritto(null);
		
		i.setGiaPrenotazione(false);
		
		
		return plate;
		}else {
			return null;
		}


	}
	// R3 #################################################################################################################################
	
	public Set<String> getReserved(String parking) throws InvalidName{
		
		if(!parkings.containsKey(parking)) {
			throw new InvalidName();		
		}
		
		Parking p = parkings.get(parking);
		List<String> res = new ArrayList<String>();
		
		for(Car c : p.getCars().values()) {
			if(c.isPrenotata()==1) {
				res.add(c.getLicencePlate());
			}
			
		}
		
		Collections.sort(res);
		Set<String> ris = new TreeSet<String>(res);
		
		return ris;

    }
	
	public String useCar(String card, String plate) throws InvalidName{
		
		if(!subscribers.containsKey(card)) {
			throw new InvalidName();
		}
		if(!cars.containsKey(plate)) {
			throw new InvalidName();
		}
		
		Car c = cars.get(plate);
		
		if(c.getIscritto()==null || c.getIscritto().getCard().compareTo(card)!=0) {
			return null;
		}else {
			c.setNelParcheggio(false);
		c.getParking().getCars().remove(plate);
		return plate;
		}
		

		
	}
	
	public String terminate(String card, String plate, String parking, int min, int km) throws InvalidName{
		
		if(!subscribers.containsKey(card)) {
			throw new InvalidName();
		}
		
		if(!parkings.containsKey(parking)) {
			throw new InvalidName();		
		}
		
		Car c = cars.get(plate);
		Parking p = parkings.get(parking);
		Subscriber s = subscribers.get(card);
		
		if(c.getIscritto()==null || c.getIscritto().getCard().compareTo(card)!=0 ) {
			return null; 
			}
		else {
			double tot= (min*c.getMinRate()) + (km*c.getKmRate());
			String res = card+":"+plate+":"+tot+":"+c.getParking().getName()+":"+parking;
			
			c.setParking(p);
		    p.addCar(plate, c);
			c.setPrenotata(0);
			c.setNelParcheggio(true);
			c.setIscritto(null);
			s.setGiaPrenotazione(false);
			s.addNote(res);
			
			Note n = new Note(res, card, plate, parking, min, km, tot);
			notes.add(n);
			
			return res;
		
		}
		
		


	}
	
	public class NoteComparator implements Comparator<Note> {

	    @Override
	    public int compare(Note n1,Note n2) {
	       return - Integer.compare((int)n1.getAddebito(),(int)n2.getAddebito());
	    }

	}

	public List<String> charges() {
		List<Note> n = new ArrayList<Note>(notes);
		 NoteComparator comp = new  NoteComparator();
		Collections.sort(n, comp);
		
		//System.out.println(n);
		
		List<String>res = new ArrayList<String>();
		for(Note note : n) {
			res.add(note.getId());
		}
		
		return res;

    }
		
	public List<String> subscriberCharges(String card) throws InvalidName{
		
		if(!subscribers.containsKey(card)) {
			throw new InvalidName();
		}
		
		return subscribers.get(card).getNote();

    }
	
	public double averageCharge() {
		double res =0.0;
		
		for(Note n : notes) {
			res=res+n.getAddebito();
		}
		
		
		
		return ((double)res/notes.size());

    }
	
	public long departuresFrom(String parking) throws InvalidName{
		
		if(!parkings.containsKey(parking)) {
			throw new InvalidName();
		}
		
		int i=0;
		
		for(Note n : notes) {
			//System.out.println(n.getId());
			if(n.getId().split(":")[3].compareTo(parking)==0) {
				i++;
			}
		}
		return i;

    }
}
