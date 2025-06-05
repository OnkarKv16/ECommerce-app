package util; 
 
import java.io.InputStream; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.util.Properties; 
 
public class DatabaseInitializer { 
	 
	private static String BASE_URL; 
	private static String DB_NAME; 
	private static String USER; 
	private static String PASSWORD; 
	 
	static { 
		try { 
			Properties props = new Properties(); 
			InputStream input = DatabaseInitializer.class.getClassLoader().getResourceAsStream("db.properties"); 
			 
			if(input == null) { 
				throw new RuntimeException("db.properties not found in classpath."); 
			} 
			 
			props.load(input); 
			 
			String fullUrl = props.getProperty("db.url"); 
			USER = props.getProperty("db.username"); 
			PASSWORD = props.getProperty("db.password"); 
			 
			int lastSlash = fullUrl.lastIndexOf('/'); 
			BASE_URL = fullUrl.substring(0, lastSlash + 1); //jdbc:mysql://localhost:3306/ 
			DB_NAME = fullUrl.substring( lastSlash + 1); //db name 
		 
		}catch (Exception e) { 
			System.out.println("Failed to load database config: " + e.getMessage()); 
		} 
	} 
	 
	public static void initialize() { 
		try  (Connection conn = DriverManager.getConnection(BASE_URL, USER, PASSWORD); Statement stmt = conn.createStatement()) { 
			 
			//create the database if it doesnt exist   
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME); 
			System.out.println("Database " + DB_NAME + " checked/created."); 
			 
			try (Connection dbConn = DriverManager.getConnection(BASE_URL + DB_NAME, USER, PASSWORD);  
					Statement dbStmt = dbConn.createStatement()) { 
				 
				String createUsers = "CREATE TABLE IF NOT EXISTS users (" +  
								"user_id INT AUTO_INCREMENT PRIMARY KEY," + 
								"username VARCHAR(50) UNIQUE," + 
								"password VARCHAR(100)," + 
								"role ENUM('USER', 'ADMIN', 'GUEST')," + 
								"first_name VARCHAR(50)," + 
								"last_name VARCHAR(50)," + 
								"city VARCHAR(50)," + 
								"email VARCHAR(100)," + 
								"mobile VARCHAR(15)"+ 
							")"; 
				 
				String createProducts = "CREATE TABLE IF NOT EXISTS products (" + 
								"product_id INT AUTO_INCREMENT PRIMARY KEY," + 
								"name VARCHAR(100)," + 
								"description TEXT,"+ 
								"price DECIMAL(10, 2)," + 
								"quantity INT" + 
							")"; 
						 
				String createCart = "CREATE TABLE IF NOT EXISTS cart (" + 
	                    		"cart_id INT AUTO_INCREMENT PRIMARY KEY," + 
	                    		"user_id INT," + 
	                    		"product_id INT," + 
	                    		"quantity INT," + 
	                    		"FOREIGN KEY (user_id) REFERENCES users(user_id)," + 
	                    		"FOREIGN KEY (product_id) REFERENCES products(product_id)" + 
	                        ")"; 
				 
				String createPurchaseHistory = "CREATE TABLE IF NOT EXISTS purchase_history (" + 
                        		"purchase_id INT AUTO_INCREMENT PRIMARY KEY," + 
                        		"user_id INT," + 
                        		"product_id INT," + 
                        		"quantity INT," + 
                        		"purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," + 
                        		"FOREIGN KEY (user_id) REFERENCES users(user_id)," + 
                        		"FOREIGN KEY (product_id) REFERENCES products(product_id)" + 
                        	")"; 
				 
				dbStmt.executeUpdate(createUsers); 
				dbStmt.executeUpdate(createProducts); 
				dbStmt.executeUpdate(createCart); 
				dbStmt.executeUpdate(createPurchaseHistory); 

				System.out.println("Tables created or already exist."); 
			} 
			 
		}catch (SQLException e) { 
				System.out.println("Database initialization failed: " + e.getMessage()); 
			 
		} 
	} 
}