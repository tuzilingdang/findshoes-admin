<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.model.Users"%>
<%@ page import="java.sql.*"%>  
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" import="java.sql.*,java.io.*,java.util.*"%>
<%@ page contentType="text/html;charset=utf-8"%>
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
    <link type="image/x-icon" href="javascript(void(0))" rel="shortcut icon" />
    <link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon" />
    
    <!-- <link href="css/HttpUploader.css" type="text/css" rel="Stylesheet"/> -->
    <script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="js/HttpUploader.js" charset="utf-8"></script>
    
    <style>
		.td-url{/*text-overflow:ellipsis;*/white-space:nowrap;overflow: hidden}
		.table{position:relative;table-layout:fixed;padding-top:1.5em}
		.table tr th{font-size:.8em;white-space:nowrap;overflow: hidden}
		.table td{font-size:.6em;white-space:nowrap;overflow: hidden}
		
		.button-group{
			display:inline-block;border:solid 1px #ddd;border-radius:4px;color:#333;padding:8px 12px;
			line-height:12px;display:block;transition:all 1s cubic-bezier(0.175, 0.885, 0.32, 1) 0s; background-color:#fff}		

	     .ckButton{float:left}
	     .select-group{float:right}
	</style>
	
	    <script type="text/javascript" language="javascript">
		  function skipPage(){
			alert("num");
			var num = document.getElementById("skip").value;
			
			var pstPageNo = num;
			alert( pstPageNo);
			var search_data = document.getElementById("map_str").value;
			alert( search_data);
			$.ajax({
				url:"goPage.action",
				type:"post",
				dataType:"json",
				data:{"pstPageNo":pstPageNo,"search_data":search_data},
				success:function(){
					  alert("ajax success"); 
					 location.reload();
				  /*   window.self.location = "article.jsp"; */ 
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					        alert(XMLHttpRequest.status);
	                        alert(XMLHttpRequest.readyState);
	                        alert(textStatus);
				}
			});  
		}
 
        function pageChg(){			
             var pageNo = document.getElementById("pageNo").value;
               /* 	alert(pageNo); */
             if (parseInt(pageNo)%5 == 1){
             	 $("#input1").css("background","#E0FFFF")	;
				document.getElementById("input1").value= parseInt(pageNo);
				document.getElementById("input2").value= parseInt(pageNo)+parseInt(1);
				document.getElementById("input3").value= parseInt(pageNo)+parseInt(2);
				document.getElementById("input4").value= parseInt(pageNo)+parseInt(3);
				document.getElementById("input5").value= parseInt(pageNo)+parseInt(4);
             }
             if (parseInt(pageNo)%5 == 2){
             	 $("#input2").css("background","#E0FFFF")	;
				document.getElementById("input1").value= parseInt(pageNo)-parseInt(1);
				document.getElementById("input2").value= parseInt(pageNo);
				document.getElementById("input3").value= parseInt(pageNo)+parseInt(1);
				document.getElementById("input4").value= parseInt(pageNo)+parseInt(2);
				document.getElementById("input5").value= parseInt(pageNo)+parseInt(3);
             }
        	 
        	 if (parseInt(pageNo)%5 == 3){
             	 $("#input3").css("background","#E0FFFF")	;
				document.getElementById("input1").value= parseInt(pageNo)-parseInt(2);
				document.getElementById("input2").value= parseInt(pageNo)-parseInt(1);
				document.getElementById("input3").value= parseInt(pageNo);
				document.getElementById("input4").value= parseInt(pageNo)+parseInt(1);
				document.getElementById("input5").value= parseInt(pageNo)+parseInt(2);
             }
             
             if (parseInt(pageNo)%5 == 4){
             	 $("#input4").css("background","#E0FFFF")	;
				document.getElementById("input1").value= parseInt(pageNo)-parseInt(3);
				document.getElementById("input2").value= parseInt(pageNo)-parseInt(2);
				document.getElementById("input3").value= parseInt(pageNo)-parseInt(1);
				document.getElementById("input4").value= parseInt(pageNo)
				document.getElementById("input5").value= parseInt(pageNo)+parseInt(1);
             }
             
               if (parseInt(pageNo)%5 == 0){
               /*  alert("go into page 10"); */
             	 $("#input5").css("background","#E0FFFF")	;
				document.getElementById("input1").value= parseInt(pageNo)-parseInt(4);
				document.getElementById("input2").value= parseInt(pageNo)-parseInt(3);
				document.getElementById("input3").value= parseInt(pageNo)-parseInt(2);
				document.getElementById("input4").value= parseInt(pageNo)-parseInt(1);;
				document.getElementById("input5").value= parseInt(pageNo);
             }         
        }
    </script>  
	
</head>

<body onload="pageChg()">
<%
		//驱动程序名 
		String driverName = "com.mysql.jdbc.Driver";		//数据库用户名 
		String userName = "root";		//密码 
		String userPasswd = "root";		//数据库名 
		String dbName = "whrsdb";		//表名 
		String tableName = "article";//联结字符串 
		
		int pageSize=10;
		int pageNow=1;//默认显示第一页
		int rowCount=0;//该值从数据库中查询
		int pageCount=0;//该值是通过pageSize和rowCount

		String s_pageNow=request.getParameter("pageNow");//接受用户希望显示的页数（pageNow）
		if(s_pageNow!=null){
			pageNow=Integer.parseInt(s_pageNow);
		}

		String url = "jdbc:mysql://localhost:3306/" + dbName + "?user="
				+ userName + "&password=" + userPasswd;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(url);
		Statement statement = connection.createStatement();
		//String sql = "SELECT * FROM " + tableName;
		ResultSet rs = statement.executeQuery("select count(*) from article");

		if(rs.next()){
			rowCount=rs.getInt(1);
		}
		//计算pageCount
		if(rowCount%pageSize==0){
			pageCount=rowCount/pageSize;
		}
		else{
			pageCount=rowCount/pageSize+1;
		}
		//查询出需要显示的记录
	//	String sql = "SELECT * FROM article WHERE article.artical_id >100 LIMIT 10" ;
	    String sql = "SELECT * FROM article WHERE article.artical_id >"+pageSize*(pageNow-1)+" LIMIT "+pageSize ; 
		System.out.println(pageCount);
	//	System.out.println(s);
		rs = statement.executeQuery(sql);
%>

<div class="lefter">
    <div class="logo"><strong>女鞋后台管理系统</strong></div>
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
                <!-- <li><a href="system.jsp" class="icon-home"> 系统</a> -->
              <!--  <li><a href="home.jsp" class="icon-file-text"> 首页</a>  -->
                <li><a href="shoes-search.jsp" class="icon-file-text"> 鞋子</a> </li>
               <!--  <li><a href="classify.jsp" class="icon-file-text"> 分类条件</a> </li> -->
                <li class="active"><a href="article.jsp" class="icon-cog"> 文章</a>
            		<ul><li class="active"><a href="article.jsp">文章列表</a></li><li ><a href="article-add.jsp">添加</a></li></ul>
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
            <ul class="bread">
               <li><a href="index.jsp" class="icon-home"> 开始</a></li>
               <li><a href="article.jsp">文章</a></li>
               <li>文章列表</li>
            </ul>
        </div>
    </div>
</div>

<div class="admin">
    <div class="panel admin-panel">
    	<div class="panel-head"><strong>文章列表</strong></div>
    	
        <div class="padding border-bottom">
            <input type="button" onClick="checkAllaction(1)" ass="button button-small checkall" name="checkall" checkfor="id" value="全选" />
            <a  class="button button-small border-green" href="article-add.jsp" target="#">添加文章</a>
           <!--  <input type="button" class="button button-small border-yellow" value="批量删除" /> -->
           <!--  <input type="button" class="button button-small border-blue" value="回收站" /> -->
        </div>
        <table class="table table-hover">
        	<tr><th width="45">选择</th><th width="50"><%out.print("ID");%></th><th width="50"><%out.print("类型");%></th><th width="200"><%out.print("名称");%></th><th width="400"><%out.print("内容");%></th><th width="180"><%out.print("时间");%></th><th width="100">操作</th></tr>         
        <%
			while (rs.next()) {
		%>
		<tr>
			<td><input class="deleck" type="checkbox" name="deleck" value="<%= rs.getString(1)%>"/></td>
			<td><%out.print(rs.getString(1));%></td>
			<td><%out.print(rs.getString(8));%></td>
			<td><%out.print(rs.getString(2));%></td>
			<td><%out.print(rs.getString(11));%></td>
			<td><%out.print(rs.getString(10));%></td>
			<td><!-- <a class="button border-blue button-little" >修改</a> --> <%--  <button class="button border-green button-little" onclick="goDetail('<%out.print(rs.getString(1));%>')">查看</button> --%>
			</td>
		</tr>
		<%
			}
		%>
            
            
        </table>
        <input name="delselect" type="button" class="button button-small border-yellow" value="删除选中项" onclick="deleSelect()">
        
        <div class="panel-foot text-center">
					<div class="page-group">
			
           <%
				//上一页
				if(pageNow!=1){
					out.println("<a href=article.jsp?pageNow="+(pageNow-1)+">上一页</a>");
				}
				//显示超链接
				for(int i=1;i<=pageCount;i++){
					out.println("<a href=article.jsp?pageNow="+i+">["+i+"]</a>");
				}
				//下一页
				if(pageNow!=pageCount){
					out.println("<a href=article.jsp?pageNow="+(pageNow+1)+">下一页</a>");
				}
			%>
           
 <%--            <ul class="pagination pagination-group">
           		 <li><button class="button-group"  onclick = " goFirst()" >首页</button></li>
                <li><button class="button-group"  onclick = "MinusPageno()" >
	                <%
						//上一页
						if(pageNow!=1){
							out.println("<a href=article.jsp?pageNow="+(pageNow-1)+">上一页</a>");
						}
					%>
				</button></li>
                <li><input class="button-group" type="submit" id="input1" value="" onclick="goPage(1)"/>
                 <%
					for(int i=1;i<=pageCount;i++){
						out.println("<a href=article.jsp?pageNow="+i+">["+i+"]</a>");
					}
				%>
                </li>

                <li><button class="button-group"  >
				 <%
                	if(pageNow!=pageCount){
						out.println("<a href=article.jsp?pageNow="+(pageNow+1)+">下一页</a>");
					}
				 %>
					</button></li>
            </ul> 
            <ul class="pagination"> <li><button class="button-group"  onclick = "goEnd()" >尾页</button></li></ul> --%>
       	   </div>
        </div>
    </div>
    <br />
</div>

<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>
 <script type="text/javascript" language="javascript">
 	function goDetail(id){
 		document.location.href = "art-detail.jsp?"+id;
 	}
	function deleSelect(){
			/* var array = new Array(); //用于保存 选中文章ID */
			var array ="";
			var flag =false;
			$(".deleck").each(function(){
				if($(this).attr("checked")){
					flag =true;
				}
			});
			if (flag == true){
				$(".deleck").each(function(){
					if($(this).attr("checked")){
						/* array.push($(this).val()); */
						if(array == "")
							array = array +  $(this).val();
						else
							array = array + "," + $(this).val();
					}
				});

			}
			else{
				alert("至少你要选择一个待删除记录"); 
			}
				
 			$.ajax({
				url:"deleSelect.action",
				type:"post",
				dataType:"json",
				data:{"array":array},
				success:function(){
					/* alert("ajax success");  */
				     location.reload();
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					        alert(XMLHttpRequest.status);
	                        alert(XMLHttpRequest.readyState);
	                        alert(textStatus);
				}
			}); 
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

</html>


    