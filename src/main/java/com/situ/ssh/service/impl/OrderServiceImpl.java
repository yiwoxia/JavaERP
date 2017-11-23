package com.situ.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.ssh.dao.IOrderDao;
import com.situ.ssh.service.IOrderService;
import com.situ.ssh.util.PageBean;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderDao orderDao;
	@Override
	public void findAll(PageBean pageBean) {
		// TODO Auto-generated method stub
		orderDao.pageQuery(pageBean);
	}

}
