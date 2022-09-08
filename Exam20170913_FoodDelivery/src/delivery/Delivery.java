package delivery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

import java.text.DecimalFormat;

public class Delivery {
	
	int i=0;
	int o=0;
	Map<String, Customer> clienti = new TreeMap<String, Customer>();
	Map<Integer, Customer> clientiID = new TreeMap<Integer, Customer>();
	List<Menu> menu = new ArrayList<Menu>();
	Map<Integer, Order> ordini = new TreeMap<Integer, Order>();
	Map<String, Menu> menuDesc = new TreeMap<String, Menu>();
	
	public static enum OrderStatus { NEW, CONFIRMED, PREPARATION, ON_DELIVERY, DELIVERED } 
    
	
    public int newCustomer(String name, String address, String phone, String email) throws DeliveryException {
		
    	if(clienti.containsKey(email)) {
    		throw new DeliveryException();
    	}
    	
    	i++;
    	Customer c = new Customer(i, name, address, phone, email);
    	clienti.put(email, c);
    	clientiID.put(i, c);
    	
    	c.setFormato(c.getName()+", "+c.getAddress()+", "+c.getPhone()+", "+c.getEmail());
    	
    	return i;
    	
    }
    
   
    public String customerInfo(int customerId){
		return clientiID.get(customerId).getFormato();
      }
    
    
    public List<String> listCustomers(){
    	List<String> cost = new ArrayList<String>();
    	for(Customer c : clienti.values()) {
    		cost.add(c.getFormato());	
    	}
    	Collections.sort(cost);
		return cost;
    	

    }
    
  

    public void newMenuItem(String description, double price, String category, int prepTime){
    	
    	Menu m = new Menu(description, price, category, prepTime);
    	menuDesc.put(description, m);
    	menu.add(m);

    }
    
    

    public List<String> findItem(String search){
    	
    	String find = search.toLowerCase();
    	List<Menu> contenenti = new ArrayList<Menu>();
    	
    	for(Menu m : menu) {
    		if(m.getDescription().toLowerCase().contains(find)) {
    			contenenti.add(m);
    		}
    	}
    	
    	List<Menu> ordinati = contenenti.stream().sorted(comparing(Menu::getCategory)
    			.thenComparing(Menu::getDescription)).collect(Collectors.toList());
    	
    	List<String> res = new ArrayList<String>();
    	for(Menu mm : ordinati) {
    		res.add("["+mm.getCategory()+"] "+mm.getDescription()+" : "+String.format("%.2f",mm.getPrice()).replace(",", "."));
    	}
		
    	
		return res;
    

    }
    
    

    public int newOrder(int customerId){
    	
    	o++;
    	Order ord = new Order(o, customerId);
    	ordini.put(o, ord);
    	
    	ord.setStato(OrderStatus.NEW);
    	
    	Customer c = clientiID.get(customerId);
    	c.addOrder(ord);
		return o;
    	

    }
    
    
   
    public int addItem(int orderId, String search, int qty) throws DeliveryException {
    	
    	if(!ordini.containsKey(orderId)) {
    		throw new DeliveryException();
    	}
    	
    	List<Menu> elementi = new ArrayList<Menu>();
    	
    	for(Menu m : menu) {
    		if(m.getDescription().toLowerCase().contains(search.toLowerCase())) {
    			
    			elementi.add(m);
    		}
    	}
    	
    	
    	if(elementi.size()!=1){
    		throw new DeliveryException();
    	}
    	
    	Menu elemento =  elementi.get(0);
    	
    	Order o = ordini.get(orderId);
    	//System.out.println(elemento);
    	o.addItem(elemento, qty);
    	
    	
    	
		return o.getItems().get(elemento.getDescription());
    	

    }
    
    

    public List<String> showOrder(int orderId) throws DeliveryException {
    	if(!ordini.containsKey(orderId)) {
    		throw new DeliveryException();
    	}
    	
    	Order o = ordini.get(orderId);
    	Map<String, Integer> items = new TreeMap<String, Integer>(o.getItems());
    	List<String> ris = new ArrayList<String>();
    	for(String ss : items.keySet()) {
    		String s= ss+", "+items.get(ss);
    		ris.add(s);
    	}
    	
		return ris;
    

    }
    
    

    public double totalOrder(int orderId) throws DeliveryException {
    	if(!ordini.containsKey(orderId)) {
    		throw new DeliveryException();
    	}
    	
    	double d=0.0;
    	Order o = ordini.get(orderId);
    	Map<String, Integer> items = new TreeMap<String, Integer>(o.getItems());
    	
    	for(String s : items.keySet()) {
    		//System.out.println(menuDesc);
    		d = d + (menuDesc.get(s).getPrice()*items.get(s));
    	}
    	
    	o.setTotPrice(d);
    	
		return d;
    	

    }
    
   

    public OrderStatus getStatus(int orderId) throws DeliveryException {
    	if(!ordini.containsKey(orderId)) {
    		throw new DeliveryException();
    	}
    	return ordini.get(orderId).getStato();
    	

    }
    
   

    public int confirm(int orderId) throws DeliveryException {
    	if(!ordini.containsKey(orderId)) {
    		throw new DeliveryException();
    	}
    	Order o = ordini.get(orderId);
    	if(o.getStato()!=OrderStatus.NEW) {
    		throw new DeliveryException();
    	}
    	
    	o.setStato(OrderStatus.CONFIRMED);
    	Map<String, Integer> items = new TreeMap<String, Integer>(o.getItems());
    	
    	
    	int time=5+15;
    	
    	
    	
    	int max=0;
    	for(String s : items.keySet()) {
    		
    		if(menuDesc.get(s).getPrepTime()>max) {
    			max=menuDesc.get(s).getPrepTime();
    		}
    		
    	}
    	
    	
    	
		return (time+max);
    

    }

   

    public int start(int orderId) throws DeliveryException {
    	
    	if(!ordini.containsKey(orderId)) {
    		throw new DeliveryException();
    	}
    	Order o = ordini.get(orderId);
    	if(o.getStato()!=OrderStatus.CONFIRMED) {
    		throw new DeliveryException();
    	}
    	
    	o.setStato(OrderStatus.PREPARATION);
    	Map<String, Integer> items = new TreeMap<String, Integer>(o.getItems());
    	
    	
    	int time=15;
    	
    	int max=0;
    	for(String s : items.keySet()) {
    		if(menuDesc.get(s).getPrepTime()>max) {
    			max=menuDesc.get(s).getPrepTime();
    		}
    		
    	}
    	
    	
    	
		return (time+max);
    	

    }

    

    public int deliver(int orderId) throws DeliveryException {
    	if(!ordini.containsKey(orderId)) {
    		throw new DeliveryException();
    	}
    	Order o = ordini.get(orderId);
    	if(o.getStato()!=OrderStatus.PREPARATION) {
    		throw new DeliveryException();
    	}
    	
    	o.setStato(OrderStatus.ON_DELIVERY);
    	
    	
    	int time=15;
    	
		return time;
    	

    }
    
    

    public void complete(int orderId) throws DeliveryException {
    	if(!ordini.containsKey(orderId)) {
    		throw new DeliveryException();
    	}
    	Order o = ordini.get(orderId);
    	if(o.getStato()!=OrderStatus.ON_DELIVERY) {
    		throw new DeliveryException();
    	}
    	
    	o.setStato(OrderStatus.DELIVERED);
    	
    	

    }
    
   

    public double totalCustomer(int customerId){
    	
    	Customer c = clientiID.get(customerId);
    	double tot=0;
    	for(Order o : c.getOrdini()) {
    		if(o.getStato()!=OrderStatus.NEW) {
    			
    			if(o.getTotPrice()==0.0) {
    				try {
    				o.setTotPrice(this.totalOrder(o.getId()));	
					tot=tot+	this.totalOrder(o.getId());
					} catch (DeliveryException e) {
						e.printStackTrace();
					}
    			}else {
    			
    			tot = tot + o.getTotPrice();
    			}
    		}
    	}
    	
    	//System.out.println(c.getId());
    	c.setTotSpeso(tot);
    	
		return tot;
    	

    }
    
    

    public SortedMap<Double,List<String>> bestCustomers(){
    	
    	for(Customer c : clienti.values()) {
    		if(c.getTotSpeso()==0.0) {
    			c.setTotSpeso(this.totalCustomer(c.getId())); 
    		}
    		
    	}
    	
    	
    	Map<Double,List<String>> customerM=   clienti.values().stream()
      .collect(Collectors.groupingBy(Customer::getTotSpeso,
                Collectors.mapping(Customer::getFormato,
	            Collectors.toList())));
    	
    	TreeMap<Double, List<String>> tm = new TreeMap<Double,List<String>>();
	 	tm.putAll(customerM);
    	
		return tm.descendingMap();
				                	
        		
    }
    

}
