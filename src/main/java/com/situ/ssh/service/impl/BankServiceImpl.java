package com.situ.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.ssh.dao.IBankDao;
import com.situ.ssh.pojo.Bank;
import com.situ.ssh.pojo.Bank;
import com.situ.ssh.service.IBankService;
import com.situ.ssh.util.PageBean;

@Service
public class BankServiceImpl implements IBankService {

	@Autowired
	private IBankDao bankDao;
	@Override
	public void findAll(PageBean pageBean) {
		// TODO Auto-generated method stub
		bankDao.pageQuery(pageBean);
	}
	@Override
	public void addBank(Bank model) {
		// TODO Auto-generated method stub
		bankDao.save(model);
	}
	@Override
	public void updateBank(Bank model) {
		// TODO Auto-generated method stub
		bankDao.update(model);
	}
	@Override
	public void deleteBank(String ids) {
		// TODO Auto-generated method stub
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			Bank bank = new Bank();
			bank.setId(Integer.parseInt(id));
			bankDao.delete(bank);
		}
	}

}
