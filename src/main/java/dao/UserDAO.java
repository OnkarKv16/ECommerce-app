package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbUtil.DBConnection;
import model.User;

public class UserDAO {
	
	public static boolean registerUser(User user) {
		String sql  = "INSERT INTO users (username, password, role, first_name, last_name, city, email, mobile) " + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getRole());
			stmt.setString(4, user.getFirstName());
			stmt.setString(5, user.getLastName());
			stmt.setString(6, user.getCity());
			stmt.setString(7, user.getEmail());
			stmt.setString(8, user.getMobile());
			
			int rows = stmt.executeUpdate();
			
			return rows > 0;
			
		} catch (SQLException e) {
			System.out.println("Error registering user: " + e.getMessage());
			return false;
		}
	}
	
	// authenticate user during login
	public static User login(String username, String password) {
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setCity(rs.getString("city"));
				user.setEmail(rs.getString("email"));
				user.setMobile(rs.getString("mobile"));
				return user;
			}
		} catch (SQLException e) {
			System.out.println("Login failed: " + e.getMessage());
		}
		
		return null;
	}
	
	//admin view all users
	public static ArrayList<User> getAllUsers() {
		ArrayList<User> userList = new ArrayList<>();
		String sql = "SELECT * FROM users";
		
		try (Connection conn = DBConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				User user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setCity(rs.getString("city"));
				user.setEmail(rs.getString("email"));
				user.setMobile(rs.getString("mobile"));
				userList.add(user);
			}
		} catch (SQLException e) {
			System.out.println("Error fetching users: " + e.getMessage());
		}
		
		return userList;
		
	}
	
	//admin view specific user history
	public static User getUserByUsername(String username) {
		String sql = "SELECT * FROM users WHERE username = ?";
		
		try(Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setCity(rs.getString("city"));
				user.setEmail(rs.getString("email"));
				user.setMobile(rs.getString("mobile"));
				return user;
			}
			
		} catch (SQLException e) {
			System.out.println("Error fetcching user by username: " + e.getMessage());
		}
		
		return null;
	}
	
	public static void viewUserPurchaseHistory(int userId) {
	    String sql = "SELECT p.name, h.quantity, h.purchase_date " +
	                 "FROM purchase_history h JOIN products p ON h.product_id = p.product_id " +
	                 "WHERE h.user_id = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, userId);
	        ResultSet rs = stmt.executeQuery();

	        System.out.println("\n Purchase History:");
	        System.out.println("Product Name       | Quantity | Purchase Date");
	        System.out.println("---------------------------------------------");

	        boolean hasHistory = false;
	        while (rs.next()) {
	            hasHistory = true;
	            System.out.printf("%-20s | %8d | %s\n",
	                    rs.getString("name"),
	                    rs.getInt("quantity"),
	                    rs.getTimestamp("purchase_date").toString());
	        }

	        if(!hasHistory) {
	            System.out.println("No purchase history found for this user.");
	        }

	    } catch(SQLException e) {
	        System.out.println("Error fetching purchase history: " + e.getMessage());
	    }
	}
	

}
