package com.devProgram.javaCoreAssessment;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.devProgram.javaCoreAssessment.services.CollectionService;
import com.devProgram.javaCoreAssessment.services.ProductService;

@EnableJpaRepositories
@SpringBootApplication
public class JavaCoreAssessmentApplication implements CommandLineRunner {

	private Boolean system = true;

	private final CollectionService collectionService;
	private final ProductService productService;

	public JavaCoreAssessmentApplication(ProductService productService, CollectionService collectionService) {
		this.collectionService = collectionService;
		this.productService = productService;
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaCoreAssessmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws InputMismatchException, InterruptedException, IOException {
		Scanner scanner = new Scanner(System.in);

		while (system) {

			displayMainMenu(scanner);

			try {
				System.out.println("\nPlease, enter the desired menu index:\n");

				Integer function = scanner.nextInt();

				if (function <= 2) {

					switch (function) {
					case 1:
						collectionService.collectionManagerMenu(scanner);
						break;
					case 2:
						productService.productManagerMenu(scanner);
						break;
					default:
						System.out.println("\nBye! See you soon...\n");
						system = false;
						break;
					}

				} else {
					System.err.println("\nInvalid index! Please enter a valid option!");
				}
			} catch (InputMismatchException e) {
				System.err.println("\nPlease enter a numeric value!");
				scanner.next();
			}
		}

		scanner.close();

	}

	public void displayMainMenu(Scanner scanner) throws InterruptedException, IOException {
		System.out.println("\n################################################################################ \n");
		System.out.println("WELCOME TO DEV_PROGRAM E-COMMERCE MANAGEMENT APP!\n");
		System.out.println("Please, choose the option which you desire:\n");
		System.out.println("0 - Exit");
		System.out.println("1 - Collection Management");
		System.out.println("2 - Product Management");
		System.out.println("\n################################################################################");
	}
}
