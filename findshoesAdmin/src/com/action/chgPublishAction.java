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

public class chgPublishAction extends ActionSupport{
	private Article article;
	private Shoes shoes;
	
	public String artpubChg() throws Exception { 
		ArticleDAO articleDao = new ArticleDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		String array =  request.getParameter("array");
		String val =  request.getParameter("val");
        /*System.out.println(array);*/
        String s[] = array.split(",");
        /*System.out.println(s.length);*/

        for(String stemp:s){ 
        	System.out.println(stemp);
        }
        if (s.length > 0){
        	for(int i = 0; i < s.length; i++){
        		int id = Integer.parseInt(s[i]);
        		article = articleDao.findById(id);
                
                if(val == "1"){
                	article.setStatus(1); // 1Îªpublish,2 Îª´ýÉóºË×´Ì¬
                }
                if(val == "2"){
                	article.setStatus(0); // 1Îªpublish,2 Îª´ýÉóºË×´Ì¬
                }
                else 
                	return ERROR;
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
	
    public String shoespubChg() throws Exception { 
		ShoesDAO shoesDao = new ShoesDAO();
		shoes= new Shoes();
		HttpServletRequest request = ServletActionContext.getRequest();
		String array =  request.getParameter("array");
		String val = request.getParameter("val");
        System.out.println(val);
        String s[] = array.split(",");
        /*System.out.println(s.length);*/
        for(String stemp:s){ 
        	System.out.println(stemp);
        }
      /*  */
        
        if (s.length > 0){
        	for(int i = 0; i < s.length; i++){
        		String id = s[i];
        		shoes = shoesDao.findById(id);
                if(val.equals("1")){
                	shoes.setFlag(1); // 1Îªpublish,2 Îª´ýÉóºË×´Ì¬
                }
                if(val.equals("2") ){
                	shoes.setFlag(0); // 1Îªpublish,2 Îª´ýÉóºË×´Ì¬
                }
/*                else 
                	return ERROR;*/
                shoesDao.save(shoes);
        	}
        }
//        HttpServletResponse res = ServletActionContext.getResponse(); 
//        res.reset(); 
//        res.setContentType("text/html;charset=utf-8"); 
//        PrintWriter pw = res.getWriter(); 
//        String string = "[{ y: \"55.11\", color : \"colors[0]\" },{ y: \"21.63\", color: \"colors[1]\" }]";
//        JSONArray jsonArr = JSONArray.fromObject(string);  
//        pw.print(string); 
//        pw.flush(); 
//        pw.close();
        return SUCCESS;
    }
	
}