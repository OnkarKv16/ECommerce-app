package admin;

import java.util.Scanner;

import dao.ProductDAO;

public class AdminProductManager {
	
	public static void addProduct(Scanner sc) {
		System.out.println("Enter product name: ");
		String name = sc.nextLine();
	
		System.out.println("Enter product description: ");
		String desc = sc.nextLine();
		
		System.out.print("Enter product price: ");
        double price = sc.nextDouble();	
        sc.nextLine();

		
        System.out.print("Enter product quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine(); 
		
		ProductDAO.addProduct(name, desc, price, quantity);
		System.out.println("Product added successfully.");
	}
	
	public static void checkQuantity(Scanner sc) {
		System.out.println("Enter product ID: ");
		int productId = sc.nextInt();
		sc.nextLine();
		
		int qty = ProductDAO.getQuantityByProductId(productId);
		
		if(qty >= 0) {
			System.out.println("Available quantity: " +qty);
		}else {
			System.out.println("Product not found.");
		}
			
	}
}
