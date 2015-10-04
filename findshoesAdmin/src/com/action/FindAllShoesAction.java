package com.action;

import java.util.List;

import com.dao.ShoesDAO;
import com.opensymphony.xwork2.ActionSupport;

public class FindAllShoesAction extends ActionSupport {

	private List shoes;

	public List getShoes() {
		return shoes;
	}

	public void setShoes(List shoes) {
		this.shoes = shoes;
	}
	
	@Override
	public String execute() throws Exception{
		
		ShoesDAO shoesDAO = new ShoesDAO();
		shoes = shoesDAO.findAll();
		return SUCCESS;
	}
	
}
