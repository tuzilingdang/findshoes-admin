package com.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileUpload;

import com.dao.PageDAO;
import com.dao.UsersDAO;
import com.model.Users;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SearchUserAction extends ActionSupport {

	private List<Users> users;
    //�����ؼ���
	private String userId;
	private String nick;
	private String tel;
	private String email;
	private String address;
	private String vip;
	private String balance;
	private String regTime;
	// ��������
	private List<String> searchValue = new ArrayList<String>();
	// �����û����������ID
	private String id;
	private int pageNow = 1; // ��ʼ��Ϊ1,Ĭ�ϴӵ�һҳ��ʼ��ʾ
	private int pageSize = 2; // ÿҳ��ʾ5����¼
	private int pageNum; // ҳ������
	private int totalRows; // ��¼����
	private List list;
	private final int num = 5;

	private PageDAO pageDAO = new PageDAO();
	private UsersDAO userDAO = new UsersDAO();

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public List<String> getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(List<String> searchValue) {
		this.searchValue = searchValue;
	}

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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	// ���������û�
	public String findAll() {
		UsersDAO userDAO = new UsersDAO();
		users = userDAO.findAll();
		return SUCCESS;
	}

	// ����searchValuesָ�����û�
	public String searchUser() throws Exception{
		//������ֵ����searchValue��
		searchValue.clear();
		
		//String newId = new String(userId.getBytes("iso-8859-1"),"utf-8");
		//String newNick = new String(nick.getBytes("iso-8859-1"),"utf-8");
		//String newAddress = new String(address.getBytes("iso-8859-1"),"utf-8");
		
		searchValue.add(userId);
		searchValue.add(nick);
		searchValue.add(tel);
		searchValue.add(address);
		searchValue.add(balance);
		searchValue.add(email);
		searchValue.add(vip);
		searchValue.add(regTime);
		
		
        // ��¼������
		
		totalRows = pageDAO.SearchTotalRows(searchValue);
		// �ܹ��ֶ���ҳ
		if (totalRows % pageSize == 0) {
			pageNum = totalRows / pageSize;
		} else {
			pageNum = totalRows / pageSize + 1;
		}
		
		list = new ArrayList();
        
		if(pageNum < 6){
			for(int i=0;i<pageNum;i++){
				list.add(i);
			}
		}else{
			for(int i=0;i<num;i++){
				list.add(i);
			}
		}		

		if (pageNow <= 0) {
			pageNow = 1;
		}

		if (pageNow > pageNum) {
			pageNow = pageNum;
		}
		
		boolean flag = false;
		for (String str : searchValue) {
			if (!("".equals(str)))
				flag = true;
		}
		if (flag) {
			users = userDAO.searchUser(searchValue, pageSize, pageNow);
			return SUCCESS;
		} else {
			ActionContext.getContext().put("Tip", "��������������");
			return ERROR;
		}
	}

	// ����ָ��ID�����û�
	public String searchUserById() throws Exception {
		UsersDAO userDAO = new UsersDAO();
		// ����ת��
		//String userId = new String(id.getBytes("iso-8859-1"), "utf-8");
		users = userDAO.findUsersById(id);
		return SUCCESS;
	}

}
