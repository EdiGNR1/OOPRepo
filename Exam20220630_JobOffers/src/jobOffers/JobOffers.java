package jobOffers; 
import java.util.*;
import java.util.stream.Collectors;


public class JobOffers  {
	
	Set<String> capacita = new TreeSet<>();
	Map<String, Posizione> posizioni = new TreeMap<>();
	Map<String, Candidato> candidati = new TreeMap<>();
    Map<String, Consulente> consulenti = new TreeMap<>();
    Map<String, Punteggio> punteggi = new TreeMap<>();

 //R1
	public int addSkills (String... skills) {
		
		List<String> skil = Arrays.asList(skills);
		capacita.addAll(skil);
		
		return capacita.size();
	
	}
	
	public int addPosition (String position, String... skillLevels) throws JOException {
		
		if(posizioni.containsKey(position)) {
			throw new JOException("errore");
		}
		
		List<String> sl = Arrays.asList(skillLevels);
		Map<String,Integer> cl = new TreeMap<>();
		
		for(String s : sl) {
			String cap = s.split(":")[0];
			int level =Integer.parseInt(s.split(":")[1]);
			
			if(level<4 || level>8 || !capacita.contains(cap)) {
				throw new JOException("errore");
			}
			
			cl.put(cap, level);		
					
		}
		
		Posizione p = new Posizione(position);
		posizioni.put(position, p);
		p.addMappa(cl);
		
		return (int) Math.floor(p.getSkillLivello().values().stream().mapToInt(Integer::valueOf).average().getAsDouble());
		


	}
	
//R2	
	public int addCandidate (String name, String... skills) throws JOException {
		List<String> sl = Arrays.asList(skills);
		
		if(!capacita.containsAll(sl)) {
			throw new JOException("errore");
		}
		
		if(candidati.containsKey(name)){
			throw new JOException("errore");
		}
		
		Candidato c = new Candidato(name);
		candidati.put(name, c);
		c.addCap(sl);
		
		
		return c.getCapacita().size();
		
	
	}
	
	public List<String> addApplications (String candidate, String... positions) throws JOException {
		
		List<String> pos = Arrays.asList(positions);
		
		if(!candidati.containsKey(candidate)) {
			throw new JOException("errore");
		}
		
		if(!posizioni.keySet().containsAll(pos)){
			throw new JOException("errore");
		}
		
		Set<String> res = new TreeSet<>();
		for(String s : pos) {
		  if( !candidati.get(candidate).getCapacita().containsAll(posizioni.get(s).getSkillLivello().keySet())) {
			  throw new JOException("errore");
		  }
		  String sT=candidate+":"+s;
		  res.add(sT);
		  posizioni.get(s).addCandidato(candidati.get(candidate));
		  candidati.get(candidate).addPos(posizioni.get(s));
		}
		
		
		
		//System.out.println(res);
	
		return res.stream().sorted().collect(Collectors.toList());
		
	} 
	
	public TreeMap<String, List<String>> getCandidatesForPositions() {
		return posizioni.values().stream().sorted(Comparator.comparing(Posizione::getName))
			   .filter(p->p.getCandidati().size()>0)
	.collect(Collectors.toMap(Posizione::getName, Posizione::getCandidatiSTR,(x,y)->x, TreeMap::new
	));
	
	}
	
	
//R3
	public int addConsultant (String name, String... skills) throws JOException {
		
		if(consulenti.containsKey(name)) {
			throw new JOException("errore");
		}
		List<String> sl = Arrays.asList(skills);
		if(!capacita.containsAll(sl)) {
			throw new JOException("errore");
		}
		
		Consulente c = new Consulente(name);
		consulenti.put(name, c);
		c.addCap(sl);
		return c.getCapacita().size();
		
    
	}
	
	public Integer addRatings (String consultant, String candidate, String... skillRatings)  throws JOException {
		if(!consulenti.containsKey(consultant)) {
			throw new JOException("errore");
		}
		if(!candidati.containsKey(candidate)) {
			throw new JOException("errore");
		}
		
		List<String> sr = Arrays.asList(skillRatings);
		Map<String,Integer> cr = new TreeMap<>();
		
		for(String s : sr) {
			String cap = s.split(":")[0];
			int rate =Integer.parseInt(s.split(":")[1]);
			
			if(rate<4 || rate>10 || !capacita.contains(cap)) {
				throw new JOException("errore");
			}
			
			cr.put(cap, rate);		
					
		}
		
		if(!consulenti.get(consultant).getCapacita().containsAll(candidati.get(candidate).getCapacita())) {
			throw new JOException("errore");
		}
		
		Punteggio p = new Punteggio(candidate);
		p.setConsultant(consultant);
		p.addMap(cr);
		punteggi.put(candidate, p);
		
		return (int) Math.floor(p.getCr().values().stream().mapToDouble(Integer::valueOf).average().orElse(0));
		

	}
	
//R4
	public List<String> discardApplications() {
		Set<String> res = new TreeSet<>();


		for(Posizione p : posizioni.values()) {
			for(Candidato c : p.getCandidati()) {
				for(String cap : c.getCapacita()) {
					if(punteggi.get(c.getName())!=null) {
					if(!punteggi.get(c.getName()).getCr().containsKey(cap)) {
						res.add(c.getName()+":"+p.getName());
					}
					else if(!p.getSkillLivello().containsKey(cap)) {
						
					}
					else if(p.getSkillLivello().get(cap) > punteggi.get(c.getName()).getCr().get(cap)) {
						res.add(c.getName()+":"+p.getName());
					  }
					}
					else {
						res.add(c.getName()+":"+p.getName());
					}
				}
			}
		}
		
		
		return res.stream().sorted().collect(Collectors.toList());
	
	}
	
	 
	public List<String> getEligibleCandidates(String position) {
		Set<String> res = new TreeSet<>();
		
		for(Posizione p : posizioni.values()) {
			
			if(p.getName().compareTo(position)==0) {
			for(Candidato c : p.getCandidati()) {
				boolean b = true;
				if(c.getCapacita().containsAll(p.getSkillLivello().keySet())) {
				  for(String cap : c.getCapacita()) {
					if(!punteggi.get(c.getName()).getCr().containsKey(cap)) {
				     }else if(!p.getSkillLivello().containsKey(cap)) {
					
				}
					else if(punteggi.get(c.getName())!=null) {
					   if(p.getSkillLivello().get(cap) > punteggi.get(c.getName()).getCr().get(cap)) {
						
					     b=false;
					  }
					}
				  }
				}
				
				if(b==true) {
					res.add(c.getName());
				}
		 	}
		  }
		}
		
		
		return res.stream().sorted().collect(Collectors.toList());
	
	}
	

	
}

		
