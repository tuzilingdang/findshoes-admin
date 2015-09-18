package com.model;

/**
 * OnlineStore entity. @author MyEclipse Persistence Tools
 */
public class OnlineStore extends AbstractOnlineStore implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OnlineStore() {
	}

	/** minimal constructor */
	public OnlineStore(String onlineUrl, String goodsId, Double price,
			Boolean defunct, Boolean isVisited) {
		super(onlineUrl, goodsId, price, defunct, isVisited);
	}

	/** full constructor */
	public OnlineStore(String onlineUrl, String goodsId, Double price,
			Integer like, String imgUrl, String storeName, String storeImg,
			String fromEWeb, Boolean defunct, Boolean isVisited) {
		super(onlineUrl, goodsId, price, like, imgUrl, storeName, storeImg,
				fromEWeb, defunct, isVisited);
	}

}
