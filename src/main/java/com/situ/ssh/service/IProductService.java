package com.situ.ssh.service;

import com.situ.ssh.pojo.Products;
import com.situ.ssh.util.PageBean;

public interface IProductService {

	void findProduct(PageBean pageBean);

	void addProduct(Products model);

	void updateProduct(Products model);

	void deleteProduct(String ids);

}
