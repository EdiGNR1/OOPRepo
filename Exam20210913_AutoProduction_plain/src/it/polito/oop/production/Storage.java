package it.polito.oop.production;

import java.util.HashMap;
import java.util.*;

public class Storage {
	
	private String name;
	private int capacity;
	private List<Model> models = new ArrayList<>();
	public Storage(String name, int capacity) {
		super();
		this.name = name;
		this.capacity = capacity;
	}
	public String getName() {
		return name;
	}
	public int getCapacity() {
		return capacity;
	}
	public List<Model> getModels(){
		return models;
	}

	public void setModel(Model car) throws BrandException {
		if(this.capacity == models.size() + 1)
			throw new BrandException("full");
			models.add(car);
		
	}
	
	public void removeModel(Model car)throws BrandException {
		if( models.size() == 0)
			throw new BrandException("empty");
		models.remove(car);
	}
}
