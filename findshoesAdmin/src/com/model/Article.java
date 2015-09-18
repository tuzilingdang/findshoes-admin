package com.model;

import java.sql.Timestamp;


/**
 * Article entity. @author MyEclipse Persistence Tools
 */
public class Article extends AbstractArticle implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Article() {
    }

	/** minimal constructor */
    public Article(Integer articalId, String title, Integer status, Integer topLevel, String type, String author, Timestamp date, String content, String defunct) {
        super(articalId, title, status, topLevel, type, author, date, content, defunct);        
    }
    
    /** full constructor */
    public Article(Integer articalId, String title, String lead, String keywords, Integer status, String imgUrl, Integer topLevel, String type, String author, Timestamp date, String content, String height, String weight, String bwh, String shoeUrl, String defunct, String footType, Integer footLength, Integer footWidth, String imgPath) {
        super(articalId, title, lead, keywords, status, imgUrl, topLevel, type, author, date, content, height, weight, bwh, shoeUrl, defunct, footType, footLength, footWidth, imgPath);        
    }
   
}
