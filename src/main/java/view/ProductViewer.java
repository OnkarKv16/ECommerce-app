package view;

import java.util.List;
import dao.ProductDAO;
import model.Product;

public class ProductViewer {
	
	public static void showProductList() {
		
		List<Product> productList = ProductDAO.getAllProduct("product_id", "ASC");
		if (productList.isEmpty()) {
			System.out.println(" No products found.");
			return;
		}
		
		 System.out.println("\n Available Products:");
	        System.out.println("ID | Name              | Price   | Quantity");
	        System.out.println("----------------------------------------------");
	        for (Product p : productList) {
	            System.out.printf("%2d | %-17s | ₹%.2f | %3d\n",
	                    p.getProduct_id(), p.getName(), p.getPrice(), p.getQuantity());
	        }
		
		
	}
}
