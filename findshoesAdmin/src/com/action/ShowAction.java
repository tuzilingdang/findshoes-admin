package com.action;

import java.util.ArrayList;
import java.util.List;

import com.dao.PageDAO;
import com.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class ShowAction extends ActionSupport {
	private static final long serialVersionUID = -620994446528568157L;
	private List<Users> users;
	private int pageNow = 1; // 鍒濆鍖栦负1,榛樿浠庣涓�〉寮�鏄剧ず
	private int pageSize = 2; // 姣忛〉鏄剧ず5鏉¤褰�
	private int pageNum;      //椤甸潰鎬绘暟
	private int totalRows;    //璁板綍鎬绘暟
	private List list;
    private final int num = 5;
    
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
	
		// 璁板綍鎬绘潯鏁�
		
		totalRows = pageDAO.TotalRows();
		// 鎬诲叡鍒嗗灏戦〉
		if (totalRows % pageSize == 0) {
			pageNum = totalRows / pageSize;
		} else {
			pageNum = totalRows / pageSize + 1;
		}
		
		
		list = new ArrayList();
		
        if(pageNow == totalRows){
          	
        }
        
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
		users = pageDAO.queryPage(pageSize, pageNow);
		return SUCCESS;
	}
}
