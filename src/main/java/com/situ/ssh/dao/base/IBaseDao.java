package com.situ.ssh.dao.base;
import java.io.Serializable;
import java.util.List;

import com.situ.ssh.util.PageBean;

public interface IBaseDao<T> {
	public boolean save(T entity);
	public boolean delete(T entity);
	public boolean update(T entity);
	public T findById(Serializable id);
	public List<T> findAll();
	public void pageQuery(PageBean pageBean);
}