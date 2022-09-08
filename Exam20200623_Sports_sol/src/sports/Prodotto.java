package sports;

public class Prodotto {
	
	String name;
	String activityName;
	Categoria c;
	
	public Prodotto(String name, String activityName, Categoria c) {
		super();
		this.name = name;
		this.activityName = activityName;
		this.c = c;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Categoria getC() {
		return c;
	}
	public void setC(Categoria c) {
		this.c = c;
	};
	
	

}
