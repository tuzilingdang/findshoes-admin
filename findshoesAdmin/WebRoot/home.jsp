<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.model.Users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.model.showArticle, com.model.Article , java.util.ArrayList"%>
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
		.table tr th{font-size:1.1em}
		.table td{font-size:1.1em}
		.button-group{
			display:inline-block;border:solid 1px #ddd;border-radius:4px;color:#333;padding:8px 12px;
			line-height:12px;display:block;transition:all 1s cubic-bezier(0.175, 0.885, 0.32, 1) 0s; background-color:#fff}		

	     .ckButton{float:left}
	     .select-group{float:right}
	</style>
    

    <style>
	    tbody {
	  	    counter-reset:sectioncounter;
		}                      
		.SortId:before {
	  		content:counter(sectioncounter); 
	   		counter-increment:sectioncounter;
		}
    </style>
</head>

<body >
	<div class="lefter">
	    <div class="logo"><strong>女鞋后台管理系统</strong></div>
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
	                <!-- <li><a href="system.jsp" class="icon-home"> 系统</a> -->
	               <li  class="active"><a href="home.jsp" class="icon-file-text"> 首页</a> 
	               			<ul><li><a href="home.jsp">轮播图管理</a></li></ul>
	               </li>
	                <li><a href="shoes-search.jsp" class="icon-file-text"> 鞋子</a> </li>
	               <!--  <li><a href="classify.jsp" class="icon-file-text"> 分类条件</a> </li> -->
	                <li ><a href="article.jsp" class="icon-cog"> 文章</a>
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
	               <li><a href="home.jsp">轮播图管理</a></li>
	            </ul>
	        </div>
	    </div>
	</div>

<div class="admin">
		<div style="position:relative;padding-top:2em;width:100%">
		<div id="display" >
             <table class="table table-hover" style="position:relative;padding-top:1.5em;table-layout:fixed;overflow:hidden;width:100%;font-size:1em">
             	<tbody>		
             			<tr > <th style="font-size:.9em;padding-bottom:.8em;" colspan="9"><strong>轮播图管理</strong></th></tr>
                        <tr >
                            <!-- <th width=20 style="">选择</th>  -->   <th width=20 >序号</th>   <th width=90>文章ID</th>     <th width=90>类型</th>  <th style="width:90">标题</th>
                            <th width=120 style="overflow:hidden">文章导语</th> <th width=120>首页图片</th>     <!-- <th width=120>状态</th> --><th width=220 style = "overflow:hidden">首页发布状态</th>
                            <!-- <th width="60">分享数</th><th width="60">收藏数</th> --><th width=220 style = "overflow:hidden">操作</th>
                        </tr> 					
                        <%
				             showArticle showarticle = new showArticle();  
				             showarticle.classifybyInitial();
				             List<Article> articleList =   new ArrayList();	
				             articleList = 	showarticle.getAllarticlelist();			             			          
				             for(int i = 0; i< 10; i++){
				             	Article tmp = articleList.get(i);
				         %>	 			        
				                 
	                        <tr class="SortId" style="text-overflow: ellipsis;white-space: nowrap;overflow:scroll;font-size:.8em;height:1.5em">
	                        	
	                            <%-- <td width=20 style="overflow:hidden;"><input type="checkbox" id="goodsId"  value="<%=tmp.getArticalId() %>"  class ="deleck"/></td>  --%>  	            
	                             <td style="overflow:hidden;"><%= tmp.getArticalId() %></td> 
	                             <td style="overflow:hidden;"><%= tmp.getType() %></td>  
	                             <td style="overflow:hidden;"><%= tmp.getTitle() %></td>
	                            <td style="overflow:hidden;"><%= tmp.getLead() %></td>  
	                            <td style="overflow:hidden;"><%= tmp.getImgPath() %></td>
	                            <td style = "overflow:hidden"><%= tmp.getDefunct() %></td> 
	                            <s:form id="form1" data-ajax="false" action=" homeImgupload.action" method="post"  enctype="multipart/form-data" data-transition="slide" theme="simple" > 
	                            <td style = "overflow:hidden"><s:file name="myFile" label="图片1"></s:file>
	                            <input type="hidden"  id="hidden-array" name="articalId" value="<%= tmp.getArticalId() %>" />
	                            <input type="hidden" name="hidden-defunct"  value="<%= tmp.getDefunct() %>" />
	                            <td style = "overflow:hidden">
	                            	<input type="button" id ="pub" class="button border-blue button-little"  value="发布" onclick="checkNum('<%= tmp.getArticalId() %>')">
	                            	<input type="button" class="button border-yellow button-little" onclick="delPub('<%= tmp.getArticalId() %>')" value="取消">
								</td>
								</s:form>
	                        </tr> 
	                     
   					  <% }%>    
   				</tbody>							
             </table>
        </div>
      	</div>
 						
  		<div class="page-group" style="margin-top:1em; margin-left:25em">			
           <!--  <ul class="pagination"></ul> -->
           <ul class="pagination"><li><button class="button-group"  onclick = " goFirst()" >首页</button></li></ul> 
            <ul class="pagination pagination-group">
                <li><button class="button-group"  onclick = "MinusPageno()" >前5页</button></li>
                <li><input class="button-group" type="submit" id="input1" value="1" onclick="goPage(1)"/></li>
                <li><input type="submit" class="button-group" id="input2" value="2" onclick="goPage(2)"/></li>
                <li><input type="submit" class="button-group" id="input3" value="3" onclick="goPage(3)"/></li>
                <li><input type="submit" class="button-group" id="input4" value="4" onclick="goPage(4)"/></li>
                <li><input type="submit" class="button-group" id="input5" value="5" onclick="goPage(5)"/></li>
                <li><button class="button-group" onclick = "addPageno()" >后5页</button>
            </ul>
            <ul class="pagination"></ul>
            <ul class="pagination"> <li><button class="button-group"  onclick = "goEnd()" >尾页</button></li></ul> 
       	</div>
       	<div id ="seleadd" style="margin-top:2em"></div>
    </div>
	
    <div class="hot">
       <!-- <div><label>输入爬虫网址</label><input type="text" style="width:80px;height:10px"/><input><input  type="button" value="确定"/></div> -->
    </div>
	
	<input type="hidden" id="record" value="<s:property value='#session.totalRecords'/>" />
	<input type="hidden" id="pageNo" value="<s:property value='#session.pstPageNo'/>"/> 
	<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>
</div> <!--admin  -->

     <script type="text/javascript" language="javascript">
        function pageChg(num){		      
              var pageNo = num; 
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

<script type="text/javascript"> 
	function goPage(num){
		var pageNums = Math.ceil(parseInt(document.getElementById("record").value)/10);		
		if(num == 1)
		    var pstPageNo = document.getElementById("input1").value;		
		if(num == 2)
			var pstPageNo = document.getElementById("input2").value;	
		if(num == 3)
			var pstPageNo = document.getElementById("input3").value;
		if(num == 4)
			var pstPageNo = document.getElementById("input4").value;
		if(num == 5)
			var pstPageNo = document.getElementById("input5").value;
		if (pstPageNo > pageNums) {
			alert("超出最大页！");
			return false;
		}
		var pageNum = 10;		
		$.ajax({
			url:"goPageArticle.action",
			type:"post",
			dataType:"json",
			data:{"pstPageNo":pstPageNo, "pageNum":pageNum},
			success:function(){
				 $("#display").load("article-table.jsp"); 
				 $("#display").load(pageChg(parseInt(pstPageNo)));				  
			     //window.self.location = "home.jsp"; 
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
			}
		}); 		
	}

	function delPub(array){
		var table = "";
	    //var array = document.getElementByName("hidden-array").value;
		$.ajax({
				url:"delPub.action",
				type:"GET",
				dataType:"json",
				data:{"array":array},
				success:function(data){
					/* alert("select Change ajax success"); */
					//alert(data.articleList[0].title);	
					//$("#seleadd").load("home-load.jsp?array");
					/* document.getElementById("hidden-array").value = array; */
		//			alert(document.getElementById("hidden-status").value);
				   /*  window.self.location = "search-succ.jsp";   */
				     window.location.reload();   
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					        alert(XMLHttpRequest.status);
	                        alert(XMLHttpRequest.readyState);
	                        alert(textStatus);
				}
			}); 	
	}
	
	function getSltarray(){
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
		return array;
	}
	
	function goFirst(){
		goPage(1);
	}
	function goEnd(){
		var num = document.getElementById("record").value;
		if (parseInt(num)%10 ==0)
			var pageNo = Math.floor(parseInt(num)/10) ;
		if (parseInt(num)%10 !=0)
			var pageNo = Math.floor(parseInt(num)/10) +1;
			goPage(pageNo);
	}
	  
	function addPageno(){
		var num = document.getElementById("record").value;
	  	if (parseInt(num)%10 ==0)
	  		var totalNo = Math.floor(parseInt(num)/10) ;
	  	if (parseInt(num)%10 !=0)
	  		var totalNo = Math.floor(parseInt(num)/10) +1;
		var pageNo = document.getElementById("input1").value;
		if (parseInt(pageNo) <= parseInt(totalNo)){
			document.getElementById("input1").value= parseInt(pageNo)+parseInt(5);
			document.getElementById("input2").value= parseInt(pageNo)+parseInt(6);
			document.getElementById("input3").value= parseInt(pageNo)+parseInt(7);
			document.getElementById("input4").value= parseInt(pageNo)+parseInt(8);
			document.getElementById("input5").value= parseInt(pageNo)+parseInt(9);
		}
	}
		
	function MinusPageno(){
		var pageNo = document.getElementById("input1").value;
		if(pageNo > 5){
			document.getElementById("input1").value= parseInt(pageNo)-parseInt(5);
			document.getElementById("input2").value= parseInt(pageNo)+parseInt(1)-parseInt(5);
			document.getElementById("input3").value= parseInt(pageNo)+parseInt(2)-parseInt(5);
			document.getElementById("input4").value= parseInt(pageNo)+parseInt(3)-parseInt(5);
			document.getElementById("input5").value= parseInt(pageNo)+parseInt(4)-parseInt(5);
		}
		else
		  pageNo = 1;			
	}
		
	function checkNum(articalId){
		var defunct = document.getElementsByName("hidden-defunct");
		alert(defunct.length);
		var num = 0;
		for (var i = 0; i < parseInt(defunct.length); i++){
		    if(defunct[i].value=="Y")
				num ++;
		}
    	if(num > 4){
    		alert("首页轮播图不能超过5个");     		
    	 	return false;
    	}
    	else{
    		//$("#form1").submit();
    		$.ajax({
				url:"homePub.action",
				type:"post",
				dataType:"json",
				data:{"articalId":articalId},
				success:function(){	
					$("#form1").submit();		  
				    document.location.herf = "home.jsp"; 
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					        alert(XMLHttpRequest.status);
	                        alert(XMLHttpRequest.readyState);
	                        alert(textStatus);
				}
			}); 		
    	}		
		}
</script> 
 
</body>
    

</html>
    