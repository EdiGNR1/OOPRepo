package mountainhuts;

import java.util.Optional;

/**
 * Represents a mountain hut
 * 
 * It includes a name, optional altitude, category,
 * number of beds and location municipality.
 *  
 *
 */
public class MountainHut {
	
	private String name;
	private Optional<Integer> altitude;
	private String category;
	private Integer bedsNumber;
	private Municipality municipality;
	private Integer altitudeInt;
	
	
	
	
	public MountainHut(String name, Integer altitude, String category, Integer bedsNumber, Municipality municipality) {
		super();
		this.name = name;
		this.altitude = Optional.ofNullable(altitude);
		this.category = category;
		this.bedsNumber = bedsNumber;
		this.municipality = municipality;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Optional<Integer> getAltitude() {
		return altitude;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getBedsNumber() {
		return bedsNumber;
	}
	public void setBedsNumber(Integer bedsNumber) {
		this.bedsNumber = bedsNumber;
	}
	public Municipality getMunicipality() {
		return municipality;
	}
	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}
	public Integer getAltitudeInt() {
		return altitudeInt;
	}
	public void setAltitudeInt(Integer altitudeInt) {
		this.altitudeInt = altitudeInt;
	}
	
	

	
	
	
}
