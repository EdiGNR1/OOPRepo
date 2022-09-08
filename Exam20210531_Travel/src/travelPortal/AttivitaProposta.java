package travelPortal;

public class AttivitaProposta {
	
	Proposta prop;
	String activityType;
	String description;
	int price;
	
	public AttivitaProposta(Proposta prop, String activityType, String description, int price) {
		super();
		this.prop = prop;
		this.activityType = activityType;
		this.description = description;
		this.price = price;
	}

	public Proposta getProp() {
		return prop;
	}

	public void setProp(Proposta prop) {
		this.prop = prop;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
