package com.devProgram.javaCoreAssessment.services;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devProgram.javaCoreAssessment.services.actions.ProductActions;

@Service
public class ProductService {

	private Boolean system = true;
	
	@Autowired
	private ProductActions productActions;

	public void productManagerMenu(Scanner scanner) {
		
		system = true;
		
		while (system) {
			displayProductOptionsMenu(scanner);
		}

	}

	public void displayProductOptionsMenu(Scanner scanner) {
		System.out.println("\nWhat action of product would you like to conduct?");
		System.out.println("0 - Main Menu");
		System.out.println("1 - Save");
		System.out.println("2 - List");
		System.out.println("3 - Search");
		
		System.out.println("\nPlease, enter the desired menu action index:\n");

		Integer action = scanner.nextInt();

		switch (action) {
		case 1:
			productActions.save(scanner);
			break;
		case 2:
			productActions.list(scanner);
			break;
		case 3:
			productActions.search(scanner);
			break;
		default:
			system = false;
			break;
		}
		
	}
}
