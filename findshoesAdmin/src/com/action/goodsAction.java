package com.action;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.dao.BrandDAO;
import com.dao.ClassifyFindDao;
import com.dao.OnlineStoreDAO;
import com.dao.ShoesDAO;
import com.model.Brand;
import com.model.OnlineStore;
import com.model.PageModel;
import com.model.Shoes;
import com.model.ShowDetail;
import com.model.showShoes;
import com.opensymphony.xwork2.ActionSupport;

public class goodsAction extends ActionSupport{
	
	private String searchStr; //搜索的货号
	private List<Shoes> classifyShoesList;	//存放通过条件搜索找到的鞋子
	private List<Shoes> pageShoesList;	//存放通过条件搜索找到的鞋子
	private List<showShoes> showShoesList;
	private int pstPageNo;//当前页面号
	public PageModel pageModel;
	public String totalRecords;
	
	//chesbox内容
	private String shBrand;
	private String shGoodsid;
	private String shSeason;
	private String shPrice;
	private String shHotpoint;
	private String shShowdate;
	private String shHeight;
	/*private String shHeelHeight;*/
	private String shFashion;
	private String shOccasion;
	private String shStyle;
	private String shToe;
	private String shHeelStyle;
	private String shLeather;
	private String shSole;
	private String shUpperHeight;
	private String shInnerMaterial;
	private String shUpperMaterial;
	
	private showShoes shShow; 
	private ShowDetail showDetail; //显示鞋子详细信息
	
	public ShowDetail getShowDetail() {
		return showDetail;
	}

	public void setShowDetail(ShowDetail showDetail) {
		this.showDetail = showDetail;
	}
	
	public String getShGoodsid() {
		return shGoodsid;
	}

	public void setShGoodsid(String shGoodsid) {
		this.shGoodsid = shGoodsid;
	}
	
	public String getShBrand() {
		return shBrand;
	}

	public void setShBrand(String shBrand) {
		this.shBrand = shBrand;
	}
	
	public String getShHeight() {
		return shHeight;
	}

	public void setShHeight(String shHeight) {
		this.shHeight = shHeight;
	}
	
	public String getShShowdate() {
		return shShowdate;
	}

	public void setShShowdate(String shShowdate) {
		this.shShowdate = shShowdate;
	}
	
	public String getShHotpoint() {
		return shHotpoint;
	}
	
	public void setShHotpoint(String shHotpoint) {
		this.shHotpoint = shHotpoint;
	}

	public String getShFashion() {
		return shFashion;
	}

	public void setShFashion(String shFashion) {
		this.shFashion = shFashion;
	}

	public String getShOccasion() {
		return shOccasion;
	}
	
	public void setShOccasion(String shOccasion) {
		this.shOccasion = shOccasion;
	}

	public String getShStyle() {
		return shStyle;
	}
	
	public void setShStyle(String shStyle) {
		this.shStyle = shStyle;
	}
	
	public String getShToe() {
		return shToe;
	}
	
	public void setShToe(String shToe) {
		this.shToe = shToe;
	}
	
	public String getShLeather() {
		return shLeather;
	}
	
	public void setShLeather(String shLeather) {
		this.shLeather = shLeather;
	}
	
	public String getShUpperHeight() {
		return shUpperHeight;
	}
	
	public void setShUpperHeight(String shUpperHeight) {
		this.shUpperHeight = shUpperHeight;
	}
	
	public String getShSole() {
		return shSole;
	}
	
	public void setShSole(String shSole) {
		this.shSole = shSole;
	}
	
	public String getTotalRecords() {
		return totalRecords;
	}
	
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	
/*	public String getShSeason() {
		return shSeason;
	}
	
	public void setShSeason(String shSeason) {
		this.shSeason = shSeason;
	}*/

	
	public void setShInnerMaterial(String shInnerMaterial) {
		this.shInnerMaterial = shInnerMaterial;
	}

	public String getShInnerMaterial() {
		return shInnerMaterial;
	}
	
	public void setShUpperMaterial(String shUpperMaterial) {
		this.shUpperMaterial = shUpperMaterial;
	}

	public String getShUpperMaterial() {
		return shUpperMaterial;
	}

	public List<showShoes> getShowShoesList() {
		return showShoesList;
	}
	
	public void setShowShoesList(List<showShoes> showShoesList) {
		this.showShoesList = showShoesList;
	}
	
	public int getPstPageNo() {
		return pstPageNo;
	}

	public void setPstPageNo(int pstPageNo) {
		this.pstPageNo = pstPageNo;
	}
	
	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel= pageModel;
	}
	
	
	public String chgGoods() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String name =  request.getParameter("name");
		String val =  request.getParameter("val");
		String goodsId=  request.getParameter("goodsId");
		System.out.println("goodsId"+goodsId);
		ShoesDAO shoesdao = new ShoesDAO();
		Shoes shoe = new Shoes();
		shoe = shoesdao.findById(goodsId);
		/*String status =*/ shoesdao.change(name,val,shoe);
		/*if(status.equals("SUCCESS"))*/
		return SUCCESS;
/*		else
			return ERROR;*/
	}
	
	public String goPage() throws Exception{
		/*System.out.println(" goPage:pageModel.getPageSize(): "+pageShoesList.size());*/
		HttpServletRequest request = ServletActionContext.getRequest();
		String pstPageNo =  request.getParameter("pstPageNo");
		String search_data =  request.getParameter("search_data");
		
/*		System.out.println(pstPageNo);
		System.out.println("search_data:"+search_data);*/
		
		Map<String, String> classifyMap = new HashMap<String, String>(); //存放条件属性
		showShoesList = new ArrayList<showShoes>();
		
		classifyMap = strHandle(search_data);
		
		showShoesList = searchGoods(classifyMap,pstPageNo);
		System.out.println("showShoesList.size():"+showShoesList.size());

		/*String map = maptoString(classifyMap);*/
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("showShoesList",showShoesList);
		session.setAttribute("search_data",search_data);
		session.setAttribute("pstPageNo",pstPageNo);
		session.setAttribute("totalRecords",totalRecords);
	
		return SUCCESS;
	}
	
	public List<showShoes> searchGoods(Map<String, String> classifyMap, String pstPageNo) throws Exception {
		classifyShoesList = new ArrayList<Shoes>();
		pageShoesList =  new ArrayList<Shoes>();

/*		System.out.println("classifyMap "+classifyMap);
		System.out.println("pstPageNo "+pstPageNo);
		*/
		if (classifyMap.size() == 0){
			return null;
		}
		else{	
			/*pageModel= new PageModel();*/
			ClassifyFindDao classifydao = new ClassifyFindDao();
			classifyShoesList=classifydao.classifyFindShoes(classifyMap);		// 搜索条件查询
			totalRecords = String.valueOf(classifyShoesList.size());	
			System.out.println("classifyShoesList"+totalRecords);
			
			List<showShoes> showList = new ArrayList<showShoes>();
			pageShoesList = PageDisplay(classifyShoesList, pstPageNo);
			
			if(pageShoesList.size() != 0){
				for(Shoes tmp:pageShoesList){
					showShoes tmpShowShoes = new showShoes();//展示的临时实体类，showShoesList会将其add进去	
					tmpShowShoes.generateClass(tmp);
					showList.add(tmpShowShoes);
				}
				
				/*ServletActionContext.getRequest().getSession().setAttribute("showShoesList",showShoesList);*/
			
		/*		List<HttpSession> sessionList =(List)ServletActionContext.getRequest().getSession();*/
				
		/*		sessionList.setAttribute("showShoesList ",showShoesList); */
			/*	ServletActionContext.getRequest().getSession().setAttribute("showShoesList",showShoesList);*/								
/*				JSONArray showArry = new JSONArray();  
				showArry=JSONArray.fromObject(showShoesList);  				
				System.out.println("showArry == "+showArry);*/	
			}
			return showList;
		}		  
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
	

	
	private Map<String, String> strHandle(String map){
		Map<String, String> tmpMap = new HashMap<String, String>();
		map = map.replace("[","\"").replace("]","\"");
		System.out.println("quchu []de map"+map);
		String[] array = map.split(",");
		System.out.println("quchu []de map"+array.length);
		for(int i = 0; i < array.length; i++){
			String[] val = array[i].split("=");
			if(val.length == 2){
				System.out.println("val[0]"+val[0]);
				System.out.println("val[1]"+val[1]);
				tmpMap.put(val[0], val[1]);
			}
		}
		System.out.println("tmpMap"+tmpMap.size());
		return tmpMap;
	}
	
	public String goodsDetail() throws Exception {
		// action 中获得 request	
		HttpServletRequest request = ServletActionContext.getRequest();
		String gId=request.getParameter("gid"); //要显示详情的鞋子Id
		System.out.println("gid == "+ gId);
		
		showDetail = new ShowDetail(); //详情显示对象初始化
		//System.out.println("kitty");
		showDetail.generateClass(gId);
		return SUCCESS;
	}
}
