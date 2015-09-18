package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.dao.UsersDAO;
import com.model.Users;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MD5Utils;

public class AddUserAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private static final long serialVersionUID = 5899879534740953584L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	// 上传的 文件
	private File upload;
	// 文件类型
	private String uploadContentType;
	// 文件名
	private String uploadFileName;
	// 头像存储的文件夹，在struts.xml中配置
	private String savePath;

	private Users user;

	public String getSavePath() {
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	//添加用户
	@Override
	public String execute() throws Exception {

		UsersDAO userDAO = new UsersDAO();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//判断是否允许添加
		if(userDAO.checkId(user.getUserId()) && userDAO.checkEmail(user.getEmail()) 
				&& userDAO.checkTel(user.getTelephone())){
			//上传文件
			if (this.getUpload() != null) {
				String newName = UUID.randomUUID() + 
				    this.getUploadFileName().substring(this.getUploadFileName().lastIndexOf("."));
				FileOutputStream fos = new FileOutputStream(this.getSavePath()
						+ "\\" + newName);
				FileInputStream fis = new FileInputStream(this.getUpload());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}		
				
				this.setUploadFileName(newName);
				String realPath = ServletActionContext.getServletContext().getRealPath("/");
				user.setIcon(realPath+"images" + "/" + this.getUploadFileName());					
			}
			
			//将当前时间设置为注册时间
			user.setRegTime(new Timestamp(System.currentTimeMillis()));
			//密码加密
		    user.setPassword(MD5Utils.md5(user.getPassword()));	
		    //设置IP
		    user.setIp(userDAO.getIp());
			//添加用户
			userDAO.save(user);	
			
			request.setAttribute("Tip", "添加成功");
		
			//out.print("<script>alert('添加成功！');</script>");
			//out.print("<script>history.back();</script>");
			//String realPath = ServletActionContext.getServletContext().getRealPath("/");
			//out.print("<script>window.location.href="+realPath+"'/users-add.jsp'</script>");
			return SUCCESS;
		}
		return ERROR;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	

}
