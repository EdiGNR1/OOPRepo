package delivery;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	int id;
	String name;
	String address;
	String phone;
	String email;
	String formato;
	List<Order> ordini = new ArrayList<Order>();
	double totSpeso;
	
	public Customer(int id, String name, String address, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public List<Order> getOrdini() {
		return ordini;
	}
	public void setOrdini(List<Order> ordini) {
		this.ordini = ordini;
	}
	public void addOrder(Order ord) {
		ordini.add(ord);
		
	}
	public double getTotSpeso() {
		
		return totSpeso;
	}
	public void setTotSpeso(double totSpeso) {
		this.totSpeso = totSpeso;
	}
	
	
	

}
