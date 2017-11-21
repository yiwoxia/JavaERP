package com.situ.ssh.controller;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.situ.ssh.pojo.Admin;
import com.situ.ssh.service.IAdminService;

@Controller
@Scope("/prototype")
public class AdminBackAction extends ActionSupport{
	@Autowired
	private IAdminService adminService;
	
	private Admin admin;
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String login() {
		
		if (adminService.login(admin) != null) {
			//登陆成功，放到session中，跳转到首页
			ServletActionContext.getRequest().getSession().setAttribute("admin", admin);
			return "index";
		} else {
			return LOGIN;
		}
	}
}
