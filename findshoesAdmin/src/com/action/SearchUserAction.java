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
    //搜索关键字
	private String userId;
	private String nick;
	private String tel;
	private String email;
	private String address;
	private String vip;
	private String balance;
	private String regTime;
	// 搜索条件
	private List<String> searchValue = new ArrayList<String>();
	// 更新用户传入的搜索ID
	private String id;
	private int pageNow = 1; // 初始化为1,默认从第一页开始显示
	private int pageSize = 2; // 每页显示5条记录
	private int pageNum; // 页面总数
	private int totalRows; // 记录总数
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

	// 查找所有用户
	public String findAll() {
		UsersDAO userDAO = new UsersDAO();
		users = userDAO.findAll();
		return SUCCESS;
	}

	// 搜索searchValues指定的用户
	public String searchUser() throws Exception{
		//将搜索值存入searchValue中
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
		
		
        // 记录总条数
		
		totalRows = pageDAO.SearchTotalRows(searchValue);
		// 总共分多少页
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
			ActionContext.getContext().put("Tip", "请输入搜索条件");
			return ERROR;
		}
	}

	// 根据指定ID搜索用户
	public String searchUserById() throws Exception {
		UsersDAO userDAO = new UsersDAO();
		// 编码转换
		//String userId = new String(id.getBytes("iso-8859-1"), "utf-8");
		users = userDAO.findUsersById(id);
		return SUCCESS;
	}

}
