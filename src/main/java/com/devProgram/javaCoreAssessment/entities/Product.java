package com.devProgram.javaCoreAssessment.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.devProgram.javaCoreAssessment.enums.CategoryEnum;

@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRO_ID", nullable = false)
	private Long id;
	
    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private List<SubCollection> subCollection;

	@Column(name = "PRO_NAME", nullable = false)
	private String name;
	
	@Lob
	@Column(name = "PRO_IMAGES", nullable = true)
	private List<byte[]> images;

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

	public Product(Long id, List<SubCollection> subCollection, String name, List<byte[]> images, BigDecimal price, String description, Integer quantity,
			CategoryEnum category) {
		this.id = id;
		this.subCollection = subCollection;
		this.name = name;
		this.images = images;
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
	
	public List<SubCollection> getSubCollection() {
		return subCollection;
	}

	public void setSubCollection(List<SubCollection> subCollection) {
		this.subCollection = subCollection;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<byte[]> getImages() {
		return images;
	}

	public void setImages(List<byte[]> images) {
		this.images = images;
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
