package com.action;

import com.model.Users;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1316238270059631496L;

	
	@Override
	public String execute() throws Exception {
	   Users user = (Users)ActionContext.getContext().getSession().get("loginUser");
	   if(user != null){
		   ActionContext.getContext().getSession().remove("loginUser");
	   }
	   return SUCCESS;
	}
}
