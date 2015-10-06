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
    <script src="js/jquery-1.11.2.js"></script>
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
                <li><a href="index.jsp" class="icon-home"> 开始</a>
                <!-- <li><a href="system.jsp" class="icon-home"> 系统</a> -->
                </li>            
               <!-- <li><a href="home.jsp" class="icon-file-text"> 首页</a>  -->
                <li   class="active"><a href="shoes-search.jsp" class="icon-cog"> 鞋子</a>
            		<ul><li   class="active"><a href="shoes-search.jsp">鞋子搜索</a></li>
            			<li><a href="shoes-add.jsp">添加</a></li>
            			<li><a href="shoes-batchadd.jsp">批量添加</a></li>
            		</ul>
                </li>
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
                <li><a href="shoes-search.jsp">鞋子</a></li>
                <li>鞋子搜索</li>
            </ul>
        </div>
    </div>
</div>

<div class="admin">
 	 <table class="table table-hover" style="position:relative;table-layout:fixed;padding-top:1.5em;padding-bottom:1em">
             			<tr style="width:995px;"> <th style="font-size:.9em" colspan="14"><strong>鞋子搜索</strong></th></tr>
     </table>       			
    <div class="tab" style="margin-top:1.5em">
      <div class="tab-head"> 
<!--    	   <div class="tab-body">
        <br />
     <div class="tab-panel active" id="tab-set">  -->
     <div id="result"></div>
       <form method="post" id="search_form" > 
   			 <div style="width:100%">
				<div class="form-group1" style="float:left;;width:16em">
                	<p>货号 <input type="text" style="float:right" class="data" name="shGoodsid" value="" /></p>
 				    <p>品牌<input style="float:right" type="text" class="data" name="shBrand" id ="shBrand" value="" /></p>
                     <p>上市时间 <input style="float:right" type="text"  name="shShowdate" disabled/></p>
                    
                    <p>跟高
                    	<select style="float:right;width:11.2em" class="data" name="shHeight">
                          <option value="">无</option> <option value="平跟">平跟</option> <option value="低跟">低跟</option><option value="中跟">中跟</option> <option value="高跟">高跟</option> <option value="超高跟">超高跟</option>     
						</select></p> 
                </div>
                
                <div class="form-group2" style="float:left;position:relative;width:16em;margin-left:2.5em;">
                	<p>流行元素 <input class="data" style="float:right" type="text"  name="shFashion" value="" /></p>
 				    <p>鞋靴类型 <input class="data" style="float:right" type="text"  name="shHotpoint" value="" /></p>
                    <p>场合 <input class="data" style="float:right" type="text" name="shOccasion"  value="" /></p>
                    <p>风格 <input class="data" style="float:right" type="text" name="shStyle" value="" /></p>

                </div> 
                
                <div class="form-group3" style="float:left;position:relative;width:16em;margin-left:2.5em;">
                	<p>皮质特征 <input class="data"  style="float:right" type="text" name="shLeather" value=""/></p>
 				    <p>鞋底材质<input class="data" style="float:right" type="text"  name="shSole" value=""/></p>
                    <p>沿口高度
                    	<select class="data" style="float:right;width:11.2em" name="shUpperHeight"><option value="">无</option><option value="浅口">浅口</option> <option value="中口">中口</option><option value="深口">深口</option>
                        </select></p></p>	
                    <p>鞋头 <select class="data" style="float:right;width:11.2em" name="shToe">
 							<option value="">无</option> <option value="圆头">圆头</option> <option value="方头">方头</option><option value="尖头">尖头</option> <option value="鱼嘴">鱼嘴</option>
                            <option value="夹趾">夹趾</option>          
					</select></p>    
         <!--            <p>靴筒高 <select style="float:right;width:11.2em" name="shBootheight">
                           <option >无</option> <option value="低筒">低筒</option> <option value="中筒">中筒</option><option value="高筒">高筒</option> <option value="过膝">过膝</option> 
						</select></p> -->
              <!--       <p>靴筒材质 <input style="float:right" type="text"  /></p> -->
                </div>
                
               <div class="form-group4" style="float:left;position:relative;width:16em;margin-left:2.5em;">
                    <p>季节 <select class="data" style="float:right;width:11.2em" name="shSeason">
 							<option value="">无</option> <option value="春季">春季</option> <option value="夏季">夏季</option><option value="秋季">秋季</option> <option value="冬季">冬季</option>                     
					</select></p>    
                	<!-- <p>内里材质 <input class="data" style="float:right" type="text" name="shInnerMaterial" value="" /></p> -->
<!--  				    <p>制作工艺
                    	<select  style="float:right;width:11.2em"><option >无</option> <option value="胶黏鞋">胶黏鞋</option> <option value="手工缝制">手工缝制</option>
                        <option value="硫化鞋">硫化鞋</option>
                        </select></p></p>	</p> -->
                    <p>帮面材质 <input class="data" style="float:right" type="text"  name="shUpperMaterial" value="" /></p>
                </div> 
                
			 </div>
             <input style="display:block;text-align:center;float:left;margin-left:30em;margin-top:1em;margin-bottom:2em;width:8em;height:3em;background:#069;color:#FFF;font-size:1em;border:none;border-radius:5px" 
             	type="button" onclick="searchGoods()" id="idn" value="搜索" />
              </form> 

         
                 <div id="display">  
            <h2><s:property value="#session.goodsId" /></h2>  
        </div>  
        
        <div id = "display"></div>
         
      </div> 
   </div>
	
        
       

<!-- <div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div> -->
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
	function searchGoods(){
   		var search_data=$("#search_form .data").map(function(){
			 return ($(this).attr("name")+'='+$(this).val());
			}).get().join(",") ;
		/* alert(search_data);   */
		
		var args = new Array();
		var pairs = search_data.split(",");
		for (var i = 0; i < pairs.length; i++) { 
			 var pos = pairs[i].indexOf('='); // look for "name=value" 
                if (pos == -1) continue; // if not found, skip 
                var argname = pairs[i].substring(0, pos); // extract the name 
                var value = pairs[i].substring(pos + 1); // extract the value 
                value = decodeURIComponent(value); // decode it, if needed 
                args[argname] = value; // store as a property
                /*  alert(value);  */
    /*             if(argname = "shGoodsid")	
                	var shBrand = 
                 */
		}

		/* alert( args['shBrand']); */
/* 		 var shBrand = $("#shBrand").val();  
		 alert(shBrand); */
		
	 		$.ajax({
				url:"goPage.action",
				type:"post",
				/* dataType:"json", */
				data:{"search_data":search_data,"pstPageNo":"1"},
				/*"shGoodsid":args['shGoodsid'],"shBrand":args['shBrand'],"shHeight":args['shHeight'],
					  "shFashion":args['shFashion'],"shHotpoint":args['shHotpoint'],"shOccasion":args['shOccasion'],
					  "shStyle":args['shStyle'], "shLeather":args['shLeather'],"shSole":args['shSole'],
					  "shUpperHeight":args['shUpperHeight'], "shToe":args['shToe'],"shInnerMaterial":args['shInnerMaterial'],
					  "shToe":args['shToe'],"shUpperMaterial":args['shUpperMaterial']*/

				success:function(msg){
					if(msg.showShoesList == null)
						alert("请输入搜索条件");
				 /*  alert(msg.showShoesList.length); */
				  var flag = msg.showShoesList.length;
				  if (flag != 0)
				  	 window.self.location = "search-succ.jsp";
				  if (flag == 0)
				  	window.self.location = "shoes-err.jsp";  
				    /* alert("ajax success"); */ 

/* 					 $.each(data.showShoesList, function(i,item){ 
              		  alert(item.goodsId+","+item.brand);  });  */
/* 					 $(data.showShoesList).each(function (i, value) {  
                    $("#result").append("<div>输出了：id:" + value.goodsId + ", name: " + value.brand + "</div>");  
                });   */	  
				   /*    location.reload();    */   
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					        alert(XMLHttpRequest.status);
	                        alert(XMLHttpRequest.readyState);
	                        alert(textStatus);
				}
			});  
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
    