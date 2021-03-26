package com.devProgram.javaCoreAssessment.services.actions;

import java.math.BigDecimal;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devProgram.javaCoreAssessment.entities.Product;
import com.devProgram.javaCoreAssessment.enums.CategoryEnum;
import com.devProgram.javaCoreAssessment.repositories.ProductRepository;

@Component
public class ProductActions {

	@Autowired
	private ProductRepository productRepository;
	
	public void save(Scanner scanner){
		Product product = new Product();
		boolean wentToCatch = false;

		scanner.nextLine();
		
		System.out.println("Enter the product name:");
		String name = scanner.nextLine();
		product.setName(name);

		do {

			System.out.println("Enter the product price:");

			if (scanner.hasNextBigDecimal()) {
				BigDecimal price = scanner.nextBigDecimal();
				product.setPrice(price);

				wentToCatch = true;

			} else {
				scanner.nextLine();
				System.err.println("WRONG INPUT! Enter a valid Decimal value!\n");
				scanner.next();
			}
		} while (!wentToCatch);

		scanner.nextLine();
		
		System.out.println("Enter the product description:");
		
		String description = scanner.nextLine();
		product.setDescription(description);

		do {
			wentToCatch = false;
			
			System.out.println("Enter the product quantity:");
			
			if (scanner.hasNextInt()) {
				Integer quantity = scanner.nextInt();
				product.setQuantity(quantity);

				wentToCatch = true;

			} else {
				scanner.nextLine();
				System.err.println("WRONG INPUT! Enter a valid numeric value!\n");
				scanner.next();
			}
		} while (!wentToCatch);

		System.out.println("Enter the product category:");
		String category = scanner.next();
		product.setCategory(category.equals(CategoryEnum.MONITORS.name()) ? CategoryEnum.MONITORS
				: category.equals(CategoryEnum.COMPUTER_ACCESSORIES.name()) ? CategoryEnum.COMPUTER_ACCESSORIES : CategoryEnum.OTHER);

		productRepository.save(product);
		System.out.println("New Product Saved!");
	}

	public void list(Scanner scanner) {
		System.out.println("\nProducts saved:");
		productRepository.findAll().forEach(product -> System.out.println("\n- " + product.getName()));
	}

	public void search(Scanner scanner) {
		System.out.println("\nEnter the name of the product:");
		String name = scanner.next();

		System.out.println("\nResult:");
		productRepository.searchPriceAndDescriptionByName(name).forEach(product -> System.out.println("\nProduct: "
				+ product.getName() + "\nPrice: " + product.getPrice() + "\nDescription: " + product.getDescription()));
		
	}
}
