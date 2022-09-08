package discounts;

public class Prodotto implements Comparable<Prodotto>{
	
	String categoryId;
	String productId;
	double price;
	int discount=0;
	int qtTot=0;
	
	public Prodotto(String categoryId, String productId, double price) {
		super();
		this.categoryId = categoryId;
		this.productId = productId;
		this.price = price;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Override
	public int compareTo(Prodotto o) {
		
		return o.getProductId().compareTo(productId);
	}

	public int getQtTot() {
		return qtTot;
	}

	public void setQtTot(int qtTot) {
		this.qtTot = qtTot;
	}

	public void putQt(int qt) {
		qtTot+=qt;
		
	}
	
	

}
