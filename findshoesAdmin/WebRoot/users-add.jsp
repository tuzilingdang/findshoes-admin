<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@page import="org.springframework.web.context.request.RequestScope"%>
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
    
    <style>
		.td-url{/*text-overflow:ellipsis;*/white-space:nowrap;overflow: hidden}
	</style>
	
	<SCRIPT type="text/javascript" src="./js/checkUserId.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="./js/checkUserTel.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="./js/checkUserEmail.js"></SCRIPT>
	
<SCRIPT type="text/javascript">
  function check(){
    var userID = document.getElementById("id").value.trim();
    var userNick = document.getElementById("nick").value.trim();
    var userPass = document.getElementById("pass").value.trim(); 
    var userTel = document.getElementById("tel").value.trim(); 
    var userEmail = document.getElementById("email").value.trim();
    //手机号码格式
    var telReg = /^(((13[0-9]{1})|159|153)+\d{8})$/;
    //邮箱格式
    var emailReg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
    
    if(userID == "" || userNick == "" || userPass == ""){
       alert("用户ID、昵称或密码不能为空！");
       return false;
    }  
    
    if(userID.length < 5)
    {
      alert("用户ID长度不能小于5");
      document.getElementById("id").focus();
      return false;
    }
    
    if(userPass.length < 8){
      alert("用户密码长度不能小于8");
      document.getElementById("pass").focus();
      return false;
    }
    
    if(!telReg.test(userTel)){
      alert("请输入有效的手机号码");
      document.getElementById("tel").focus();
      return false;
    }
    
    if(!emailReg.test(userEmail)){
      alert("请输入有效的邮箱");
      document.getElementById("email").focus();
      return false;
    }
    
    return true;
  }
  
  function checkForm(){
    var msg = document.getElementById("hide").value;
    var flag = false;
    if(msg != 'null'){
      alert(msg);
      flag = true;
    }
    return flag;
  }
  
</SCRIPT>
    
</head>

<body onload="checkForm();">

<input type="hidden" value="<%= request.getAttribute("Tip") %>" id="hide">
<div class="lefter">
    <div class="logo"><a style="font-size:large" href="http://www.pintuer.com" target="_blank"><strong>女鞋后台管理系统</strong></a></div>
</div>
<div class="righter nav-navicon" id="admin-nav">
    <div class="mainer">
        <div class="admin-navbar">
            <span class="float-right">
            	<a class="button button-little bg-main" >前台首页</a>
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
               <!--  <li><a href="system.jsp" class="icon-home"> 系统</a> -->
               <li><a href="home.jsp" class="icon-file-text"> 首页</a> 
                <li><a href="shoes-search.jsp" class="icon-file-text"> 鞋子</a> </li>
               <!--  <li><a href="classify.jsp" class="icon-file-text"> 分类条件</a> </li> -->
                <li><a href="article.jsp" class="icon-cog"> 文章</a>
                </li>            
                <!-- <li><a href="store-err.jsp" class="icon-user"> 商店</a></li> -->
                <li class="active"><a href="users-search.jsp" class="icon-file">用户</a>
	           		<ul>
		           		<li><a href="users-search.jsp">用户搜索</a></li>
		           		<li class="active"><a href="users-add.jsp">添加</a></li>
	           		</ul>
                </li>
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
               <li><a href="users-search.jsp">用户</a></li>
               <li>添加</li>
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
        	<form action="addUser" method="post" class="form-x" enctype="multipart/form-data">
   			 <div style="width:100%">
				<div class="form-group1" style="float:left;;width:16em">
                	<p>用户ID <input style="float:right" type="text" name="user.userId" id="id" onblur="checkId(this.value);"/></p>	    
 				    <p>密码<input style="float:right" type="password" name="user.password" id="pass"/></p>                                                                   
                    <p>邮箱 <input id="email" style="float:right" type="text" name="user.email" onblur="checkEmail(this.value);"/></p> 			    				    				 
                </div>
                
                <div class="form-group2" style="float:left;position:relative;width:16em;margin-left:2em;">  
                    <p>昵称<input style="float:right" type="text" name="user.nick" id="nick"/></p>             	
 				    <p>联系方式<input id="tel" style="float:right" type="text" name="user.telephone" onblur="checkTel(this.value);"/></p>	
 				    <p>头像<input style="float:right; margin-left:5em; width: 12em;" type="file" name="upload" /></p>                   			      
                </div>
                
                <div class="form-group3" style="float:left;position:relative;width:16em;margin-left:2em;">
                	<p>住址 <input style="float:right" type="text" name="user.address" /></p>
                	<p>余额 <input style="float:right" type="text" name="user.balance" value="0.0"/></p>  
                	           	                                                    
                </div>     
                
                <div class="form-group4" style="float:left;position:relative;width:16em;margin-left:2em;">                 	
                	<p>用户等级
                	    <select style="float:right;width:11.2em" name="user.vip">
                           <option value="0" >管理员</option> 
                           <option value="1" selected="selected">注册用户</option>
                           <option value="2">vip用户</option>                             
						</select> 
					</p>
                	<p>defunct
                	   <select style="float:right;width:11.2em" name="user.defunct">
                           <option value="N" selected="selected">N</option> 
                           <option value="Y">Y</option>                             
					   </select> 
                	</p>                                                    
                </div>                       
              </div>
              
             <input style="float:left;margin-top:4em;width:8em;height:2.5em;background:#F30;color:#FFF;font-size:1em;border:none;border-radius:5px" 
                      type="submit" value="添加" />
             </form>
           </div>
        
         </div>
      </div>
   </div>
  </div>      

        
       
  

<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>

</body>
    

</html>
    