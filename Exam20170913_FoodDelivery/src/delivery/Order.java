package delivery;

import java.util.Map;
import java.util.TreeMap;

import delivery.Delivery.OrderStatus;

public class Order {
	
	int id;
	int customerId;
	Map<String, Integer> items = new TreeMap<String, Integer>();
	double totPrice;
	OrderStatus stato;
	
	public Order(int id, int customerId) {
		
		this.id = id;
		this.customerId = customerId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void addItem(Menu elemento, int qty) {
		
		
		int quantita=0;
		if(items.size()!=0) {
		if(items.containsKey(elemento.getDescription())){
			quantita=qty+items.get(elemento.getDescription());
			items.replace(elemento.getDescription(), quantita);		
	        }else {
	        	items.put(elemento.getDescription(), qty);
	        }
		}else {//System.out.println(elemento.getDescription());
		items.put(elemento.getDescription(), qty);
		}
		
	}

	public Map<String, Integer> getItems() {
		return items;
	}

	public void setItems(Map<String, Integer> items) {
		this.items = items;
	}

	public double getTotPrice() {
		return totPrice;
	}

	public void setTotPrice(double totPrice) {
		this.totPrice = totPrice;
	}

	public OrderStatus getStato() {
		return stato;
	}

	public void setStato(OrderStatus stato) {
		this.stato = stato;
	}
	
	
	
	

}
