package journals;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Cosa puo' dimostrare un test? 1 - La presenza di difetti nel programma
 * In seguito a quale operazione SVN  incrementa il numero di revision?1 - Commit
  * @author chiar
 *
 */


public class Journals {
	
	Map<String, Giornale> giornali = new TreeMap<>();
	Map<String, Autore> autori = new TreeMap<>();
	 
	public int addJournal (String name, double impactFactor) throws JException {
		
		if(giornali.containsKey(name)) {
			throw new JException();
		}
		
		giornali.put(name, new Giornale(name, impactFactor));
		return name.length();
		
		
		
	}
	
	
	/**
	 * * A cosa serve il diamond operator '<>' ? A indicare un'inferenza di tipo per una classe  generica
       * Cosa si trova nella sezione inferiore di una classe UML? / What is specified in the bottom section of a UML class? Lista dei metodi / List of methods
 
	 * @param name
	 * @return
	 * @throws JException
	 */

	
	public double getImpactFactor (String name) throws JException {
		
		if(!giornali.containsKey(name)) {
			throw new JException();
		}
				
		return giornali.get(name).getImpactFactor();

	
	}
	
	
	/**
	 *  Che operazione crea una copia locale di un repository SVN? What operation makes a local copy of an SVN repository? Check-out
     *  Quali di queste affermazioni sono valide per un'interfaccia Java? / Which among the following statements are correct for a Java interface?
        0 - Un'interfaccia puo' contenere dei metodi astratti / An interface can contain abstract methods
        2 - Un'interfaccia puo' essere vuota / An interface can be empty
        3 - Un'interfaccia puo' contenere dei metodi statici / An interface can contain static methods
 
	 * @return
	 */

	
	public SortedMap<Double, List<String>> groupJournalsByImpactFactor () {
		
		return giornali.values().stream().sorted(Comparator.comparing(Giornale::getName))
		.collect(Collectors.groupingBy(Giornale:: getImpactFactor, TreeMap::new, mapping(Giornale::getName, toList())));
		
		
	}
	
	/**
	 *  Cosa contiene la sezione centrale di una classe UML? / What is present in the middle section of a UML class? 2 - Attributi / Attributes
     *  Che metodo usa SVN per risolvere conflitti? / What methods does SVN adopts to resolve conflics? Copy-Modify-Merge
     *  Qual e' la differenza tra verifica e convalida? Una determina la qualita' l'altra l'utilita' del sistema
 
	 * @param authorNames
	 * @return
	 */

	
	public int registerAuthors (String... authorNames) {
		
		for(String s : authorNames) {
			if(!autori.keySet().contains(s)) {
				autori.put(s, new Autore(s));
			}
		}
		
		return autori.size();
		
	
	}
	
	/**
	 * * Per quali caratteristiche sono adatti i metodi agili?
         2 - La variabilita' dei requisiti
         4 - I tempi di sviluppo
       * Quali di queste affermazioni sono valide? / Which among the following statements are correct?
         0 - Lo sviluppo del software test-driven richiede che il programma sia testato dopo ogni modifica. / Test driven development demands testing a program after each change
         1 - Una baseline e' un insieme di versioni di configuration item / A baseline is a set of  configuration item versions
         2 - Il test black box non considera la struttura interna del codice / Black box testing does not  consider internal data structure
 
	 * @param journalName
	 * @param paperTitle
	 * @param authorNames
	 * @return
	 * @throws JException
	 */
	
	public String addPaper (String journalName, String paperTitle, String... authorNames) throws JException {
		
		if(!giornali.containsKey(journalName)) {
			throw new JException();
		}
		
		Giornale j = giornali.get(journalName);
		
		if(j.getArticoli().keySet().contains(paperTitle)) {
			throw new JException();
		}
		
		List<Autore> lista = new ArrayList<>();
		
		for(String s : authorNames) {
			if(!autori.containsKey(s)) {
				throw new JException();
			}else {
			  lista.add(autori.get(s));
			  autori.get(s).addArticolo(j, paperTitle);
			}
		}
		
		j.addArticoloAutori(paperTitle, lista);
		
		return journalName+":"+paperTitle;
	
	}
	
   /**
    *  Quali afferamzioni sono vere per la sezione superiore di una classe UML? / Which statements are  true for the upper section of a UML class? Contiene l'implementazione / Contains the implementation
    *  Quali di queste affermazioni sono valide per una espressione lambda? / Which among the  following statements are correct for a lambda expression?
        0 - Una lambda restituisce un oggetto / A lambda returns an object
        1 - Una lambda implementa una interfaccia funzionale / A lambda implements a functional interface
        2 - Una lambda può sostituire qualsiasi method reference / A lambda can replace any method reference

    * @return
    */

	public SortedMap<String, Integer> giveNumberOfPapersByJournal () {
		return giornali.values().stream().sorted(Comparator.comparing(Giornale::getName))
				.filter(g->g.getArticoli().size()>0)
				.collect(Collectors.toMap(Giornale::getName,  j -> j.getArticoli().size(), (o1, o2) -> o1 ,() -> new TreeMap<String, Integer>()));
			
		

	}
	
   /**
    * Quando un metodo è dichiarato 'static'.. ...non richiede un reference per essere invocato
    * Un errore (rispetto a failure) di un test JUnit: corrisponde ad una eccezione lanciata dal programma testato 
    * L'ereditarietà tra due classi permette di: 2 - aggiungere attributi, 4 - ridefinire metodi, 5 - aggiungere metodi
 
    * @param authorName
    * @return
    * @throws JException
    */
	
	public double getAuthorImpactFactor (String authorName) throws JException {
		
		if(!autori.containsKey(authorName)) {
			throw new JException();
		}
		
		if(autori.get(authorName).getArticoli().isEmpty()) {
			return 0;
		}else {
			double res=autori.get(authorName).getArticoli().values().stream()
					.mapToDouble(Giornale::getImpactFactor).sum();
		    return res;
		}
		
	}

	/**
	 * Quali di queste affermazioni sono valide? / Which among the following statements are correct?
       2 - In the V model the detailed specifications are used to devise the unit testing
       3- Code smell detects structures in the code that may negatively impact design quality
    *  Which among the following statements are correct for a comparator?
        1- A comparator implements method compareTo()
        4 -A comparator can be passed as argument to method sort() of class Collections
 
	 * @return
	 */

	public SortedMap<Double, List<String>> getImpactFactorsByAuthors () {
		return autori.values().stream().sorted(Comparator.comparing(Autore::getName))
			.filter(a->a.getArticoli().size()>0)
			.collect(Collectors.groupingBy(Autore::getImpactFactor, TreeMap::new, mapping(Autore::getName, toList())));
	
	}
	
	/**
	 *  Quali di queste affermazioni sono valide per il modificatore 'static'? 
        2 - Per un metodo significa che puo' accedere ad attributi solo static
        3 - Per una classe significa che puo' accedere agli attributi della classe contenitore
     *  Per ottenere un ordinamento naturale degli oggetti di una classe che cosa serve fare? implementare l'interfaccia Comparable
 
	 * @return
	 */
	
	public SortedMap<String, Integer> getNumberOfPapersByAuthor() {
		return autori.values().stream().sorted(Comparator.comparing(Autore::getName))
	    .filter(a->a.getArticoli().size()>0)
	    .collect(Collectors.toMap(Autore::getName, Autore::getNumArticoli,(x,y)->x, TreeMap::new));
	    

	}
	
	/**
	 * Come si puo' testare un metodo che lancia un'eccezione? Si verificano i due casi con e senza eccezione
     * Per l'associazione Persona --(possiede) Auto, sapendo che una persona in generale può non possedere l’automobile ma anche essere proprietario di più automobili, che molteplicità  si scrive vicino a Auto? 0,*
     * What is the purpose of the operator "->"? - to implement a functional interface 

	 * @return
	 */
	
	public String getJournalWithTheLargestNumberOfPapers() {
		
	    return giornali.values().stream().sorted(Comparator.comparing(Giornale::getName))
		.max(Comparator.comparing(Giornale::getNumArticoli)).orElse(null).toString();
		
		

	}

}

