package com.situ.ssh.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.situ.ssh.dao.IDataDicDao;
import com.situ.ssh.dao.base.impl.BaseDaoImpl;
import com.situ.ssh.pojo.Data_dic;
import com.situ.ssh.service.IDataDicService;
import com.situ.ssh.util.PageBean;
@Repository
public class DataDicDaoImpl extends BaseDaoImpl<Data_dic> implements IDataDicDao {

	@Override
	public List<Data_dic> findDepartmentdic() {
		// TODO Auto-generated method stub
		DetachedCriteria beautyCriteria = DetachedCriteria.forClass(Data_dic.class);
		beautyCriteria.add(Restrictions.eq("name", "部门"));
		List<Data_dic> list = (List<Data_dic>) getHibernateTemplate().findByCriteria(beautyCriteria);
		return list;
	}

	@Override
	public List<Data_dic> findUserRoledic() {
		// TODO Auto-generated method stub
		DetachedCriteria beautyCriteria = DetachedCriteria.forClass(Data_dic.class);
		beautyCriteria.add(Restrictions.eq("name", "登录角色"));
		List<Data_dic> list = (List<Data_dic>) getHibernateTemplate().findByCriteria(beautyCriteria);
		return list;
	}
	
	@Override
	public List<Data_dic> findCustomerLeveldic() {
		// TODO Auto-generated method stub
		DetachedCriteria beautyCriteria = DetachedCriteria.forClass(Data_dic.class);
		beautyCriteria.add(Restrictions.eq("name", "顾客等级"));
		List<Data_dic> list = (List<Data_dic>) getHibernateTemplate().findByCriteria(beautyCriteria);
		return list;
	}

}
