package com.action;

import com.dao.ArticleDAO;
import com.dao.ShoesDAO;
import com.model.Article;
import com.model.Shoes;
import com.opensymphony.xwork2.ActionSupport;
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;  
import java.util.Date;  
import java.util.List;  
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;  
import com.opensymphony.xwork2.ActionSupport; 
/*import com.util.MD5Util;*/

public class deleSelectAction extends ActionSupport{
	private Article article;
	private Shoes shoes;
	
	public String articleSlt() throws Exception { 
		ArticleDAO articleDao = new ArticleDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		String array =  request.getParameter("array");
        System.out.println("array:"+array);
        String s[] = array.split(",");
        /*System.out.println(s.length);*/
        for(String stemp:s){ 
        	System.out.println(stemp);
        }
        if (s.length > 0){
        	for(int i = 0; i < s.length; i++){
        		int id = Integer.parseInt(s[i]);
        		article = articleDao.findById(id);
        		articleDao.delete(article);
        	}
        }
/*        Map<String, Object> dataMap = null;
        dataMap.put("success", true);
        HttpServletResponse res = ServletActionContext.getResponse(); 
        res.reset(); 
        res.setContentType("application/json;charset=utf-8"); 
        PrintWriter pw = res.getWriter(); 
        String str = JSONArray.fromObject("success").toString();  
        pw.print(dataMap); 
        pw.flush(); 
        pw.close(); */
  
        return SUCCESS; 
    }
	
    public String shoesSlt() throws Exception { 
		ShoesDAO shoesDao = new ShoesDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		String array =  request.getParameter("array");
        /*System.out.println(array);*/
        String s[] = array.split(",");
        /*System.out.println(s.length);*/
        for(String stemp:s){ 
        	System.out.println(stemp);
        }
        if (s.length > 0){
        	for(int i = 0; i < s.length; i++){
        		String id = s[i];
        		shoes = shoesDao.findById(id);
        		shoesDao.delete(shoes);
        	}
        }
        return SUCCESS;
    }
	
}