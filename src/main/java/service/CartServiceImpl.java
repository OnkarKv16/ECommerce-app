package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ProductDAO;
import dbUtil.DBConnection;
import model.CartItem;
import model.Product;
import model.User;

public class CartServiceImpl implements CartService{
	
	private List<CartItem> cart = new ArrayList<>();

	@Override
	public void addToCart(Scanner sc) {
		
		System.out.println("Enter Product Id to add: ");
		
		int product_id = sc.nextInt();
		sc.nextLine();
		
		Product product = ProductDAO.getProductById(product_id); 
		
		if(product == null) {
			System.out.println("Product not found");
			return;
		}
		
		System.out.println("Enter quantity");
		int quantity = sc.nextInt();
		sc.nextLine();
		
		if(quantity <= 0 || quantity > product.getQuantity()) {
			System.out.println("invalid quantity");
			return;
		}
		
		CartItem item = new CartItem(product_id, product.getName(), product.getPrice(), quantity);
		
		cart.add(item);
		System.out.println("Added into the cart: " + product.getName());

	}

	@Override
	public void viewCart() {
		if (cart.isEmpty()) {
			System.out.println("your cart is empty.");
			return;
		}
		
		System.out.println("\n Your Cart:");
		System.out.println("ID | Name              | Qty | Price | Total ");
		System.out.println("----------------------------------------------");
		
		for(CartItem item : cart) {
			System.out.printf("%2d | %-15s | %3d | ₹%.2f | ₹%.2f\n",item.getProduct_id(), item.getProductName(), item.getQuantity(), item.getPrice(), item.getTotal());
		}
		System.out.println("Grand Total: "+ calculateTotal());
	}

	@Override
	public double calculateTotal() {
		double total = 0;
		for (CartItem item : cart) {
			total += item.getTotal();
		}
		return total;
	}

	@Override
	public void checkout(User user) {
		if(cart.isEmpty()) {
			System.out.println(" cart is empty. Cannot proceed to checkout.");
			return;
		}
		
		try (Connection conn = DBConnection.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO purchase_history (user_id, product_id, quantity) VALUES (?, ?, ?)";
			
			for (CartItem item : cart) {
				try (PreparedStatement stmt = conn.prepareStatement(sql)) {
					stmt.setInt(1, user.getUser_id());
					stmt.setInt(2, item.getProduct_id());
					stmt.setInt(3, item.getQuantity());
					stmt.executeUpdate();
				}
				ProductDAO.reduceProductStock(conn, item.getProduct_id(), item.getQuantity());
			}
			
			conn.commit();
			System.out.println("Purchase successful!");
			viewCart();
			cart.clear();
		
		} catch (SQLException e) {
			System.out.println("Checkout failed: " + e.getMessage());
		}
		
		
		
		
	}
	
	

}
