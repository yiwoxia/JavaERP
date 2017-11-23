package com.situ.ssh.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.ssh.common.ServerResponse;
import com.situ.ssh.controller.base.BaseAction;
import com.situ.ssh.pojo.Store;
import com.situ.ssh.service.IStoreService;

@Controller
@Scope("prototype")
public class StoreAction extends BaseAction<Store>{

	@Autowired
	private IStoreService storeService;

	public String findAll() throws UnsupportedEncodingException{
		String name = model.getName();
		if (StringUtils.isNotEmpty(name)) {
	           detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
	       }
	       String person = model.getPerson();
	       if (StringUtils.isNotEmpty(person)) {
	           detachedCriteria.add(Restrictions.like("person", "%" + person + "%"));
	       }
		storeService.findAll(pageBean);
		System.out.println(pageBean.toString());
		obj2JsonForEasyUI(pageBean);
		return NONE;
	}
	
	public String addStore(){
		try {
			storeService.addStore(model);
			obj2Json(ServerResponse.createSuccess("添加成功"));
		} catch (Exception e) {
			// TODO: handle exception
			obj2Json(ServerResponse.createError("添加失败"));
		}
		return NONE;
	}
	public String updateStore(){
		try {
			storeService.updateStore(model);
			obj2Json(ServerResponse.createSuccess("修改成功"));
		} catch (Exception e) {
			// TODO: handle exception
			obj2Json(ServerResponse.createError("修改失败"));
		}
		return NONE;
	}
	public String deleteStore(){
		System.out.println(ids);
		storeService.deleteStore(ids);
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		obj2Json(ServerResponse.createSuccess("删除成功"));
		return NONE;
	}
}
