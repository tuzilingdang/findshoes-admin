package com.action;

import java.util.HashMap;
import java.util.Map;

import com.dao.UsersDAO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author Administrator
 * 检测用户注册时用户ID、邮箱、联系方式是否已存在
 *
 */
public class CheckUserAction extends ActionSupport {

	private static final long serialVersionUID = -7514845464031764470L;

	private String id;
	private String email;
	private String tel;
	private Map IdMap;
	private Map EmailMap;
	private Map TelMap;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map getIdMap() {
		return IdMap;
	}

	public void setIdMap(Map idMap) {
		IdMap = idMap;
	}

	public Map getEmailMap() {
		return EmailMap;
	}

	public void setEmailMap(Map emailMap) {
		EmailMap = emailMap;
	}

	public Map getTelMap() {
		return TelMap;
	}

	public void setTelMap(Map telMap) {
		TelMap = telMap;
	}

	// 判断用户ID是否唯一
	public String checkId(){
		UsersDAO userDAO = new UsersDAO();
		if (!userDAO.checkId(id)) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tip", "用户ID已用");
			this.setIdMap(map);
		}
		return SUCCESS;
	}

	// 判断用户邮箱是否唯一
	public String checkEmail() {
		UsersDAO userDAO = new UsersDAO();
		if (!userDAO.checkEmail(email)) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tip", "用户Email已用");
			this.setEmailMap(map);
		}
		return SUCCESS;
	}

	// 判断用户联系方式是否唯一
	public String checkTel() {
		UsersDAO userDAO = new UsersDAO();
		if (!userDAO.checkTel(tel)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tip", "用户联系方式已用");
			this.setTelMap(map);
		}
		return SUCCESS;
	}
}
