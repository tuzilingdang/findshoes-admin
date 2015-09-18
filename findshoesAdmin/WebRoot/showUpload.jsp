<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>                                       
  	<%@ taglib prefix="s" uri="/struts-tags" %>                                                            
  	<%                                                                                                     
  	String path = request.getContextPath();                                                                
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
 	%>                                                                                                       
                                                                                                           
  	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">                                        
  	<html>                                                                                                 
  	  <head>                                                                                               
  	    <base href="<%=basePath%>">                                                                        
 	                                                                                                         
 	    <title>Show Image</title>                                                                            
 	                                                                                                         
 		<meta http-equiv="pragma" content="no-cache">                                                          
 		<meta http-equiv="cache-control" content="no-cache">                                                   
 		<meta http-equiv="expires" content="0">                                                                
 		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">                                      
 		<meta http-equiv="description" content="This is my page">                                              
 		<!--                                                                                                   
 		<link rel="stylesheet" type="text/css" href="styles.css" mce_href="styles.css">                                              
 		-->                                                                                                    
 	                                                                                                         
 	  </head>                                                                                                
 	                                                                                                         
 	  <body>                                                                                                 
 	  <s:iterator value="imageFileName" status="length">                                                     
 	                                                                                                         
 			<div>                                                                                                
 				<img id="upload-img" style="padding: 3px; border: solid 1px #ccc; text-align: center;width:400px;height:500px" src='UploadImages/<s:property value ="imageFileName" /> ' />                                  
 				<br />                                                                                             
 				<s:property value="caption" />                                                                     
 			</div>                                                                                               
 			</s:iterator>                                                                                        
 			<s:property value ="caption" />                                                                      
 			                                                                                                     
 		</body>                                                                                                
 	</html>                                                                                                  
