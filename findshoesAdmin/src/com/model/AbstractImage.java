package com.model;



/**
 * AbstractImage entity provides the base persistence definition of the Image entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractImage  implements java.io.Serializable {


    // Fields    

     private String imageUrl;
     private String goodsId;


    // Constructors

    /** default constructor */
    public AbstractImage() {
    }

    
    /** full constructor */
    public AbstractImage(String imageUrl, String goodsId) {
        this.imageUrl = imageUrl;
        this.goodsId = goodsId;
    }

   
    // Property accessors

    public String getImageUrl() {
        return this.imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGoodsId() {
        return this.goodsId;
    }
    
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
   








}