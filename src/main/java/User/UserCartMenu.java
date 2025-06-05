package User;

import java.util.Scanner;

import model.User;
import service.CartService;
import service.CartServiceImpl;
import view.ProductViewer;

public class UserCartMenu {
	
	public static void showUserMenu(User user, Scanner sc) {
		CartService cartService = new CartServiceImpl();
		
		while (true) {
			System.out.println("\n===== User Dashboard =====");
			System.out.println("1. View Product List");
			System.out.println("2. Add Product to Cart");
			System.out.println("3. View Cart");
			System.out.println("4. Checkout");
			System.out.println("5. Logout");
			System.out.println("Enter choice:");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
				case 1: ProductViewer.showProductList();
					break;
				
				case 2: cartService.addToCart(sc);
					break;
				
				case 3: cartService.viewCart();
					break;
				
				case 4: cartService.checkout(user);
					break;
				
				case 5: 
					System.out.println("Logging out...");
					return;
				default:
					System.out.println(" Invalid choice. Try again.");
			}
		}
	}
}
