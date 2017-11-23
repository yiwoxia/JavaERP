package com.situ.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.ssh.dao.IClientDao;
import com.situ.ssh.pojo.Client;
import com.situ.ssh.pojo.Staff;
import com.situ.ssh.service.IClientService;
import com.situ.ssh.util.PageBean;
@Service
public class ClientServiceImpl implements IClientService {
	@Autowired
	private IClientDao clientDao;

	@Override
	public void pageQuery(PageBean pageBean) {
		clientDao.pageQuery(pageBean);
	}


	@Override
	public boolean save(Client model) {
		return clientDao.save(model);
	}

	@Override
	public boolean update(Client model) {
		return clientDao.update(model);
	}

	@Override
	public void findAll(PageBean pageBean) {
		// TODO Auto-generated method stub
		clientDao.pageQuery(pageBean);
	}
	
	@Override
	public boolean delete(String ids) {
		String[] idArray = ids.split(",");;
		for (String id : idArray) {
			Client client = new Client();
			client.setId(Integer.parseInt(id));
			clientDao.delete(client);
		}
		return true;
	}

}
