package com.situ.ssh.dao.impl;
import org.springframework.stereotype.Repository;

import com.situ.ssh.dao.IStudentDao;
import com.situ.ssh.dao.base.impl.BaseDaoImpl;
import com.situ.ssh.pojo.Student;

@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements IStudentDao {

}