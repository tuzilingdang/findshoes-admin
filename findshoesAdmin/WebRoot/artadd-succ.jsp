<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

    
</head>

<body>



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
               <!--  <li><a href="system.jsp" class="icon-home"> 系统</a> -->
                <!-- <li><a href="content.html" class="icon-file-text"> 首页</a> -->
                <li><a href="content.html" class="icon-file-text"> 鞋子</a> </li>
               <!--  <li><a href="content.html" class="icon-file-text"> 分类条件</a> </li> -->
                <li class="active"><a href="article.jsp" class="icon-cog"> 文章</a>
            		<ul><li class="active"><a href="article.jsp" target="#">文章列表</a></li><li  ><a href="#">添加</a></li><li><a href="#" >批量添加</a></li></ul>
                </li>                <li><a href="store-err.jsp" class="icon-user"> 商店</a></li>
                <li><a href="users-search.jsp" class="icon-file">用户</a></li>
            </ul>
        </div>
        <div class="admin-bread">
            <span>您好，admin，欢迎您的光临。</span>
            <ul class="bread">
                <li><a href="article.jsp" class="icon-home"> 文章列表</a></li>
                <li><a href="article-add.jsp">添加</a></li>
                <!-- <li>批量添加</li> -->
            </ul>
        </div>
    </div>
</div>

<div class="admin">

    <div class="panel admin-panel">
    	<div class="panel-head"><strong>文章列表</strong></div>
        <div class="padding border-bottom">
            <!-- <input type="button" class="button button-small checkall" name="checkall" checkfor="id" value="全选" /> -->
            <a  class="button button-small border-green" href="article-add.jsp" target="#">添加文章</a>
        </div>
        <div style="font-size:3rem;font-family:Microsoft YaHei; color:#FF6600;margin-left:15em;"><span>添加成功</span></div>
        <a  class="button button-small border-green" href="shoes-add.jsp" target="#">
        	<div style="font-size:1em;font-family:Microsoft YaHei; color:#666;margin-left:15em;"><span>返回</span></div>
        </a>
        <div class="formtitle"></div>
        <table class="table table-hover" style="table-layout:fixed">
        	<tr><th width="50">文章ID</th><th width="30">类型</th><th width="*">名称</th><th width="*">内容</th><th width="100">时间</th></tr>
            <tr><td><s:property value ="article.artical_id" /></td><td><s:property value ="article.type" /></td><td><s:property value ="article.title" />                                 
 				 </td><td><s:property value ="article.content" /></td><td>2015-2-6</td></tr>

        </table>

    </div>
    <br />
    
</div>

<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>
  

  
      
</body>   

</html>



