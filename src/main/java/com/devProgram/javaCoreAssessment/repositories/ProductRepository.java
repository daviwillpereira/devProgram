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
	
	@Query("select p from Product p inner join p.subCollections s where p.name LIKE %:name% and s.id = :id")
	List<Product> searchProductsByNameAndSubCollectionId(@Param("name") String name, @Param("id") Long id);
	
	@Query("select p from Product p inner join p.subCollections s where s.id = :id")
	List<Product> listProductsBySubCollectionId(@Param("id") Long id);
	
	@Query("select p from Product p inner join p.subCollections s inner join s.collection c where c.id = :id")
	List<Product> listProductsByCollectionId(@Param("id") Long id);
}
