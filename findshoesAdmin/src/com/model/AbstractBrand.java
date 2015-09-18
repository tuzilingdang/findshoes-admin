package com.model;

/**
 * AbstractBrand entity provides the base persistence definition of the Brand
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBrand implements java.io.Serializable {

	// Fields

	private BrandId id;
	private String brandBaseUrl;
	private Boolean isVisited;
	private Boolean online;

	// Constructors

	/** default constructor */
	public AbstractBrand() {
	}

	/** full constructor */
	public AbstractBrand(BrandId id, String brandBaseUrl, Boolean isVisited,
			Boolean online) {
		this.id = id;
		this.brandBaseUrl = brandBaseUrl;
		this.isVisited = isVisited;
		this.online = online;
	}

	// Property accessors

	public BrandId getId() {
		return this.id;
	}

	public void setId(BrandId id) {
		this.id = id;
	}

	public String getBrandBaseUrl() {
		return this.brandBaseUrl;
	}

	public void setBrandBaseUrl(String brandBaseUrl) {
		this.brandBaseUrl = brandBaseUrl;
	}

	public Boolean getIsVisited() {
		return this.isVisited;
	}

	public void setIsVisited(Boolean isVisited) {
		this.isVisited = isVisited;
	}

	public Boolean getOnline() {
		return this.online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

}