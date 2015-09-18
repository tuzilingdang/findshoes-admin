package com.model;

/**
 * AbstractBrandId entity provides the base persistence definition of the
 * BrandId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBrandId implements java.io.Serializable {

	// Fields

	private String brandName;
	private Short source;

	// Constructors

	/** default constructor */
	public AbstractBrandId() {
	}

	/** full constructor */
	public AbstractBrandId(String brandName, Short source) {
		this.brandName = brandName;
		this.source = source;
	}

	// Property accessors

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Short getSource() {
		return this.source;
	}

	public void setSource(Short source) {
		this.source = source;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractBrandId))
			return false;
		AbstractBrandId castOther = (AbstractBrandId) other;

		return ((this.getBrandName() == castOther.getBrandName()) || (this
				.getBrandName() != null && castOther.getBrandName() != null && this
				.getBrandName().equals(castOther.getBrandName())))
				&& ((this.getSource() == castOther.getSource()) || (this
						.getSource() != null && castOther.getSource() != null && this
						.getSource().equals(castOther.getSource())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBrandName() == null ? 0 : this.getBrandName().hashCode());
		result = 37 * result
				+ (getSource() == null ? 0 : this.getSource().hashCode());
		return result;
	}

}