package com.devProgram.javaCoreAssessment.entities;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "COLLECTION")
public class Collection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COL_ID", nullable = false)
	private Long id;

	@Column(name = "COL_NAME", nullable = false)
	private String name;

	@Column(name = "COL_DESCRIPTION", nullable = false)
	private String description;

	@Size(max = 6)
	@ElementCollection
	@CollectionTable(name = "COL_KEYWORDS",
					 joinColumns = @JoinColumn(name = "COL_ID"))
	@Column(name = "KEYWORDS", nullable = true)
	private List<String> keywords;

	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy = "collection")
	private List<SubCollection> subCollection;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<SubCollection> getSubCollection() {
		return subCollection;
	}

	public void setSubCollection(List<SubCollection> subCollection) {
		this.subCollection = subCollection;
	}

}
