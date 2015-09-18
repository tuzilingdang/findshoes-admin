package com.model;

/**
 * AbstractOnlineStore entity provides the base persistence definition of the
 * OnlineStore entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOnlineStore implements java.io.Serializable {

	// Fields

	private String onlineUrl;
	private String goodsId;
	private Double price;
	private Integer like;
	private String imgUrl;
	private String storeName;
	private String storeImg;
	private String fromEWeb;
	private Boolean defunct;
	private Boolean isVisited;

	// Constructors

	/** default constructor */
	public AbstractOnlineStore() {
	}

	/** minimal constructor */
	public AbstractOnlineStore(String onlineUrl, String goodsId, Double price,
			Boolean defunct, Boolean isVisited) {
		this.onlineUrl = onlineUrl;
		this.goodsId = goodsId;
		this.price = price;
		this.defunct = defunct;
		this.isVisited = isVisited;
	}

	/** full constructor */
	public AbstractOnlineStore(String onlineUrl, String goodsId, Double price,
			Integer like, String imgUrl, String storeName, String storeImg,
			String fromEWeb, Boolean defunct, Boolean isVisited) {
		this.onlineUrl = onlineUrl;
		this.goodsId = goodsId;
		this.price = price;
		this.like = like;
		this.imgUrl = imgUrl;
		this.storeName = storeName;
		this.storeImg = storeImg;
		this.fromEWeb = fromEWeb;
		this.defunct = defunct;
		this.isVisited = isVisited;
	}

	// Property accessors

	public String getOnlineUrl() {
		return this.onlineUrl;
	}

	public void setOnlineUrl(String onlineUrl) {
		this.onlineUrl = onlineUrl;
	}

	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getLike() {
		return this.like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreImg() {
		return this.storeImg;
	}

	public void setStoreImg(String storeImg) {
		this.storeImg = storeImg;
	}

	public String getFromEWeb() {
		return this.fromEWeb;
	}

	public void setFromEWeb(String fromEWeb) {
		this.fromEWeb = fromEWeb;
	}

	public Boolean getDefunct() {
		return this.defunct;
	}

	public void setDefunct(Boolean defunct) {
		this.defunct = defunct;
	}

	public Boolean getIsVisited() {
		return this.isVisited;
	}

	public void setIsVisited(Boolean isVisited) {
		this.isVisited = isVisited;
	}

}