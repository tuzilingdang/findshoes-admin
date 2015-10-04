<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.dao.UsersDAO"%>
<%@page import="com.model.Users"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
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
    
    <style>
		.td-url{/*text-overflow:ellipsis;*/white-space:nowrap;overflow: hidden}
	</style>
    
</head>
<SCRIPT type="text/javascript">
      function check(){
        var msg = document.getElementById("hide").value;
        var flag = false;
       if(msg != 'null'){
         alert(msg);
         flag = true;
       }
        return flag;
      }
    </SCRIPT>
<body onload="check();">
<input type="hidden" value="<%= request.getAttribute("Tip") %>" id="hide">
<div class="lefter">
    <div class="logo"><a style="font-size:large" href="http://www.pintuer.com" target="_blank"><strong>女鞋后台管理系统</strong></a></div>
</div>
<div class="righter nav-navicon" id="admin-nav">
    <div class="mainer">
        <div class="admin-navbar">
            <span class="float-right">
            	<a class="button button-little bg-main" href="#">前台首页</a>
                <!-- 根据是否已登录显示登录或注册 -->
            	<%
            	  Users users = (Users)ActionContext.getContext().getSession().get("loginUser");
            	  if(users==null){
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
                <li><a href="index.jsp" class="icon-home"> 开始</a></li>
               <!--  <li><a href="home.jsp" class="icon-file-text"> 首页</a></li> -->
                <li ><a href="shoes-search.jsp" class="icon-cog"> 鞋子</a>
                </li>
                <li><a href="article.jsp" class="icon-file-text"> 文章</a></li>
                <li class="active"><a href="users-search.jsp" class="icon-file">用户</a>
                	<ul>
            		  <li class="active"><a href="users-search.jsp">用户搜索</a></li>
            		  <li><a href="users-add.jsp">添加</a></li>
            		</ul>
                </li>
            </ul>
        </div>
        <div class="admin-bread">
            <%
              if(users == null){
             %>
            <span>您好，请先登录</span>
            <%
              }else{
             %>
             <span>您好，<%= users.getUserId() %>，欢迎您的光临。</span>
             <%
              }
              %>
				<ul class="bread">
					<li><a href="home.jsp" class="icon-home"> 开始</a></li>
					<li><a href="users-search.jsp">用户</a></li>
					<li>用户搜索</li>
				</ul>
        </div>
    </div>
</div>

<div class="admin">
 	 <strong>添加</strong>
    <div class="tab">
      <div class="tab-head"> 
   	   <div class="tab-body">
        <br />
         <div class="tab-panel active" id="tab-set">
         
            <%
               String userId = request.getParameter("id");       
               UsersDAO dao = new UsersDAO();
               Users user = dao.findById(userId);
            %>
         
        	<form method="post" class="form-x" action="updateUser?id=<%= user.getUserId() %>" enctype="multipart/form-data" >
   			 <div style="width:100%">
				<div class="form-group1" style="float:left;;width:17em">
                	<p>用户ID <input style="float:right" type="text" name="userId" value= "<%= user.getUserId() %>" 
                	    readonly="readonly"/></p>	   
                	<p>昵称<input style="float:right" type="text" name="nick" value= "<%= user.getNick() %>"/></p>                                                                                                     
                </div>
                
                <div class="form-group2" style="float:left;position:relative;width:17em;margin-left:3em;">                                 	
 				    <p>联系方式<input style="float:right" type="text" name="telephone" value= "<%= user.getTelephone() %>" /></p>				    
 				    <p>邮箱 <input style="float:right" type="text" name="email" value= "<%= user.getEmail() %>"/></p> 				    				    				    
                </div>
                
                <div class="form-group3" style="float:left;position:relative;width:17em;margin-left:3em;">
                	<p>住址 <input style="float:right" type="text" name="address" value= "<%= user.getAddress() %>"/></p>
                	<p>余额 <input style="float:right" type="text" name="balance" value= "<%= user.getBalance() %>"/></p>  
                	            	                                                    
                </div>     
                
                <div class="form-group4" style="float:left;position:relative;width:17em;margin-left:3em;">      
                    <p>用户等级<input style="float:right" type="text" name="vip" value= "<%= user.getVip() %>"/>               
                    </p>                        	
                	<p>defunct<input style="float:right" type="text" name="defunct" value= "<%= user.getDefunct() %>"/></p>                                                    
                </div>                       
              </div>
              
             <input style="float:left;margin-top:4em;width:8em;height:2.5em;background:#F30;color:#FFF;font-size:1em;border:none;border-radius:5px" 
                      type="submit" value="更新" onclick="{if(confirm('确认修改?')){return true;}return false;}"/>
             </form>
           </div>
        
         </div>
      </div>
   </div>
  </div>      

        
       
  

<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>

</body>
    

</html>
    