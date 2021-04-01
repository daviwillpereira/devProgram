package com.devProgram.javaCoreAssessment.services.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devProgram.javaCoreAssessment.entities.Collection;
import com.devProgram.javaCoreAssessment.entities.SubCollection;
import com.devProgram.javaCoreAssessment.repositories.CollectionRepository;
import com.devProgram.javaCoreAssessment.repositories.ProductRepository;
import com.devProgram.javaCoreAssessment.repositories.SubCollectionRepository;

@Component
public class CollectionActions {

	@Autowired
	private CollectionRepository collectionRepository;

	@Autowired
	private SubCollectionRepository subCollectionRepository;

	@Autowired
	private ProductRepository productRepository;

	public void create(Scanner scanner) {
		Collection collection = new Collection();
		boolean stop = false;

		scanner.nextLine();

		// SETTING NAME
		System.out.println("Enter the new Collection name:");
		collection.setName(scanner.nextLine());

		// SETTING DESCRIPTION
		System.out.println("\nEnter the collection description:");
		collection.setDescription(scanner.nextLine());

		// SETTING KEYWORDS

		List<String> keywords = new ArrayList<>();
		int keywordsCount = 0;

		while (!stop) {

			keywordsCount++;

			if (keywordsCount <= 6) {
				System.out.println("\nEnter a keyword ( " + keywordsCount + " to 6 )? [ y / n ]");
				String yesOrNo = scanner.nextLine();

				if (yesOrNo.equals("y")) {
					System.out.println("\nEnter keyword:");
					keywords.add(scanner.nextLine());
				} else if (yesOrNo.equals("n")) {
					stop = true;
				} else {
					System.err.println("WRONG INPUT! Enter y or n\n");
					keywordsCount--;
				}

			} else {
				System.err.println("Limit of keywords exceeded! Please continue...\n");
				stop = true;
			}

		}
		collection.setKeywords(keywords);

		// PERSISTING COLLECTION
		collectionRepository.save(collection);

	}

	public void createSubCollection(Scanner scanner) {
		SubCollection subCollection = new SubCollection();
		boolean stop = false;

		scanner.nextLine();

		// SETTING NAME
		System.out.println("\nEnter the new SubCollection name:");
		subCollection.setName(scanner.nextLine());

		// SETTING COLLETION
		List<Collection> collections = collectionRepository.findAll();

		while (!stop) {
			if (!collections.isEmpty()) {
				System.out.println("\nCollections:\n");
				collections.forEach(collection -> System.out.println(collection.getId() + "- " + collection.getName()));
				System.out.println("\nEnter the Collection index which you desire to link the SubCollection:\n");
				Optional<Integer> index = Optional.of(Integer.parseInt(scanner.nextLine()));
				if (index.get() <= collections.size()) {
					subCollection.setCollection(collections.get(index.get()-1));
					subCollectionRepository.save(subCollection);
					System.out.println("\nSubCollection '" + subCollection.getName() + "' created with success!\n");
					stop = true;

				} else {
					System.err.println("\nInvalid index! Please enter a valid option!\n");
				}
			} else {
				System.err.println("\nNo Colletion found!");
				stop = true;
			}
		}

	}

	public void listCollections() {

		List<Collection> collections = collectionRepository.findAll();

		if (!collections.isEmpty()) {
			System.out.println("\nList of Collections:");
			for (Collection collection : collections) {
				System.out.println("\nCollection: " + collection.getName() + "\nSubCollection(s):");

				List<SubCollection> subCollections = collection.getSubCollection();

				for (SubCollection subCollection : subCollections) {
					System.out.println("   - " + subCollection.getName());
				}
			}
		} else {
			System.err.println("\nNo Collections found!\n");
		}
	}

	public void searchSubCollectionProducts(Scanner scanner) {
		boolean stop = false;

		while (!stop) {
			System.out.println("\nChoose a SubCollection:");
			List<SubCollection> subCollections = subCollectionRepository.findAll();

			if (!subCollections.isEmpty()) {
				for (SubCollection subCollection : subCollections) {
					System.out.println("   " + (subCollection.getId() - 1) + "- " + subCollection.getName());
				}

				scanner.nextLine();
				System.out.println("\nEnter the SubCollection index:\n");
				Optional<Integer> index = Optional.of(Integer.parseInt(scanner.nextLine()));
				if (index.get() <= subCollections.size()) {

					System.out.println("\nEnter the name of the product:");
					String name = scanner.next();

					System.out.println("\nProduct(s) in '" + subCollections.get(index.get()).getName() + "' :");
					productRepository
							.searchProductsByNameAndSubCollectionId(name, Long.valueOf(index.get().longValue() + 1))
							.forEach(product -> System.out.println("   " + product.getId() + "- " + product.getName()));

					stop = true;

					System.out.println("\nType anything to continue...\n");
					scanner.nextLine();
					scanner.nextLine();

				} else {
					System.err.println("\nInvalid index! Please enter a valid option!\n");
				}
			} else {
				System.err.println("\nNo SubCollections found!\n");
				stop = true;
			}
		}
	}

	public void listForProductsInCollection(Scanner scanner) {
		boolean stop = false;

		scanner.nextLine();
		System.out.println("Type anything to continue...");
		scanner.nextLine();
		

		while (!stop) {
			System.out.println("\nOptions:\n");
			System.out.println("1- Collection(s)");
			System.out.println("2- SubCollection(s)");
			System.out.println("\nChoose a option:");

			Optional<Integer> index = Optional.of(Integer.parseInt(scanner.nextLine()));
			if (index.get() == 1) {
				List<Collection> collections = collectionRepository.findAll();

				if (!collections.isEmpty()) {
					System.out.println("\nCollections: \n");
					for (Collection collection : collections) {
						System.out.println(collection.getId() + "- " + collection.getName());
					}
					System.out.println("\nFrom which index you'd like to list products?");
					System.out.println("\nProducts:");
					Optional<Integer> indexCol = Optional.of(Integer.parseInt(scanner.nextLine()));

					if (indexCol.get() <= collections.size()) {
						productRepository.listProductsByCollectionId(Long.valueOf(indexCol.get().longValue()))
								.forEach(p -> System.out.println("\n" + p.getId() + "- " + p.getName()));
					} else {
						System.err.println("\nInvalid index! Please enter a valid option!\n");
					}
				} else {
					System.err.println("\nNo Collections found!\n");
					stop = true;
				}
			} else if (index.get() == 2) {
				List<SubCollection> subCollections = subCollectionRepository.findAll();

				if (!subCollections.isEmpty()) {
					System.out.println("\nSubCollections: \n");
					for (SubCollection subCollection : subCollections) {
						System.out.println("   " + (subCollection.getId()) + "- " + subCollection.getName());
					}

					System.out.println("\nFrom which index you'd like to list products?");
					Optional<Integer> indexSub = Optional.of(Integer.parseInt(scanner.nextLine()));
					System.out.println("\nProducts:");

					if (indexSub.get() <= subCollections.size()) {
						productRepository.listProductsBySubCollectionId(Long.valueOf(indexSub.get().longValue()))
								.forEach(p -> System.out.println(p.getId() + "- " + p.getName() + "\n"));
					} else {
						System.err.println("\nInvalid index! Please enter a valid option!\n");
					}
				} else {
					System.err.println("\nNo SubCollections found!\n");
					stop = true;
				}
			} else {
				System.err.println("\nInvalid index! Please enter a valid option!\n");
			}
		}
	}

}
