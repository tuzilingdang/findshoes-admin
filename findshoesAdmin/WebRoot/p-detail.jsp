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
    <link rel="stylesheet" type="text/css" media="screen" href="css/css-table.css" />
     <link rel="stylesheet" href="css/idangerous.swiper.css">
    <script type="text/javascript" src="js/style-table.js"></script>
    <script type="text/javascript" src="js/zDrag.js"></script>
	<script type="text/javascript" src="js/zDialog.js"></script>
    <style>
		.td-url{/*text-overflow:ellipsis;*/white-space:nowrap;overflow: hidden}
		.table tr th{font-size:.8em}
		.table td{font-size:.6em;}
		#tranvel tr{height:50px;}
		.button-group{
			display:inline-block;border:solid 1px #ddd;border-radius:4px;color:#333;padding:8px 12px;
			line-height:12px;display:block;transition:all 1s cubic-bezier(0.175, 0.885, 0.32, 1) 0s; background-color:#fff}	
		
		.swiper-container {
			  width: 340px;
			  height: 420px;
			  color: #fff;
			  text-align: center;
			  margin-right: 1em;
			}

			.swiper-slide .title {
			  font-style: italic;
			  font-size: 42px;
			  margin-top: 80px;
			  margin-bottom: 0;
			  line-height: 45px;
			}
			.swiper-slide p {
			  font-style: italic;
			  font-size: 25px;
			}
			.pagination {
			  position: absolute;
			  z-index: 20;
			  left: 10px;
			  bottom: 10px;
			}
			.swiper-pagination-switch {
			  display: inline-block;
			  width: 5px;
			  height: 5px;
			  border-radius: 8px;
			  background: #555;
			  margin-right: 5px;
			  opacity: 0.8;
			  border: 1px solid #fff;
			  cursor: pointer;
			}
			.swiper-active-switch {
			  background: #fff;
			}
			.swiper-dynamic-links {
			  text-align: center;
			}
			.swiper-dynamic-links a {
			  display: inline-block;
			  padding: 5px;
			  border-radius: 3px;
			  border: 1px solid #ccc;
			  margin: 5px;
			  font-size: 12px;
			  text-decoration: none;
			  color: #333;
			  background: #eee;
			}
	</style>   

</head>

<body onload="chgStatus()">
<div class="lefter">
    <div class="logo"><a style="font-size:large" href="index.jsp" target="_blank"><strong>女鞋后台管理系统</strong></a></div>
</div>
<div class="righter nav-navicon" id="admin-nav">
    <div class="mainer">
         <div class="admin-navbar">
            <span class="float-right">
            	<!-- <a class="button button-little bg-main" >前台首页</a> -->
                <a class="button button-little bg-yellow" href="login.html">注销登录</a>
            </span>
            <ul class="nav nav-inline admin-nav"  >
                <li><a href="index.jsp" class="icon-home"> 开始</a>
                <!-- <li><a href="system.jsp" class="icon-home"> 系统</a> -->
               <!--  <li><a href="home.jsp" class="icon-file-text"> 首页</a> -->
                <li class="active"><a href="shoes-search.jsp" class="icon-cog"> 鞋子</a>
            		<ul><li><a href="shoes-search.jsp">鞋子搜索</a></li><li  class="active"><a href="shoes-add.jsp">添加</a></li><li><a href="shoes-batchadd.jsp">批量添加</a></li></ul>
                </li>
                <li><a href="classify.jsp" class="icon-file-text"> 分类条件</a> </li>
                <li><a href="article.jsp" class="icon-file-text"> 文章</a></li>
                <li><a href="#" class="icon-user"> 商店</a></li>
                <li><a href="#" class="icon-file">用户</a></li>
            </ul>
        </div>
        <div class="admin-bread" style="margin-left:1.5em">
            <span>您好，admin，欢迎您的光临。</span>
            <ul class="bread">
                <li><a href="index.html" class="icon-home"> 开始</a></li>
                <li><a href="system.html">鞋子</a></li>
                <li>鞋子搜索</li>
            </ul>
        </div>
    </div>
</div>

<div class="admin" style="margin-left:3.5em;margin-top:1.5em;">
        
		<div style="position:relative;padding-top:1.5em;display:block;">
             <table class="table table-hover" style="position:relative;table-layout:fixed;padding-top:1.5em">
             		<tr style="width:995px;"> <th style="font-size:.9em" colspan="14"><strong>鞋子详情</strong></th></tr>
 					<tr><caption>
					</caption></tr>
             </table>
             <div >
           		  <label style="display:inline;font-size:1.2em">更新时间: <s:property value="showDetail.shoe.updateTime" /> </label>
           		  <label  style="display:inline;font-size:1.2em;margin-left:6em">状态: <div id ="status" style="display:inline;"><p></p></div></label>
           		  
             </div>
             <div style="float:left">
	             <div id ="left" style="float:left">
	             <!-- 	<div class="top" style="display:block;background:#91c5d4;font-size:13px">z</div> -->
	             	<div class="swiper-container">
					    <div class="swiper-wrapper">
	                	 <s:iterator value="showDetail.imageList" status="statu">
	                 	 	<img class="swiper-slide blue-slide"  style="max-width:100%;overflow:hidden;" src="<s:property value='imageUrl' />" />
	                 	 </s:iterator> 
					    </div>
					     <div class="pagination"></div> 
					 </div>
	             </div>
	             <div id ="right" style="float:left;width:600px;">
				     <table id="travel" style="table-layout:fixed;height:400px;margin-left:50px">
					    <thead>    
					    	<tr>
					    		<!-- <th scope="col" rowspan="8">图片</th> -->
					            <th scope="col" colspan="4">详细信息</th>
					        </tr>
					        
					        <tr>
					            <th scope="col">货号</th>
					            <th scope="col">品牌</th>
					            <th scope="col">上市时间</th>
					            <th scope="col">季节</th>			            
					        </tr>        
					    </thead>
					    
		<!-- 			    <tfoot>
					    	<tr>
					        	<th scope="row">All modes</th>
					            <td><s:property value="showDetail.brandName" /></td>
					            <td><s:property value="showDetail.goodId" /></td>
					            <td>27</td>
					            <td>39</td>
					            <td>20</td>
					            <td>23</td>
					        </tr>
					    </tfoot> -->
					    
					    <tbody>
					    	<tr>
					            <td><s:property value="showDetail.goodId" /></td>
					            <td><s:property value="showDetail.brandName" /></td>
					            <td><s:property value="showDetail.shoe.showDate" /></td>
					            <td><s:property value="showDetail.shoe.season" /></td>
					        </tr>
					        
					    <thead>    			        
					        <tr>
					        	<th scope="col">跟高</th>
					            <th scope="col">鞋头</th>
					            <th scope="col">跟型</th>
					            <th scope="col">沿口高度</th>
					        </tr>        
					    </thead>
					        
					        <tr>
					        	<td><s:property value="showDetail.shoe.heelHeight" /></td>
					        	<td><s:property value="showDetail.shoe.toe" /></td>
					            <td><s:property value="showDetail.shoe.heeltylheelStyle" /></td>
					            <td><s:property value="showDetail.shoe.upperHeight" /></td>		            
					        </tr>
					        
					    <thead>    		        
					        <tr>
					            <th scope="col">图案</th>
					            <th scope="col">靴筒材质</th>
					            <th scope="col">选购热点</th>
					            <th scope="col">流行元素</th>
					        </tr>        
					    </thead>
					        
					        <tr>
					            <td><s:property value="showDetail.shoe.pattern" /></td>
		 						<td><s:property value="showDetail.shoe.bootMaterial" /></td>
					            <td><s:property value="showDetail.shoe.hotPoint" /></td>	
					            <td style="overflow:hidden;"><s:property value="showDetail.shoe.fashion" /></td>	           
					        </tr>
					        
					    <thead>    				        
					        <tr>
					        	<th scope="col">颜色</th>
					        	<th scope="col">风格</th>
					        	<th scope="col">帮面材质</th>
					            <th scope="col">鞋底材质</th>
					        </tr>        
					    </thead>
					        
					        <tr>
					        	<td><s:property value="showDetail.shoe.color" /></td>	
					        	<td><s:property value="showDetail.shoe.style" /></td>
					        	<td><s:property value="showDetail.shoe.sole" /></td> 
					        	<td><s:property value="showDetail.shoe.upperMaterial" /></td>		            
					        </tr>	        
					        
					     <thead>    				        
					        <tr>
								<th scope="col">场合</th>
					        	<th scope="col">靴筒高度</th>
					            <th scope="col">皮质特征</th> 
					            <th scope="col">内里材质</th>
					        </tr>        
					    </thead>
					        
					        <tr>
					        	<td><s:property value="showDetail.shoe.occasion" /></td>				            	
					            <td><s:property value="showDetail.shoe.bootHeight" /></td>
					        	<td><s:property value="showDetail.shoe.leather" /></td>	 
					            <td><s:property value="showDetail.shoe.innerMaterial" /></td>			            
					        </tr>	        
					    </tbody>		
					</table>
				</div>
			 	<div  style="clear:both"></div>
			 </div>
      	</div>
		<div  style="display:block;margin-top:39em">
			<input  class="button border-yellow button-little"  style="margin-left:37em;width:8em;height:3em;background:#f60;;color:#FFF;font-size:1.2em;border:none;border-radius:5px" 
             	type="button" onclick="openPop()"  value="修改" />
         </div>
</div>

<!-- <h1 id="result">Click the pager below.</h1>
<div id="pager" ></div> -->
<input type="hidden" id="hidden-id" value=<s:property value="showDetail.shoe.goodsId" /> />
<input type="hidden" id="hidden-status" value=<s:property value="showDetail.shoe.flag" /> />
<input type="hidden" id="getval_name" value="窗口的值返回到这里"/>
<input type="hidden" id="getval_val" value="窗口的值返回到这里"/>
<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>


  <script src="js/idangerous.swiper.min.js"></script>
        <script>
			var mySwiper = new Swiper('.swiper-container',{
			  pagination: '.pagination',
			  loop:true,
			  grabCursor: true,
			  paginationClickable: true
			})
  </script>
  
  
 <script type="text/javascript">  
  	function chgGoods(){
		var goodsId =  document.getElementById("hidden-id").value;
		var name =  document.getElementById("getval_name").value;
		var val =  document.getElementById("getval_val").value;
		

		$.ajax({
				url:"chgGoods.action",
				type:"post",
				dataType:"json",
				data:{"name":name,"val":val,"goodsId":goodsId},
				success:function(string){
					alert("修改成功");
					 /* goPage(num); *//* goodsDetail.action?gid=<s:property value="shoes.goodsId" /> */
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
	
	function openPop()
{
	var diag = new Dialog();
	diag.Title = "选择修改的属性";
	diag.URL = "test.html";
	diag.OKEvent = function(){$id('getval_name').value = diag.innerFrame.contentWindow.document.getElementById('a').value;diag.close();
					openPop2()};
	diag.show();
	var doc=diag.innerFrame.contentWindow.document;
	doc.open();
	doc.write('<html><body>选择属性<select id="a" style="margin-left:10em;margin-top:8em;width:11.2em;height:2em">\
		<option value="brand">品牌</option><option value="showDate">上市时间</option>\
		<option value="season">季节</option><option value="heelHeight">跟高</option>\
		<option value="toe">鞋头</option><option value="heelStyle">跟型</option>\
		<option value="upperHeight">沿口高度</option><option value="heelStyle">跟型</option>\
		<option value="pattern">图案</option><option value="bootMaterial">靴筒材质</option>\
		<option value="hotPoint">选购热点</option><option value="fashion">流行元素</option>\
		<option value="color">颜色</option><option value="style">风格</option>\
		<option value="upperMaterial">帮面材质</option><option value="sole">鞋底材质</option>\
		<option value="occassion">场合</option><option value="bootHeight">靴筒高度</option>\
		<option value="leather">皮质特征</option><option value="innerMaterial">内里材质</option>\
	 </select></body></html>') ;
	doc.close();
}
function openPop2()
{
	var diag = new Dialog();
	diag.Title = "修改内容";
	diag.URL = "test.html";
	diag.OKEvent = function(){$id('getval_val').value = diag.innerFrame.contentWindow.document.getElementById('a').value;diag.close();
					chgGoods();};
	diag.show();
	var doc=diag.innerFrame.contentWindow.document;
	doc.open();
	doc.write('<html><body>请输入修改的内容<input id="a" type="text"  style="margin-left:3em;margin-top:8em;width:21.2em;height:5em"/></body></html>') ;
	doc.close();
}

	function chgStatus(){
		var flag =  document.getElementById("hidden-status").value; 
		if (flag =="1")
		  /* document.getElementById("status").value ="发布"; */
		  $('#status').html( "发布");
		if (flag =="0")
			$('#status').html( "待审核");
		 /*  document.getElementById("status").value ="待审核"; */
	}

   </script>
   
<!--    	<style>
a { color: #07c; text-decoration: none; border: 0; background-color: transparent; }
  q, iframe, form { margin: 0; padding: 0; } 
img, fieldset { border: none 0; }
textarea { word-break: break-all; word-wrap: break-word; line-height:1.6; } 
input, textarea, select, button { margin: 0; font-size: 14px; font-family: Tahoma, SimSun, sans-serif; }

ol li,ul li{ margin-bottom:0.5em;}
pre, code { font-family: "Courier New", Courier, monospace; word-wrap:break-word; line-height:1.4; font-size:12px;}
pre{background:#f6f6f6; border:#eee solid 1px; margin:1em 0.5em; padding:0.5em 1em;}
#content { padding-left:50px; padding-right:50px; }
#content h2 { font-size:20px; color:#069; padding-top:8px; margin-bottom:8px; }
#content h3 { margin:8px 0; font-size:14px; COLOR:#693; }
#content h4 { margin:8px 0; font-size:16px; COLOR:#690; }
#content div.item { margin-top:10px; margin-bottom:10px; padding:10px; }
hr { clear:both; margin:7px 0; +margin: 0;
border:0 none; font-size: 1px; line-height:1px; color: #069; background-color:#069; height: 1px; }
.infobar { background:#fff9e3;  color:#743e04; }
.buttonStyle{width:64px;height:22px;line-height:22px;color:#369;text-align:center;background:url(images/buticon.gif) no-repeat left top;border:0;font-size:12px;}
.buttonStyle:hover{background:url(images/buticon.gif) no-repeat left -23px;}

</style>
    -->
</body>
    

</html>
    