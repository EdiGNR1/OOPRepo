package it.polito.oop.production;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * Facade class used to interact with the system.
 *
 */
public class Carmaker {

	/** unique code for diesel engine **/
	public static final int DIESEL = 0;
	/** unique code for gasoline engine **/
	public static final int GASOLINE = 1;
	/** unique code for gpl engine **/
	public static final int GPL = 2;
	/** unique code for electric engine **/
	public static final int ELECTRIC = 3;
	
	//lista di modelli
	//lista Factory
	//lista Magazzini
	
	
	
	//Modello codice, nome, annoEmissione, cilindrata, motorizzazione, isActive() -> java.time.LocalDate.now().getYear()
	//Factory nome, lista line
	//Line nome, capacitaProduzione,motorizzazion
	//Storage nome, capienzaMax
	
	private float ismin;
	private float ismax;
	private Map<String,Model> models = new HashMap<>();
	private Map<String,Factory> factories = new HashMap<>();
	private Map<String,Storage> storages = new HashMap<>();
	
    
	// **************** R1 ********************************* //

	/**
	 * Add a new model to the brand factory.
	 * 
	 * Models are uniquely identified by a code
	 * 
	 * @param code 	code
	 * @param name  name
	 * @param year	year of introduction in the market
	 * @param displacement  displacement of the engine in cc
	 * @param enginetype	the engine type (e.g., gasoline, diesel, electric)
	 * @return {@code false} if code is duplicate, 
	*/
	public boolean addModel(String code, String name,  int year, float displacement, int enginetype) {
		Model model = models.get(code);
		if(model == null) {
			models.put(code, new Model(code,name,year,displacement,enginetype));
			return true;
		}
        return false;
	}
	
	/**
	 * Count the number of models produced by the brand
	 * 
	 * @return models count
	 */
	public int countModels() {
		return models.size();
	}
	
	/**
	 * Retrieves information about a model.
	 * Information is formatted as code, name, year, displacement, enginetype
	 * separate by {@code ','} (comma).
	 * 	 
	 * @param code code of the searched model
	 * @return info about the model
	 */
	public String getModel(String code) {
		return models.get(code).toString();
	}
	
	
	/**
	 * Retrieves the list of codes of active models.
	 * Active models not older than 10 years with respect to the execution time.
	 * 	 
	 * @return a list of codes of the active models
	 */
	public List<String> getActiveModels() {
		return models.values().stream().filter(Model::isActive).map(Model::getCode).collect(Collectors.toList());
	}
	
	
	/**
	 * Loads a list of models from a file.
	 * @param Filename path of the file
	 * @throws IOException in case of IO problems
	 */
	public void loadFromFile(String Filename) throws IOException  {
		List<String> lines = readData(Filename);
		if(lines==null)
			throw new IOException("no lines read");
		for(String line: lines) {
			String[] fields = line.split("\t");
			if(fields.length == 5) 
				this.addModel(fields[0].trim(), fields[1].trim(), Integer.parseInt(fields[2]), Float.parseFloat(fields[3]), Integer.parseInt(fields[4]));
		}
	}
	
	
	
	
	private static List<String> readData(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	
	// **************** R2 ********************************* //

	
	
	/**
	 * Creates a new factory given its name. Throws Brand Exception if the name of the factory already exists.
	 * 
	 * @param name the unique name of the factory.
	 * @throws BrandException
	 */
	public void buildFactory(String name) throws BrandException {
		Factory factory = this.factories.get(name);
		if(factory != null)
			throw new BrandException("duplicato");
		factories.put(name, new Factory(name));
	}
	
	
	
	/**
	 * Returns a list of the factory names. The list is empty if no factories are created.
	 * @return A list of factory names.
	 */
	public List<String> getFactories() {
		return new ArrayList<String>(factories.keySet());
	}
	
	
	/**
	 * Create a set of production lines for a factory.
	 * Each production line is identified by name,capacity and type of engines it can handle.
	 * 
	 * @param fname The factory name
	 * @parm  line	An array of strings. Each string identifies a production line.
	 * 
	 * @throws BrandException if factory name is not defined or line specification is malformed
	 */
	public void setProductionLines (String fname, String... lines) throws BrandException {
		Factory factory = factories.get(fname);
		for(String line: lines) {
			String [] fields = line.split(":");
			if(fields.length != 3 || Integer.parseInt(fields[1])<0 || Integer.parseInt(fields[2]) < 0 || Integer.parseInt(fields[2]) > 3)
				throw new BrandException("boh");
			factory.setLine(new Line(fields[0].trim(),Integer.parseInt(fields[1]), Integer.parseInt(fields[2])));
				
		}
	}
	
	/**
	 * Returns a map reporting for each engine type the yearly production capacity of a factory.
	 * 
	 * @param fname factory name
	 * @return A map of the yearly productions
	 * @throws BrandException if factory name is not defined or it has no lines
	 */
	public Map<Integer, Integer> estimateYearlyProduction(String fname) throws BrandException {
		if(factories.get(fname) == null || factories.get(fname).getLines() == null)
			throw new BrandException("boh");
		
		return factories.get(fname).getLines().values().stream().collect(Collectors.groupingBy(Line::getEnginetype, Collectors.summingInt(Line::getCapacity)));

	}

	// **************** R3 ********************************* //

	
	/**
	 * Creates a new storage for the car maker
	 * 
	 * @param name		Name of the storage
	 * @param capacity	Capacity (number of cars) of the storage
	 * @throws BrandException if name already defined or capacity &le; 0
	 */
	public void buildStorage (String name, int capacity) throws BrandException {
		Storage storage = this.storages.get(name);
		if(storage != null)
			throw new BrandException("duplicato");
		storages.put(name, new Storage(name, capacity));
	}
	
	/**
	 * Retrieves the names of the available storages. 
	 * The list is empty if no storage is available
	 * 
	 * @return List<String> list of storage names
	 */
	public List<String> getStorageList() {
		return new ArrayList<String>(storages.keySet());
	}
	
	/**
	 * Add a car to the storage if possible
	 * 
	 * @param sname		storage name
	 * @param model		car model
	 * 
	 * @throws BrandException if storage or model not defined or storage is full
	 */
	public void storeCar(String sname, String model) throws BrandException {
		Storage storage = this.storages.get(sname);
		Model car = this.models.get(model);
		if(car == null || storage == null) {
			throw new BrandException("sbagliato");
		}
		storage.setModel(car);
		
	}
	
	
	/**
	 * Remove a car to the storage if possible.
	 * 
	 * @param sname		Storage name
	 * @param model		Car model
	 * @throws BrandException  if storage or model not defined or storage is empty
	 */
	public void removeCar(String sname, String model) throws BrandException {
		Storage storage = this.storages.get(sname);
		Model car = this.models.get(model);
		if(car == null || storage == null) {
			throw new BrandException("sbagliato");
		}
		storage.removeModel(car);
	}
	
	/**
	 * Generates a summary of the storage.
	 * 
	 * @param sname		storage name
	 * @return map of models vs. quantities
	 * @throws BrandException if storage is not defined
	 */
	public Map<String,Integer> getStorageSummary(String sname) throws BrandException {
		return storages.get(sname).getModels().stream().collect(Collectors.groupingBy(Model::getCode, Collectors.reducing(0, e -> 1, Integer::sum)));
	}
	
	// **************** R4 ********************************* //
	

	/**
	 * Sets the thresholds for the sustainability level.
	 * 
	 * @param ismin	lower threshold
	 * @param ismax upper threshold
	 */
	public void setISThresholds (float ismin, float ismax) {
         this.ismax = ismax;
         this.ismin = ismin;
	}
	
	
	
	
	/**
	 * Retrieves the models classified in the given Sustainability class.
	 * 
	 * @param islevel sustainability level, 0:low 1:medium 2:high
	 * @return the list of model names in the class
	 */
	public List<String> getModelsSustainability(int islevel) {
		return this.models.values().stream().filter( c -> c.getSustainability(this.ismin,this.ismax) == islevel).map(Model::getCode).collect(Collectors.toList());
	}
	
	
	/**
	 * Computes the Carmaker Sustainability level
	 * 
	 * @return sustainability index
	 */
	
	
	public float getCarMakerSustainability() {
		 return (float) (this.storages.values().stream().flatMap(c -> c.getModels().stream()).mapToDouble(Model::getSustainabilityIndex).average().getAsDouble());
	}
	
	// **************** R5 ********************************* //

	/**
	 * Generates an allocation production plan
	 * 
	 * @param request allocation request string
	 * @return {@code true} is planning was successful
	 */
	public boolean plan(String request) {
		String[] list = request.split(",");
		int all = 0;
		boolean sut = false;
		for(String req: list){
			String[] field = req.split(":");
			Model model = models.get(field[0]);
			int quanti = Integer.parseInt(field[1]);
			for(Factory factory: this.factories.values()) {
				for(Line line: factory.getLines().values()) {
					if(line.getEnginetype() == model.getEnginetype()) {
						if(line.updateActualCapacity(quanti))
							sut = true;
							all++;
							break;
					}
				}
				if (sut)
					break;
			}
		}
		return all == list.length;
	}
	
	
	
	/**
	 * Returns the capacity of a line
	 * 
	 * @param fname factory name
	 * @param lname line name
	 * @return total capacity of the line
	 */
	public int getLineCapacity(String fname, String lname) {
		return this.factories.get(fname).getLines().get(lname).getCapacity();
	}
	
	/**
	 * Returns the allocated capacity of a line
	 * @param fname factory name
	 * @param lname line name
	 * @return already allocated capacity for the line
	 */
	public int getLineAllocatedCapacity(String fname, String lname) {
		return this.factories.get(fname).getLines().get(lname).getActualCapacity();
	}
	
	
	
	// **************** R6 ********************************* //
	
	/**
	 * compute the proportion of lines that are fully allocated
	 * (i.e. allocated capacity == total capacity) as a result
	 * of previous calls to method {@link #plan}
	 * 
	 * @return proportion of lines fully allocated
	 */
	public float linesfullyAllocated() {
		long total = this.factories.values().stream().flatMap(c->c.getLines().values().stream()).count();
		long partial = this.factories.values().stream().flatMap(c->c.getLines().values().stream()).filter(c-> c.getActualCapacity()== c.getCapacity()).count();
		return (float) partial/total;
	}

	/**
	 * compute the proportion of lines that are unused
	 * (i.e. allocated capacity == 0) as a result
	 * of previous calls to method {@link #plan}
	 * 
	 * @return proportion of unused lines
	 */
	public float unusuedLines() {
		long total = this.factories.values().stream().flatMap(c->c.getLines().values().stream()).count();
		long partial = this.factories.values().stream().flatMap(c->c.getLines().values().stream()).filter(c-> c.getActualCapacity()== 0.0).count();
		return (float) partial/total;
	}
}
