package com.action;

import com.dao.UsersDAO;
import com.opensymphony.xwork2.ActionSupport;

/*
 * 根据用户ID
 * 进行批量删除
 */

public class DelSelectUserAction extends ActionSupport {

	// 获取用户ID
	private String[] userIds;

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	@Override
	public String execute() throws Exception {
		
		if(userIds != null){
			UsersDAO userDAO = new UsersDAO();
			userDAO.delSelect(userIds);
			return SUCCESS;
		}
		return INPUT;
	}

}
