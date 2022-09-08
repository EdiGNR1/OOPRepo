package supermarket;
import java.util.*;
import java.util.stream.*;

public class Supermarket {

	//R1

	Map<String, Categoria> categorie = new TreeMap<>();
	Map<String, Prodotto> prodotti = new TreeMap<>();
	Map<Integer, Carta> carte = new TreeMap<>();
	Map<Integer,Integer> puntiSconti = new TreeMap<>();
	int cont = 999;
	int acq =99;
	int scont=-1;
	Map<Integer, Acquisto> acquisti = new TreeMap<>();
	Map<Integer, Scontrino> scontrini = new TreeMap<>();

	public int addProducts (String categoryName, String productNames, String productPrices) throws SMException {
		
		if(categorie.containsKey(categoryName)) {
			throw new SMException("erroe");
		}
		
		List<String> prod = Arrays.asList(productNames.split(","));
		List<String> price = Arrays.asList(productPrices.split(","));
		
		if(prod.size()!=price.size()) {
			throw new SMException("erroe");
		}
		
		for(String s : prod) {
			
//		System.out.println(categoryName+","+categorie.values().stream().filter(c->c.getProdCosto().keySet().contains(s))
//		.collect(Collectors.toList()));
		
		if(categorie.values().stream().filter(c->c.getProdCosto().keySet().contains(s))
		.collect(Collectors.toList()).size()!=0) {
			throw new SMException("erroe");
		}
		}
		
		for(String s : prod) {
			if(prodotti.containsKey(s)) {
				throw new SMException("erroe");
			}
			}
		
		categorie.put(categoryName, new Categoria(categoryName));
		
		Map<String,Double> prodCosto = new TreeMap<>();
		
		
		int i=0;
		for(String s : prod) {
			prodCosto.put(s, Double.parseDouble(price.get(i)));
			i++;
		}
		categorie.get(categoryName).setProdCosto(prodCosto);
	
		int x=0;
		for(String s : prod) {
			prodotti.put(s, new Prodotto(s, Double.parseDouble(price.get(x)), categorie.get(categoryName)));
			x++;
		}
		
		return prod.size();
		


	}

	public double getPrice (String productName) throws SMException {
		
		 if(!prodotti.containsKey(productName)){
        	 throw new SMException("errore");
         }
         
         Categoria c = categorie.values().stream().filter(x->x.getProdCosto().keySet().contains(productName))
	     .findFirst().get();	    
			
         if(c==null) {
        	 throw new SMException("errore");
         }
         
        
         return c.getProdCosto().get(productName);
	}

	public SortedMap<String,String> mostExpensiveProductPerCategory () {
		
		
		return categorie.values().stream().collect(Collectors.toMap(Categoria::getCategoryName,
				Categoria::getProdMax,(x,y)->x,TreeMap::new));

				
	}

	//R2
	

	public void setDiscount (String categoryName, int percentage) throws SMException {

		if(percentage>40 || !categorie.containsKey(categoryName)) {
			throw new SMException("errore");
		}

		if(categorie.get(categoryName).getSconti().size()==0) {
			categorie.get(categoryName).addSconto(0);
		}
		
		categorie.get(categoryName).addSconto(percentage);
		categorie.get(categoryName).getProdCosto().keySet().stream()
		.forEach(s->prodotti.get(s).addSconto(percentage));
		
		
	}

	public void setDiscount (int percentage, String... productNames) throws SMException {
		
		if(percentage>40 ) {
			throw new SMException("errore");
		}
		
		List<String> prod = Arrays.asList(productNames);
		prodotti.keySet().stream().filter(s->prod.contains(s))
		.forEach(s->prodotti.get(s).addSconto(percentage));



    }
	
	public List<Integer> getDiscountHistoryForCategory(String categoryName) {
		return categorie.get(categoryName).getSconti();
	
	}

	public List<Integer> getDiscountHistoryForProduct(String productName) {
		return prodotti.get(productName).getDiscount();
		}

	//R3


	public int issuePointsCard (String name, String dateOfBirth) throws SMException {
		
		List<Carta> copie =carte.values().stream().
		filter(c->c.getName().compareTo(name)==0 && c.getDateOfBirth().compareTo(dateOfBirth)==0)
		.collect(Collectors.toList());
		
		if(copie.size()!=0) {
			throw new SMException("errore");
		}
		
		cont++;
		carte.put(cont, new Carta(cont, name, dateOfBirth));
		return cont;
		




	}


	public void fromPointsToDiscounts (String points, String discounts) throws SMException {
		
		List<String> p = Arrays.asList(points.split(","));
		List<String> d = Arrays.asList(discounts.split(","));

		if(p.size()!=d.size()) {
			throw new SMException("errore");
		}
		
		
		int i=0;
		
		for(String s : p) {
			puntiSconti.put(Integer.parseInt(s), Integer.parseInt(d.get(i)));
			i++;
		}
		
		



	}

	public SortedMap<Integer, Integer>  getMapPointsDiscounts() {
		return (SortedMap<Integer, Integer>) puntiSconti;



	}

	public int getDiscountFromPoints (int points) {
		if(puntiSconti.containsKey(points)) {
		return puntiSconti.get(points);
		}else {
			return 0;
		}
		



	}

	//R4
	
	public int getCurrentPoints (int pointsCardCode) throws SMException {
		
		if(!carte.containsKey(pointsCardCode)) {
			throw new SMException("errore");
		}
		
		return carte.get(pointsCardCode).getPunti();



	}

	public int getTotalPoints (int pointsCardCode) throws SMException {
		if(!carte.containsKey(pointsCardCode)) {
			throw new SMException("errore");
		}
		
		return carte.get(pointsCardCode).getPuntiTuttiAcquisti();


	}


    public int addPurchase (int pointsCardCode, int pointsRedeemed, String ... productNames) throws SMException {
		
    	
    	if(pointsRedeemed > carte.get(pointsCardCode).getPunti()) {
    		throw new SMException("errore");
    	}
    	
    	List<Prodotto> prod = new ArrayList<>();
        Arrays.asList(productNames).stream().forEach(s->prod.add(prodotti.get(s)));
    	
    	acq++;
    	acquisti.put(acq, new Acquisto(carte.get(pointsCardCode), pointsRedeemed, prod));
    	
    	List<Double> scontrino = new ArrayList<>();
    	List<Double> sconti = new ArrayList<>();
    	prod.stream().forEach(p-> scontrino.add(p.getPrice()- ((double)p.getPrice()*p.getDiscount().get(p.getDiscount().size()-1)/100)));
    	prod.stream().forEach(p-> sconti.add(((double)p.getPrice()*p.getDiscount().get(p.getDiscount().size()-1)/100)));
    	
    	int sconto=0;
    	//System.out.println(puntiSconti.get(pointsRedeemed));
    	if(puntiSconti.containsKey(pointsRedeemed)) {
    		sconto = puntiSconti.get(pointsRedeemed);
    	}
    	//System.out.println(sconto+","+pointsCardCode);
    	
    	int tot =(int) Math.round(scontrino.stream().mapToDouble(Double::valueOf).sum())-sconto;
    	
    	acquisti.get(acq).setTot(scontrino.stream().mapToDouble(Double::valueOf).sum()-sconto);
    	acquisti.get(acq).setScontoTot(sconti.stream().mapToDouble(Double::valueOf).sum()+sconto);
    	//System.out.println(acquisti.get(acq).getScontoTot()+","+pointsCardCode);
    	
    	carte.get(pointsCardCode).addPunti(tot);
    	carte.get(pointsCardCode).addTuttiPunti(tot);
    	
    	if(sconto!=0) {
    	carte.get(pointsCardCode).removePunti(pointsRedeemed);
    	}
    	
    	return acq;

	}

	public double getPurchasePrice (int purchaseCode) throws SMException {
		
		if(!acquisti.containsKey(purchaseCode)) {
			throw new SMException("errore");
		}
		
		return acquisti.get(purchaseCode).getTot();
		


	}
	
	public double getPurchaseDiscount (int purchaseCode) throws SMException {
		
		if(!acquisti.containsKey(purchaseCode)) {
			throw new SMException("errore");
		}
		
//		System.out.println(
//		carte.get(acquisti.get(purchaseCode).getCardCode().getId()).getPunti());
		
		
		return acquisti.get(purchaseCode).getScontoTot();

	}
	
	
	//R5

	public SortedMap<Integer, List<Integer>> pointsCardsPerTotalPoints () {
		return carte.values().stream().sorted(Comparator.comparing(Carta::getId))
		.filter(c->c.getPuntiTuttiAcquisti()!=0)		
		.collect(Collectors.groupingBy(Carta::getPuntiTuttiAcquisti, 
		TreeMap::new, Collectors.mapping(Carta::getId, Collectors.toList())));
		


	}
	
	public SortedMap<String, SortedSet<String>> customersPerCategory () {
		
		SortedMap<String, SortedSet<String>> result = new TreeMap<>();
		for(String cat: categorie.keySet()) {
			for(Prodotto p: prodotti.values()) {
				
				if(p.getC().getCategoryName().compareTo(cat)==0) {
					
				acquisti.values().stream().filter(a -> a.getProdotti().stream()
			       .collect(Collectors.mapping(Prodotto::getName, Collectors.toList()))
			       .contains(p.getName())).forEach(a-> {
					if(!result.containsKey(cat)) {
						result.put(cat, new TreeSet<>());
					}
					result.get(cat).add(carte.get(a.getCardCode().getId()).getName());
				});
			}
			}
		}
		return result;

	}
	
	public SortedMap<Integer, List<String>> productsPerDiscount() {
		return prodotti.values().stream().filter(p->p.getMaxSconto()!=0)
			.sorted(Comparator.comparing(Prodotto::getName))
			.collect(Collectors.groupingBy(Prodotto::getMaxSconto,() -> new TreeMap<>(Comparator.reverseOrder()),
				Collectors.mapping(Prodotto::getName, Collectors.toList() )));
		
		


}
	
	// R6



	public int newReceipt() {
		
		scont++;
		scontrini.put(scont, new Scontrino(scont));
		
		return scont;
		



	}

    public void receiptAddCard(int receiptCode, int PointsCard)  throws SMException { // add the points card info to the receipt

    	if(!carte.containsKey(PointsCard)){
			throw new SMException("errore");
		}
    	if(!scontrini.containsKey(receiptCode)) {
    		throw new SMException("errore");
    	}
    	
    	if(scontrini.get(receiptCode).isB()==true) {
    	    scontrini.get(receiptCode).setC(carte.get(PointsCard));
    	}else {
    		throw new SMException("errore");
    	}

	}

	public int receiptGetPoints(int receiptCode)  throws SMException {
		
		if(!scontrini.containsKey(receiptCode)) {
    		throw new SMException("errore");
    	}
		if(scontrini.get(receiptCode).getC()==null) {
    		throw new SMException("errore");
    	}
		return scontrini.get(receiptCode).getC().getPunti();
		


	}

	public void receiptAddProduct(int receiptCode, String product)  throws SMException { // add a new product to the receipt

		if(!scontrini.containsKey(receiptCode)) {
    		throw new SMException("errore");
    	}
		if(!prodotti.containsKey(product)) {
    		throw new SMException("errore");
    	}
		
		scontrini.get(receiptCode).addProdotto(prodotti.get(product));


	}

	public double receiptGetTotal(int receiptCode)  throws SMException {
		
		if(!scontrini.containsKey(receiptCode)) {
    		throw new SMException("errore");
    	}
		
		double sumProd=scontrini.get(receiptCode).getProdotti().stream().mapToDouble(Prodotto::getPrice).sum();
		double sumScont=scontrini.get(receiptCode).getProdotti().stream()
		.mapToDouble(p->(p.getPrice() *((double) p.getDiscount().get(p.getDiscount().size()-1)/100))).sum();
		
		scontrini.get(receiptCode).inserTotale(sumProd-sumScont);
		//System.out.println(sumProd+","+sumScont);
		return scontrini.get(receiptCode).getTotale();


		
	}

	public void receiptSetRedeem(int receiptCode, int points)  throws SMException { // sets the amount of points to be redeemed

		if(!scontrini.containsKey(receiptCode)) {
    		throw new SMException("errore");
    	}
		if(scontrini.get(receiptCode).getC()==null) {
			throw new SMException("errore");
		}
		if(points> scontrini.get(receiptCode).getC().getPunti()) {
			throw new SMException("errore");
		}
		if(!puntiSconti.containsKey(points)) {
			throw new SMException("errore");
		}
		scontrini.get(receiptCode).setScontoCarta(puntiSconti.get(points));
		scontrini.get(receiptCode).getC().removePunti(points);


	}


    public int closeReceipt(int receiptCode)  throws SMException {
    	

		if(!scontrini.containsKey(receiptCode)) {
    		throw new SMException("errore");
    	}
		
		
				
    	
		Scontrino s = scontrini.get(receiptCode);
		String [] a = s.getProdotti().stream().map(p->p.getName()).collect(Collectors.toList()).toArray(new String[0]);
		int res = this.addPurchase(s.getC().getId(), s.getC().getPunti(), a);
		scontrini.remove(s.getId());
		//System.out.println(s.getC().getId()+","+s.getC().getPunti()+","+a.toString());
		return res;
		


}
}