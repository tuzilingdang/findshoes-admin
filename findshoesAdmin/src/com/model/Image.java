package com.model;



/**
 * Image entity. @author MyEclipse Persistence Tools
 */
public class Image extends AbstractImage implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Image() {
    }

    
    /** full constructor */
    public Image(String imageUrl, String goodsId) {
        super(imageUrl, goodsId);        
    }
   
}
