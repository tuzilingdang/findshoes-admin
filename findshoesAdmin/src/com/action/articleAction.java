package com.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.ArticleDAO;
import com.model.Article;
import com.model.showArticle;
import com.model.showShoes;
import com.opensymphony.xwork2.ActionSupport;

public class articleAction extends ActionSupport {
	
	private ArrayList<showArticle> showArticlesList;
	private showArticle showArticleBean;

	private List<showArticle> showArticleList;
	private Article article;
	
	public List<showArticle> getShowArticleList() {
		return showArticleList;
	}
	public void setShowArticleList(List<showArticle> showArticleList) {
		this.showArticleList = showArticleList;
	}
	
	public String goPageArticle() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String pstPageNo = request.getParameter("pstPageNo");
		int pageNo = Integer.valueOf(pstPageNo).intValue();
		String pageNum =  request.getParameter("pageNum");
		System.out.println("pageNum"+pageNum);
		List<Article> allArticleList = new ArrayList<Article>();
		
		showArticleList = new ArrayList<showArticle>();
		showArticle showarticle = new showArticle();
		showarticle.classifybyInitial();
		allArticleList = showarticle.getAllarticlelist();
		
		int num =( ((pageNo-1)*10+10) > allArticleList.size())?allArticleList.size():((pageNo-1)*10+10);
		for (int i = (pageNo-1)*10; i < num; i++ ){
			showArticle shArticle = new showArticle();
			shArticle.generateClass(allArticleList.get(i));
			showArticleList.add(shArticle) ;
		}
		
		System.out.println("showArticleList:"+showArticleList.size());
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("showArticleList",showArticleList);
		//session.setAttribute("pstPageNo",pstPageNo);
		session.setAttribute("totalRecords",allArticleList.size());	
		return SUCCESS;
	}	
	
	public String findArticle() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String getval = request.getParameter("getval");
		int id = Integer.valueOf(getval).intValue();
		
		article = new Article();
		ArticleDAO artDao = new ArticleDAO();
		article = artDao.findById(id);
		System.out.println("article"+article.getTitle());
		return SUCCESS;
	}



}