package com.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;


import com.dao.ArticleDAO;
import com.dao.BrandDAO;
import com.dao.ImageDAO;
import com.dao.OnlineStoreDAO;
import com.dao.ShoesDAO;
import com.dao.UsersDAO;

import com.model.Article;
import com.model.Brand;
import com.model.Image;
import com.model.PageModel;
import com.model.Shoes;
import com.model.showShoes;
import com.opensymphony.xwork2.ActionSupport;


public class ajaxAction extends ActionSupport{
	
	private List<Image> imageList;
	private boolean loadSuccess; 
	//閿熸枻鎷烽敓鑺傚瓨鍌ㄩ敓鏂ゆ嫹閿熺潾鐚存嫹閿熺Ц钘忕殑璇ф嫹閿熸枻鎷烽敓鏂ゆ嫹閿燂拷
	private boolean result;
	
	private List<String> imgUrlList;
	private List<showShoes> showShoesList;
	public  List<Article> articleList;
	
	private String test;
	//閿熸枻鎷烽敓鏂ゆ嫹娉ㄩ敓鏂ゆ嫹閿熸枻鎷烽敓鐭紮鎷烽敓鏂ゆ嫹閿熻鍑ゆ嫹閿熸枻鎷烽敓锟�	private boolean userIdExsited;
	private int pstPageNo;//閿熸枻鎷峰墠椤甸敓鏂ゆ嫹閿燂拷
	public PageModel pageModel;
	public String totalRecords;
	private List<Shoes> pageShoesList;	//閿熸枻鎷烽敓閰殿煉鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹涓氶敓鏂ゆ嫹閿熷彨顒婃嫹閿燂拷
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}
	
	public List<String> getImgUrlList() {
		return imgUrlList;
	}

	public void setImgUrlList(List<String> imgUrlList) {
		this.imgUrlList = imgUrlList;
	}
	
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public boolean getLoadSuccess() {
		return loadSuccess;
	}

	public void setLoadSuccess(boolean loadSuccess) {
		this.loadSuccess = loadSuccess;
	}

	public List<Image> getImageList() {
		return imageList;
	}

	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}
	
	public List<showShoes> getShowShoesList() {
		return showShoesList;
	}
	
	public void setShowShoesList(List<showShoes> showShoesList) {
		this.showShoesList = showShoesList;
	}
	
	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel= pageModel;
	}
	
	public int getPstPageNo() {
		return pstPageNo;
	}

	public void setPstPageNo(int pstPageNo) {
		this.pstPageNo = pstPageNo;
	}
	
	public String getTotalRecords() {
		return totalRecords;
	}
	
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String brandList() throws Exception{
		//閿熸枻鎷峰彇鍝侀敓鏂ゆ嫹
		pageShoesList =  new ArrayList<Shoes>();
		showShoesList = new ArrayList<showShoes>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String brand = request.getParameter("brand");
		String pstPageNo =  request.getParameter("pstPageNo");

		//brand = java.net.URLDecoder.decode(brand,"UTF-8"); 
//     	brand=new String(brand.getBytes("GB2312"),"8859_1");
	//	brand  = new String(brand.getBytes("UTF-8"),"ISO-8859-1"); 
//		brand = java.net.URLDecoder.decode(brand, "UTF-8");
//		brand = URLDecoder.decode(brand, "UTF-8"); 
//		brand = new String(brand.getBytes("utf-8"),"gbk");
		System.out.println("brand:"+brand);
		System.out.println("pstPageNo:"+pstPageNo);
		OnlineStoreDAO onlineStoreDAO = new OnlineStoreDAO();
		ShoesDAO shoesdao = new ShoesDAO();
		
		List<Shoes> shoesList;
		shoesList = shoesdao.findByBrand(brand);
		
		pageShoesList = PageDisplay(shoesList, pstPageNo);
		totalRecords = String.valueOf(shoesList.size());
		//System.out.println(showShoesImgsList.get(0).getImg_url());

		if(pageShoesList.size() != 0){
			for(Shoes tmp:pageShoesList){
				showShoes tmpShowShoes = new showShoes();//灞曠ず閿熸枻鎷烽敓鏂ゆ嫹鏃跺疄閿熸枻鎷烽敓娲侊紝showShoesList閿熺粨灏嗛敓鏂ゆ嫹add閿熸枻鎷峰幓	
				tmpShowShoes.generateClass(tmp);
				showShoesList.add(tmpShowShoes);
			}
		}
		
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("showShoesList",showShoesList);
		session.setAttribute("brand",brand);
		session.setAttribute("pstPageNo",pstPageNo);
		session.setAttribute("totalRecords",totalRecords);
		return SUCCESS;
	}

	private List<Shoes> PageDisplay(List<Shoes> totalList, String pstPageNo){
		int pageNo = Integer.parseInt(pstPageNo); 
		List<Shoes> list = new ArrayList<Shoes>();
		PageModel pageModel= new PageModel();
		
		System.out.println("totalList"+totalList.size());
		
		pageModel.setTotalRecords(totalList.size());
		pageModel.setPageSize(10);
		pageModel.setPageNo(pageNo);
		pageModel.setList(totalList);
		System.out.println("PageModel"+pageModel.getPageSize());

		return pageModel.getCurrentPageList();		
	}
	
	public  String delPub() throws Exception{
		System.out.println("array");
		HttpServletRequest request = ServletActionContext.getRequest();
		String array = request.getParameter("array");
		articleList = new ArrayList();
		System.out.println("delpub:"+array);
		
		Article article = new Article();
		ArticleDAO articledao = new ArticleDAO();
		String[] strArray = array.split(",");
		for (int i = 0; i < strArray.length; i++ ){
			article = articledao.findById(  Integer.valueOf(strArray[i]).intValue());
			article.setDefunct("N");
			article.setImgPath(null);
			articledao.save(article);
			System.out.println("save");
		}	
		
/*	       HttpServletResponse res = ServletActionContext.getResponse(); 
	        res.reset(); 
	        res.setContentType("application/json;charset=utf-8"); 
	        PrintWriter pw = res.getWriter(); 
	        pw.print(articleList);
//	        String str = JSONArray.fromObject("success").toString();  
	        pw.flush(); 
	        pw.close(); */
/*		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("articleList",articleList);*/

		return SUCCESS;
	}
}
