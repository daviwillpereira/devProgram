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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((keywords == null) ? 0 : keywords.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subCollection == null) ? 0 : subCollection.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collection other = (Collection) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (keywords == null) {
			if (other.keywords != null)
				return false;
		} else if (!keywords.equals(other.keywords))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subCollection == null) {
			if (other.subCollection != null)
				return false;
		} else if (!subCollection.equals(other.subCollection))
			return false;
		return true;
	}
	
	

}
