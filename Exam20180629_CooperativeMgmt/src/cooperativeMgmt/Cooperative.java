package cooperativeMgmt;

import java.util.*;
import java.util.stream.Collectors;
//import static java.util.stream.Collectors.*;

public class Cooperative {

	
	Map<String, Socio> soci = new TreeMap<>();
	Map<String, Prodotto> prodotti = new TreeMap<>();
	Map<String, Campagna> campagne = new TreeMap<>();
	Map<String, Ordine> ordini = new TreeMap<>();
	//R1
	public List<String> addMembers (String... names) {
		
		List<String> res = new ArrayList<>();
		
		for(String s :names) {
			if(!soci.containsKey(s)) {
				res.add(s);
				soci.put(s, new Socio(s));
			}
		}
		
		Collections.sort(res);
		
		return res;



	}
	
	public List<String> addProducts (String... items) {
		List<String> res = new ArrayList<>();
		
		for(String s : items) {
			prodotti.put(s.split(":")[0], new Prodotto(s.split(":")[0], Integer.parseInt(s.split(":")[1])));
			res.add(s.split(":")[0]);
		}
		Collections.sort(res);
		return res;
		


	}
	
	public List<String> addCampaign (String id, String... productIds) throws  CMException{
		
		List<String> res = new ArrayList<>();
		List<Prodotto> prod = new ArrayList<>();
		
		for(String s : productIds) {
			if(prodotti.containsKey(s)) {
				
				res.add(s);
				prod.add(prodotti.get(s));
				
			}else {
				 throw new CMException();
			}	
		}
		
		campagne.put(id, new Campagna(id));
		campagne.get(id).addProd(prod);
		
		Collections.sort(res);
		return res;
		
		
	}
	
	//R2
	public int join(String memberName, String campaignId) throws  CMException{
		
		if(!soci.containsKey(memberName)){
			 throw new CMException();
		}	
		
		if(!campagne.containsKey(campaignId)){
			 throw new CMException();
		}	
		
		soci.get(memberName).addCampagna(campagne.get(campaignId));
		campagne.get(campaignId).addSocio(soci.get(memberName));
		return soci.get(memberName).getCampagne().size();
		
	

	}

	public int addPayment(String memberName, int amount) throws  CMException{
		
		if(!soci.containsKey(memberName)){
			 throw new CMException();
		}
		
		soci.get(memberName).addPagamento(amount);
		
		return soci.get(memberName).getVersamenti().stream().mapToInt(Integer::valueOf).sum();

		
		
	}
	
	public List<Integer> getPayments(String memberName) throws  CMException{
		

		if(!soci.containsKey(memberName)){
			 throw new CMException();
		}
		
		return soci.get(memberName).getVersamenti();
	
	}
	
	//R3
	public int getBalance(String memberName) {
		
		int ver = soci.get(memberName).getVersamenti().stream().mapToInt(Integer::valueOf).sum();
		int ord = ordini.values().stream().filter(o->o.getS().getName().compareTo(memberName)==0)
				.mapToInt(Ordine::getTotOrdine).sum();
		
		return ver-ord;
	
	}

	public int addOrder (String orderId, String memberName, String campaignId, String... items) 
			throws  CMException{
			
	   
	    if(!campagne.get(campaignId).getSoci().contains(soci.get(memberName))){
			 throw new CMException();
		}
	    
	    Map<Prodotto, Integer> prodQuant = new TreeMap<>();
	    
	    for(String s : items) {
	    	if(!campagne.get(campaignId).getProdotti().contains(prodotti.get(s.split(":")[0]))) {
	    		throw new CMException();
	    	}
			prodQuant.put(prodotti.get(s.split(":")[0]) , Integer.parseInt(s.split(":")[1]));
			
		}
	    
	    int tot=0;
	    for(Prodotto p: prodQuant.keySet()) {
			tot+=p.getPrezzo()*prodQuant.get(p);
		}
	    
	    if(this.getBalance(memberName)<((double) tot/2)) {
	    	throw new CMException();
	    }
	    
	    Socio s = soci.get(memberName);
	    Campagna c = campagne.get(campaignId);
	    Ordine o = new Ordine(orderId, s, c);
	    
	    
		ordini.put(orderId, o);
		o.addProdQt(prodQuant);
				
	  return o.getTotOrdine();
		
	}

	//R4
	public SortedMap<String, Integer> nOfUnitsPerProductFromOrders() {
		
		SortedMap<String, Integer> res = new TreeMap<>();
		
		for(Ordine o : ordini.values()) {
			o.getProdottiQuantita().forEach((k, v) -> res.put(k.getId(), res.getOrDefault(k.getId(), 0) + v));
		}
		
		
		return res;
		
		
	}
	
	public SortedMap<String, Integer> amountPerMember() {
		return ordini.values().stream()
		.collect(Collectors.groupingBy(Ordine::getSocioName,TreeMap::new,
		Collectors.summingInt(Ordine::getTotOrdine)));
		
	
	}
}
