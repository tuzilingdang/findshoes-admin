package com.action;

import com.dao.UsersDAO;
import com.model.Users;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4779967918490165269L;
	private String userId;
	private String nick;
	private String telephone;
	private String email;
	private String address;
	private String balance;
	private String vip;
	private String defunct;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public String getDefunct() {
		return defunct;
	}

	public void setDefunct(String defunct) {
		this.defunct = defunct;
	}

	@Override
	public String execute() throws Exception {

		UsersDAO userDAO = new UsersDAO();
		Users newUser = userDAO.findById(userId);
		newUser.setNick(nick);
		newUser.setTelephone(telephone);
		newUser.setEmail(email);
		newUser.setAddress(address);
		newUser.setVip(vip);
		newUser.setDefunct(defunct);
		newUser.setBalance(Double.parseDouble(balance));
		userDAO.updateUserInfo(newUser);
		
		ActionContext.getContext().put("Tip", "修改成功");
		return SUCCESS;
	}

}
