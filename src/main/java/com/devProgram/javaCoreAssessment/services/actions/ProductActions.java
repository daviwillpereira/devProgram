package com.devProgram.javaCoreAssessment.services.actions;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devProgram.javaCoreAssessment.entities.Product;
import com.devProgram.javaCoreAssessment.entities.SubCollection;
import com.devProgram.javaCoreAssessment.enums.VariantEnum;
import com.devProgram.javaCoreAssessment.repositories.ProductRepository;
import com.devProgram.javaCoreAssessment.repositories.SubCollectionRepository;
import com.devProgram.javaCoreAssessment.utils.ImageUtils;

@Component
public class ProductActions {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SubCollectionRepository subCollectionRepository;

	public void save(Scanner scanner) {
		Product product = new Product();
		boolean stop = false;
		boolean insertImage = true;
		boolean cancelSaving = false;

		scanner.nextLine();

		// SETTING NAME
		System.out.println("Enter the product name:");
		String name = scanner.nextLine();
		product.setName(name);

		// SETTING SUBCOLLETION

		List<SubCollection> subCollections = subCollectionRepository.findAll();
		List<SubCollection> productSubCollections = new ArrayList<>();

		while (!stop) {
			if (!subCollections.isEmpty()) {
				boolean linkSubCollection = true;

				while (linkSubCollection) {
					System.out.println("\nSubCollections:\n");
					subCollections.forEach(subCollection -> System.out
							.println(subCollection.getId() + "- " + subCollection.getName()));
					System.out.println("\nEnter a SubCollection index to link the Product with:\n");
					Optional<Integer> index = Optional.of(Integer.parseInt(scanner.nextLine()));

					if (index.get() <= subCollections.size()) {

						productSubCollections.add(subCollections.get(index.get() - 1));

						System.out.println("Link another SubColletion to this product? [ y / n ]");
						String yesOrNo = scanner.nextLine();
						if (yesOrNo.equals("y")) {
							if (subCollections.size() == 1 && !productSubCollections.isEmpty()) {
								product.setSubCollections(productSubCollections);
								System.out.println("\nNo more Subcollection available to be added!\n");
								System.out.println("SubCollection(s) linked with success!\n");
								linkSubCollection = false;
								stop = true;
							} else {
								linkSubCollection = true;
							}
						} else if (yesOrNo.equals("n")) {
							linkSubCollection = false;
							stop = true;
							product.setSubCollections(productSubCollections);
							System.out.println("\nSubCollection(s) linked with success!\n");
						} else {
							linkSubCollection = true;
							System.err.println("WRONG INPUT! Enter y or n\n");
							scanner.nextLine();
						}

					} else {

						System.err.println("\nInvalid index! Please enter a valid option!\n");
					}
				}
			} else {
				System.err.println("\nNo SubCollections found! Please, create one first.");
				cancelSaving = true;
				stop = true;
			}
		}

		if (cancelSaving == false) {

			// SETTING IMAGE
			do {
				stop = false;
				List<byte[]> imgList = new ArrayList<>();

				System.out.println("\nDo you want to link this product to one or more image? [ y / n ]");
				String yesOrNo = scanner.nextLine();
				if (yesOrNo.equals("y")) {
					int imgCount = 0;

					do {
						System.out.println("\nEnter a image URL ( png, jpg, bmp ):");
						String image = scanner.nextLine();

						try {
							imgCount++;

							if (imgCount <= 5) {
								byte[] byteImage = ImageUtils.convertImageByte(new URL(image));
								if (byteImage != null) {
									ImageUtils.toBufferedImage(byteImage);
									imgList.add(byteImage);
								} else {
									System.err.println(
											"\nMake sure the URL you provided is linking to a valid image file format such as jpg, png, bmp!\n");
									imgCount--;
								}
							} else {
								System.err.println("Limit of images exceeded! Please continue...\n");
								imgCount--;
								scanner.nextLine();
							}

						} catch (IOException e) {
							e.getStackTrace();
						}

						System.out.println(
								"\nTry to add a image to this product again( " + imgCount + " of 5 ) " + "? [ y / n ]");
						yesOrNo = scanner.nextLine();
						if (yesOrNo.equals("y")) {
							insertImage = true;
						} else if (yesOrNo.equals("n")) {
							insertImage = false;
							stop = true;
							product.setImages(imgList);
						} else {
							insertImage = false;
							System.err.println("WRONG INPUT! Enter y or n\n");
							scanner.nextLine();
						}
					} while (insertImage);

				} else if (yesOrNo.equals("n")) {
					stop = true;
				} else {
					System.err.println("WRONG INPUT! Enter y or n\n");
				}
			} while (!stop);

			System.out.println("\nType anything to continue...\n");
			scanner.nextLine();

			// SETTING PRICE
			do {
				stop = false;

				System.out.println("Enter the product price:");

				if (scanner.hasNextBigDecimal()) {
					BigDecimal price = scanner.nextBigDecimal();
					product.setPrice(price);

					stop = true;

				} else {
					System.err.println("WRONG INPUT! Enter a valid Decimal value!\n");
					System.out.println("Type anything to continue...\n");
					scanner.nextLine();
					scanner.nextLine();
				}
			} while (!stop);

			scanner.nextLine();

			// SETTING DESCRIPTION
			System.out.println("Enter the product description:");

			String description = scanner.nextLine();
			product.setDescription(description);

			// SETTING QUANTITY
			do {
				stop = false;

				System.out.println("Enter the product quantity:");

				if (scanner.hasNextInt()) {
					Integer quantity = scanner.nextInt();
					product.setQuantity(quantity);

					stop = true;

				} else {
					scanner.nextLine();
					System.err.println("WRONG INPUT! Enter a valid numeric value!\n");
					scanner.next();
				}
			} while (!stop);

			scanner.nextLine();

			// SETTING VARIANT

			stop = false;
			while (!stop) {
				System.out.println("Choose a variant index to link to the product:\n");

				Stream.of(VariantEnum.values()).forEach(variantMenu -> {
					System.out.println(String.valueOf(variantMenu.getIndex()) + "- " + variantMenu.getDescription());
				});
				System.out.println((VariantEnum.values().length + 1) + "- Any");

				Optional<Integer> index = Optional.of(Integer.parseInt(scanner.nextLine()));
				if (index.get() <= VariantEnum.values().length) {
					product.setVariant(VariantEnum.fromIndex(index.get()));
					stop = true;
				} else if (index.get() == VariantEnum.values().length + 1) {
					product.setVariant(null);
					stop = true;
				} else {
					System.err.println("\nInvalid index! Please enter a valid option!");
				}
			}
			// PERSISTING PRODUCT
			productRepository.save(product);
			System.out.println("New Product Saved!");
		}
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
