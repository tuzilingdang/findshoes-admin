<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>拼图后台管理-后台管理</title>
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
    <div class="logo"><a style="font-size:large"  target="_blank"><strong>女鞋后台管理系统</strong></a></div>
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
                <li class="active"><a href="index.jsp" class="icon-home"> 开始</a>
               <!--  <li><a href="system.jsp" class="icon-home"> 系统</a> -->
               <!-- <li><a href="home.jsp" class="icon-file-text"> 首页</a>  -->
                <li><a href="shoes-search.jsp" class="icon-file-text"> 鞋子</a> </li>
               <!--  <li><a href="classify.jsp" class="icon-file-text"> 分类条件</a> </li> -->
                <li><a href="article.jsp" class="icon-cog"> 文章</a>          
                </li>            
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
        </div>
    </div>
</div>

<div class="admin" style="width:100%; position:relative; left:0em; top:-0.5em">
	<div class="line-big">
    	<div class="xm3">
        	<div class="panel border-back">
            	<div class="panel-body text-center">
                	<img src="images/face.jpg" width="120" class="radius-circle" /><br />            
                </div>
                <div class="panel-foot bg-back border-back"> 
                <span>
                	<%
		              if(user == null){
		             %>
		              <span>请在右上角点击登陆进入后台管理系统</span>
		            <%
		               }else{
		            %>
		            <span>您好，<%= user.getUserId() %>，欢迎您的光临。</span>
		            <%} %>
				</span>
				</div>
            </div>
            <br />
        	<div class="panel">
            	<div class="panel-head"><strong>站点统计</strong></div>
                <ul class="list-group">
                	<li><span class="float-right badge bg-red">88</span><span class="icon-user"></span> 会员</li>
                    <li><span class="float-right badge bg-main">828</span><span class="icon-file"></span> 鞋子</li>
                    <li><span class="float-right badge bg-main">828</span><span class="icon-shopping-cart"></span> 商店</li>
                    <li><span class="float-right badge bg-main">828</span><span class="icon-file-text"></span> 文章</li>
                    <li><span class="float-right badge bg-main">828</span><span class="icon-database"></span> 数据库</li>
                </ul>
            </div>
            <br />
        </div>


        </div>
    </div>
    <p class="text-right text-gray"></p>
    
    <div class="clearfix text-center">

        <br /><br />
    </div>
    <br />
</div>

<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>
</body>
</html>
