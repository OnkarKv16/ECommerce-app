package service;

import java.util.Scanner;

import model.User;

public interface CartService {
	
	void addToCart(Scanner sc);
	
	void viewCart();
	
	double calculateTotal();
	
	void checkout(User user);
}
