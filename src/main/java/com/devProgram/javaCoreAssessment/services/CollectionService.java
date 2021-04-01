package com.devProgram.javaCoreAssessment.services;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devProgram.javaCoreAssessment.services.actions.CollectionActions;

@Service
public class CollectionService {

private Boolean system = true;
	
	@Autowired
	private CollectionActions collectionActions;
	
	public void collectionManagerMenu(Scanner scanner) {
		system = true;

		while (system) {
			displayCollectionOptionsMenu(scanner);
		}

	}

	public void displayCollectionOptionsMenu(Scanner scanner) {
		System.out.println("\nWhat action of collection would you like to manage?\n");
		System.out.println("0 - Main Menu");
		System.out.println("1 - Create Collection");
		System.out.println("2 - Create SubCollection");
		System.out.println("3 - List Collection(s)");
		System.out.println("4 - Search SubCollection products");
		System.out.println("5 - List for Products in a Collection");
		
		System.out.println("\nPlease, enter the desired menu action index:\n");

		Integer action = scanner.nextInt();

		switch (action) {
		case 1:
			collectionActions.create(scanner);
			break;
		case 2:
			collectionActions.createSubCollection(scanner);
			break;
		case 3:
			collectionActions.listCollections();
			break;
		case 4:
			collectionActions.searchSubCollectionProducts(scanner);
			break;
		case 5:
			collectionActions.listForProductsInCollection(scanner);
			break;
		default:
			system = false;
			break;
		}
	}

}
