//����չʾЬ��
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

	//�����������Զ����ɸ�����������ԡ�Ʒ������Ь������
	public void generateClass(Shoes shShoe) {

		ShoesDAO shoesDao = new ShoesDAO();
		
/*        if (!shShoe.getBrand().isEmpty()){
        	showBrandName = shShoe.getBrand();
        }
        else
        	showBrandName="��";
        
		if(!shShoe.getGoodsId().isEmpty()){
			showGoodsid = shShoe.getGoodsId();
		}
        else
        	showGoodsid="��";*/
		if(shShoe.getFlag()==1)
			showFlag ="����";
		if(shShoe.getFlag()==0)
			showFlag ="�����";
		shoes = shShoe;
		/*System.out.println("showBrandName"+shoes.getGoodsId());*/	
	}
}
