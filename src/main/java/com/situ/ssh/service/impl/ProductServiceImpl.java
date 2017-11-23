package com.situ.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.ssh.dao.IProductDao;
import com.situ.ssh.pojo.Products;
import com.situ.ssh.service.IProductService;
import com.situ.ssh.util.PageBean;
@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao productDao;
	@Override
	public void findProduct(PageBean pageBean) {
		// TODO Auto-generated method stub
		productDao.pageQuery(pageBean);
	}
	@Override
	public void addProduct(Products model) {
		// TODO Auto-generated method stub
		productDao.save(model);
	}
	@Override
	public void updateProduct(Products model) {
		// TODO Auto-generated method stub
		productDao.update(model);
	}
	@Override
	public void deleteProduct(String ids) {
		// TODO Auto-generated method stub
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			Products product = new Products();
			product.setId(Integer.parseInt(id));
			productDao.delete(product);
		}
	}

}
