package com.action;

import java.util.ArrayList;
import java.util.List;

import com.dao.BrandDAO;
import com.dao.ShoesDAO;
import com.dao.UsersDAO;
import com.model.Brand;
import com.model.BrandId;
import com.model.Shoes;
import com.model.Users;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class brandAction extends ActionSupport {

	private String newbrand;
	/*private String brandid;*/
	private String prebrand;

	public String getNewbrand() {
		return newbrand;
	}

	public void setNewbrand(String newbrand) {
		this.newbrand = newbrand;
	}

/*	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}*/
	
	public String getPrebrand() {
		return prebrand;
	}

	public void setPrebrand(String prebrand) {
		this.prebrand = prebrand;
	}



	public String chBrand() throws Exception{
		BrandDAO brandDao = new BrandDAO();
		ShoesDAO shoesDao = new ShoesDAO();
		List<Shoes> shoesList = new ArrayList();
		Brand brand = new Brand();
		Shoes shoes = new Shoes();
		System.out.println(newbrand);
		//System.out.println(brandid);
		System.out.println(prebrand);
		
		//brand=new String(brand.getBytes("GB2312"),"8859_1"); 
		
		List<Brand> p_brand = brandDao.findByBrandname("brandName",prebrand);
		
		shoesList = shoesDao.findByBrand(prebrand);
		for(Shoes tmp:shoesList){
			tmp.setBrand(newbrand);
			shoesDao.save(tmp);			
		}	
				
		//System.out.println(p_brand.get(0).getBrandBaseUrl());
		for(Brand tmp:p_brand){
			tmp.getId().setBrandName(newbrand);
			brandDao.save(tmp);		
		}	
/*		p_brand.clear();
		
		p_brand = brandDao.findByProperty("brandName",prebrand);
		for(Brand tmp:p_brand){
			brandDao.delete(tmp);		
		}	
			
		brand.setBrandName(newbrand);
		brand.setBrandId(id);
		brandDao.save(brand);*/
		return SUCCESS;
	}

}
