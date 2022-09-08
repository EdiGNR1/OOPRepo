package it.polito.oop.production;
import java.util.TreeMap;
import java.util.Map;
import java.util.List;

public class Factory {
	private String name;
	private Map<String, Line> lines = new TreeMap<>();
	
	public Factory(String name) {
		this.name = name;
	}

	public Map<String, Line> getLines() {
		return lines;
	}

	public void setLine(Line line) {
			Line linea = this.lines.get(line.getName());
			if( linea != null) {
				linea.updateCapacity(line.getCapacity());
			}
			else {
				this.lines.put(line.getName(), line);
			}
	}

	public String getName() {
		return name;
	}
	
	
	
}
