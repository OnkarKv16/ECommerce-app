package guest;

import view.ProductViewer;

import java.util.Scanner;

public class GuestMenu {

    public static void showGuestMenu(Scanner sc) {
        
    	while(true){
            
    		System.out.println("\n===== Guest Menu=====");
            System.out.println("1. View Product list");
            System.out.println("2. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch(choice) {
                case 1:
                    ProductViewer.showProductList();  
                    break;

                case 2:
                    System.out.println("Log out");
                    return;

                default:
                    System.out.println(" Invalid choice. Try again.");
            }
        }
    }
}
