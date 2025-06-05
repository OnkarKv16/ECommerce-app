package login;

import java.util.Scanner;

import User.UserCartMenu;
import admin.AdminMenu;
import dao.UserDAO;
import model.User;
import view.ProductViewer;

public class UserLogin {
	
	public static void loginUser(Scanner sc) {

		
		System.out.println("Enter username:  ");
		String username = sc.nextLine();
		
		System.out.println("Enter password:  ");
		String password = sc.nextLine();
		
		User loggedIn = UserDAO.login(username, password);
		
		if (loggedIn != null) {
		    System.out.println("Login successful. Welcome " + loggedIn.getFirstName());
		    System.out.println("Role: " + loggedIn.getRole());

		    if (loggedIn.getRole().equalsIgnoreCase("USER")) {
		        UserCartMenu.showUserMenu(loggedIn, sc);
		    } else if (loggedIn.getRole().equalsIgnoreCase("ADMIN")) {
		    	AdminMenu.showAdminMenu(sc);
		    } else if (loggedIn.getRole().equalsIgnoreCase("GUEST")) {
		        System.out.println("As a guest, you can only view products.");
		        ProductViewer.showProductList();
		    }
		}else {
			System.out.println("Invalid choice.");
		}
	}
	

}
