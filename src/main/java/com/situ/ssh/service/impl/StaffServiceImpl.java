package com.situ.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.ssh.dao.IStaffDao;
import com.situ.ssh.pojo.Staff;
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

	@Override
	public boolean save(Staff model) {
		return staffDao.save(model);
	}

	@Override
	public boolean update(Staff model) {
		return staffDao.update(model);
	}

	@Override
	public boolean delete(String ids) {
		String[] idArray = ids.split(",");;
		for (String id : idArray) {
			Staff staff = new Staff();
			staff.setId(Integer.parseInt(id));
			staffDao.delete(staff);
		}
		return true;
	}


}
