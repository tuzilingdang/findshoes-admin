<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.model.showBrand, com.model.Brand , java.util.ArrayList"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> -->
<title>Personal Center</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"  /> 
      <link rel="stylesheet" type="text/css" href="css/jquery.mobile.flatui.css" />  
     <script src="js/jquery.mobile-1.4.0-rc.1.js"></script> 
     <script src="js/jquery.js"></script>
    <title>拼图后台管理-后台管理</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    <script src="js/respond.js"></script>
    <script src="js/admin.js"></script>
    <style type="text/css"> 
/* BODY {
	width: 100%;PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
} */
DIV {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
DL {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
DT {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
DD {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
UL {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
OL {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
LI {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
H1 {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}
H2 {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}

P {
	PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
}

LI {
	LIST-STYLE-TYPE: none; LIST-STYLE-IMAGE: none
}

H2 {
	FONT-SIZE: 100%
}

#change-city{width: 100%;height:auto;margin:0px,0px,0px,0px}
#change-city .choosecities {
	BORDER-BOTTOM: #a3d7df 1px solid; BORDER-LEFT: #a3d7df 1px solid; PADDING-BOTTOM: 60px; BACKGROUND: #fff; 
	BORDER-TOP: #a3d7df 1px solid; BORDER-RIGHT: #a3d7df 1px solid; box-shadow: 0 0 1px #d4edf4; width:100%;
	margin-top:3em;height:auto
}

#title_h2 {
	display:block; POSITION: relative; PADDING-BOTTOM: 0px; LINE-HEIGHT: 30px; MARGIN: 20px 20px 12px; 
	PADDING-LEFT: 14px; PADDING-RIGHT: 14px; ZOOM: 1; DISPLAY: inline-block; BACKGROUND: #09C; HEIGHT: 32px; COLOR: #fff; PADDING-TOP: 0px; border-radius: 0
}

#change-city .choosecities .label{	display:block; margin-left: 1.4em; margin-top: 1em;text-align:center; BORDER-BOTTOM: #ddd 1px solid; BORDER-LEFT: #ddd 1px solid; LINE-HEIGHT: 24px; WIDTH: 30px; ZOOM: 1; DISPLAY: inline-block; HEIGHT: 30px; COLOR: #666; VERTICAL-ALIGN: top; BORDER-TOP: #ddd 1px solid; MARGIN-RIGHT: 5px; BORDER-RIGHT: #ddd 1px solid; border-radius: 0; background-color:#F5F5F5}

.hasallcity li{BORDER-BOTTOM: #CCC 1px solid;}
.hasallcity li:hover{BORDER-BOTTOM: #CCC 1px solid; background-color:#F5F5F5}

.hasallcity .ui-grid-d{
	margin-left: 1.4em;  margin-top: 1em; margin-bottom: 1em;
	}

.hasallcity .ui-grid-d input{display:block;  margin-top: .5em;text-align:left; 
	 BORDER:none;  ZOOM: 1; overflow:hidden;DISPLAY: inline-block;width:130px; HEIGHT: 22px; COLOR:#09C;/* #09C */ 
	 background-color:transparent;
	 }
	 
.hasallcity .ui-grid-d input:active{display:block;  margin-top: .5em;text-align:left; 
	 BORDER:none;  ZOOM: 1; overflow:hidden;DISPLAY: inline-block;width:130px; HEIGHT: 22px; COLOR:#fff; 
	 background-color:#09C; boder-radius: 40px
	 }
 .hasallcity .ui-grid-d input:hover{display:block;  margin-top: .5em;text-align:left; 
	 BORDER:#09C 1px solid;  ZOOM: 1; overflow:hidden;DISPLAY: inline-block;width:130px; HEIGHT: 22px; COLOR:#fff; 
	 background-color:#09C; border-radius: 20px
	 }

.hasallcity .ui-grid-c label:active{background-color:#F5F5F5}

#change-city .citieslist A:hover {
	BACKGROUND: #09C; COLOR: #fff
}
#change-city .citieslist .isonline {
	FONT-WEIGHT: bold
}
#change-city .citieslist .isoffline {
	FONT-WEIGHT: bold
}
/* BODY {
	FONT: 14px/1.5 Tahoma, Helvetica, arial, sans-serif; BACKGROUND: #d6e9ec; COLOR: #000;height:auto
} */
A {
	COLOR: #399; TEXT-DECORATION: none
}
A:hover {
	TEXT-DECORATION: underline
}
SPAN.money {
	FONT-FAMILY: arial
}
SPAN.required {
	COLOR: red
}

	#confirm-button{display:inline;position:relative;left:20.8em;top:0.55em;height:1.9em;width:4.9em;
	/*background:#F98B86;*/
		background:#FF9393;
		color:#FFF;
		font-size:1.05em;
		border:none;
	}

	#clear-button{
		display:inline;position:relative;left:6.2em;top:0.4em;height:1.9em;width:4.9em;
		/*background:#F98B86;*/
		background:#999;
		color:#fff;
		font-size:1.05em;
		border:1px solid #999;
	}

	</style>

   <script language="javascript" type="text/javascript">
	 	function hide (){
	 		if(document.getElementById("adio-choice-2").value ==""){
	 		    $(this).setAttribute("type","hidden");
	 		
	 		}
	 	}
	 	
       function getUrl(brand){ 
	 	  alert(brand); 

	      $.ajax({
	    	type:"post",
	        url:"brandList.action",
	        data:{"brand":brand,"pstPageNo":"1"},
	        dataType:"json",
	        success:function(msg){
	        	 if(msg.showShoesList == null)
						alert("请输入搜索条件");
				 /*  alert(msg.showShoesList.length); */
				  var flag = msg.showShoesList.length;
				  if (flag != 0)
				  	 window.self.location = "brand-succ.jsp";
				  if (flag == 0)
				  	window.self.location = "shoes-err.jsp";  
	        },
				error:function(XMLHttpRequest, textStatus, errorThrown){
					        alert(XMLHttpRequest.status);
	                        alert(XMLHttpRequest.readyState);
	                        alert(textStatus);
				}
	    });
   	  }
	 </script>
</head>

<body onload="hide()">
<div class="lefter">
    <div class="logo"><a style="font-size:large" href="http://www.pintuer.com" target="_blank"><strong>女鞋后台管理系统</strong></a></div>
</div>
<div class="righter nav-navicon" id="admin-nav">
    <div class="mainer">
        <div class="admin-navbar">
            <span class="float-right">
            	<a class="button button-little bg-main" href="http://www.pintuer.com" target="_blank">前台首页</a>
                <a class="button button-little bg-yellow" href="login.html">注销登录</a>
            </span>
            <ul class="nav nav-inline admin-nav">
                <li class="active"><a href="index.jsp" class="icon-home"> 开始</a>
                    <ul><li><a href="system.jsp">系统设置</a></li><li><a href="">首页管理</a></li><li><a href="shoes-search.jsp">鞋子管理</a></li><li class="active"> <a href="#">品牌管理</a> </li><li><a href="#">文章管理</a></li><li><a href="article.jsp">商店管理</a></li><li><a href="users-search.jsp">用户管理</a></li></ul>
                </li>
                <li><a href="system.jsp" class="icon-cog"> 系统</a>
            		<ul><li><a href="#">全局设置</a></li><li class="active"><a href="#">系统设置</a></li><li><a href="#">会员设置</a></li><li><a href="#">积分设置</a></li></ul>
                </li>
               <!--  <li><a href="home.jsp" class="icon-file-text"> 首页</a> -->
				<!-- 	<ul><li><a href="#">轮播图管理</a></li><li class="active"><a href="#">人气热款管理</a></li><li><a href="#">品牌新款管理</a></li></ul> -->
                <li><a href="shoes-search.jsp" class="icon-file-text"> 鞋子</a>
					<ul><li><a href="#">鞋子搜索</a></li><li class="active"><a href="#">添加</a></li><li><a href="#">批量添加</a></li><li><a href="#">链接管理</a></li></ul>
                    
                 <li><a href="classify.jsp" class="icon-file-text"> 品牌管理</a>
					<ul><li>条件管理</li><li class="active"><a href="#">品牌</a></li><li><a href="#">流行元素</a></li><li><a href="#">选购热点</a></li><li><a href="#">场合</a></li><li><a href="#">风格</a></li><li><a href="#">皮质特征</a></li><li><a href="#">鞋底材质</a></li></ul> 
                    
                <li><a href="article.jsp" class="icon-file-text"> 文章</a>
					<ul><li><a href="#">添加内容</a></li><li class="active"><a href="#">内容管理</a></li><li><a href="#">分类设置</a></li><li><a href="#">链接管理</a></li></ul>
                    
               <li><a href="content.html" class="icon-file-text"> 商店</a>
					<ul><li><a href="#">添加内容</a></li><li class="active"><a href="#">内容管理</a></li><li><a href="#">分类设置</a></li><li><a href="#">链接管理</a></li></ul>
                    
               <li><a href="content.html" class="icon-file-text"> 用户</a>
					<ul><li><a href="#">添加内容</a></li><li class="active"><a href="#">内容管理</a></li><li><a href="#">分类设置</a></li><li><a href="#">链接管理</a></li></ul>
            </ul>
        </div>
        <div class="admin-bread">
            <span>您好，admin，欢迎您的光临。</span>
            <ul class="bread">
                <li><a href="index.html" class="icon-home"> 开始</a></li>
                <li>品牌管理</li>
                <li>品牌列表</li>
            </ul>
        </div>
    </div>
</div>
         <div class="admin">	           	  
              <div id="change-city" style="height:auto">
                    <div class="choosecities" style="height:auto">
                      <p class="cf" style="width:100%;height:auto">
                      <span style="display:block"><h2 id="title_h2">品牌列表</h2></span>
                          
                      <ol class="hasallcity" >                      
                        <%
				             showBrand showbrand = new showBrand();  
				             showbrand.classifybyInitial();  				          
				             char a = 'A';
				             for(int j=0 ;j < 26; j++){
				         %>
				         <li id="city-A" style="width:100%">
					    <span class="label" style="display:block"><strong><%= a %></strong></span>
                       	<span> 
                       	<%    	     
                       	    a += 1;
	                       	for(int i = 0; i < showbrand.getAllbrandlist().get(j).size(); i = i+5) { 
	                       			List<String> showlist = new ArrayList();
	                       			if ( i == showbrand.getAllbrandlist().get(j).size()-1){
	                       				showlist.add(showbrand.getAllbrandlist().get(j).get(i).getId().getBrandName());
	                       				showlist.add("");
	                       				showlist.add("");
	                       				showlist.add("");
	                       		  	    showlist.add("");	           
	                       			}               		                  			
	                       			else if ((i+1)  == showbrand.getAllbrandlist().get(j).size()-1){
	                       				showlist.add(showbrand.getAllbrandlist().get(j).get(i).getId().getBrandName());
	                       				showlist.add(showbrand.getAllbrandlist().get(j).get(i+1).getId().getBrandName());
	                       				showlist.add("");
	                       				showlist.add("");
	                       				showlist.add("");
	                       			}
	                       			else if ((i+2)  == showbrand.getAllbrandlist().get(j).size()-1){
	                       				showlist.add(showbrand.getAllbrandlist().get(j).get(i).getId().getBrandName());
	                       				showlist.add(showbrand.getAllbrandlist().get(j).get(i+1).getId().getBrandName());
	                       				showlist.add(showbrand.getAllbrandlist().get(j).get(i+2).getId().getBrandName());
	                       				showlist.add("");
	                       				showlist.add("");
	                       			}
	                       				
	                       			else if ((i+3)  == showbrand.getAllbrandlist().get(j).size()-1){
	                       				showlist.add(showbrand.getAllbrandlist().get(j).get(i).getId().getBrandName());
	                       				showlist.add(showbrand.getAllbrandlist().get(j).get(i+1).getId().getBrandName());
	                       				showlist.add(showbrand.getAllbrandlist().get(j).get(i+2).getId().getBrandName());
	                       				showlist.add(showbrand.getAllbrandlist().get(j).get(i+3).getId().getBrandName());
	                       				showlist.add("");
	                       			}
	                       			else {
	                       				showlist.add(showbrand.getAllbrandlist().get(j).get(i).getId().getBrandName());
	                       			    showlist.add(showbrand.getAllbrandlist().get(j).get(i+1).getId().getBrandName());
	                       				showlist.add(showbrand.getAllbrandlist().get(j).get(i+2).getId().getBrandName());
	                       				showlist.add(showbrand.getAllbrandlist().get(j).get(i+3).getId().getBrandName()); 
	                       				showlist.add(showbrand.getAllbrandlist().get(j).get(i+4).getId().getBrandName());               			
	                       			}		
                       	   %>   
                          <div class="ui-grid-d"  style="color:#0CC">
                              <div class="ui-block-a">
<%-- 	                                <input type="radio" name="radio-choice" id="radio-choice-2" value="choice-2" style="width:10px;height:10px"/>
	  								<label for="radio-choice-2"><%=showlist.get(0) %></label> --%>
	  								<input type="button" name="radio-choice" id="radio-choice-1" value="<%=showlist.get(0) %>" style="" onclick="getUrl('<%=showlist.get(0) %>')"/>
  							  </div>
                               <div class="ui-block-b">
                              	<input type="button" name="radio-choice" id="radio-choice-2" value="<%=showlist.get(1) %>" style="" onclick="getUrl('<%=showlist.get(1) %>')"/></div>
                              <div class="ui-block-c">
                              	<input type="button" name="radio-choice" id="radio-choice-3" value="<%=showlist.get(2) %>" style="" onclick="getUrl('<%=showlist.get(2) %>')"/></div>
                              <div class="ui-block-d">
                              	<input type="button" name="radio-choice" id="radio-choice-4" value="<%=showlist.get(3) %>" style="" onclick="getUrl('<%=showlist.get(3) %>')"/></div>
                              <div class="ui-block-e">
                              	<input type="button" name="radio-choice" id="radio-choice-5" value="<%=showlist.get(4) %>" style="" onclick="getUrl('<%=showlist.get(4) %>')"/></div>
                          </div>
                           <%}  %> 
                          </span>
                        <!--  </p>-->
                        </li>
                        <%} %>    
                     </ol>
                   </div>  <!--choosecities-->
              </div> <!--change-city-->
              
<!--               <div id="ui-grid-solo-btn" data-role="ui-grid-solo"  data-theme="f"  >
               		 <input id="confirm-button" type="submit" value="确定" />  
                     <input id="clear-button" type="submit" value="清除" />  
              </div> -->
   <!--      </div> -->   
      </div>

</body>
</html>
