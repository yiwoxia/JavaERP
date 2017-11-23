package com.situ.ssh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.ssh.controller.base.BaseAction;
import com.situ.ssh.pojo.Orders;
import com.situ.ssh.service.IOrderService;

@Controller
@Scope("prototype")
public class OrdersAction extends BaseAction<Orders>{

	@Autowired
	private IOrderService orderService;

	public String findAll(){
		System.out.println("OrdersAction.findAll()");
		orderService.findAll(pageBean);
		System.out.println(pageBean.toString());
		obj2JsonForEasyUI(pageBean);
		return NONE;
	}
}
