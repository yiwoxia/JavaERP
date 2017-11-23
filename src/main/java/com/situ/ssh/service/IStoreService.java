package com.situ.ssh.service;

import com.situ.ssh.pojo.Store;
import com.situ.ssh.util.PageBean;

public interface IStoreService {

	void findAll(PageBean pageBean);

	void addStore(Store model);

	void updateStore(Store model);

	void deleteStore(String ids);

}
