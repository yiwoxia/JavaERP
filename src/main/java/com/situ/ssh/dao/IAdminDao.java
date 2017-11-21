package com.situ.ssh.dao;
import com.situ.ssh.dao.base.IBaseDao;
import com.situ.ssh.pojo.Admin;

public interface IAdminDao extends IBaseDao<Admin>{
	
	public Admin login(Admin admin);
}
