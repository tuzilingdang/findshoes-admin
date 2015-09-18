package com.model;

/**
 * Brand entity. @author MyEclipse Persistence Tools
 */
public class Brand extends AbstractBrand implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Brand() {
	}

	/** full constructor */
	public Brand(BrandId id, String brandBaseUrl, Boolean isVisited,
			Boolean online) {
		super(id, brandBaseUrl, isVisited, online);
	}

}
