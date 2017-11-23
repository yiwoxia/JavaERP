package com.situ.ssh.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.omg.PortableInterceptor.NON_EXISTENT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.ssh.common.ServerResponse;
import com.situ.ssh.controller.base.BaseAction;
import com.situ.ssh.pojo.Products;
import com.situ.ssh.service.IProductService;

@Controller
@Scope("prototype")
public class ProductAction extends BaseAction<Products> {

	@Autowired
	private IProductService productService;
	
	public String findProduct() throws UnsupportedEncodingException{
		String name = model.getName();
		if (StringUtils.isNotEmpty(name)) {
			detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
		}
		productService.findProduct(pageBean);
		obj2JsonForEasyUI(pageBean);
		return NONE;
	}
	public String addProduct(){
		try {
			productService.addProduct(model);
			obj2Json(ServerResponse.createSuccess("添加成功"));
		} catch (Exception e) {
			// TODO: handle exception
			obj2Json(ServerResponse.createError("添加失败"));
		}
		return NONE;
	}
	public String updateProduct(){
		try {
			productService.updateProduct(model);
			obj2Json(ServerResponse.createSuccess("修改成功"));
		} catch (Exception e) {
			// TODO: handle exception
			obj2Json(ServerResponse.createError("修改失败"));
		}
		return NONE;
	}
	public String deleteProduct(){
		productService.deleteProduct(ids);
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		obj2Json(ServerResponse.createSuccess("删除成功"));
		return NONE;
	}
	
}
