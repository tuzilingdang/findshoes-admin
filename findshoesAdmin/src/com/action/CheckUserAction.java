package com.action;

import java.util.HashMap;
import java.util.Map;

import com.dao.UsersDAO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author Administrator
 * ����û�ע��ʱ�û�ID�����䡢��ϵ��ʽ�Ƿ��Ѵ���
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

	// �ж��û�ID�Ƿ�Ψһ
	public String checkId(){
		UsersDAO userDAO = new UsersDAO();
		if (!userDAO.checkId(id)) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tip", "�û�ID����");
			this.setIdMap(map);
		}
		return SUCCESS;
	}

	// �ж��û������Ƿ�Ψһ
	public String checkEmail() {
		UsersDAO userDAO = new UsersDAO();
		if (!userDAO.checkEmail(email)) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tip", "�û�Email����");
			this.setEmailMap(map);
		}
		return SUCCESS;
	}

	// �ж��û���ϵ��ʽ�Ƿ�Ψһ
	public String checkTel() {
		UsersDAO userDAO = new UsersDAO();
		if (!userDAO.checkTel(tel)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tip", "�û���ϵ��ʽ����");
			this.setTelMap(map);
		}
		return SUCCESS;
	}
}
