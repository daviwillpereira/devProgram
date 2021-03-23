package com.devProgram.javaCoreAssessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devProgram.javaCoreAssessment.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

	@Query("from Product p where p.name LIKE %:name%")
	List<Product> searchPriceAndDescriptionByName(@Param("name") String name);
}
