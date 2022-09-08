package mountainhuts;

public class Municipality {
	
	private String name;
	private String province;
	private Integer altitude;
	
	public Municipality(String name, String province, Integer altitude) {
		
		this.name = name;
		this.province = province;
		this.altitude = altitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getAltitude() {
		return altitude;
	}

	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}
	
	
	

}
