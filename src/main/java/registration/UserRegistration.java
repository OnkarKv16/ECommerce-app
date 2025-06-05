package registration;

import java.util.Scanner;
import java.util.regex.Pattern;

import dao.UserDAO;
import model.User;

public class UserRegistration {
	
	public static void registerUser(Scanner sc) {
		
	User user = new User();
	
	System.out.println("Enter username:  ");
	user.setUsername(sc.nextLine());

	String password = readPasswordWithScanner(sc);
	user.setPassword(password);
	
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
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }
    }
    user.setRole(role);
	
	System.out.println("Enter firstname:  ");
	user.setFirstName(sc.nextLine());
	
	System.out.println("Enter lastname:  ");
	user.setLastName(sc.nextLine());
	
	System.out.println("Enter Citye:  ");
	user.setCity(sc.nextLine());
	
	while (true) {
        System.out.print("Enter email: ");
        String email = sc.nextLine().trim();
        if (isValidEmail(email)) {
            user.setEmail(email);
            break;
        }
        System.out.println("Invalid email format.");
    }
	
	while (true) {
        System.out.print("Enter mobile number: ");
        String mobile = sc.nextLine().trim();
        if (isValidMobile(mobile)) {
            user.setMobile(mobile);
            break;
        }
        System.out.println("Invalid mobile number. Must be 10 digits.");
    }
	
	boolean success = UserDAO.registerUser(user);
	System.out.println(success ? " Registration succesfull!" : " Registration failed.");
			

	}
	
	private static String readPasswordWithScanner(Scanner sc) {
        while (true) {
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            if (isStrongPassword(password)) {
                return password;
            } else {
                System.out.println(" Password must be at least 8 characters, include 1 digit, 1 uppercase, and 1 special character.");
            }
        }
    }
	
	
	private static boolean isStrongPassword(String password){
        return password.length() >= 8 &&
               Pattern.compile("[A-Z]").matcher(password).find() &&
               Pattern.compile("[0-9]").matcher(password).find() &&
               Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
    }
	
	private static boolean isValidEmail(String email){
        return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email);
    }
	
	private static boolean isValidMobile(String mobile) {
        
		return Pattern.matches("^\\d{10}$", mobile);
    }
	
}
