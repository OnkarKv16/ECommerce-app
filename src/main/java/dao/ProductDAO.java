package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBConnection;
import model.Product;

public class ProductDAO {
	
	public static List<Product> getAllProduct(String sortBy, String order) {
		ArrayList<Product> productList = new ArrayList<>();
		
		String sql = "SELECT * FROM products ORDER BY " + sortBy + " " + order;
		
		try (Connection conn = DBConnection.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			
			
			while (rs.next()) {
				Product product = new Product();
				product.setProduct_id(rs.getInt("product_id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				productList.add(product);
			}
		} catch (SQLException e) {
			System.out.println("Error fetching products: " + e.getMessage());
		}
		
		return productList;
	}
	
	
	public static void addProduct(String name, String description, double price, int quantity) {
		String sql = "INSERT INTO products (name, description, price, quantity) VALUES (?, ?, ?, ?)";
		
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setDouble(3, price);
			stmt.setInt(4, quantity);
			stmt.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Error inserting product: " + e.getMessage());
		}
	}
	
	public static Product getProductById(int product_id) {
		
		String sql = "SELECT * FROM products WHERE product_id = ?";
		
		try(Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, product_id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				Product product = new Product();
				
				product.setProduct_id(rs.getInt("product_id"));
				product.setName(rs.getString("name"));
	            product.setDescription(rs.getString("description"));
	            product.setPrice(rs.getDouble("price"));
	            product.setQuantity(rs.getInt("quantity"));
	            return product;
				
			} 
						
		}catch(SQLException e) {
			System.out.println("Error fetching product by ID: " + e.getMessage());
		}
		return null;
	}
	
	public static int getQuantityByProductId(int productId) {
		String sql = "SELECT quantity FROM products WHERE product_id = ?";
		
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("quantity");
			}
		} catch (SQLException e) {
			System.out.println("Error fetching quantity: " + e.getMessage());
		}
		return -1;
	}
	
	public static void reduceProductStock(Connection conn, int product_id, int quantity) {
		String sql = "UPDATE products SET quantity = quantity - ? where product_id = ?";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setInt(1, quantity);
			stmt.setInt(2, product_id);
			stmt.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("Error reducing stock: " + e.getMessage());
		}
							
	}
	
}
