package com.situ.ssh.controller;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.ssh.common.ServerResponse;
import com.situ.ssh.controller.base.BaseAction;
import com.situ.ssh.pojo.Data_dic;
import com.situ.ssh.service.IDataDicService;

@Controller
@Scope("prototype")
public class DataDicAction extends BaseAction<Data_dic>{

private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@Autowired
	private IDataDicService dataDicService;
	
	public String deleteDataDic(){
		try {
				dataDicService.deleteDicService(ids);
			obj2Json(ServerResponse.createSuccess("删除成功"));
		} catch (Exception e) {
			// TODO: handle exception
			obj2Json(ServerResponse.createError("删除失败"));
		}
		return NONE;
	}
	
	public String updateDataDic(){
		try {
			dataDicService.updateDataDic(model);
			obj2Json(ServerResponse.createSuccess("修改成功"));
		} catch (Exception e) {
			// TODO: handle exception
			obj2Json(ServerResponse.createError("修改失败"));
		}
		return NONE;
	}
	
	public String addDataDic(){
		try {
			dataDicService.addDataDic(model);
			obj2Json(ServerResponse.createSuccess("添加成功"));
		} catch (Exception e) {
			// TODO: handle exception
			obj2Json(ServerResponse.createError("添加失败"));
		}
		return NONE;
		
	}
	
	public String findDepartment(){
		DetachedCriteria beautyCriteria = DetachedCriteria.forClass(Data_dic.class);
		beautyCriteria.add(Restrictions.eq("name", "部门"));
		pageBean.setDetachedCriteria(beautyCriteria);
		dataDicService.pageQuery(pageBean);
		System.out.println(pageBean);
		obj2JsonForEasyUI(pageBean);
		return NONE;
	}
	
	public String findDepartmentdic(){
		List<Data_dic> list = dataDicService.findDepartmentdic();
		arr2json(list);
		return NONE;
	}
	public String findUserRoledic(){
		List<Data_dic> list = dataDicService.findUserRoledic();
		arr2json(list);
		return NONE;
	}
	
	public String findCustomerLeveldic(){
		List<Data_dic> list = dataDicService.findCustomerLeveldic();
		arr2json(list);
		return NONE;
	}
	
	public String findUserRole(){
		DetachedCriteria beautyCriteria = DetachedCriteria.forClass(Data_dic.class);
		beautyCriteria.add(Restrictions.eq("name", "登录角色"));
		pageBean.setDetachedCriteria(beautyCriteria);
		dataDicService.pageQuery(pageBean);
		System.out.println(pageBean);
		obj2JsonForEasyUI(pageBean);
		return NONE;
	}

}
