package com.action;

import com.dao.UsersDAO;
import com.model.Users;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private String admin;
	private String password;
	private String passcode;

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	@Override
	public String execute() throws Exception {
		
		if(passcode.equals((String)ActionContext.getContext().getSession().get("code"))){		
			UsersDAO userDAO = new UsersDAO();
			Users user = userDAO.check(admin, password);
			if (user == null) {
				return ERROR;
			}else{
				ActionContext.getContext().getSession().put("loginUser", user);
				return SUCCESS;
			}
		}
		return ERROR;		
	}

}
