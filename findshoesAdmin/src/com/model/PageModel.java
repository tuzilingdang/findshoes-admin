package com.model;

import java.util.ArrayList;
import java.util.List;

import com.dao.*;

public class PageModel<E> {   
    private List<E>  list;  //结果集      
    private int totalRecords;     //查询记录数   
    private int pageSize;         //每页多少条数据  
    private int pageNo;	    //第几页 
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    
    /* 总页数 */ 
    public int getTotalPages() { 
        return (totalRecords + pageSize - 1) / pageSize; 
    } 
       
    /* 取得首页*/ 
    public int getTopPageNo() { 
        return 1; 
    } 
    
    /* 上一页 */ 
    public int getPreviousPageNo() { 
        if (pageNo <= 1) { 
            return 1; 
        } 
        return pageNo - 1; 
    } 
       
    /* 下一页 */ 
    public int getNextPageNo() { 
        if (pageNo >= getBottomPageNo()) { 
            return getBottomPageNo(); 
        } 
        return pageNo + 1;   
    } 
    
    public List<E>  getCurrentPageList(){
    	List<E>  pageList = new ArrayList();
		int start = ( pageNo-1)*pageSize+1;
		int end = pageNo*pageSize;
		if(end > this.getTotalRecords()){
			for (int i = start; i <= this.getTotalRecords(); i++){
				
				pageList.add(this.getList().get(i-1));
			}
		}
		else{
			for (int i = start; i <= end; i++){
				pageList.add(this.getList().get(i-1));
			}
		}
		return pageList;
    }
    
    public List<E>  getPreviousPageList(){
    	List<E>  pageList = new ArrayList();
    	pageNo = pageNo -1 ;
    	if (pageNo<1)
    		pageNo = 1;
    	if (pageNo >= pageSize)
    		hasNextPage = false;
    	else
    		hasNextPage = true;
    	pageList = this.getCurrentPageList();
		return pageList;
    }
    
    public List<E>  getNextPageList(){
    	List<E>  pageList = new ArrayList();
    	pageNo = pageNo +1 ;

    	if (pageNo >= pageSize){
    		hasNextPage = false;
    		pageNo = pageSize;
    	}
    	else
    		hasNextPage = true;
    	
    	if((pageNo-1)>0){
    	     hasPreviousPage=true;
    	}
    	else{
    	     hasPreviousPage=false;
    	        }

    	pageList = this.getCurrentPageList();
		return pageList;
    }
    
       
    /* 取得尾页*/ 
    public int getBottomPageNo() { 
        return getTotalPages(); 
    } 
       
    public List<E>  getList() { 
    	
        return list; 
    } 
   
    public void setList(List<E>  list) { 
        this.list = list; 
    } 
   
    public int getTotalRecords() { 
        return totalRecords; 
    } 
   
    public void setTotalRecords(int totalRecords) { 
        this.totalRecords = totalRecords; 
    } 
   
    public int getPageSize() { 
        return pageSize; 
    } 
   
    public void setPageSize(int pageSize) { 
        this.pageSize = pageSize; 
    } 
   
    public int getPageNo() { 
        return pageNo; 
    } 
   
    public void setPageNo(int pageNo) { 
        this.pageNo = pageNo; 
    } 
}