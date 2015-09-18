package com.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.BrandDAO;
import com.dao.OnlineStoreDAO;
import com.dao.ShoesDAO;
import com.model.Brand;
import com.model.OnlineStore;
import com.model.Shoes;
import com.opensymphony.xwork2.ActionSupport;

public class shoesAction extends ActionSupport{
	private Shoes shoes;
	private Brand brand;
	private OnlineStore onlinestore;
	
	public OnlineStore getOnlineStore() {
		return onlinestore;
	}
	
	public void setOnlineStore(OnlineStore onlinestore) {
		this.onlinestore = onlinestore;
	}
	
	public Shoes getShoes() {
		return shoes;
	}
	
	public void setShoes(Shoes shoes) {
		this.shoes = shoes;
	}
	public Brand getBrand() {
		return brand;
	}
	
	public void setBrands(Brand brand) {
		this.brand = brand;
	}

	
	public String addShoe() throws Exception { 
		System.out.println("hi");
	/*	shoes =  new Shoes();*/
/*		System.out.println(shoes.getGoodsId());*/
		System.out.println(shoes.getBrand());
		System.out.println(shoes.getShowDate());
		/*System.out.println(onlinestore.getStoreName());*/
		ShoesDAO shoesDao =new ShoesDAO();
		OnlineStoreDAO onlinestoreDao = new OnlineStoreDAO();
		if(shoes.getGoodsId()==null){
			System.out.println("请输入货号！");
			return ERROR;
		}
/*		else if (onlinestore.getOnlineUrl()==null){
			System.out.println("请输入线上URL！");
			return ERROR;
		}*/
		else if (shoes.getShowDate()==null){
			System.out.println("请输入上市时间！");
			return ERROR;
		}
		else if (shoes.getBrand()==null){
			System.out.println("请输入品牌！");
			return ERROR;
		}
		else{
			shoes.setGoodsId(shoes.getGoodsId());
			shoes.setBrand(shoes.getBrand());
			shoes.setShowDate(shoes.getShowDate());
			if(shoes.getHotPoint()=="null"){
				shoes.setHotPoint("无");
			}
			else
				shoes.setHotPoint(shoes.getHotPoint());
			
			if(shoes.getHeelHeight()=="null"){
				shoes.setHeelHeight("无");
			}
			else
			shoes.setHeelHeight(shoes.getHeelHeight());		
			shoes.setFashion(shoes.getFashion());
			shoes.setColor(shoes.getColor());
			shoes.setOccasion(shoes.getOccasion());
			shoes.setStyle(shoes.getStyle());
			shoes.setToe(shoes.getToe());
			shoes.setSeason(shoes.getSeason());
			shoes.setLeather(shoes.getLeather());
			shoes.setSole(shoes.getSole());
			shoes.setUpperHeight(shoes.getUpperHeight());
			shoes.setBootHeight(shoes.getBootHeight());
			if(shoes.getBootMaterial()=="null"){
				shoes.setBootMaterial("无");
			}
			else
				shoes.setBootMaterial(shoes.getBootMaterial());	

			shoes.setPattern(shoes.getPattern());
			shoes.setInnerMaterial(shoes.getInnerMaterial());
			shoes.setCraft(shoes.getCraft());
			shoes.setUpperMaterial(shoes.getUpperMaterial());
	        shoes.setFlag(1);
	        shoes.setDefunct(true); //1 ==N
	        
/*	        onlinestore.setDefunct("N");
	        onlinestore.setGoodsId(shoes.getGoodsId());
	        onlinestore.setPrice(onlinestore.getPrice());
	        onlinestore.setOnlineUrl("");"onlinestore.getOnlineUrl()"
	        onlinestore.setStoreName(onlinestore.getStoreName());*/
	        
	        shoesDao.save(shoes);
	       /* onlinestoreDao.save(onlinestore);*/
	        return SUCCESS;
		}
	}
	
}