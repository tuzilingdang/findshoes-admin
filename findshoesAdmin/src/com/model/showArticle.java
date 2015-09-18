package com.model;

import com.dao.*;
import com.util.ChineseInitial;
import java.util.ArrayList;
import java.util.List;

public class showArticle {
	
	private List<Article> allArticlelist;
	private Article article;
/*	private List<Brand> abrandList;
	private List<Brand> bbrandList;
	private List<Brand> cbrandList;
	private List<Brand> dbrandList;
	private List<Brand> ebrandList;
	private List<Brand> fbrandList;
	private List<Brand> gbrandList;
	private List<Brand> hbrandList;
	private List<Brand> ibrandList;*/
	
/*	public List<Brand> getAbrandlist() {
		return abrandList;
	}

	public void setAbrandList(List<Brand> abrandList) {
		this.abrandList = abrandList;
	}
	
	public List<Brand> getBbrandlist() {
		return bbrandList;
	}

	public void setBbrandList(List<Brand> bbrandList) {
		this.bbrandList = bbrandList;
	}*/
	
	public Article getArticle() {
		return article;
	}

	public void setShoes(Article article) {
		this.article =article;
	}
	
	public List<Article> getAllarticlelist() {
		return allArticlelist;
	}

	public void setAllarticleList(List<Article> allArticlelist) {
		this.allArticlelist = allArticlelist;
	}
	
	public void classifybyInitial(){
		allArticlelist = new ArrayList();
		ArticleDAO brandDao = new ArticleDAO();
		allArticlelist = brandDao.findAll();	
	}
	
	public void generateClass(Article shArticle) {
		article = shArticle;
	}
}