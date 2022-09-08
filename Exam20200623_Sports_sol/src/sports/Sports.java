package sports;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

/**
 * Facade class for the research evaluation system
 *
 */
public class Sports {
 
//R1
	List<String> attivita = new ArrayList<>();
	Map<String,Categoria> categorie = new TreeMap<>();
	Map<String,Prodotto> prodotti = new TreeMap<>();
	List<Recensione> recensioni =  new ArrayList<>();
	
    public void defineActivities (String... activities) throws SportsException {
    	
    	List<String> res = new ArrayList<>();
    	
    	for(String s : activities ) {
    		res.add(s);
    	}
    
    	if(res.isEmpty()) {
    		throw new SportsException("vuoto");
    	}
    	
    	attivita.addAll(res);
    	
       }

   
    public List<String> getActivities() {
		return attivita.stream().sorted().collect(Collectors.toList());     
    }


    
    public void addCategory(String name, String... linkedActivities) throws SportsException {
        List<String> res = new ArrayList<>();
    	
    	for(String s : linkedActivities ) {
    		res.add(s);
    	}
    	
    	if(!attivita.containsAll(res)) {
    		throw new SportsException("errore");
    	}
    	
    	Categoria c = new Categoria(name);
    	categorie.put(name, c);
    	c.addAttivita(res);
    	
    }
    
   
    public int countCategories() {
		return categorie.size();
		 
    }

  
    public List<String> getCategoriesForActivity(String activity) {
    	List<String> res = categorie.values().stream().filter(c->c.getAttivita().contains(activity))
    			.map(x->x.getName()).sorted().collect(toList());
    	if(res.isEmpty()) {
    		List<String> vuota = new ArrayList<>();
		  return vuota;
    	}else {
    		return res;
    	}
    	
    
     }

//R2
    
    public void addProduct(String name, String activityName, String categoryName) throws SportsException {
    	
    	if(prodotti.containsKey(name)) {
    		throw new SportsException("errore");
    	}
    	
    	prodotti.put(name, new Prodotto(name, activityName, categorie.get(categoryName)));
    	
    	
        }

    
    public List<String> getProductsForCategory(String categoryName){
    	
    	List<String> res = prodotti.values().stream().filter(c->c.getC().getName().compareTo(categoryName)==0)
    	.map(x->x.getName()).sorted().collect(toList());
    	if(res.isEmpty()) {
    		List<String> vuota = new ArrayList<>();
		  return vuota;
    	}else {
    		return res;
    	}
    	
    	
       }

    
    public List<String> getProductsForActivity(String activityName){
    	
    List<String> res = prodotti.values().stream().filter(c->c.getActivityName().compareTo(activityName)==0)
    	    	.map(x->x.getName()).sorted().collect(toList());
    	    	if(res.isEmpty()) {
    	    		List<String> vuota = new ArrayList<>();
    			  return vuota;
    	    	}else {
    	    		return res;
    	    	}
    	    	
    	
    }

   
    public List<String> getProducts(String activityName, String... categoryNames){
		
    	List<String> cat = Arrays.asList(categoryNames);
    	
    	
    	
    	List<String> res = prodotti.values().stream().filter(c->c.getActivityName().compareTo(activityName)==0)
    	.filter(c->cat.contains(c.getC().getName())).map(x->x.getName()).sorted().collect(toList());
    	
    	
    	if(res.isEmpty()) {
    		List<String> vuota = new ArrayList<>();
		  return vuota;
    	}else {
    		return res;
    	}
    	
    }

//R3
    
    public void addRating(String productName, String userName, int numStars, String comment) throws SportsException {
      
    	if(numStars<0 || numStars>5) {
    		throw new SportsException("errore");
    	}
    	
    	recensioni.add(new Recensione(prodotti.get(productName), userName, numStars, comment));

  }

    

   
    public List<String> getRatingsForProduct(String productName) {
		return recensioni.stream().filter(r->r.getP().getName().compareTo(productName)==0)
			  .sorted(Comparator.comparing(Recensione::toString).reversed())
			  .map(Recensione::toString)
			  .collect( Collectors.toList());

   
    }


//R4
   
    public double getStarsOfProduct (String productName) {
		return recensioni.stream().filter(r->r.getP().getName().compareTo(productName)==0)
			   .mapToInt(Recensione::getNumStars).average().orElse(0);


    }

    
    public double averageStars() {
		return recensioni.stream().mapToInt(Recensione::getNumStars).average().orElse(0);
    	
        
        
    }

//R5 
   
    public SortedMap<String, Double> starsPerActivity() {
		return recensioni.stream()
	.collect(Collectors.groupingBy(r->r.getP().getActivityName(),TreeMap::new,
	Collectors.averagingDouble(Recensione::getNumStars)));
    	
		   
    }
    
   
    public SortedMap<Double, List<String>> getProductsPerStars () {
    	SortedMap<String, Double> prodMedia=recensioni.stream().collect(Collectors.groupingBy(r->r.getP().getName(),TreeMap::new,Collectors.averagingDouble(Recensione::getNumStars)));
	
		return prodMedia.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue,() -> new TreeMap<>(Comparator.reverseOrder()), Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
        
    }

}