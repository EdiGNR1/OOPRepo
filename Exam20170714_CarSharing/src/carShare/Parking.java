package carShare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Parking {
	
	String name;
	//List<Car> cars = new ArrayList<Car>();
	Map<String, Car> cars = new TreeMap<String,Car>();

	public Parking(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addCar(String s , Car car) {
		cars.put(s, car);
		
	}

	public Map<String, Car> getCars() {
		return cars;
	}

	public void setCars(Map<String, Car> cars) {
		this.cars = cars;
	}
	
	public boolean almenoUnaLibera() {
		boolean b=false;
		
		
		for(Car c: cars.values()) {
		//	System.out.println(c.isPrenotata());
			if(c.isPrenotata()==0) {
				b=true;
			}
		}
		
		return b;
		
		
	}

	public Car prenota(Subscriber subscriber) {
		List<String> targhe = new ArrayList<String>(cars.keySet());
		Collections.sort(targhe);
		
		Car ris = null;
		
		for(String s : targhe) {
			
			if(ris==null) {
			Car c = cars.get(s);
			if(c.isPrenotata()==0) {//System.out.println(1);
				ris=c;
			 }
			}
		}
		
		return ris;
	}

	
	
	
	
	

}
