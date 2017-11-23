package com.situ.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.ssh.dao.IStoreDao;
import com.situ.ssh.pojo.Store;
import com.situ.ssh.pojo.Store;
import com.situ.ssh.service.IStoreService;
import com.situ.ssh.util.PageBean;

@Service
public class StoreServiceImpl implements IStoreService {

	@Autowired
	private IStoreDao storeDao;
	@Override
	public void findAll(PageBean pageBean) {
		// TODO Auto-generated method stub
		storeDao.pageQuery(pageBean);
	}
	@Override
	public void addStore(Store model) {
		// TODO Auto-generated method stub
		storeDao.save(model);
	}
	@Override
	public void updateStore(Store model) {
		// TODO Auto-generated method stub
		storeDao.update(model);
	}
	@Override
	public void deleteStore(String ids) {
		// TODO Auto-generated method stub
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			Store store = new Store();
			store.setId(Integer.parseInt(id));
			storeDao.delete(store);
		}
	}

}
