package com.devProgram.javaCoreAssessment.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.devProgram.javaCoreAssessment.enums.CategoryEnum;

@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRO_ID", nullable = false)
	private Long id;

	@Column(name = "PRO_NAME", nullable = false)
	private String name;

	@Column(name = "PRO_PRICE", nullable = false)
	private BigDecimal price;

	@Column(name = "PRO_DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "PRO_QUANTITY", nullable = false)
	private Integer quantity;

	@Enumerated(EnumType.STRING)
	@Column(name = "PRO_CATEGORY", nullable = false)
	private CategoryEnum category;
	
	public Product() {
		
	}

	public Product(Long id, String name, BigDecimal price, String description, Integer quantity,
			CategoryEnum category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public CategoryEnum getCategory() {
		return category;
	}

	public void setCategory(CategoryEnum category) {
		this.category = category;
	}

}
