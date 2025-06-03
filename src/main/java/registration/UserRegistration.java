package registration;

import java.util.Scanner;

import dao.UserDAO;
import model.User;

public class UserRegistration {
	
	public static void registerUser(Scanner sc) {
		
			User user = new User();
			
			System.out.println("Enter username:  ");
			user.setUsername(sc.nextLine());

			System.out.println("Enter password:  ");
			user.setPassword(sc.nextLine());
			
			//chose role
			String role = "";
	        while (true) {
	            System.out.print("Choose role (1. USER, 2. GUEST): ");
	            String choice = sc.nextLine();
	            if (choice.equals("1")) {
	                role = "USER";
	                break;
	            } else if (choice.equals("2")) {
	                role = "GUEST";
	                break;
	            } else {
	                System.out.println("❌ Invalid choice. Please enter 1 or 2.");
	            }
	        }
	        user.setRole(role);
			
			System.out.println("Enter firstname:  ");
			user.setFirstName(sc.nextLine());
			
			System.out.println("Enter lastname:  ");
			user.setLastName(sc.nextLine());
			
			System.out.println("Enter Citye:  ");
			user.setCity(sc.nextLine());
			
			System.out.println("Enter email:  ");
			user.setEmail(sc.nextLine());
			
			System.out.println("Enter mobile:  ");
			user.setMobile(sc.nextLine());
			
			boolean success = UserDAO.registerUser(user);
			System.out.println(success ? " Registration succesfull!" : " Registration failed.");
			

	}

}
