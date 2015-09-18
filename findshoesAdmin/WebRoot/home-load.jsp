<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" import="java.sql.*,java.io.*,java.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>女鞋后台管理-后台管理</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    <script src="js/respond.js"></script>
    <script src="js/admin.js"></script>
    <link type="image/x-icon" href="http://www.pintuer.com/favicon.ico" rel="shortcut icon" />
    <link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon" />
    
<!--     <link href="css/HttpUploader.css" type="text/css" rel="Stylesheet"/> -->
    <script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="js/HttpUploader.js" charset="utf-8"></script>
    
    <style>
		.td-url{/*text-overflow:ellipsis;*/white-space:nowrap;overflow: hidden}
	</style>

	 <script language="javascript" type="text/javascript">		
	
	   function load(){
	   alert("hh");
	   		var thisURL = document.URL;  
	   		var  getval =thisURL.split('?')[1];   	
	   		alert(getval);
	   }
				 
	 </script> 
    
</head>

<body onload="load()">
<s:form data-ajax="false" action=" homeImgupload.action" method="post"  enctype="multipart/form-data" data-transition="slide" theme="simple"> 
<!--  	<div class="form-group">            
        <s:file name ="myFile" label ="图片1"/> 
        <s:file name ="myFile" label ="图片2"/>  
        <s:file name ="myFile" label ="图片3"/>            
	</div>  -->
	<table class="table table-hover" style="position:relative;padding-top:1.5em;table-layout:fixed;overflow:hidden;width:100%;font-size:1em">
		<tr > <th style="font-size:.9em;padding-bottom:.8em;" colspan="6"><strong>选择结果</strong></th></tr>
        <tr >
            <!-- <th width=20 style="">选择</th> -->    <th width=20 >序号</th>   <th width=90>文章ID</th>      <th style="width:90">标题</th>
             <th width=120>首页图片</th>    ><th width=220 style = "overflow:hidden">首页发布状态</th>
           <th width=220 style = "overflow:hidden">操作</th>
        </tr>
      <s:iterator value="#session.articleList" status="index">
		  <tr style="text-overflow: ellipsis;white-space: nowrap;overflow:scroll;font-size:.8em">
		    <%--   <td width=20 style="overflow:hidden;"><input type="checkbox" id="goodsId"  value="<s:property value="article.articalId" />"  class ="deleck"/></td>  --%> <td width=20><s:property value="#index.count"/></td> 
		       <td ><s:property value="articalId" /></td> 
		      <!--  <td style="overflow:hidden;"><s:property value="type" /></td>  --> 
		       <td style="overflow:hidden;"><s:property value="title" /></td>
		     <!--  <td style="overflow:hidden;"><s:property value="lead" /></td>   -->
		      <td style="overflow:hidden;"> <s:property value="imgPath" /></td>
		     <!--  <td style="overflow:hidden;"><s:property value="status" /></td> -->
		      <td style = "overflow:hidden"><s:property value="defunct" /></td> 
		      <td style="overflow:hidden;"> 
		      <div style="background-color:transparent;diaplay:inline;">
		      	<div style="background-color:transparent; float:left;" >  <!-- onclick="homeImg(<s:property value="articalId" />)" -->
		      	<input type="hidden"   name="articalId" value="<s:property value="articalId" />" /><!--<s:property value="articalId" />  --></div>
		          <!-- <input type="button" class="button border-yellow button-little" onclick="deleSelect(1)" value="删除"> -->
		      </td>
		     <td style = "overflow:hidden"><s:file name="myFile" label="图片1"></s:file>
		     </td>
		  </tr>
	  </s:iterator>
     </table>
     <input type="submit"   class="button border-blue button-little" value ="发布" /></div>
      </s:form>
</body>   

<script type="text/javascript"> 

	function homeImg(id){
		alert(id);
		$.ajax({
				url:"homeImgupload.action",
				type:"GET",
				dataType:"json",
				data:{"id":id},
				success:function(data){
					 alert("select Change ajax success"); 
					/* alert(data.articleList[0].title); */
		
				   /*  window.self.location = "search-succ.jsp";   */
				   /*  window.location.reload();   */
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					        alert(XMLHttpRequest.status);
	                        alert(XMLHttpRequest.readyState);
	                        alert(textStatus);
				}
			}); 
	}
</script>

</html>



