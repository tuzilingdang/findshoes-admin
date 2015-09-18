<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
</head>  
<body>  
    <s:form action="UploadTest" method="post" enctype="multipart/form-data">
        <tr>
   	 		<td>上传文件:<s:file name="file"></s:file></td>
   		 </tr>
    	<tr>
   		 <td>再次上传文件:<s:file name="file"></s:file></td>
   		 </tr>
   		 <tr>
   			 <td align="left"><s:submit name="submit" value="提交"></s:submit></td>
   		 </tr>
    </s:form>
</body>  
</html>  