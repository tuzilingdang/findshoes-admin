
<%@ page contentType="text/html; charset=gb2312" language="java" 
import="java.util.*,com.jspsmart.upload.*" errorPage="" %> 
<html> 
<head> 
<title>文件上传处理页面</title> 
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"> 
</head> 

<body> 
<% 
// 新建一个SmartUpload对象 
SmartUpload su = new SmartUpload(); 
// 上传初始化 
su.initialize(pageContext);
su.setAllowedFilesList( "jpg,jpeg,gif,bmp,JPG,GIF,BMP,JPEG ");   
su.upload(); 
// 将上传文件全部保存到指定目录 
int count = su.save("/upload"); 
out.println(count+"个文件上传成功！<br>"); 

// 利用Request对象获取参数之值 
out.println("TEST="+su.getRequest().getParameter("TEST") 
+"<BR><BR>"); 

// 逐一提取上传文件信息，同时可保存文件。 
for (int i=0;i<su.getFiles().getCount();i++) 
{ 
com.jspsmart.upload.File file = su.getFiles().getFile(i); 

// 若文件不存在则继续 
if (file.isMissing()) continue; 

// 显示当前文件信息 
out.println("<TABLE BORDER=1>"); 
out.println("<TR><TD>表单项名（FieldName）</TD><TD>" 
+ file.getFieldName() + "</TD></TR>"); 
out.println("<TR><TD>文件长度（Size）</TD><TD>" + 
file.getSize() + "</TD></TR>"); 
out.println("<TR><TD>文件名（FileName）</TD><TD>"
+ file.getFileName() + "</TD></TR>"); 
out.println("<TR><TD>文件扩展名（FileExt）</TD><TD>" 
+ file.getFileExt() + "</TD></TR>"); 
out.println("<TR><TD>文件全名（FilePathName）</TD><TD>" 
+ file.getFilePathName() + "</TD></TR>"); 
out.println("</TABLE><BR>"); 



} 
%> 
</body> 
</html> 
