package admin;

import java.util.List;
import java.util.Scanner;

import dao.UserDAO;
import model.User;

public class AdminUserManager {
	
	public static void viewAllUsers() {
		List<User> users = UserDAO.getAllUsers();
		
		if(users.isEmpty()) {
			System.out.println("No users found");
			return;
		}
		
		System.out.println("\n Registered Users:");
        System.out.println("ID | Username   | Role   | Name           | City       | Email");
        System.out.println("---------------------------------------------------------------------");
        for (User u : users) {
            String fullName = u.getFirstName() + " " + u.getLastName();
            System.out.printf("%2d | %-10s | %-6s | %-14s | %-10s | %s\n",
                    u.getUser_id(), u.getUsername(), u.getRole(), fullName, u.getCity(), u.getEmail());
        }
	}
	
	public static void viewUserHistory(Scanner sc) {
		sc.nextLine();
		System.out.println("Enter username to view purchase history: ");
		String username = sc.nextLine().trim();
		
		User user = UserDAO.getUserByUsername(username);
		if(user == null) {
			System.out.println("User not found.");
			return;
		}
		
		UserDAO.viewUserPurchaseHistory(user.getUser_id());
		
	}
}
