package main;
import registration.UserRegistration;

import java.util.Scanner;

import dao.UserDAO;
import login.UserLogin;
import model.User;
import util.DatabaseInitializer;

public class MainUI {
	
	public static void main(String[] args) {
		//ensure DB and tables exist
		DatabaseInitializer.initialize();
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("====== Welcome to ECommerce App ======");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.println("Enter choice: ");
			
			int choice = sc.nextInt();
			sc.nextLine(); // clear newline
			
			switch(choice) {
			
			case 1: UserRegistration.registerUser(sc);
				break;
			
			case 2: UserLogin.loginUser(sc);
				break;
				
			case 3: System.out.println("Exiting the Application.");
				sc.close();
				return;
	
				
			default: System.out.println("Invalid choice.");
			
			}
			
		}
		
	}

}
