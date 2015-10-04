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
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>拼图后台管理-后台管理</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="css/article-add.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    <script src="js/respond.js"></script>
    <script src="js/admin.js"></script>  
    <link type="image/x-icon" href="http://www.pintuer.com/favicon.ico" rel="shortcut icon" />
    <link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon" />
    
    <style>
    	.forminfo{list-style:none;margin-top:1.5em;}
		.forminfo li{margin-top:1em;list-style:none;}
    </style>
    	<script type="text/javascript" language="javascript">
		document.getElementsByName("article.type")[1].checked="checked";
	    document.getElementsByName("article.type")[2].checked="checked";
	    document.getElementsByName("article.type")[3].checked="checked";
	    
	    function check1(){
	    	var val=$('input:radio[id="radio1"]:checked').val();
	    	if(!(val =="1")){
	    		alert("请选择类型");
	    		return false;
	    	}
	    		
	    	//检测图片的类型和大小
			 var imgs = document.getElementsByName("myFile");
			 if(imgs.length == 0){
			 	alert("请至少上传一张图片！");
			 	return false;
			 }
			 for(var i=0;i<imgs.length;i++){
			    //alert(imgs[i].value);
			    //判断某个图片的类型是否正确
			    var imgBool = checkImgFile(imgs[i]);
			    if(imgBool == false){
			      alert("图片类型不正确！");
			      return false;
			    }
			    
			        //判断某个图片的大小是否小于3M
			    var sizeBool = checkImgSize(imgs[i]);
			    	if(sizeBool == false){
			    		alert("图片大小超出限制！");
			      		return false;
			    }
			 }
			 return true;
	    }
	    
	    function check2(){
	    	var val=$('input:radio[id="radio2"]:checked').val();
	    	if(!(val =="2")){
	    		alert("请选择类型");
	    		return false;
	    	}

	    	 //检测图片的类型和大小
			 var imgs = document.getElementsByName("myFile");
			 if(imgs.length == 0){
			 	alert("请至少上传一张图片！");
			 	return false;
			 }
			 for(var i=0;i<imgs.length;i++){
			    //alert(imgs[i].value);
			    //判断某个图片的类型是否正确
			    var imgBool = checkImgFile(imgs[i]);
			    if(imgBool == false){
			      alert("图片类型不正确！");
			      return false;
			    }
			    
			        //判断某个图片的大小是否小于3M
			    var sizeBool = checkImgSize(imgs[i]);
			    	if(sizeBool == false){
			    		alert("图片大小超出限制！");
			      		return false;
			    }
			 }
			 return true;
	    }
	    
		//检测文件类型
		function checkImgFile(obj){
		   if(obj==null || obj.value==""){
		     return true;
		   }else{
		   imgExt = obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
		   //alert(imgExt);
		   if(imgExt != '.jpg' && imgExt != '.png'){
		       alert("图片格式不正确，请上传后缀名为.jpg或.png的图片");
		       return false;
		   }else{
		     return true;
		   }
		  }
		}

		//检测图片大小
		function checkImgSize(obj){
		      
		     if(obj==null || obj.value==""){
		     return true;
		     }else{
		      var fileSize = 0;
		      var isIE = /msie/i.test(navigator.userAgent) && !window.opera;            
		      if (isIE && !obj.files) {          
		         var filePath = this.value;            
		         var fileSystem = new ActiveXObject("Scripting.FileSystemObject");   
		         var file = fileSystem.GetFile (filePath);               
		         fileSize = file.Size;         
		    }else {  
		         fileSize = obj.files[0].size;     
		    } 
		    fileSize=Math.round(fileSize/1024*100)/100; //单位为KB
		    //alert(fileSize);
		    if(fileSize>=3072){
		        alert("照片最大尺寸为3M，请重新上传!");
		        return false;
		    }
		   }
		}
	</script>
 </head>
  
<body>
<input type="hidden" value="<%=ActionContext.getContext().get("Tip")%>" id="hide">
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
               <!-- <li><a href="home.jsp" class="icon-file-text"> 首页</a>  -->
                <li><a href="shoes-search.jsp" class="icon-file-text"> 鞋子</a> </li>
               <!--  <li><a href="classify.jsp" class="icon-file-text"> 分类条件</a> </li> -->
                <li class="active"><a href="article.jsp" class="icon-cog"> 文章</a>
            		<ul><li><a href="article.jsp">文章列表</a></li><li  class="active"><a href="article-add.jsp">添加</a></li></ul>
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
               <li>添加</li>
            </ul>
        </div>
    </div>
</div>

		<div class="admin">	    
          <div class="tab">
                <div class="tab-head">
                  <strong>类型选择</strong>
                  <ul class="tab-nav">
                    <li class="active"><a href="#tab-set">类型1</a></li>
                    <!-- <li><a href="#tab-email">类型2</a></li> -->
                    <!-- <li><a href="#tab-upload">类型3</a></li> -->
                  </ul>
                </div>
     
         	     <div class="tab-body">
           		    <br />
                  
             		  <div class="tab-panel active" id="tab-set">
                      <s:form action ="uploadImg" method ="POST" enctype ="multipart/form-data" >  
              		     <div class="form-group">
              		  	  <!--  <input type="radio" name="article.type" id="radio1" value="1"/>类型 1 -->
      				         </div>
                  		     	
                        <div class="form-group">
                           <div class="label"><label for="articlename">文章标题</label></div>
                           <div class="field">
                            	<input type="text" class="input" id="articlename" name="article.title" size="50" placeholder="文章标题" data-validate="required:请填写你网站的名称" />
                           </div>
                        </div>
                            
                        <div class="form-group">
                           <div class="label"><label for="leadname">文章导语</label></div>
                           <div class="field">
                          	<!-- <textarea  class="input"  rows="2" cols="50" name="article.lead"  placeholder="文章导语" data-validate="required:请填写文章导语" /></textarea> -->
                          	<input type="text" class="input" id="leadname" name="article.lead"  placeholder="文章导语" data-validate="required:请填写文章导语" />
                           </div>
                        </div>
                            
                        <div class="form-group">
                            <div class="label"><label for="article.keywords">关键字</label></div>
                            <div class="field">
                            	<input type="text" class="input"  name="article.keywords"  placeholder="请填写关键字，多个关键字用,隔开"  />
                            </div>
                        </div>
                          
                        <div class="form-group">
                            <div class="label"><label for="readme">文章内容</label></div>
                            <div class="field">
                            	<textarea class="input" rows="5" cols="50" name="article.content" placeholder="文章内容" data-validate="required:请填写文章内容，建议在200字以内。"></textarea>
                            </div>
                        </div>

          				<div class="form-group">                
                            <s:file name ="myFile" label ="图片1"/> 
                            <s:file name ="myFile" label ="图片2"/> 
                            <s:file name ="myFile" label ="图片3"/> 
          				</div>

          			    <div class="form-group">
                          	<input type="submit" style="float:right;margin-top:5em;width:8em;height:2.5em;background:#3366FF;color:#FFF;font-size:1em;border:none;border-radius:5px;"  value="添加" onclick="" />
                   	  	</div>
             	       </s:form>  	       
                  </div>  <!-- tab-set -->

                 <!--   <div class="tab-panel" id="tab-email">
                  	   <s:form action ="uploadImg" method ="POST" enctype ="multipart/form-data" >  
                  		  <div class="form-group">
                  			   <input type="radio" name="article.type" id="radio2" value="2"/>类型2
          				  </div>     	

                        <div class="form-group">
                           <div class="label"><label for="sitename">文章标题</label></div>
                           <div class="field">
                          	<input type="text" class="input" id="articlename" name="article.title" size="50" placeholder="文章标题" data-validate="required:请填写你网站的名称" />
                           </div>
                        </div>
                            
                       <div class="form-group">
                         <div class="label"><label for="leadname">文章导语</label></div>
                         <div class="field">
                        	<textarea  class="input"  rows="2" cols="50" name="article.lead"  placeholder="文章导语" data-validate="required:请填写文章导语" /></textarea>
                          	<input type="text" class="input" id="leadname" name="article.lead"  placeholder="文章导语" data-validate="required:请填写文章导语" />
                         </div>
                       </div>      
                        
                        <div class="form-group">
                            <div class="label"><label for="readme">文章内容</label></div>
                            <div class="field">
                            	<textarea class="input" rows="5" cols="50" name="article.content" placeholder="文章内容" data-validate="required:请填写文章内容，建议在200字以内。"></textarea>
                            </div>
                        </div>
                            
                       <div class="form-group">
                           <div class="label"><label for="sitename">鞋子链接</label></div>
                           <div class="field">
                          	<input type="text" class="input" id="articlename" name="article.shoeUrl" size="50" placeholder="文章标题" data-validate="required:请填写你网站的名称" />
                           </div>
                        </div>
                        
                        <div class="form-group">
                        	<div style="position:relative;float:left;width:8em">
                              <div class="label"><label for="readme">模特身高</label></div>
                              <div class="field">
                              	<input class="input"  name="article.height" placeholder="模特身高" data-validate="required:请填写"></textarea>
                              </div>
                       </div> 

                        <div style="position:relative;float:left;width:8em;margin-left:2em;margin-bottom:2em">
                            <div class="label"><label for="readme">模特体重</label></div>
                            <div class="field">
                            	<input class="input"  name="article.weight" placeholder="模特体重" data-validate="required:请填写"></textarea>
                            </div>
                        </div> 

                        <div style="position:relative;float:left;width:8em;margin-left:2em;margin-bottom:2em">
                            <div class="label"><label for="readme">模特三围</label></div>
                            <div class="field">
                            	<input class="input"  name="article.bwh" placeholder="模特三围" data-validate="required:请填写"></textarea>
                            </div>
                        </div> 

                        <div style="position:relative;float:left;width:8em;margin-left:2em;margin-bottom:2em">
                            <div class="label"><label for="readme">模特脚型</label></div>
                            <div class="field">
                            	<input class="input"  name="article.footType" placeholder="模特脚型" data-validate="required:请填写"></textarea>
                            </div>
                        </div> 

                        <div style="position:relative;float:left;width:8em;margin-left:2em;margin-bottom:2em">
                            <div class="label"><label for="readme">模特脚长</label></div>
                            <div class="field">
                            	<input class="input"  name="article.footHeight" placeholder="模特脚长" data-validate="required:请填写"></textarea>
                            </div>
                        </div> 

                        <div style="position:relative;float:left;width:8em;margin-left:2em;margin-bottom:2em">
                            <div class="label"><label for="readme">模特脚宽</label></div>
                            <div class="field">
                            	<input class="input"  name="article.footWidth" placeholder="模特脚宽" data-validate="required:请填写"></textarea>
                            </div>
                        </div> 

                        <div class="form-group" style="margin-top:2em">
                            <s:file name ="myFile" label ="图片1"/> 
                            <s:file name ="myFile" label ="图片2"/> 
                            <s:file name ="myFile" label ="图片3"/> 
                             <s:textfield name ="caption" label ="Caption" />  
                           <ul>
                        </div>

                        <div class="form-group">
                          <input type="submit" style="float:right;margin-top:5em;width:8em;height:2.5em;background:#3366FF;color:#FFF;font-size:1em;border:none;border-radius:5px;"  value="添加" onclick="" />
                        </div>

                        </s:form>  
                    </div> -->

               </div><!-- tab-body -->
          </div> <!-- tab-->
    </div>
</div>
           	    

	  	  
</body>
</html>
