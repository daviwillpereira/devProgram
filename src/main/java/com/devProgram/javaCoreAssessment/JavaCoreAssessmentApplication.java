package com.devProgram.javaCoreAssessment;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.devProgram.javaCoreAssessment.services.CrudProductService;

@EnableJpaRepositories
@SpringBootApplication
public class JavaCoreAssessmentApplication implements CommandLineRunner {

	private Boolean system = true;

	private final CrudProductService productService;

	public JavaCoreAssessmentApplication(CrudProductService productService) {
		this.productService = productService;
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaCoreAssessmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("\n################################################################################ \n");
			System.out.println("WELCOME TO DEV_PROGRAM SHOP!\n");
			System.out.println("Which function would you like to execute?\n");
			System.out.println("0 - Exit");
			System.out.println("1 - Product");
			System.out.println("\n################################################################################");


			System.out.println("\nPlease, enter the desired menu index:\n");

			Integer function = scanner.nextInt();

			switch (function) {
			case 1:
				productService.initial(scanner);
				break;
			default:
				System.out.println("Bye! See you soon...\n");
				system = false;
				break;
			}
		}

	}
}
