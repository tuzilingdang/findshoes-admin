//����չʾЬ������
package com.model;

import java.awt.Image;
import java.util.List;

import com.dao.*;

public class ShowDetail {
	private Shoes shoe; //ָʾ����˫Ь
	private String brandName;
	private String goodId;
	private String heelHeight;
	private String color;
	private String occasionName;
	private String styleName;
	private String toeName;
	private String leatherName;   //Ƥ������
	private String soleName;   //Ь�ײ��ʡ��������ޡ�
	private String heelStyle;   //Ь�ײ��ʡ��������ޡ�
	private List<OnlineStore> dangdangList;  //��ŵ��������������ͬһ���ŵġ����꡿
	private List<Image> imageList; 
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getGoodId() {
		return goodId;
	}

	public void setGoodId(String goodid) {
		this.goodId = goodId;
	}
	
	public String getHeelHeight() {
		return heelHeight;
	}

	public void setHeelHeight(String heelHeight) {
		this.heelHeight = heelHeight;
	}
	
	public String getHeelStyle() {
		return heelStyle;
	}

	public void setHeelStyle(String heelStyle) {
		this.heelStyle = heelStyle;
	}
	
	
	public String getToeName() {
		return toeName;
	}

	public void setToeName(String toeName) {
		this.toeName = toeName;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getOccasionName() {
		return occasionName;
	}

	public void setOccasionName(String occasionName) {
		this.occasionName = occasionName;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getLeatherName() {
		return leatherName;
	}

	public void setLeatherName(String leatherName) {
		this.leatherName = leatherName;
	}

	public String getSoleName() {
		return soleName;
	}

	public void setSoleName(String soleName) {
		this.soleName = soleName;
	}

	public Shoes getShoe() {
		return shoe;
	}
	
	public void setShoe(Shoes shoe) {
		this.shoe = shoe;
	}
	
	public List<OnlineStore> getDangdangList() {
		return dangdangList;
	}
	
	public void setDangdangList(List<OnlineStore> dangdangList) {
		this.dangdangList = dangdangList;
	}
	
	public List<Image> getImageList() {
		return imageList;
	}
	
	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}
	
	
	//�����������Զ�����䡿�ø������������
	public void generateClass(String goodsId) {
		
		ShoesDAO shoesDao = new ShoesDAO();
	/*	BrandDAO brandDao = new BrandDAO();*/
/*		OccasionDAO occasionDao = new OccasionDAO();
		shoes_styleDAO shoesStyleDao = new shoes_styleDAO();
		styleDAO styleDao = new styleDAO();
		LeatherDAO leatherDao = new LeatherDAO();
		soleDAO soleDao = new soleDAO();*/
		OnlineStoreDAO olsDao = new OnlineStoreDAO();
		ImageDAO imgDao = new ImageDAO();
		
		shoe = shoesDao.findById(goodsId);
		//����һ��Ь���ڲ�����
		goodId = goodsId;
		
		if(shoe.getBrand() == null)
			brandName = "����";
		else
			brandName = shoe.getBrand();
		
		if(shoe.getHeelHeight() == null)
			heelHeight = "����";
		else
			heelHeight = shoe.getHeelHeight();
		System.out.println("shoe.getHeelHeight()");
		System.out.println(heelHeight);
		
		if(shoe.getColor() == null)
			color = "����";
		else
			color = shoe.getColor();
		
		if(shoe.getOccasion() == null)
			occasionName = "����";
		else
			occasionName = shoe.getOccasion();
		
		//��styleName
		if(shoe.getStyle() == null)
			styleName = "����";
		else
			styleName = shoe.getStyle();
		
		if(shoe.getToe() == null)
			toeName = "����";
		else
			toeName = shoe.getToe();
		
		
		//��leatherName
		if(shoe.getLeather() == null)
			leatherName = "����";
		else
			leatherName = shoe.getLeather();
		
		//��sole
		if(shoe.getSole() == null)
			soleName = "����";
		else
			soleName = shoe.getSole();
		
		if(shoe.getHeelStyle() == null)
			heelStyle="����";
		else
			heelStyle = shoe.getHeelStyle();

		/*List<ShoesStyle> shoesStyleList = shoesStyleDao.findByGoodsId(goodsId);
		if(shoesStyleList.size() == 0)
			styleName = "����";
		else{
			for(int i=0;i<shoesStyleList.size();i++){
				//�ϳ�styleName
				if(i == 0)
					styleName = styleDao.findById(shoesStyleList.get(i).getStyleId()).getStyleName();
				else
					styleName += "|";
				styleName += styleDao.findById(shoesStyleList.get(i).getStyleId()).getStyleName();
			}
		}
		
		if(shoe.getLeatherId() == null)
			leatherName = "����";
		else
			leatherName = leatherDao.findById(shoe.getLeatherId()).getLeatherName();
		
		if(shoe.getSoleId() == null)
			soleName = "����";
		else
			soleName = soleDao.findById(shoe.getSoleId()).getSoleName();
		*/
		
		dangdangList = olsDao.findByGoodsId(goodsId);
		imageList = imgDao.findByGoodsId(goodsId);
		System.out.println("litty");
		System.out.println(imageList.size());
	}
	
}
