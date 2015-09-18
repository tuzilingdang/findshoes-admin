package com.model;

import java.sql.Timestamp;

/**
 * Shoes entity. @author MyEclipse Persistence Tools
 */
public class Shoes extends AbstractShoes implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Shoes() {
	}

	/** minimal constructor */
	public Shoes(String goodsId, String brand, Timestamp modifiedTime,
			Integer flag) {
		super(goodsId, brand, modifiedTime, flag);
	}

	/** full constructor */
	public Shoes(String goodsId, String brand, String showDate, String season,
			Integer like, String occasion, String heelHeight, String toe,
			String heelStyle, String upperHeight, String bootHeight,
			String leather, String sole, String pattern, String craft,
			String bootMaterial, String upperMaterial, String color,
			String hotPoint, String fashion, String style,
			String innerMaterial, Boolean defunct, Timestamp modifiedTime,
			Integer flag) {
		super(goodsId, brand, showDate, season, like, occasion, heelHeight,
				toe, heelStyle, upperHeight, bootHeight, leather, sole,
				pattern, craft, bootMaterial, upperMaterial, color, hotPoint,
				fashion, style, innerMaterial, defunct, modifiedTime, flag);
	}

}
