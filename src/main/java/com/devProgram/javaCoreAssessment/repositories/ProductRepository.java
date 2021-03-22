package com.devProgram.javaCoreAssessment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devProgram.javaCoreAssessment.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

	
}
