package admin;

import java.util.Scanner;

public class AdminMenu {
	
	public static void showAdminMenu(Scanner sc) {
		while(true) {
			System.out.println("\n====== Admin menu ======");
			System.out.println("1. View all users");
			System.out.println("2. View users purcchase history");
			System.out.println("3. Check Product Quantity");
			System.out.println("4. Add product");
			System.out.println("5. Logout");
			System.out.println("Enter choice: ");
			
			int choice = sc.nextInt();
			
			switch(choice) {
			
				case 1:
					AdminUserManager.viewAllUsers();
					break;
				case 2:
					AdminUserManager.viewUserHistory(sc);
					break;
				case 3:
					AdminProductManager.checkQuantity(sc);
					break;
				case 4:
					sc.nextLine();
					AdminProductManager.addProduct(sc);
					break;
				case 5:
					System.out.println("Logging out");
					return;
				default:
					System.out.println("Invalid choice. Try again.");
						
			}
			
		}
	}

}
