package com.situ.ssh.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.ssh.common.ServerResponse;
import com.situ.ssh.controller.base.BaseAction;
import com.situ.ssh.pojo.Bank;
import com.situ.ssh.service.IBankService;

@Controller
@Scope("prototype")
public class BankAction extends BaseAction<Bank>{

	@Autowired
	private IBankService bankService;

	public String findAll() throws UnsupportedEncodingException{
		System.out.println(model);
		String person = model.getPerson();
		if (StringUtils.isNotEmpty(person)) {
	           detachedCriteria.add(Restrictions.like("person", "%" + person  + "%"));
	       }
		bankService.findAll(pageBean);
		System.out.println(pageBean.toString());
		obj2JsonForEasyUI(pageBean);
		return NONE;
	}
	
	public String addBank(){
		try {
			bankService.addBank(model);
			obj2Json(ServerResponse.createSuccess("添加成功"));
		} catch (Exception e) {
			// TODO: handle exception
			obj2Json(ServerResponse.createError("添加失败"));
		}
		return NONE;
	}
	public String updateBank(){
		try {
			bankService.updateBank(model);
			obj2Json(ServerResponse.createSuccess("修改成功"));
		} catch (Exception e) {
			// TODO: handle exception
			obj2Json(ServerResponse.createError("修改失败"));
		}
		return NONE;
	}
	public String deleteBank(){
		bankService.deleteBank(ids);
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		obj2Json(ServerResponse.createSuccess("删除成功"));
		return NONE;
	}
}
