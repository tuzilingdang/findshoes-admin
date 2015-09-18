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
<input type="hidden" value="<%=ActionContext.getContext().get("Tip")%>" id="hide">
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
                <li class="active"><a href="shoes-search.jsp" class="icon-cog"> 鞋子</a>
            		<ul>
            			<li><a href="shoes-search.jsp">鞋子搜索</a></li>
            			<li><a href="shoes-add.jsp">添加</a></li>
            			<li  class="active"><a href="shoes-batchadd.jsp">批量添加</a></li>
            		</ul>
                </li>
                <!-- <li><a href="classify.jsp" class="icon-file-text"> 分类条件</a> </li> -->
                <li><a href="article.jsp" class="icon-file-text"> 文章</a></li>
                <!-- <li><a href="#" class="icon-user"> 商店</a></li> -->
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
                <li>批量添加</li>
            </ul>
        </div>
    </div>
</div>

<div class="admin">
 	 <strong>批量添加</strong>
    <div class="tab">
      <div class="tab-head"> 
   	   <div class="tab-body">
        <br />
         <div class="tab-panel active" id="tab-set">
			<div style="width:40%;padding:10px;"><!--  border: 10px solid #99CCFF;  -->
				<s:form id="form1" action="roleFileUpload" method="post" enctype="multipart/form-data">
				<p>
					<span style="font-size:1em;font-weight:700;color:#000">
					    请选择要上传的Excel文件
					</span>
					<br/>
					<input type="file" name="uploadFile" id="uploadFile" />
				</p>
				<p style="width:100%;margin-top:3em;">
					<span style="font-size:1em;font-weight:700;color:#000">
					    请选择要上传的图片			
					</span>												
	                <span id="more"></span>	                                   							
					<span>
					   <input type="button" value="添加更多图片" onclick=addMore();>
					</span>
				</p>	
				
				<p>
				  <input style="float:left;margin-left:35em;margin-top:4em;width:8em;height:2.5em;background:#3366FF;color:#FFF;font-size:1em;border:none;border-radius:5px" type="submit" value="确定" />
			    </p>																					
			</s:form>
		</div>
         </div>
        
       </div>
     </div>
   </div>
        

        
       
  

<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>

</body>

<script type="text/javascript" language="javascript">
//用于提交检测
function check(){
  //检测Excel文件类型是否正确
  var fileBool = checkFile();
  if(fileBool == false){
    alert("文件类型不正确，请上传excel文件！");
    return false;
  }
  //检测图片的类型和大小
  var imgs = document.getElementsByName("file");
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
      return false;
    }
  }
  //alert("sucess");
  return true;
}

function file_remove(){
   var file = document.getElementById("file");
   var button = document.getElementById("remove");
   var span = document.getElementById("more");
   span.removeChild(file);
   span.removeChild(button);
}

//添加多个文件上传域
function addMore() {
	var td = document.getElementById("more");
	var br = document.createElement("br");
	var input = document.createElement("input");
	var button = document.createElement("input");
	var span = document.createElement("span");
	input.type = "file";
	input.name = "file";
	button.type = "button";
	button.value = "删除";

	button.onclick = function() {
		td.removeChild(br);
		td.removeChild(input);
		td.removeChild(button);
		td.removeChild(span);
	}
	
	input.onchange = function(){
      //检测文件类型
      checkImgFile(this);
      //检测文件大小
      checkImgSize(this);
	}
	
	td.appendChild(br);
	td.appendChild(input);
	td.appendChild(span);
	td.appendChild(button);
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

//用于显示提示信息 
function checkForm() {
	var msg = document.getElementById("hide").value;
	var flag = false;
	if (msg != 'null') {
		alert(msg);
		flag = true;
	}
	return flag;
}

//检测Excel文件类型
function checkFile() {
	var fileName = document.getElementById("uploadFile").value;
	if (fileName != "") {
		var fileType = (fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length)).toLowerCase();
		var supportFile = [ "xls", "XLS", "xlsx", "XLSX" ];
		for ( var i = 0; i < supportFile.length; i++) {
			if (supportFile[i] == fileType) {
				return true;
			}
		}
		alert("文件格式不对");
		return false;
	} else {
		alert("请选择你需要导入的文件");
		return false;
	}
}
	
  </script>
</html>
