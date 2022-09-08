package delivery;

public class Menu implements Comparable<Menu>{
	
	String description;
	double price;
	String category;
	int prepTime;
	
	public Menu(String description, double price, String category, int prepTime) {
		super();
		this.description = description;
		this.price = price;
		this.category = category;
		this.prepTime = prepTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPrepTime() {
		return prepTime;
	}
	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}
	@Override
	public int compareTo(Menu o) {
		if(  this.description.equals(o.description) &&
			    this.price==(o.price) &&
			    this.category.equals(o.category) &&
			    this.prepTime==o.prepTime)
		    	  return 1;
		      else 
		    	  return -1;
	}
	
	


	
	

}
