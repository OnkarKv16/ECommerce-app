package model;

public class CartItem {
	
	private int product_id;
	private String productName;
	private double price;
	private int quantity;
	
	public CartItem(int product_id, String productName, double price, int quantity) {
		 this.product_id = product_id;
		 this.productName = productName;
		 this.price = price;
		 this.quantity = quantity;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getTotal() {
		return price * quantity;
	}
	
}
