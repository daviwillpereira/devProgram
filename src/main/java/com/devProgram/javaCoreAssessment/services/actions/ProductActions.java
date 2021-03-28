package com.devProgram.javaCoreAssessment.services.actions;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devProgram.javaCoreAssessment.ImageUtils;
import com.devProgram.javaCoreAssessment.entities.Product;
import com.devProgram.javaCoreAssessment.enums.CategoryEnum;
import com.devProgram.javaCoreAssessment.repositories.ProductRepository;

@Component
public class ProductActions {

	@Autowired
	private ProductRepository productRepository;

	public void save(Scanner scanner) {
		Product product = new Product();
		boolean wentToCatch = false;
		boolean insertAnotherImage = true;

		scanner.nextLine();

		// SETTING NAME
		System.out.println("Enter the product name:");
		String name = scanner.nextLine();
		product.setName(name);

		// SETTING IMAGE
		do {
			wentToCatch = false;

			System.out.println("\nDo you want to link this product to one or more image? [ true / false ]");
			if (scanner.hasNextBoolean()) {
				scanner.nextLine();
				int imgCount = 0;

				do {
					System.out.println("\nEnter a image URL ( png, jpg, bmp ):");
					String image = scanner.nextLine();

					try {
						imgCount++;
						List<byte[]> imgList = new ArrayList<>();

						if (imgCount <= 5) {
							byte[] byteImage = ImageUtils.convertImageByte(new URL(image));
							imgList.add(byteImage);
						} else {
							System.err.println("Limit of images exceeded! Please continue...\n");
							scanner.nextLine();
						}
					} catch (IOException e) {
						System.err.println("Unsupported format!\n");
						wentToCatch = true;
					}

					System.out.println(
							"\nAdd another image to this product( " + imgCount + " of 5 ) " + "? [ true/ false ]");
					if(scanner.hasNextBoolean()) {
						insertAnotherImage = true;
						scanner.nextLine();
					} else {
						insertAnotherImage = false;
					}
				} while (insertAnotherImage);

			} else {
				scanner.nextLine();
				System.err.println("WRONG INPUT! Enter true or false\n");
				scanner.next();
			}
		} while (!wentToCatch);

		scanner.nextLine();

		// SETTING PRICE
		do {
			wentToCatch = false;

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

		// SETTING DESCRIPTION
		System.out.println("Enter the product description:");

		String description = scanner.nextLine();
		product.setDescription(description);

		// SETTING QUANTITY
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

		scanner.nextLine();

		// SETTING CATEGORY
		System.out.println("Enter the product category:");
		String category = scanner.next();
		product.setCategory(category.equals(CategoryEnum.MONITORS.name()) ? CategoryEnum.MONITORS
				: category.equals(CategoryEnum.COMPUTER_ACCESSORIES.name()) ? CategoryEnum.COMPUTER_ACCESSORIES
						: CategoryEnum.OTHER);

		// PERSISTING PRODUCT
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
