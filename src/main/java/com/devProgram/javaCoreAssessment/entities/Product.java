package com.devProgram.javaCoreAssessment.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.devProgram.javaCoreAssessment.enums.VariantEnum;

@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRO_ID", nullable = false)
	private Long id;
	
	@Size(max = 1)
    @ManyToMany
	@JoinTable(name = "PRODUCT_SUB_COLLECTION", 
	   joinColumns = { @JoinColumn(name = "PRO_ID") }, 
	   inverseJoinColumns = { @JoinColumn(name = "SUB_COL_ID") })
    private List<SubCollection> subCollections;

	@Column(name = "PRO_NAME", nullable = false)
	private String name;
	
	@Size(max = 5)
	@Lob
	@ElementCollection
	@Column(name = "PRO_IMAGES", nullable = true)
	private List<byte[]> images;

	@Column(name = "PRO_PRICE", nullable = false)
	private BigDecimal price;

	@Column(name = "PRO_DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "PRO_QUANTITY", nullable = false)
	private Integer quantity;

	@Enumerated(EnumType.STRING)
	@Column(name = "PRO_VARIANT", nullable = true)
	private VariantEnum variant;

	public Product() {
		
	}

	public Product(Long id, List<SubCollection> subCollections, String name, List<byte[]> images, BigDecimal price, String description, Integer quantity,
			VariantEnum variant) {
		this.id = id;
		this.subCollections = subCollections;
		this.name = name;
		this.images = images;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.variant = variant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<SubCollection> getSubCollections() {
		return subCollections;
	}

	public void setSubCollections(List<SubCollection> subCollections) {
		this.subCollections = subCollections;
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

	public VariantEnum getVariant() {
		return variant;
	}

	public void setVariant(VariantEnum category) {
		this.variant = category;
	}

}
