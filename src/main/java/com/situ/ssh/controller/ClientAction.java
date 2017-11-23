package com.situ.ssh.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.ssh.common.ServerResponse;
import com.situ.ssh.controller.base.BaseAction;
import com.situ.ssh.pojo.Client;
import com.situ.ssh.service.IClientService;

@Controller
@Scope("prototype")
public class ClientAction extends BaseAction<Client> {
	@Autowired
	private IClientService clientService;
	
	public String findAll() throws UnsupportedEncodingException{
		System.out.println("ClientAction.findAll()");
		String name = model.getName();
	       if (StringUtils.isNotEmpty(name)) {
	           detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
	       }
	       String level = model.getLevel();
	       if (StringUtils.isNotEmpty(level)) {
	           detachedCriteria.add(Restrictions.like("level", "%" + level + "%"));
	       }
	       String salesperso = model.getSalesperso();
	       if (StringUtils.isNotEmpty(salesperso)) {
	           detachedCriteria.add(Restrictions.like("salesperso", "%" + salesperso + "%"));
	       }
	       clientService.findAll(pageBean);
		obj2JsonForEasyUI(pageBean);
		return NONE;
	}
	
	public String addBatch(){
		ServerResponse  serverResponse = null;
		System.out.println(model);
		if (clientService.save(model)) {
			serverResponse = ServerResponse.createSuccess("添加成功");
		}else{
			serverResponse = ServerResponse.createError("添加失败");
		}
		obj2Json(serverResponse);
		System.out.println(serverResponse);
		return NONE;
	}
	
	public String updateBatch(){
		ServerResponse  serverResponse = null;
		if (clientService.update(model)) {
			serverResponse = ServerResponse.createSuccess("修改成功");
		}else{
			serverResponse = ServerResponse.createError("修改失败");
		}
		obj2Json(serverResponse);
		return NONE;
	}
	public String deleteBatch(){
		ServerResponse serverResponse = null;
		if (clientService.delete(ids)) {
			serverResponse = ServerResponse.createSuccess("删除成功");
		}else{
			serverResponse = ServerResponse.createError("删除失败");
		}
		obj2Json(serverResponse);
		return NONE;
		
	}

}
