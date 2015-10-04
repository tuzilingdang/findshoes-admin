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

<body  onload="pageChg()">
     <table class="table table-hover" style="position:relative;padding-top:1.5em;table-layout:fixed;overflow:hidden;width:100%;font-size:1em">
     	<tbody>		
		<tr > <th style="font-size:.9em;padding-bottom:.8em;" colspan="9"><strong>轮播图管理</strong></th></tr>
		    <tr >
		        <!-- <th width=20 style="">选择</th>  -->   <th width=20 >序号</th>   <th width=90>文章ID</th>     <th width=90>类型</th>  <th style="width:90">标题</th>
		        <th width=120 style="overflow:hidden">文章导语</th> <th width=120>首页图片</th>     <!-- <th width=120>状态</th> --><th width=220 style = "overflow:hidden">首页发布状态</th>
		        <!-- <th width="60">分享数</th><th width="60">收藏数</th> --><th width=220 style = "overflow:hidden">操作</th>
		        </tr> 							        
		<s:form data-ajax="false" action=" homeImg.action" method="post"  enctype="multipart/form-data" data-transition="slide" theme="simple" >    
		   	<s:iterator value="#session.showArticleList" status="var">     
	            <tr class="SortId" style="text-overflow: ellipsis;white-space: nowrap;overflow:scroll;font-size:.8em;height:1.5em">
		            <%-- <td width=20 style="overflow:hidden;"><input type="checkbox" id="goodsId"  value="<%=tmp.getArticalId() %>"  class ="deleck"/></td>  --%>  	            
		            <td style="overflow:hidden;"><s:property value="article.articalId" /></td> 
		            <td style="overflow:hidden;"><s:property value="article.type" /></td>  
		            <td style="overflow:hidden;"><s:property value="article.title" /></td>
		            <td style="overflow:hidden;"><s:property value="article.lead" /></td>  
		            <td style="overflow:hidden;"> <s:property value="article.imgPath" /></td>
		            <td style = "overflow:hidden"><s:property value="article.defunct" /></td> 
		            <td style = "overflow:hidden"><s:file name="myFile" label="图片1"></s:file>
		            <input type="hidden" id="hidden-array" name="articalId" value="<s:property value="article.articalId" />" />
		            <input type="hidden" name="hidden-defunct"  value="<s:property value="article.defunct" />" />
		            <td style = "overflow:hidden">
		            	<input type="submit" id ="pub" class="button border-blue button-little"  value="发布" onclick="checkNum()">
		            	<input type="button" class="button border-yellow button-little" onclick="delPub('<s:property value="article.articalId" />')" value="取消">
					</td>
	            </tr>
		    </s:iterator> 
		  </s:form>
		</tbody>							
      </table>
	
	<input type="hidden" id="record" value="<s:property value='#session.totalRecords'/>" />
	<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>
</div> <!--admin  -->

     <script type="text/javascript" language="javascript">
        function pageChg(){		      
              //var pageNo = document.getElementById("pageNo").value;
              var pageNo = 1;
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
		
		var pageNum = 10;		
		$.ajax({
			url:"goPageArticle.action",
			type:"post",
			dataType:"json",
			data:{"pstPageNo":pstPageNo, "pageNum":pageNum},
			success:function(){
				 /*  $("#display").load("home.jsp"); 
				  $("#display").load(pageChg());  */				  
			     window.self.location = "home.jsp"; 
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
			alert(pageNo);
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
		
		function checkNum(){
			var defunct = document.getElementByName("hidden-defunct").value;
        	if(defunct.length > 5){
        		alert("首页轮播图不能超过5个");
        	 	return false;
        	}
        	else
        		return true;
		}
</script> 
 
</body>
    

</html>
    