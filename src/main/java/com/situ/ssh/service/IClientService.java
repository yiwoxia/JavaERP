package com.situ.ssh.service;

import com.situ.ssh.pojo.Client;
import com.situ.ssh.util.PageBean;

public interface IClientService {

	void pageQuery(PageBean pageBean);

	boolean save(Client model);

	boolean update(Client model);

	boolean delete(String ids);

	void findAll(PageBean pageBean);
	
}
