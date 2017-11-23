package com.situ.ssh.dao;

import java.util.List;

import com.situ.ssh.dao.base.IBaseDao;
import com.situ.ssh.pojo.Data_dic;

public interface IDataDicDao extends IBaseDao<Data_dic>{
	

	List<Data_dic> findDepartmentdic();

	List<Data_dic> findUserRoledic();

	List<Data_dic> findCustomerLeveldic();

}
