package com.situ.ssh.service;
import java.util.List;

import com.situ.ssh.pojo.Student;
import com.situ.ssh.util.PageBean;

public interface IStudentService {
	public void pageQuery(PageBean pageBean);

	public List<Student> findAll();
}
