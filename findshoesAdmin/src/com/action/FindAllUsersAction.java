package com.action;

import java.util.List;

import com.dao.PageDAO;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class FindAllUsersAction extends ActionSupport {
	private static final long serialVersionUID = -620994446528568157L;
	private List<Users> users;
	private int pageNow = 1; // 初始化为1,默认从第一页开始显示
	private int pageSize = 5; // 每页显示5条记录
	private int pageNum;      //页面总数
	private int totalRows;    //记录总数
    
	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	private PageDAO pageDAO = new PageDAO();

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

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

	@Override
	public String execute() throws Exception {
	
		// 记录总条数	
		totalRows = pageDAO.TotalRows();
		// 总共分多少页
		if (totalRows % pageSize == 0) {
			pageNum = totalRows / pageSize;
		} else {
			pageNum = totalRows / pageSize + 1;
		}
  
		//当前页的临界处理，若小于0则置于0，大于最大页则置最大页数
		if (pageNow <= 0) {
			pageNow = 1;
		}

		if (pageNow > pageNum) {
			pageNow = pageNum;
		}
		
		users = pageDAO.queryPage(pageSize, pageNow);
		return SUCCESS;
	}
}
