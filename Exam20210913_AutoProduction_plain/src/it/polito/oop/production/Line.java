package it.polito.oop.production;

public class Line {
	private String name;
	private int capacity;
	private int enginetype;
	private int actualCapacity;
	
	public int getActualCapacity() {
		return this.actualCapacity;
	}
	
	public boolean updateActualCapacity(int cap) {
		if(cap + actualCapacity == capacity) {
			capacity = cap + actualCapacity;
			return true;
		}else
			return false;
	}
	public String getName() {
		return name;
	}
	public int getCapacity() {
		return capacity;
	}
	public int getEnginetype() {
		return enginetype;
	}
	
	public void updateCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Line(String name, int capacity, int enginetype) {
		super();
		this.name = name;
		this.capacity = capacity;
		this.enginetype = enginetype;
	}
	
	
}
