//用于展示鞋子
package com.model;

import com.dao.*;

public class showShoes {
	private OnlineStore showOnlineStore;
	private String showBrandName;
	private String showGoodsid;
	private Shoes shoes;
	private String showFlag;
	
	public Shoes getShoes() {
		return shoes;
	}

	public void setShoes(Shoes shoes) {
		this.shoes =  shoes;
	}
	
	
	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag =  showFlag;
	}
	
	public OnlineStore getShowOnlineStore() {
		return showOnlineStore;
	}


	public void setShowOnlineStore(OnlineStore showOnlineStore) {
		this.showOnlineStore = showOnlineStore;
	}


	public String getShowGoodsid() {
		return showGoodsid;
	}

	public void setShowGoodsid(String showGoodsid) {
		this.showGoodsid = showGoodsid;
	}
	
	public String getShowBrandName() {
		return showBrandName;
	}

	public void setShowBrandName(String showBrandName) {
		this.showBrandName = showBrandName;
	}

	//传入主键，自动生成该类的其他属性【品牌名和鞋子名】
	public void generateClass(Shoes shShoe) {

		ShoesDAO shoesDao = new ShoesDAO();
		
/*        if (!shShoe.getBrand().isEmpty()){
        	showBrandName = shShoe.getBrand();
        }
        else
        	showBrandName="无";
        
		if(!shShoe.getGoodsId().isEmpty()){
			showGoodsid = shShoe.getGoodsId();
		}
        else
        	showGoodsid="无";*/
		if(shShoe.getFlag()==1)
			showFlag ="发布";
		if(shShoe.getFlag()==0)
			showFlag ="待审核";
		shoes = shShoe;
		/*System.out.println("showBrandName"+shoes.getGoodsId());*/	
	}
}
