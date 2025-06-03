package login;

import java.util.Scanner;

import dao.UserDAO;
import model.User;

public class UserLogin {
	
	public static void loginUser(Scanner sc) {

		
		System.out.println("Enter username:  ");
		String username = sc.nextLine();
		
		System.out.println("Enter password:  ");
		String password = sc.nextLine();
		
		User loggedIn = UserDAO.login(username, password);
		
		if (loggedIn != null) {
			System.out.println("Login succesful. welcome, "+ loggedIn.getFirstName());
			System.out.println("Role: "+loggedIn.getRole());
		}else {
			System.out.println("Invalid choice.");
		}
	}
	

}
