package com.situ.ssh.dao.impl;

import org.springframework.stereotype.Repository;

import com.situ.ssh.dao.IOrderDao;
import com.situ.ssh.dao.base.impl.BaseDaoImpl;
import com.situ.ssh.pojo.Orders;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Orders> implements IOrderDao{

}
