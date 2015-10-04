package com.action;

import com.dao.UsersDAO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteUserAction extends ActionSupport {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		
        UsersDAO userDAO = new UsersDAO();   
        userDAO.delete(id);
		return SUCCESS;
	}
}
