package it.polito.oop.production;

public class Model {
	
	private String code;
	private String name;
	private int year;
	private float displacement;
	private int enginetype;

	public Model(String code, String name, int year, float displacement, int enginetype) {
		super();
		this.code = code;
		this.name = name;
		this.year = year;
		this.displacement = displacement;
		this.enginetype = enginetype;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public float getDisplacement() {
		return displacement;
	}

	public void setDisplacement(float displacement) {
		this.displacement = displacement;
	}

	public int getEnginetype() {
		return enginetype;
	}

	public void setEnginetype(int enginetype) {
		this.enginetype = enginetype;
	}
	
	public boolean isActive() {
		return java.time.LocalDate.now().getYear() - year <= 10;
	}
	
	public float getSustainabilityIndex() {
		return (float)(this.enginetype * 100) / (float)(java.time.LocalDate.now().getYear() - year - 1);
				
	}
	
	public int getSustainability(float ismin, float ismax) {
		float is = (this.enginetype * 100) / (java.time.LocalDate.now().getYear() - year - 1);
		if(is <ismin)
			return 0;
		else if(is>ismax) 
			return 2;
		else 
			return 1;
				
	}

	@Override
	public String toString() {
		return  code + "," + name + "," + year + "," + displacement + "," + enginetype;
	}
	
}
