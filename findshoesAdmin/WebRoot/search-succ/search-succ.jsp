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
        		var flag =  document.getElementById("hidden-status").value; 
				if (flag =="1")
				  $('#status').html( "发布");
				if (flag =="0")
					$('#status').html( "待审核");
			
             var pageNo = document.getElementById("pageNo").value;
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

<div class="lefter">
    <div class="logo"><a style="font-size:large" href="http://www.pintuer.com" target="_blank"><strong>女鞋后台管理系统</strong></a></div>
</div>
<div class="righter nav-navicon" id="admin-nav">
    <div class="mainer">
         <div class="admin-navbar">
            <span class="float-right">
            	<a class="button button-little bg-main" href="#">前台首页</a>
                <a class="button button-little bg-yellow" href="login.html">注销登录</a>
            </span>
            <ul class="nav nav-inline admin-nav">
                <li><a href="index.jsp" class="icon-home"> 开始</a>
                <li><a href="system.jsp" class="icon-home"> 系统</a>
                <li><a href="home.jsp" class="icon-file-text"> 首页</a>
                <li class="active"><a href="shoes-search.jsp" class="icon-cog"> 鞋子</a>
            		<ul><li   class="active"><a href="shoes-search.jsp">鞋子搜索</a></li><li><a href="shoes-add.jsp">添加</a></li><li><a href="shoes-batchadd.jsp">批量添加</a></li></ul>
                </li>
                <li><a href="classify.jsp" class="icon-file-text"> 分类条件</a> </li>
                <li><a href="article.jsp" class="icon-file-text"> 文章</a></li>
                <li><a href="#" class="icon-user"> 商店</a></li>
                <li><a href="#" class="icon-file">用户</a></li>
            </ul>
        </div>
        <div class="admin-bread">
            <span>您好，admin，欢迎您的光临。</span>
            <ul class="bread">
                <li><a href="index.html" class="icon-home"> 开始</a></li>
                <li><a href="system.html">鞋子</a></li>
                <li>鞋子搜索</li>
            </ul>
        </div>
    </div>
</div>

<div class="admin">
        <input type="hidden" id="map_str" value="<s:property value='#session.search_data'/>"/> 
         <input type="hidden" id="pageNo" value="<s:property value='#session.pstPageNo'/>"/> 
		<div style="position:relative;padding-top:2em;width:100%">
             <table class="table table-hover" style="position:relative;padding-top:1.5em;table-layout:fixed;overflow:hidden;width:100%">
             			<tr > <th style="font-size:.9em;padding-bottom:.8em;" colspan="12"><strong>搜索结果</strong></th></tr>
                        <tr >
                            <th width=20 style="">选择</th>    <th width=20 >序号</th>   <th width=90>货号</th>     <th width=90>品牌</th>  <th style="width:90">上市时间</th>
                            <th width=120 style="overflow:hidden">流行元素</th> <th width=120>跟高</th>     <th width=120>风格</th><th width=220 style = "overflow:hidden">颜色</th>
                            <!-- <th width="60">分享数</th><th width="60">收藏数</th> --><th width="80">时间</th><th width="50">状态</th><th width="150">操作</th>
                        </tr>
                        <s:iterator value="#session.showShoesList" status="index"><!-- status="var" -->
	                        <tr style="text-overflow: ellipsis;white-space: nowrap;overflow:scroll;font-size:.8em">
	                            <td width=20 style="overflow:hidden;"><input type="checkbox" id="goodsId" value=<s:property value="shoes.goodsId" /> class ="deleck"/></td>  <td width=20><s:property value="#index.count"/></td>  <td ><s:property  value="shoes.goodsId" /></td> 
	                             <td style="overflow:hidden;"><s:property value="shoes.brand" /></td>  
	                             <td style="overflow:hidden;"><s:property value="shoes.showDate" /></td>
	                            <td style="overflow:hidden;"><s:property value="shoes.fashion" /></td>  
	                            <td style="overflow:hidden;"> <s:property value="shoes.heelHeight" /></td>
	                            <td style="overflow:hidden;"><s:property value="shoes.style" /></td>
	                            <!-- <td style="overflow:hidden;"><s:property value="shoes.occasion" /></td> --> <td style = "overflow:hidden"><s:property value="shoes.color" /></td>  <td>2014/10/23</td><td id="status"><s:property value="shoes.flag" /></td>
	                            <td style="overflow:hidden;"><a class="button border-yellow button-little" href="#">修改</a>
	                            	<a class="button border-blue button-little" href="goodsDetail.action?gid=<s:property value="shoes.goodsId" />">查看</a>
	                            	<input type="hidden" id="hidden-status" value=<s:property value="shoes.flag" /> />
	                                <!-- <input type="button" class="button border-yellow button-little" onclick="deleSelect(1)" value="删除"> -->
	                            </td>
	                        </tr>
                        </s:iterator> 
                    </table>
      	</div>
      	
      	       <div class="panel-foot text-center">
         	<div style="display:block;margin-bottom:2em">
<!--        	 <input id="checkall" style="float:left;margin-left:-1em" type=button value="全选" ><br>
			 <input style="float:left;margin-top:1em;margin-left:-3em" type=button value="取消" ><br>
-->           
			 <div class = "ckButton"> 
				<input onClick="checkAllaction(1)"  type="button" value="全选">
				<input onClick="checkAllaction(2)"  type="button" value="全不选">
				<input onClick="checkAllaction(3)"  type="button" value="反选">       		
				<input style="float:right;width:4em;height:1.6em;background:#F60;color:#FFF;font-size:1em;border:none;border-radius:5px;" type="button" value="删除" onclick="deleSelect(2)"/>
			 </div> 
			 
			 <div class = "select-group">
                 <select style="width:11.2em;margin-left:50em" id="select-publish">
                   <option value ="1">设置为发布</option>
                   <option value ="2">设置为待审核</option>     
				</select> 
				<input style="float:right;width:4em;height:1.6em;background:#069;color:#FFF;font-size:1em;border:none;border-radius:5px;" type="button" value="确定" onclick="seleChg()"/>
			 </div>
		</div>
		
		<div class="page-group">
			
           <!--  <ul class="pagination"></ul> -->
           <ul class="pagination"> <li><input type="submit" class="button-group" value="首页" /></li></ul> 
            <ul class="pagination pagination-group">
                <li><button class="button-group"  onclick = "MinusPageno()" >前5页</button></li>
                <li><input class="button-group" type="submit" id="input1" value="1" onclick="goPage(1)"/></li>
                <li><input type="submit" class="button-group" id="input2" value="2" onclick="goPage(2)"/></li>
                <li><input type="submit" class="button-group" id="input3" value="3" onclick="goPage(3)"/></li>
                <li><input type="submit" class="button-group" id="input4" value="4" onclick="goPage(4)"/></li>
                <li><input type="submit" class="button-group" id="input5" value="5" onclick="goPage(5)"/></li>
                <li><button class="button-group" onclick = "addPageno()" >后5页</button>
            </ul>
            <ul class="pagination"></li></ul>
            <ul class="pagination"> <li><input type="submit" class="button-group" value="尾页" /></li></ul> 
            <div style="position:relative;padding-top:.8em">
                <form name="formpage" >
              <!--       第<input id="skip" style=	"width: 20px;text-align:center" type="text" value="">页 -->
                    <!-- <input name="submitSkip" type="button" onclick="skipPage()" value="转到"> -->
<!--                      	第<input id="skip" type="text" style="width:20px;text-align:center" value="" />页
                    <button  onclick = "skipPage()" >转到</button> -->
                </form>
       	    </div>
       	   </div>

		</div>
	
<!-- <h1 id="result">Click the pager below.</h1>
<div id="pager" ></div> -->
<input type=hidden name=contextPath value=<s:property value="shoes.goodsId" /> />
<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>

<script type="text/javascript"> 
  
		
	function seleChg(){
		var num =  document.getElementById("pageNo").value;
		var obj=document.getElementById('select-publish'); 
		var index=obj.selectedIndex; 
		var val = obj.options[index].value; 
		var array = getSltarray();
		$.ajax({
				url:"shoespubChg.action",
				type:"post",
				dataType:"json",
				data:{"array":array,"val":val},
				success:function(string){
					alert("select Change ajax success");
					 goPage(num);
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
	
	function deleSelect(src){
			/* var array = new Array(); //用于保存 选中文章ID */
			var num =  document.getElementById("pageNo").value;
			var array ="";
			var flag =false;
			if(src == 1){
				array =  document.getElementById("goodsId").value;
				alert(array);
			}
			if(src == 2){
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
			}
			
				
 			$.ajax({
				url:"shoesSlt.action",
				type:"post",
				/* async:false,   */
				dataType:"json",
				data:{"array":array},
				success:function(){
				/* 	alert("ajax success");   */
				    /*  window.reload();   */
				    goPage(num);
				    
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					        alert(XMLHttpRequest.status);
	                        alert(XMLHttpRequest.readyState);
	                        alert(textStatus);
				}
			}); 
		}


function goPage(num){
			if(num == 1){
			 var pstPageNo = document.getElementById("input1").value;
			}
				
			if(num == 2)
				var pstPageNo = document.getElementById("input2").value;	
			if(num == 3)
				var pstPageNo = document.getElementById("input3").value;
			if(num == 4)
				var pstPageNo = document.getElementById("input4").value;
			if(num == 5)
				var pstPageNo = document.getElementById("input5").value;
				
		    else 
		       var pstPageNo = num;
			/* alert(pstPageNo); */
			
			var search_data = document.getElementById("map_str").value;
			/* alert(search_data); */
/* 			var pager = new Array();
			pager= "${request.pageModel}";  */
/* 			var totalList;
			var showShoesList = new Array();;
			totalList = '${pageModel.list}';
			
       	    var pageSize = '${pageModel.getPageSize()}';
			var start = (pstPageNo-1)*pageSize+1;
			var end = pstPageNo*pageSize;
			alert(end); 
	 	for (var i = start; i < end; i++){
			showShoesList[i] = '${totalList.get(i)}';
			alert("hey");
			alert(typeof(totalList.get(i)));
		}   */
/* 		alert("hey");
		alert(typeof(totalList.get(i))); */
		/* window.self.location="search-succ.jsp?showShoesList = '${showShoesList}'";  */
			
		 	$.ajax({
				url:"goPage.action",
				type:"post",
				dataType:"json",
				data:{"pstPageNo":pstPageNo,"search_data":search_data},
				success:function(){
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
		
				function addPageno(){
			var pageNo = document.getElementById("input1").value;
			document.getElementById("input1").value= parseInt(pageNo)+parseInt(5);
			/* document.getElementById("input1").value=pageNo; */
			document.getElementById("input2").value= parseInt(pageNo)+parseInt(6);
			document.getElementById("input3").value= parseInt(pageNo)+parseInt(7);
			document.getElementById("input4").value= parseInt(pageNo)+parseInt(8);
			document.getElementById("input5").value= parseInt(pageNo)+parseInt(9);
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
    
<!-- 
<SCRIPT language=javascript>
	function GoPage(Myself)
	{
		var Lmyself=Myself.replace(".html","")
		if (document.formpage.SkipPage.value == 1)
			{window.location.href=Myself;} //绝对路径可自己设置
		else{
			window.location.href=Lmyself+"_"+document.formpage.SkipPage.value+".html";
		}
	}
	
	
</SCRIPT> -->
</html>
    