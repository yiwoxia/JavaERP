package com.situ.ssh.service;

import com.situ.ssh.pojo.Admin;
import com.situ.ssh.util.PageBean;

public interface IAdminService {
	public Admin login(Admin admin);

	public void findAdmin(PageBean pageBean);

	public void addAdmin(Admin model);

	public void updateAdmin(Admin model);

	public void deleteAdmin(String ids);
}