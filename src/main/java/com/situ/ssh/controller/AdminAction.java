package com.situ.ssh.controller;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.situ.ssh.controller.base.BaseAction;
import com.situ.ssh.pojo.Admin;
import com.situ.ssh.service.IAdminService;

@Controller
@Scope("prototype")
public class AdminAction extends BaseAction<Admin>{
	@Autowired
	private IAdminService adminService;
	private String checkCode;
	
	
	public IAdminService getAdminService() {
		return adminService;
	}


	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}


	public String getCheckCode() {
		return checkCode;
	}


	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}


	public String login() {
		String checkCodeSession =  (String) ServletActionContext.getRequest().getSession().getAttribute("checkCodeSession");
		if (checkCode == null || checkCode.equals("")) {
			return LOGIN;
		}
		if (!checkCode.equalsIgnoreCase(checkCodeSession)) {
			return LOGIN;
		}
		Admin admin = adminService.login(model);
		if (admin != null) {
			//登陆成功，放到session中，跳转到首页
			ServletActionContext.getRequest().getSession().setAttribute("admin", admin);
			return "index";
		} else {
			return "login";
		}
		
		
	}
}
