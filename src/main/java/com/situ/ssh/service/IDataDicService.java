package com.situ.ssh.service;

import java.util.List;

import com.situ.ssh.pojo.Data_dic;
import com.situ.ssh.util.PageBean;

public interface IDataDicService {

	void pageQuery(PageBean pageBean);

	void addDataDic(Data_dic model);

	void updateDataDic(Data_dic model);

	void deleteDicService(String ids);

	Data_dic findById(Integer id);

	List<Data_dic> findDepartmentdic();

	List<Data_dic> findUserRoledic();

	List<Data_dic> findCustomerLeveldic();
	
}
