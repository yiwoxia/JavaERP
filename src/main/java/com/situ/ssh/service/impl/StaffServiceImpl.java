package com.situ.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.ssh.dao.IStaffDao;
import com.situ.ssh.service.IStaffService;
import com.situ.ssh.util.PageBean;

@Service
public class StaffServiceImpl implements IStaffService{
	@Autowired
	private IStaffDao staffDao;

	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}


}
