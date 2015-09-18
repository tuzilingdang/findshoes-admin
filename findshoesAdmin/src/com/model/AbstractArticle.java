package com.model;

import java.sql.Timestamp;


/**
 * AbstractArticle entity provides the base persistence definition of the Article entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractArticle  implements java.io.Serializable {


    // Fields    

     private Integer articalId;
     private String title;
     private String lead;
     private String keywords;
     private Integer status;
     private String imgUrl;
     private Integer topLevel;
     private String type;
     private String author;
     private Timestamp date;
     private String content;
     private String height;
     private String weight;
     private String bwh;
     private String shoeUrl;
     private String defunct;
     private String footType;
     private Integer footLength;
     private Integer footWidth;
     private String imgPath;


    // Constructors

    /** default constructor */
    public AbstractArticle() {
    }

	/** minimal constructor */
    public AbstractArticle(Integer articalId, String title, Integer status, Integer topLevel, String type, String author, Timestamp date, String content, String defunct) {
        this.articalId = articalId;
        this.title = title;
        this.status = status;
        this.topLevel = topLevel;
        this.type = type;
        this.author = author;
        this.date = date;
        this.content = content;
        this.defunct = defunct;
    }
    
    /** full constructor */
    public AbstractArticle(Integer articalId, String title, String lead, String keywords, Integer status, String imgUrl, Integer topLevel, String type, String author, Timestamp date, String content, String height, String weight, String bwh, String shoeUrl, String defunct, String footType, Integer footLength, Integer footWidth, String imgPath) {
        this.articalId = articalId;
        this.title = title;
        this.lead = lead;
        this.keywords = keywords;
        this.status = status;
        this.imgUrl = imgUrl;
        this.topLevel = topLevel;
        this.type = type;
        this.author = author;
        this.date = date;
        this.content = content;
        this.height = height;
        this.weight = weight;
        this.bwh = bwh;
        this.shoeUrl = shoeUrl;
        this.defunct = defunct;
        this.footType = footType;
        this.footLength = footLength;
        this.footWidth = footWidth;
        this.imgPath = imgPath;
    }

   
    // Property accessors

    public Integer getArticalId() {
        return this.articalId;
    }
    
    public void setArticalId(Integer articalId) {
        this.articalId = articalId;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getLead() {
        return this.lead;
    }
    
    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getKeywords() {
        return this.keywords;
    }
    
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getTopLevel() {
        return this.topLevel;
    }
    
    public void setTopLevel(Integer topLevel) {
        this.topLevel = topLevel;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getDate() {
        return this.date;
    }
    
    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getHeight() {
        return this.height;
    }
    
    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return this.weight;
    }
    
    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBwh() {
        return this.bwh;
    }
    
    public void setBwh(String bwh) {
        this.bwh = bwh;
    }

    public String getShoeUrl() {
        return this.shoeUrl;
    }
    
    public void setShoeUrl(String shoeUrl) {
        this.shoeUrl = shoeUrl;
    }

    public String getDefunct() {
        return this.defunct;
    }
    
    public void setDefunct(String defunct) {
        this.defunct = defunct;
    }

    public String getFootType() {
        return this.footType;
    }
    
    public void setFootType(String footType) {
        this.footType = footType;
    }

    public Integer getFootLength() {
        return this.footLength;
    }
    
    public void setFootLength(Integer footLength) {
        this.footLength = footLength;
    }

    public Integer getFootWidth() {
        return this.footWidth;
    }
    
    public void setFootWidth(Integer footWidth) {
        this.footWidth = footWidth;
    }

    public String getImgPath() {
        return this.imgPath;
    }
    
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
   








}