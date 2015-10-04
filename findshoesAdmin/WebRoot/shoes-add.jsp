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
	</style>

    
</head>

<body>
<div class="lefter">
    <div class="logo"><a style="font-size:large" href="http://www.pintuer.com" target="_blank"><strong>女鞋后台管理系统</strong></a></div>
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
               <!--  <li><a href="system.jsp" class="icon-home"> 系统</a> -->
                </li>            
              <!--  <li><a href="home.jsp" class="icon-file-text"> 首页</a>  -->
                <li   class="active"><a href="shoes-search.jsp" class="icon-cog"> 鞋子</a>
            		<ul><li><a href="shoes-search.jsp">鞋子搜索</a></li>
            			<li class="active"><a href="shoes-add.jsp">添加</a></li>
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
        	<s:form data-ajax="false" action=" addShoe.action" method="post" data-transition="slide" theme="simple">
   			 <div style="width:100%">
				<div class="form-group1" style="float:left;;width:17em">
                	<p>货号 <input type="text" style="float:right" name="shoes.goodsId" value="" required/></p><!-- <input style="float:right" type="text" name="fname" /> -->                	 
 				    <p>品牌<input style="float:right" type="text" name="shoes.brand" value="" /></p>
                    <p>价格<input style="float:right" type="text" name="onlinestore.price" value="" required/></p>
                    <p>上市时间 <input style="float:right" type="text" name="shoes.showDate" value="" required/></p>
                    <p>鞋靴类型 <input style="float:right" type="text" name="shoes.hotPoint" value="" required/></p>
                    <p>跟高
                    	<select style="float:right;width:11.2em" name="shoes.heelHeight" value="" required>
                           <option value="平跟">平跟</option> <option value="低跟">低跟</option><option value="中跟">中跟</option> <option value="高跟">高跟</option> <option value="超高跟">超高跟</option>     
						</select></p>
                </div>
                
                <div class="form-group2" style="float:left;position:relative;width:17em;margin-left:2.5em;">
                	<p>流行元素  <input style="float:right" type="text" name="shoes.fashion" value="" required/></p>
 				    <p>颜色<input style="float:right" type="text" name="shoes.color" value="" required/></p>
                    <p>场合 <input style="float:right" type="text" name="shoes.occasion" value="" required/></p>
                    <p>风格 <input style="float:right" type="text" name="shoes.style" value="" required/></p>
                    <p>鞋头 <select style="float:right;width:11.2em"  name="shoes.toe">
                           <option value="圆头">圆头</option> <option value="方头">方头</option><option value="尖头">尖头</option> <option value="鱼嘴">鱼嘴</option>
                            <option value="夹趾">夹趾</option>     
						</select></p>
                    <p>季节 <input style="float:right" type="text" name="shoes.season" value="" required/></p>
                </div>
                
                 <div class="form-group3" style="float:left;position:relative;width:17em;margin-left:2.5em;">
                	<p>皮质特征<input style="float:right" type="text" name="shoes.leather" value="" required/></p>
 				    <p>鞋底材质<input style="float:right" type="text" name="shoes.sole" value="" required/></p>
                    <p>沿口高度
                    	<select style="float:right;width:11.2em" name="shoes.upperHeight"><option value="浅口">浅口</option> <option value="中口">中口</option><option value="深口">深口</option>
                        </select></p></p>	
                    <p>靴筒高 <select style="float:right;width:11.2em" name="shoes.bootHeight">
                           <option value="低筒">低筒</option> <option value="中筒">中筒</option><option value="高筒">高筒</option> <option value="过膝">过膝</option> 
						</select></p>
                    <p>靴筒材质 <input style="float:right" type="text" name="shoes.bootMaterial" value=""/></p>
                    <p>图案 <input style="float:right" type="text" name="shoes.pattern" value="" /></p>
                </div>
                
                 <div class="form-group4" style="float:left;position:relative;width:17em;margin-left:2.5em;">
                	<p>内里材质 <input style="float:right" type="text" name="shoes.innerMaterial" value="" /></p>
 				    <p>制作工艺
                    	<select style="float:right;width:11.2em"><option value="胶黏鞋" name="shoes.craft" >胶黏鞋</option> <option value="手工缝制">手工缝制</option>
                        <option value="硫化鞋">硫化鞋</option>
                        </select></p></p>	</p>
                    <p>帮面材质 <input style="float:right" type="text" name="shoes.upperMaterial" value="" /></p>
                    <!-- <p>线下ID <input style="float:right" type="text" name="lname" /></p> -->
                    <p>线上URL <input style="float:right" type="text" name="onlinestore.onlineUrl"  value="" /></p>
                    <p>线上店名 <input style="float:right" type="text" name="onlinestore.storeName"  value="" /></p>
                </div>
                
			 </div>
             <input style="float:left;margin-left:35em;margin-top:4em;width:8em;height:2.5em;background:#F30;color:#FFF;font-size:1em;border:none;border-radius:5px" type="submit" value="添加" />
             </s:form>
           </div>
        
         </div>
      </div>
   </div>
        

        
       
  

<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>

</body>
    

</html>
    