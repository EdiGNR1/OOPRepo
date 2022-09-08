package travelPortal;

import java.util.*;
import java.util.stream.Collectors;

public class TravelPortal {
	

	List<String> attivita = new ArrayList<>();
	Map<String, Attivita> mappaAttivita = new TreeMap<>();
	Map<String, Agenzia> agenzie = new TreeMap<>();
	Map<String, Proposta> proposte = new TreeMap<>();
	
    //R1
	public List<String> addActivityTypes(String... names) {
		List<String> temp = new ArrayList<>();
		for(String s :names) {
			temp.add(s);
			mappaAttivita.put(s, new Attivita(s));
		}
		
		attivita.addAll(temp);
		Collections.sort(temp);
		
		return temp;
		
	
		
	}

	public int AddTravelAgency(String name, String... activityTypes) throws TPException {
		
		if(agenzie.containsKey(name)) {
			throw new TPException();
		}
		
		List<String> lista = new ArrayList<>();
		for(String s : activityTypes) {
		  if(!attivita.contains(s)) {
			  throw new TPException();
		   }else {
			   lista.add(s);
		   }
		}
		
		agenzie.put(name, new Agenzia(name));
		agenzie.get(name).addAttivita(lista);
		
		for(String s : lista) {
			
			mappaAttivita.get(s).addAgenzia(agenzie.get(name));
			mappaAttivita.get(s).addAgenziaStr(name);
		}
		

		
		return agenzie.get(name).getAttivita().size();
		


		
	}

	public SortedMap<String, List<String>> getAgenciesForActivityTypes() {
		
		
		SortedMap<String, List<String>> res = new TreeMap<>();
		mappaAttivita.values().stream()
		.filter(a->a.getAgenzie().size()>0)
		.forEach((x) -> res.put(x.getName(), 
		new ArrayList<String>( x.getAgenzieStr())));
		
		
		
		return res;




	}

//R2
	public int addProposal(String code, String agency, String destination, String period, int minNP, int maxNP,
			int price) throws TPException {
		
		if(!agenzie.containsKey(agency)) {
			throw new TPException();
		}
		
		if(proposte.containsKey(code)) {
			throw new TPException();
		}
		
		Proposta p = new Proposta(code, agency, destination, period, minNP, maxNP, price);
		
		proposte.put(code, p);
		agenzie.get(agency).addProposta(p);
		
		int day1= Integer.parseInt(period.split(":")[1].split("-")[0]);
		int day2= Integer.parseInt(period.split(":")[1].split("-")[1]);
		
	    return day2-day1;


	}

	public int addActivity(String code, String activityType, String description, int price) throws TPException {
		
		if(!agenzie.get(proposte.get(code).getAgency()).getAttivita().contains(activityType)) {
			throw new TPException();
		}
		
		
		AttivitaProposta p = new AttivitaProposta(proposte.get(code), activityType, description, price);
		agenzie.get(proposte.get(code).getAgency()).addAttivitaProposta(p);
		proposte.get(code).addAttivitaProposta(p);
		
		return proposte.get(code).getAttivitaProposte().stream().mapToInt(AttivitaProposta::getPrice).sum(); 
		
		


	}

	public int getProposalPrice(String code) throws TPException {
		if(!proposte.containsKey(code)){
			throw new TPException();
		}
		
		int priceViaggio = proposte.get(code).getPrice();
		int priceAttivita = proposte.get(code).getAttivitaProposte().stream().mapToInt(AttivitaProposta::getPrice).sum();
		
		return priceViaggio+priceAttivita;
		
		

	}

//R3
	public List<String> addParticipants(String code, String... names) throws TPException {
		
		if(names.length<proposte.get(code).minNP || names.length>proposte.get(code).maxNP) {
			throw new TPException();
		}
		List<String> res = new ArrayList<>();
		Proposta prop = proposte.get(code);
		int dayArrivoProp= Integer.parseInt(prop.getPeriod().split(":")[1].split("-")[0]);
		int dayPartenzaProp = Integer.parseInt(prop.getPeriod().split(":")[1].split("-")[1]);
		
		
		for(String s : names) {
			boolean b = true;
			for(Proposta p : proposte.values()) {
				if(p.getPartecipanti().contains(s)) {
					int dayArrivo= Integer.parseInt(p.getPeriod().split(":")[1].split("-")[0]);
					int dayPartenza = Integer.parseInt(p.getPeriod().split(":")[1].split("-")[1]);
					
					
					if((dayArrivoProp<dayArrivo && dayPartenzaProp>dayArrivo) ||
						(dayArrivoProp>dayArrivo && dayPartenzaProp<dayPartenza) ||	
						(dayArrivoProp<dayArrivo && dayPartenzaProp>dayPartenza) ||
						(dayArrivoProp<dayPartenza && dayPartenzaProp>dayPartenza)
						) {
						b=false;
					}
					}
					
				
			}
			if(b==true) {
				res.add(s);
			}
		}
		if(res.size()<proposte.get(code).minNP || res.size()>proposte.get(code).maxNP) {
			throw new TPException();
		}
		
		prop.addPartecipanti(res);
		
		return res;
		



	}

	public int getIncome(String code) {
		
		try {
			return this.getProposalPrice(code)*proposte.get(code).getPartecipanti().size();
		} catch (TPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		



	}

//R4
	public String addRatings(String code, int... evaluations) throws TPException {
		
		if(evaluations.length!=proposte.get(code).getPartecipanti().size()) {
			throw new TPException();
		}
		
		int i=0;
		String res="";
		List<String> p = proposte.get(code).getPartecipanti();
		for(Integer rate : evaluations) {
			if(i<p.size()-1) {
			res= res+""+p.get(i)+":"+rate+", ";
			}else {
				res= res+""+p.get(i)+":"+rate;	
			}
			proposte.get(code).addPartecRate(p.get(i),rate);
			i++;
		}
		
		return res;
		




	}

	public SortedMap<String, Integer> getTotalRatingsForParticipants() {
		
		SortedMap<String, Integer> res = new TreeMap<>();
		
		for(Proposta p : proposte.values()) {
		p.getPartecipRate().forEach((k, v) -> res.put(k, res.getOrDefault(k, 0) + v));
		}
         
		return res;

	}

//R5
	public SortedMap<String, Integer> incomeForActivityTypes() {
        
		List<AttivitaProposta> attivita = new ArrayList<>();
		for(Proposta p : proposte.values()) {
		attivita.addAll(p.getAttivitaProposte());
		}
		
		return attivita.stream().collect(Collectors.groupingBy(AttivitaProposta::getActivityType,
		TreeMap::new,
		Collectors.summingInt(AttivitaProposta::getPrice)));
		
		


	}

	public SortedMap<Integer, List<String>> participantsWithSameNofProposals() {
		//
		List<String> partecipanti = new ArrayList<String>();
		
		for(Proposta p : proposte.values()) {
			partecipanti.addAll(p.getPartecipanti());
			
		}
		
		Map<String, Integer> partecNumPorposte = new TreeMap<>();
		
		// mappa con chiave il nome del partecipante e come valore il numero di proposte 
		//a cui ha partecipato
		partecipanti.forEach(x ->partecNumPorposte.put(x,  partecNumPorposte.getOrDefault(x, 0) + 1));
		
		//inverto la mappa di prima raggruppando per valore (che diventa la chiave) 
		//e usando la lista dei partecipanti come valore
		return partecNumPorposte.entrySet().stream()
		.collect(Collectors.groupingBy(Map.Entry::getValue,TreeMap::new, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));



	}
}
