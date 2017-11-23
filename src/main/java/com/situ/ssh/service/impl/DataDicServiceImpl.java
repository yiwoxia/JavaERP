package com.situ.ssh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.ssh.dao.IDataDicDao;
import com.situ.ssh.pojo.Data_dic;
import com.situ.ssh.service.IDataDicService;
import com.situ.ssh.util.PageBean;

@Service
public class DataDicServiceImpl implements IDataDicService {

	@Autowired
	private IDataDicDao dataDicDao;
	@Override
	public void pageQuery(PageBean pageBean) {
		// TODO Auto-generated method stub
		dataDicDao.pageQuery(pageBean);
	}
	@Override
	public void addDataDic(Data_dic model) {
		// TODO Auto-generated method stub
		dataDicDao.save(model);
	}
	@Override
	public void updateDataDic(Data_dic model) {
		// TODO Auto-generated method stub
		dataDicDao.update(model);
	}
	@Override
	public void deleteDicService(String ids) {
		// TODO Auto-generated method stub
		System.out.println(ids);
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			Data_dic data_dic = new Data_dic();
			data_dic.setId(Integer.parseInt(id));
			dataDicDao.delete(data_dic);
		}
	}
	@Override
	public Data_dic findById(Integer id) {
		// TODO Auto-generated method stub
		return dataDicDao.findById(id);
	}
	@Override
	public List<Data_dic> findDepartmentdic() {
		// TODO Auto-generated method stub
		return dataDicDao.findDepartmentdic();
	}
	@Override
	public List<Data_dic> findUserRoledic() {
		// TODO Auto-generated method stub
		return dataDicDao.findUserRoledic();
	}
	@Override
	public List<Data_dic> findCustomerLeveldic() {
		// TODO Auto-generated method stub
		return dataDicDao.findCustomerLeveldic();
	}

}