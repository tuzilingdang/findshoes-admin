
<%@ page contentType="text/html; charset=gb2312" language="java" 
import="java.util.*,com.jspsmart.upload.*" errorPage="" %> 
<html> 
<head> 
<title>�ļ��ϴ�����ҳ��</title> 
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"> 
</head> 

<body> 
<% 
// �½�һ��SmartUpload���� 
SmartUpload su = new SmartUpload(); 
// �ϴ���ʼ�� 
su.initialize(pageContext);
su.setAllowedFilesList( "jpg,jpeg,gif,bmp,JPG,GIF,BMP,JPEG ");   
su.upload(); 
// ���ϴ��ļ�ȫ�����浽ָ��Ŀ¼ 
int count = su.save("/upload"); 
out.println(count+"���ļ��ϴ��ɹ���<br>"); 

// ����Request�����ȡ����ֵ֮ 
out.println("TEST="+su.getRequest().getParameter("TEST") 
+"<BR><BR>"); 

// ��һ��ȡ�ϴ��ļ���Ϣ��ͬʱ�ɱ����ļ��� 
for (int i=0;i<su.getFiles().getCount();i++) 
{ 
com.jspsmart.upload.File file = su.getFiles().getFile(i); 

// ���ļ������������ 
if (file.isMissing()) continue; 

// ��ʾ��ǰ�ļ���Ϣ 
out.println("<TABLE BORDER=1>"); 
out.println("<TR><TD>��������FieldName��</TD><TD>" 
+ file.getFieldName() + "</TD></TR>"); 
out.println("<TR><TD>�ļ����ȣ�Size��</TD><TD>" + 
file.getSize() + "</TD></TR>"); 
out.println("<TR><TD>�ļ�����FileName��</TD><TD>"
+ file.getFileName() + "</TD></TR>"); 
out.println("<TR><TD>�ļ���չ����FileExt��</TD><TD>" 
+ file.getFileExt() + "</TD></TR>"); 
out.println("<TR><TD>�ļ�ȫ����FilePathName��</TD><TD>" 
+ file.getFilePathName() + "</TD></TR>"); 
out.println("</TABLE><BR>"); 



} 
%> 
</body> 
</html> 
