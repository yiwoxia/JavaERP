package com.situ.ssh.controller;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.situ.ssh.common.ServerResponse;
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

	/*转到jsp界面*/
	public String toLogin(){
		return "toLogin";
		
	}
	
	public String login() {
		ServerResponse serverResponse = null;
		String checkCodeSession =  (String) ServletActionContext.getRequest().getSession().getAttribute("checkCodeSession");
		if (checkCode == null || checkCode.equals("")) {
			serverResponse = ServerResponse.createError("验证不能为空");
		}else if (!checkCode.equalsIgnoreCase(checkCodeSession)) {
			serverResponse = ServerResponse.createError("验证码错误");
		}else {
			Admin admin = adminService.login(model);
			if (admin != null) {
				//登陆成功，放到session中，跳转到首页
				ServletActionContext.getRequest().getSession().setAttribute("admin", admin);
				serverResponse = ServerResponse.createSuccess("登录成功");
			} else {
				serverResponse = ServerResponse.createError("登录失败");
			}
		}
		obj2Json(serverResponse);
		return NONE;
	}
	
	public String loginout(){
		try {
			ServletActionContext.getRequest().getSession().removeAttribute("admin");
			obj2Json(ServerResponse.createSuccess("退出成功"));
			return NONE;
		} catch (Exception e) {
			obj2Json(ServerResponse.createError("退出失败"));
			return NONE;
		}
	}
	
	public String findAdmin() throws UnsupportedEncodingException {
		System.out.println(model);
		
	   String trueName = model.getName();
       if (StringUtils.isNotEmpty(trueName)) {
           detachedCriteria.add(Restrictions.like("trueName", "%" + trueName + "%"));
       }
       String role = model.getRole();
       if (StringUtils.isNotEmpty(role)) {
           detachedCriteria.add(Restrictions.eq("role", "%" + role + "%"));
       }
       String department = model.getDepartment();
       if (StringUtils.isNotEmpty(department)) {
           detachedCriteria.add(Restrictions.eq("department", "%" + department + "%" ));
       }
		adminService.findAdmin(pageBean);
		obj2JsonForEasyUI(pageBean);
		return NONE;
	}
	
	public String addAdmin(){
		try {
			adminService.addAdmin(model);
			obj2Json(ServerResponse.createSuccess("添加成功"));
		} catch (Exception e) {
			// TODO: handle exception
			obj2Json(ServerResponse.createError("添加失败"));
		}
		return NONE;
	}
	public String updateAdmin(){
		try {
			adminService.updateAdmin(model);
			obj2Json(ServerResponse.createSuccess("修改成功"));
		} catch (Exception e) {
			// TODO: handle exception
			obj2Json(ServerResponse.createError("修改失败"));
		}
		return NONE;
	}
	public String deleteAdmin(){
		adminService.deleteAdmin(ids);
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		obj2Json(ServerResponse.createSuccess("删除成功"));
		return NONE;
	}
}
