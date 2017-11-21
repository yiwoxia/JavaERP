package com.situ.ssh.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.situ.ssh.controller.base.BaseAction;
import com.situ.ssh.pojo.Student;
import com.situ.ssh.service.IStudentService;
import com.situ.ssh.pojo.Admin;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student>{
	private Admin admin;
	@Autowired
	private IStudentService studentService;
	
	public String pageQuery() {
		studentService.pageQuery(pageBean);
		obj2JsonForEasyUI(pageBean);
		return NONE;
	}
	
	public String list(){
		//查询所有学生列表
		List<Student> list = studentService.findAll();
		//放到域对象占用
		ActionContext.getContext().getContextMap().put("list", list);
		return "list";
		
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}