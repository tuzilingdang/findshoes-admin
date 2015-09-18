package com.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.SecurityCode;
import com.util.SecurityImage;

import java.io.ByteArrayInputStream;


@SuppressWarnings("serial")
public class SecurityCodeImageAction extends ActionSupport{
	

	private ByteArrayInputStream imageStream;
	
	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}
	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}
	
	public String execute() throws Exception{
	 //获取验证码
	 String securityCode = SecurityCode.getSecurityCode();
	 System.out.println(securityCode);
	 imageStream = SecurityImage.getImageAsInputStream(securityCode);
	 //保存到session	 
	 ActionContext.getContext().getSession().put("code", securityCode);
	 
	 return SUCCESS;
	}
}
