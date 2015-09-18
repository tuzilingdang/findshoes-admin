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
    
    <style>
		.td-url{/*text-overflow:ellipsis;*/white-space:nowrap;overflow: hidden}
		.table tr th{font-size:.8em}
		.table td{font-size:.6em}
	</style>
</head>

   <SCRIPT type="text/javascript">
      function check(){
        var msg = document.getElementById("hide").value;
        var flag = true;
       if(msg != 'null'){
         alert(msg);
         flag = false;
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
		           		<li class="active"><a href="users-search.jsp">用户搜索</a></li>
		           		<li ><a href="users-add.jsp">添加</a></li>
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
               <li>用户搜索</li>
            </ul>
        </div>
    </div>
</div>

<div class="admin">
 	 <strong>用户搜索</strong>
    <div class="tab">
      <div class="tab-head"> 
   	   <div class="tab-body">
        <br />
         <div class="tab-panel active" id="tab-set">
        	<form method="post" class="form-x" action="searchUser">
   			 <div style="width:100%">
				<div class="form-group1" style="float:left;;width:17em">
                	<p>用户ID <input style="float:right" type="text" name="userId" value=""/></p>	
                	<p>昵称<input style="float:right" type="text" name="nick" value="" /></p>    
                	 				                                                                                                            
                </div>
                
                <div class="form-group2" style="float:left;position:relative;width:17em;margin-left:3em;">               	
 				    <p>联系方式<input style="float:right" type="text" name="tel" value=""/></p>
 				    <p>住址 <input style="float:right" type="text" name="address" value=""/></p> 				      				    				    				    
                </div>
                
                <div class="form-group3" style="float:left;position:relative;width:17em;margin-left:3em;">
                	<p>余额 <input style="float:right" type="text" name="balance" value=""/></p>
                	<p>邮箱 <input style="float:right" type="text" name="email" value="" /></p>                                                  
                </div>      
                
                <div class="form-group4" style="float:left;position:relative;width:17em;margin-left:3em;">
                   <p>用户等级<input style="float:right" type="text" name="vip" /></p>
                   <p>注册时间 <input style="float:right" type="text" name="regTime" /></p>
                </div>                                           
                
			 </div>
             <input type="submit" value="搜索" />                  
            </form>
            <form action="showAction" method="post" id="showForm">
              <input type="submit" value="所有用户">
            </form>
          </div>       
         </div>
      </div>
   </div>
        
		    <form action="delSelectUser" method="post" id="delForm">
             <table class="table table-hover" style="position:relative;table-layout:fixed;padding-top:1.5em">
             			<tr style="width:995px;"> <th style="font-size:.9em" colspan="14"><strong>搜索结果</strong></th></tr>
                        <tr style="width:995px;">
                            <th width="5">选择</th>    
                            <th width="10">序号</th>   
                            <th width="50">用户号</th>     
                            <th width="70">昵称</th>       
                            <th width="80">电话</th> 
                            <th width="150">邮箱</th> 
                            <th width="150" >地址</th>   
                            <th width="60" >余额</th>  
                            <th width="150">登陆时间</th> 
                            <th width="60">注册时间</th>  
                            <th width="30">用户等级</th>
                            <th width="40">Defunct</th>
                            <th width="120">操作</th>
                        </tr>                                      
                        <s:iterator value="users" status="st" id="s">
                         <tr style="text-overflow: ellipsis;white-space: nowrap;width:995px;overflow:scroll">
                            <td><input type="checkbox" name="userIds" value="${s.userId}" /></td>  
                            <td><s:property value="#st.getIndex()+1"/></td>
                            <td><s:property value="userId" /></td>                         
                            <td><s:property value="nick" /></td>  
                            <td><s:property value="telephone" /></td>
                            <td  class="td-url" ><s:property value="email" /></td>  
                            <td><s:property value="address" /></td>  
                            <td><s:property value="balance" /></td> 
                            <td><s:property value="accesstime" /></td>  
                            <td><s:property value="regTime" /></td>
                            <td><s:property value="vip" /></td> 
                            <td><s:property value="defunct" /></td>                            
                            <td><a class="button border-blue button-little" href="users-update.jsp?id=${s.userId}">修改</a>
                                <a class="button border-yellow button-little" href="deleteUser?id=${s.userId}" }">删除</a>
                            </td>
                         </tr>   
                       </s:iterator>                                           
                    </table>
                   </form>
            <div class="panel-foot text-center">
         	<div style="display:block;margin-bottom:2em">      
                <input onclick="submitDel()" type="button" value="删除所选">
				<input onClick="checkAllaction(1)" type="button" value="全选">
				<input onClick="checkAllaction(2)" type="button" value="全不选">
				<input onClick="checkAllaction(3)" type="button" value="反选"/>           
			 </div> 
			   
			 <s:if test="pageNow > 1">
				<s:url id="url_pre" value="searchUser">
					<s:param name="pageNow" value="pageNow-1"></s:param>
					<s:param name="userId" value="userId"></s:param>
					<s:param name="nick" value="nick"></s:param>
					<s:param name="tel" value="tel"></s:param>
					<s:param name="email" value="email"></s:param>
					<s:param name="address" value="address"></s:param>
					<s:param name="regTime" value="regTime"></s:param>
					<s:param name="balance" value="balance"></s:param>
					<s:param name="vip" value="vip"></s:param>
				</s:url>
			</s:if>
			<s:else>
				<s:url id="url_pre" value="searchUser">
					<s:param name="pageNow" value="1"></s:param>
					<s:param name="userId" value="userId"></s:param>
					<s:param name="nick" value="nick"></s:param>
					<s:param name="tel" value="tel"></s:param>
					<s:param name="email" value="email"></s:param>
					<s:param name="address" value="address"></s:param>
					<s:param name="regTime" value="regTime"></s:param>
					<s:param name="balance" value="balance"></s:param>
					<s:param name="vip" value="vip"></s:param>
				</s:url>
			</s:else>


			<s:if test="pageNow < pageNum">
				<s:url id="url_next" value="searchUser">
					<s:param name="pageNow" value="pageNow+1"></s:param>
					<s:param name="userId" value="userId"></s:param>
					<s:param name="nick" value="nick"></s:param>
					<s:param name="tel" value="tel"></s:param>
					<s:param name="email" value="email"></s:param>
					<s:param name="address" value="address"></s:param>
					<s:param name="regTime" value="regTime"></s:param>
					<s:param name="balance" value="balance"></s:param>
					<s:param name="vip" value="vip"></s:param>
				</s:url>
			</s:if>
			<s:else>
				<s:url id="url_next" value="searchUser">
					<s:param name="pageNow" value="pageNum"></s:param>
					<s:param name="userId" value="userId"></s:param>
					<s:param name="nick" value="nick"></s:param>
					<s:param name="tel" value="tel"></s:param>
					<s:param name="email" value="email"></s:param>
					<s:param name="address" value="address"></s:param>
					<s:param name="regTime" value="regTime"></s:param>
					<s:param name="balance" value="balance"></s:param>
					<s:param name="vip" value="vip"></s:param>
				</s:url>
			</s:else>


			<s:a href="%{url_pre}">上一页</s:a>
			
			<s:if test="pageNow-2 >= 1">
			      <s:url id="url_1" value="searchUser">
					<s:param name="pageNow" value="pageNow-2" />
					<s:param name="userId" value="userId"></s:param>
					<s:param name="nick" value="nick"></s:param>
					<s:param name="tel" value="tel"></s:param>
					<s:param name="email" value="email"></s:param>
					<s:param name="address" value="address"></s:param>
					<s:param name="regTime" value="regTime"></s:param>
					<s:param name="balance" value="balance"></s:param>
					<s:param name="vip" value="vip"></s:param>
				  </s:url>
				  <s:a href="%{url_1}">
					<s:property value="pageNow-2" /> 
				  </s:a>
				</s:if>
				
				<s:if test="pageNow-1 >= 1">
			      <s:url id="url_2" value="searchUser">
					<s:param name="pageNow" value="pageNow-1" />
					<s:param name="userId" value="userId"></s:param>
					<s:param name="nick" value="nick"></s:param>
					<s:param name="tel" value="tel"></s:param>
					<s:param name="email" value="email"></s:param>
					<s:param name="address" value="address"></s:param>
					<s:param name="regTime" value="regTime"></s:param>
					<s:param name="balance" value="balance"></s:param>
					<s:param name="vip" value="vip"></s:param>
				  </s:url>
				  <s:a href="%{url_2}">
					<s:property value="pageNow-1" /> 
				  </s:a>
				</s:if>
				
				<s:url id="url_3" value="searchUser">
					<s:param name="pageNow" value="pageNow" />
					<s:param name="userId" value="userId"></s:param>
					<s:param name="nick" value="nick"></s:param>
					<s:param name="tel" value="tel"></s:param>
					<s:param name="email" value="email"></s:param>
					<s:param name="address" value="address"></s:param>
					<s:param name="regTime" value="regTime"></s:param>
					<s:param name="balance" value="balance"></s:param>
					<s:param name="vip" value="vip"></s:param>
				</s:url>
				<s:a href="%{url_3}">
					<s:property value="pageNow" /> 
				</s:a>
				
				<s:if test="pageNow+1 <= pageNum">
				  <s:url id="url_4" value="searchUser">
					<s:param name="pageNow" value="pageNow+1" />
					<s:param name="userId" value="userId"></s:param>
					<s:param name="nick" value="nick"></s:param>
					<s:param name="tel" value="tel"></s:param>
					<s:param name="email" value="email"></s:param>
					<s:param name="address" value="address"></s:param>
					<s:param name="regTime" value="regTime"></s:param>
					<s:param name="balance" value="balance"></s:param>
					<s:param name="vip" value="vip"></s:param>
				  </s:url>
				  <s:a href="%{url_4}">
					<s:property value="pageNow+1" /> 
				  </s:a>
				</s:if>
				
				<s:if test="pageNow+2 <= pageNum">
				  <s:url id="url_5" value="searchUser">
					<s:param name="pageNow" value="pageNow+2" />
					<s:param name="userId" value="userId"></s:param>
					<s:param name="nick" value="nick"></s:param>
					<s:param name="tel" value="tel"></s:param>
					<s:param name="email" value="email"></s:param>
					<s:param name="address" value="address"></s:param>
					<s:param name="regTime" value="regTime"></s:param>
					<s:param name="balance" value="balance"></s:param>
					<s:param name="vip" value="vip"></s:param>
				  </s:url>
				  <s:a href="%{url_5}">
					<s:property value="pageNow+2" /> 
				  </s:a>
			   </s:if>
				       
			<s:a href="%{url_next}">下一页</s:a>

			总共<s:property value="pageNum" />页
			总共<s:property value="totalRows"/>条记录
			
			<div style="position:relative;float:right">
                <form action="searchUser" name="formpage">
                    第<input name='pageNow' onKeyDown='if(event.keyCode==13)event.returnValue=false' 
                    onchange="if(/\D/.test(this.value)){alert('请输入需要跳转到的 页数并且必须为整数！');this.value='1';}"
                    style='width: 20px;text-align:center' type='text' value='1' id="goPage">页 
                    <input name="userId" type="hidden" value="<s:property value='userId'/>" >
                    <input name="nick" type="hidden" value="<s:property value='nick'/>" >
                    <input name="tel" type="hidden" value="<s:property value='tel'/>" >
                    <input name="email" type="hidden" value="<s:property value='email'/>" >
                    <input name="address" type="hidden" value="<s:property value='address'/>" >
                    <input name="regTime" type="hidden" value="<s:property value='regTime'/>" >
                    <input name="balance" type="hidden" value="<s:property value='balance'/>" >
                    <input name="vip" type="hidden" value="<s:property value='vip'/>" >
                    <input type="submit"  value='转到' onclick="return check();">
                </form>
       	    </div>
			                         
		</div>
       
     </div>
	
        
       
        
	

<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>
    <script type="text/javascript"> 
/*		$(function() { 
			$("#checkall").click(function() { 
				$("input[@name='id']").each(function() { 
					$(this).attr("checked", true); 
				}); 
				}); 
				$("#delcheckall").click(function() { 
					$("input[@name='id']").each(function() { 
						$(this).attr("checked", false); 
				}); 
			}); 
		}); */
		
		function submitAll(){
		  document.getElementById("showForm").submit();
		}
		
		function submitDel(){
		  if (confirm("确认删除?"))
		   document.getElementById("delForm").submit();
		}
		
		function checkAllaction(obj)
		{
			for(var i=0;i<document.all.length;i++){
				if (document.all(i).type=="checkbox"){
					switch(obj){
						case 1:document.all(i).checked=true;
							break; //全选
						case 2:document.all(i).checked=false;
							break; //不选
						case 3:{
							if(document.all(i).checked==true){
								document.all(i).checked=false;
							}
							else{
								document.all(i).checked=true;
							}
						}
							break; //反选
					}
				}
			}
		}
	</script> 
</body>
    

<SCRIPT language=javascript>
	function check(){
    var num = document.getElementById("goPage").value; 
    var pageNum = <s:property value='pageNum'/>;
    if(num > pageNum)
    { 
      alert("该页不存在");
    }
   }
	
	function GoPage(Myself)
	{
		var Lmyself=Myself.replace(".html","")
		if (document.formpage.SkipPage.value == 1)
			{window.location.href=Myself;} //绝对路径可自己设置
		else{
			window.location.href=Lmyself+"_"+document.formpage.SkipPage.value+".html";
		}
	}		
</SCRIPT>
</html>
    