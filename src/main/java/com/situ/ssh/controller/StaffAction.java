package com.situ.ssh.controller;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.ssh.common.ServerResponse;
import com.situ.ssh.controller.base.BaseAction;
import com.situ.ssh.pojo.Staff;
import com.situ.ssh.service.IStaffService;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{
	@Autowired
	private IStaffService staffService;
	
	
	public String pageQuery(){
		//在Datacherdcriteria中封装离线查询条件
		String name = model.getName();
		if (StringUtils.isNoneEmpty(name)) {
			//添加过滤条件根据名字关键字模糊查询
			detachedCriteria.add(Restrictions.like("name","%" + name + "%"));
		}
		Integer num = model.getNum();
		if (num != null) {
			////添加过滤条件，根据年龄查询
			detachedCriteria.add(Restrictions.eq("num",num));
		}
		Integer role = model.getRole();
		if (role != null) {
			////添加过滤条件，根据年龄查询
			detachedCriteria.add(Restrictions.eq("role",role));
		}
		staffService.pageQuery(pageBean);
		obj2JsonForEasyUI(pageBean);
		return NONE;//The action execution was successful but do not show a view. 
	}

	public String addBatch(){
		ServerResponse  serverResponse = null;
		if (staffService.save(model)) {
			serverResponse = ServerResponse.createSuccess("添加成功");
		}else{
			serverResponse = ServerResponse.createError("添加失败");
		}
		obj2Json(serverResponse);
		System.out.println(serverResponse);
		return NONE;
	}
	
	public String updateBatch(){
		ServerResponse  serverResponse = null;
		if (staffService.update(model)) {
			serverResponse = ServerResponse.createSuccess("修改成功");
		}else{
			serverResponse = ServerResponse.createError("修改失败");
		}
		obj2Json(serverResponse);
		return NONE;
	}
	public String deleteBatch(){
		ServerResponse serverResponse = null;
		if (staffService.delete(ids)) {
			serverResponse = ServerResponse.createSuccess("删除成功");
		}else{
			serverResponse = ServerResponse.createError("删除失败");
		}
		obj2Json(serverResponse);
		return NONE;
		
	}

}
