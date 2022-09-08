package mountainhuts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


public class Region {
	
	private String name;
	List<Altitude> altitudes = new ArrayList<Altitude>();
	Map<String, Municipality> municipalities = new TreeMap<String, Municipality>();
	Map<String, MountainHut> mountainHuts = new TreeMap<String, MountainHut>();
	List<String> altitudesRange = new ArrayList<String>();

	
	public Region(String name) {
		this.name=name;
		
	}

	
	public String getName() {
		return name;
		
	}


	public void setAltitudeRanges(String... ranges) {
		
		for(String s : ranges) {
			int min = Integer.parseInt(s.split("-")[0]);
			int max = Integer.parseInt(s.split("-")[1]);

			Altitude a = new Altitude(min, max);
			altitudes.add(a);
			altitudesRange.add(s);
			
		}
		
		
	}

	
	public String getAltitudeRange(Integer altitude) {
		
		String range = null;
		
		for(Altitude a : altitudes) {
			if(altitude <= a.getMax() && altitude> a.getMin()) {
				range= a.getMin()+"-"+a.getMax();
			}
		}
		
		if(range == null) {
			return "0-INF";
		}
		else {
			return range;
		}
		
		
		
	}

	
	public Collection<Municipality> getMunicipalities() {
		return municipalities.values();
	}

	
	public Collection<MountainHut> getMountainHuts() {
		return mountainHuts.values();
		
	}

	
	public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {
		
		Municipality m=null;

		
		for(String s : municipalities.keySet()) {
			if(s.compareTo(name)==0) {
				m= municipalities.get(name);
			}
			
		}
		
		if(m==null) {
			m = new Municipality(name, province, altitude);
			municipalities.put(name, m);
		}
		
		
		
		return m;
		
	}

	
	public MountainHut createOrGetMountainHut(String name, String category, Integer bedsNumber,
			Municipality municipality) {
		
		MountainHut m = null;
		
		for(String s : mountainHuts.keySet()) {
			if(s.compareTo(name)==0) {
				m= mountainHuts.get(name);
			}
		}
		
		if(m==null) {
			m = new MountainHut(name,null, category, bedsNumber, municipality);
			mountainHuts.put(name, m);
		}
		//System.out.println("entra");
		


		
				return m;
		
	}

	
	public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, Integer bedsNumber,
			Municipality municipality) {
		
				
        MountainHut m = null;
		
		for(String s : mountainHuts.keySet()) {
			if(s.compareTo(name)==0) {
				m= mountainHuts.get(name);
			}
		}
		
		if(m==null) {
			m = new MountainHut(name, altitude, category, bedsNumber, municipality);
			mountainHuts.put(name, m);
			m.setAltitudeInt(altitude);
		}
		//System.out.println("entra");
		

		
				return m;
		
		
	}

	
	public static Region fromFile(String name, String file) {
		
		Region r = new Region(name);
		
		List<String> fileLine = readData(file);
		
		String[] headers = fileLine.remove(0).split(",");
		Map<String,Integer> h2i = new TreeMap<>();
		
		for(int i=0; i< headers.length; i++) {
			h2i.put(headers[i], i);
		}
		
		fileLine.forEach(line -> {
			String [] cells = line.split(";");
			String province = cells[0];
			String municipalityName = cells[1];
			Integer municipalityAltitude = Integer.parseInt(cells[2]);
			
			Municipality m = r.createOrGetMunicipality(municipalityName, province, municipalityAltitude) ;
			
			String HutName = cells[3];
			String HutAltitude = cells[4];
			String category = cells[5];
			Integer beds = Integer.parseInt(cells[6]);
			
			if(HutAltitude.isBlank()) {
				r.createOrGetMountainHut(HutName, category, beds, m);
			}else {
				r.createOrGetMountainHut(HutName, Integer.parseInt(HutAltitude), category, beds, m);
			}
		}
		);
		
		
		return r;
		
	}

	private static List<String> readData(String file) {
		
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
		
		
	}

	
	public Map<String, Long> countMunicipalitiesPerProvince() {
		
		Map<String, Long> res = new TreeMap<String, Long>();
		
		for(Municipality m : municipalities.values()) {
			
			String province = m.getProvince();
			
			if(res.get(province)==null) {
				res.put(province, (long) 1);
			}else {
				
				Long temp = res.get(province);
				res.put(province, temp+1);
				
			}
			
		}
		return res;
		
	}


	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
		
		 Map<String, Map<String, Long>> res = new TreeMap<String, Map<String, Long>>();	
		
		for(MountainHut m : mountainHuts.values()) {
			
			
			String province = m.getMunicipality().getProvince();
			


			if(!res.containsKey(province)){
				
				Map<String, Long> temp = new TreeMap<String,Long>();
				temp.put(m.getMunicipality().getName(), (long) 1);
				res.put(province, temp);
			}else {
				//System.out.println(province);
				Map<String, Long> giaPres = new TreeMap<String,Long>(res.get(province));
				
				if(giaPres.get(m.getMunicipality().getName())==null) {
					giaPres.put(m.getMunicipality().getName(), (long) 1);
				}else {
					Long prec = giaPres.get(m.getMunicipality().getName());
					giaPres.put(m.getMunicipality().getName(), prec+1);
				}
				
				res.put(province, giaPres);
				
			}
			
		}
		//System.out.println(res);
		
		return res;
		
	}


	public Map<String, Long> countMountainHutsPerAltitudeRange() {
		
		 Map<String, Long> res = new  TreeMap<String, Long>();
		
		for(Altitude a : altitudes) {
			res.put(a.getMin()+"-"+a.getMax(), (long) 0);	
			res.put("0-INF", (long) 0);
		}
		
		//System.out.println(res.keySet());
		
		for(MountainHut m : mountainHuts.values()) {
			
			int alt = 0;
			
			if(m.getAltitude().isPresent()==false) {
				alt=m.getMunicipality().getAltitude();
			}else {
				alt=m.getAltitudeInt();
			}
			//System.out.println(alt+" "+m.getName());
			String range = null;
			
			for(Altitude a : altitudes) {
				if(alt<=a.getMax() && alt>a.getMin()) {
					range= a.getMin()+"-"+a.getMax();
				}
			}
			
			if(range!=null) {
				
				//System.out.println(range);
				Long temp=res.get(range);
			
			    res.put(range, temp+1);
			}else {
				Long temp= res.get("0-INF");
				res.put("0-INF", temp+1);
			}
			
			
			
		}
		
		
		return res;
		
	}

	
	private String getAltitudeRange1(MountainHut h) {
		if(h.getAltitude().isPresent()) {
			return getAltitudeRange(h.getAltitude().get());
		}else {
			return getAltitudeRange(h.getMunicipality().getAltitude());
		}
	}

	public Map<String, Integer> totalBedsNumberPerProvince() {
		
		Map<String, Integer> res = new TreeMap<String, Integer> ();
		
		for(MountainHut m : mountainHuts.values()) {
			
			String province = m.getMunicipality().getProvince();
			int posti = m.getBedsNumber();
			
			if(res.get(province)==null) {
				res.put(province, posti);
			}else {
				int temp=res.get(province);
				res.put(province, temp+posti);
			}
		}
		
		return res;
	}

	
	public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		
		
		Map<String, Optional<Integer>> res= mountainHuts.values().stream()
				.collect(Collectors.groupingBy(x -> getAltitudeRange1(x),
						 Collectors.mapping(MountainHut::getBedsNumber,
					     Collectors.maxBy(Comparator.naturalOrder()))));
		
		//altitudesRange.stream().map(Altitude::toString)

				return res;
	}

	
	public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
		
		
		
		       //prima mi prendo lo stream di stringhe dei nomi dei comuni, poi mi faccio una mappa con
		       //nome di ocmuni e numero di hut per il comune
		return mountainHuts.values().stream().map(x -> x.getMunicipality().getName())
				.collect(Collectors.groupingBy(x -> x, TreeMap::new, Collectors.counting()))
				                                      //ordino alfabeticamente
				.entrySet().stream().collect(Collectors.groupingBy(
				
			//serve per lavorare sulle mappe con gli stream, poi devo girare la mappa
	
			    Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, 
			    		Collectors.toList())));
	
	}


}
