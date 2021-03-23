package com.devProgram.javaCoreAssessment.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.devProgram.javaCoreAssessment.entities.Product;
import com.devProgram.javaCoreAssessment.enums.CategoryEnum;
import com.devProgram.javaCoreAssessment.repositories.ProductRepository;
import com.devProgram.javaCoreAssessment.services.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
	
	@InjectMocks
	private ProductService productService;
	
	@Mock
	private ProductRepository repository;
	
	@Test
	public void listAllProducts_basic() {
		//Given
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(new Long(1), "Daaa", new BigDecimal(100.00), "desc Daaa", 30, CategoryEnum.OTHER));
		products.add(new Product(new Long(2), "D", new BigDecimal(200.99), "desc D", 40, CategoryEnum.MONITORS));
		
		//When
		when(repository.searchPriceAndDescriptionByName("d")).thenReturn(products);
		
		//Then
		assertEquals(2, null);
	}

}
