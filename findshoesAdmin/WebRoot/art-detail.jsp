<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>女鞋后台管理-后台管理</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <link href="css/Pager.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="js/jquery.pager.js" type="text/javascript"></script>
    <!-- <script src="js/pintuer.js"></script> -->
<!--     <script src="js/respond.js"></script>
    <script src="js/admin.js"></script> -->
    <link type="image/x-icon" href="http://www.pintuer.com/favicon.ico" rel="shortcut icon" />
    <link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon" />
    
    <style>
		.td-url{/*text-overflow:ellipsis;*/white-space:nowrap;overflow: hidden}
		.table tr th{font-size:.8em}
		.table td{font-size:.6em}
		.button-group{
			display:inline-block;border:solid 1px #ddd;border-radius:4px;color:#333;padding:8px 12px;
			line-height:12px;display:block;transition:all 1s cubic-bezier(0.175, 0.885, 0.32, 1) 0s; background-color:#fff}		
	</style>

       <script language="javascript" type="text/javascript">		
            function  showval(){  
			    var args = new Array();
				var thisURL = document.URL;    
 				var  getval =thisURL.split('?')[1]; 
				alert(getval); 		
				
			$.ajax({
				url:"findArticle.action",
				type:"post",
				dataType:"json",
				data:{"getval":getval},
				success:function(data){
					if(data!=null)
					 	alert("ajax success");  
					alert(data.article.); 
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					        alert(XMLHttpRequest.status);
	                        alert(XMLHttpRequest.readyState);
	                        alert(textStatus);
				}
			}); 				
  			}  
   	    </script>         

    
</head>

<body onload="showval()">
<div class="lefter">
    <div class="logo"><a style="font-size:large" href="http://www.pintuer.com" target="_blank"><strong>女鞋后台管理系统</strong></a></div>
</div>
<div class="righter nav-navicon" id="admin-nav">
    <div class="mainer">
         <div class="admin-navbar">
            <span class="float-right">
            	<!-- <a class="button button-little bg-main" >前台首页</a> -->
                <a class="button button-little bg-yellow" href="login.html">注销登录</a>
            </span>
            <ul class="nav nav-inline admin-nav">
                <li><a href="index.jsp" class="icon-home"> 开始</a>
                <!-- <li><a href="system.jsp" class="icon-home"> 系统</a> -->
              <!--  <li><a href="home.jsp" class="icon-file-text"> 首页</a>  -->
                <li><a href="shoes-search.jsp" class="icon-file-text"> 鞋子</a> </li>
               <!--  <li><a href="classify.jsp" class="icon-file-text"> 分类条件</a> </li> -->
                <li class="active"><a href="article.jsp" class="icon-cog"> 文章</a>
            		<ul><li  class="active"><a href="article.jsp">文章列表</a></li><li><a href="article-add.jsp">添加</a></li></ul>
                </li>            
                <!-- <li><a href="store-err.jsp" class="icon-user"> 商店</a></li> -->
                <li><a href="users-search.jsp" class="icon-file">用户</a></li>
            </ul>
        </div>
        <div class="admin-bread">
            <span>您好，欢迎您的光临。</span>
            <ul class="bread">
                <li><a href="index.jsp" class="icon-home"> 开始</a></li>
                <li><a href="article.jsp">文章</a></li>
                <li>文章详情</li>
            </ul>
        </div>
    </div>
</div>

<div class="admin">
        <input type="hidden" id = "hidden-id"/>
		<div style="position:relative;padding-top:2em">
             <table class="table table-hover" style="position:relative;table-layout:fixed;padding-top:1.5em">
             			<tr style="width:995px;"> <th style="font-size:.9em" colspan="14"><strong>搜索结果</strong></th></tr>
                        <tr style="width:995px;height:100px;boder:none">
                            <th style="font-size:.9em" colspan="14"><h2 style="padding-left:20em;padding-bottom:45px;font-weight:300">对不起，没有您搜索的结果。</h2></th>
                        </tr>
                         <tr style="width:995px;">
                            <th colspan="14"><a href="shoes-search.jsp" target="#" style="padding-left:45em;font-size:.9em;color:#FF6600;font-weight:200">
                            	返回</a></th>
                        </tr>
                      
                    </table>
<!--                     <ul>
                    	<li></li>
                    	<li></li>
                    </ul> -->
      	</div>
      	
      	

		</div>
	
<!-- <h1 id="result">Click the pager below.</h1>
<div id="pager" ></div> -->
<input type=hidden name=contextPath value=<s:property value="shoes.goodsId" /> />
<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>


 
</body>
    

</html>
    