<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理-管理员登录</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    <script src="js/respond.js"></script>
    <script src="js/admin.js"></script>
    <link type="image/x-icon" href="http://www.pintuer.com/favicon.ico" rel="shortcut icon" />
    <link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon" />
</head>

<body>
<div class="container">
    <div class="line">
        <div class="xs6 xm4 xs3-move xm4-move">
            <br /><br />
            <div class="media media-y">
                <a href="http://www.baidu.com" target="_blank" style="font-size:24px;font-weight:24px;font-family:microsoft YaHei">女鞋后台管理系统</a>
            </div>
            <br /><br />
            <form action="userLogin" method="post" >
            <div class="panel">
                <div class="panel-head"><strong>登录女鞋后台管理系统</strong></div>
                <div class="panel-body" style="padding:30px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input" name="admin" placeholder="登录账号" data-validate="required:请填写账号,length#>=5:账号长度不符合要求" />
                            <span class="icon icon-user"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password" class="input" name="password" placeholder="登录密码" data-validate="required:请填写密码,length#>=8:密码长度不符合要求" />
                            <span class="icon icon-key"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field">
                            <input type="text" class="input" id="code" name="passcode" placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" />
                            <img src="SecurityCodeImageAction?timestamp=<% new Date().getTime(); %>" alt="看不清，换一张" id="Verify" 
                                    style="cursor: hand;" width="80" height="32" class="passcode" />
                            <!-- <input value= <s:property value="#session.SESSION_SECURITY_CODE"/> type="hidden" id="hide"/> -->                     
                        </div>
                    </div>
                </div>
                <div class="panel-foot text-center"><button class="button button-block bg-main text-big">登录后台</button></div>
            </div>
            </form>
            
       <script type="text/javascript">
         window.onload = function(){
         var verifyObj = document.getElementById("Verify");
         verifyObj.onclick=function(){
           this.src ="SecurityCodeImageAction?timestamp="+new Date().getTime();
           alert(document.getElementById("hide").value);
          };       
        }     
        
        function checkCode(){
          var code_user = document.getElementById("code").value;
          var code_session = document.getElementById("hide").value;
          if(code_user == code_session){
             return true;
          }else{
            alert("验证码错误！");
            return false;
          }
        }      
       </script>
            
        </div>
    </div>
</div>

<div class="hidden"><script src="http://s4.cnzz.com/stat.php?id=5952475&web_id=5952475" language="JavaScript"></script></div>
</body>
</html>