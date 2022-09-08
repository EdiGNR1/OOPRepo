package discounts;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Discounts {

	int i=0;
	int a=0;
	Map<Integer, Carta> carte = new TreeMap<>();
	Map<String, Prodotto> prodotti = new TreeMap<>();
	Map<Integer, Acquisto> acquisti = new TreeMap<>();

	//R1
	public int issueCard(String name) {
		i++;
	    Carta c = new Carta(i, name);
	    carte.put(i, c);
		return i;
		
	}
	
    public String cardHolder(int cardN) {
		return carte.get(cardN).getName();
        
    }
    

	public int nOfCards() {
		return carte.size();
	      

	}
	
	//R2
	public void addProduct(String categoryId, String productId, double price) throws DiscountsException  {
			
		if(prodotti.containsKey(productId)) {
			throw new  DiscountsException();
		}
		
		prodotti.put(productId, new Prodotto(categoryId, productId, price));
		
		
	}
	
	public double getPrice(String productId) 
			throws DiscountsException {
		
		if(!prodotti.containsKey(productId)) {
			throw new  DiscountsException();
		}
		
				return prodotti.get(productId).getPrice();
		
	}

	public int getAveragePrice(String categoryId) throws DiscountsException {
		
		double d = prodotti.values().stream().filter(p->p.getCategoryId().compareTo(categoryId)==0)
				.mapToDouble(Prodotto::getPrice).average().orElseThrow(DiscountsException::new);
		
		return (int) Math.round(d);		
			  
		
		
				   
	}
	
	//R3
	public void setDiscount(String categoryId, int percentage) throws DiscountsException {
	
		if(prodotti.values().stream()
		  .filter(p->p.getCategoryId().compareTo(categoryId)==0)
		   .collect(Collectors.toList()).isEmpty()){
			   throw new DiscountsException();
		   }
		
		if(percentage < 0 || percentage > 50){
			   throw new DiscountsException();
		   }
		 
		  prodotti.values().stream()
				  .filter(p->p.getCategoryId().compareTo(categoryId)==0)
				   .collect(Collectors.toList()).forEach(p-> p.setDiscount(percentage)); 
		
		  
	}

	public int getDiscount(String categoryId) {
		return  prodotti.values().stream()
	    .filter(p->p.getCategoryId().compareTo(categoryId)==0).collect(Collectors.toList()).get(0).getDiscount();
        
	}

	//R4
	public int addPurchase(int cardId, String... items) throws DiscountsException {
		
		Map<Prodotto, Integer> prodQt = new TreeMap<>();
		
		for(String s : items) {
			String prod = s.split(":")[0];
			int qt = Integer.parseInt(s.split(":")[1]);
			
			if(!prodotti.containsKey(prod)) {
				 throw new DiscountsException();
			}else {
				prodQt.put(prodotti.get(prod), qt);
				prodotti.get(prod).putQt(qt);
			}
		}
		
		a++;
		acquisti.put(a, new Acquisto(a));
		acquisti.get(a).addProdQt(prodQt);
		acquisti.get(a).setCarta(carte.get(cardId));
		
		return a;
		
		
	}

	public int addPurchase(String... items) throws DiscountsException {
		
		Map<Prodotto, Integer> prodQt = new TreeMap<>();
		
		
		for(String s : items) {
			String prod = s.split(":")[0];
			int qt = Integer.parseInt(s.split(":")[1]);
			
			if(!prodotti.containsKey(prod)) {
				 throw new DiscountsException();
			}else {
				prodQt.put(prodotti.get(prod), qt);
				prodotti.get(prod).putQt(qt);
			}
		}
		
		a++;
		acquisti.put(a, new Acquisto(a));
		acquisti.get(a).addProdQt(prodQt);
		
		
		
		
		return a;
		
		
		
	}
	
	public double getAmount(int purchaseCode) {
		return acquisti.get(purchaseCode).getTotAcquisto();
       
	}
	
	public double getDiscount(int purchaseCode)  {
		return acquisti.get(purchaseCode).getTotSconto();
       
	}
	
	public int getNofUnits(int purchaseCode) {
		return acquisti.get(purchaseCode).getProdQuantita().values().stream()
			.mapToInt(Integer::valueOf).sum();
       
	}
	
	//R5
	public SortedMap<Integer, List<String>> productIdsPerNofUnits() {
		return prodotti.values().stream()
		.filter(p->p.getQtTot()>0)		
	    .collect(Collectors.groupingBy(
	    Prodotto::getQtTot, TreeMap::new, mapping(Prodotto::getProductId, toList())));
		
		
	}
	
	public SortedMap<Integer, Integer> totalPurchasePerCard() {
		
	
		return acquisti.values().stream().filter(a->a.getCarta()!=null)
		.collect(Collectors.groupingBy(Acquisto::getCartaId, TreeMap::new,Collectors.summingInt(Acquisto::getTotAcquistoArrotondato)));
		
		
		
       	}
	
	public int totalPurchaseWithoutCard() {
		return acquisti.values().stream().filter(a->a.getCarta()==null)
		.mapToInt(Acquisto::getTotAcquistoArrotondato).sum();
		
	}
	
	public SortedMap<Integer, Integer> totalDiscountPerCard() {
		return acquisti.values().stream().filter(a->a.getCarta()!=null)
		.filter(a->a.getTotSconto()!=0)
	   .collect(Collectors.groupingBy(Acquisto::getCartaId, 
	    TreeMap::new,Collectors.summingInt(Acquisto::getTotScontoArrotondato)));
				

    }


}
