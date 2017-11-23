package com.situ.ssh.service;

import com.situ.ssh.pojo.Staff;
import com.situ.ssh.util.PageBean;

public interface IStaffService {
	/**查询*/
	void pageQuery(PageBean pageBean);
	/**添加*/
	boolean save(Staff model);
	/**修改*/
	boolean update(Staff model);
	/**删除*/
	boolean delete(String ids);

}
