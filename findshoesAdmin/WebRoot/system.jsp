<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.model.Users"%>
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
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    <script src="js/respond.js"></script>
    <script src="js/admin.js"></script>
    <link type="image/x-icon" href="http://www.pintuer.com/favicon.ico" rel="shortcut icon" />
    <link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon" />
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
                 <%
            	    Users user = (Users)ActionContext.getContext().getSession().get("loginUser");
            	    if(user==null){
            	   %>
                    <a class="button button-little bg-yellow" href="login.jsp">登录</a>
                   <%
                   }else{
                  %>
                    <a class="button button-little bg-yellow" href="userLogout">注销</a>
                  <%
                    }
                  %>
            </span>
            <ul class="nav nav-inline admin-nav">
                <li><a href="index.jsp" class="icon-home"> 开始</a>
                <li  class="active"><a href="system.jsp" class="icon-home"> 系统</a>
                	<ul><li><a href="system.jsp">系统设置</a></li></ul>
                </li>            
               <li><a href="home.jsp" class="icon-file-text"> 首页</a> 
                <li><a href="shoes-search.jsp" class="icon-file-text"> 鞋子</a> </li>
               <!--  <li><a href="classify.jsp" class="icon-file-text"> 分类条件</a> </li> -->
                <li><a href="article.jsp" class="icon-cog"> 文章</a>
            	
                <!-- <li><a href="store-err.jsp" class="icon-user"> 商店</a></li> -->
                <li><a href="users-search.jsp" class="icon-file">用户</a></li>
            </ul>
       </div>
        <div class="admin-bread">
             <%
              if(user == null){
             %>
              <span>您好，请先登录</span>
            <%
               }else{
            %>
            <span>您好，<%= user.getUserId() %>，欢迎您的光临。</span>
            <%
                }
            %>
            <ul class="bread">
               <li><a href="index.jsp" class="icon-home"> 开始</a></li>
               <li><a href="system.jsp">系统</a></li>
               <li>系统设置</li>
            </ul>
        </div>
    </div>
</div>

<div class="admin">

    <div class="tab">
      <div class="tab-head">
        <strong>系统设置</strong>
        <ul class="tab-nav">
          <li class="active"><a href="#tab-set">系统设置</a></li>
<!--           <li><a href="#tab-email">邮件设置</a></li>
          <li><a href="#tab-upload">上传设置</a></li>
          <li><a href="#tab-visit">访问限制</a></li> -->
        </ul>
      </div>
      <div class="tab-body">
        <br />
        <div class="tab-panel active" id="tab-set">
        	<form method="post" class="form-x" action="system.html">
				<div class="form-group">
                	<div class="label"><label>网站维护状态</label></div>
                	<div class="field">
                        <div class="button-group button-group-small radio">
                            <label class="button active"><input name="pintuer" value="yes" checked="checked" type="radio"><span class="icon icon-check"></span> 开启</label>
                            <label class="button"><input name="pintuer" value="no" type="radio"><span class="icon icon-times"></span> 关闭</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label for="readme">维护说明</label></div>
                    <div class="field">
                    	<textarea class="input" rows="5" cols="50" placeholder="请填写维护说明" data-validate="required:请填写维护说明"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label for="sitename">网站名称</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="sitename" name="sitename" size="50" placeholder="网站名称" data-validate="required:请填写你网站的名称" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label for="siteurl">网址</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="siteurl" name="siteurl" size="50" placeholder="请填写网址" data-validate="required:请填写网址" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label for="logo">标志</label></div>
                    <div class="field">
                    	<a class="button input-file" href="javascript:void(0);">+ 浏览文件<input size="100" type="file" name="logo" data-validate="required:请选择上传文件,regexp#.+.(jpg|jpeg|png|gif)$:只能上传jpg|gif|png格式文件" /></a>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label for="title">优化标题</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="title" name="title" size="50" placeholder="title标题内容，用于搜索引擎优化" data-validate="required:请填写优化标题，建议在80字以内。" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label for="keywords">关键词</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="keywords" name="keywords" size="50" placeholder="站点关键词，用于搜索引擎优化" data-validate="required:请填写站点关键词，建议在100字以内。" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="label"><label for="desc">描述</label></div>
                    <div class="field">
                    	<input type="text" class="input" id="desc" name="desc" size="50" placeholder="网站的描述，用于搜索引擎优化" data-validate="required:请填写网站的描述，建议在200字以内。" />
                    </div>
                </div>
                <div class="form-button"><button class="button bg-main" type="submit" disabled>提交</button></div>
            </form>
        </div>
<!--         <div class="tab-panel" id="tab-email">邮件设置</div>
        <div class="tab-panel" id="tab-upload">上传设置</div>
        <div class="tab-panel" id="tab-visit">访问限制</div> -->
      </div>
    </div>
</div>

<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>
</body>
</html>