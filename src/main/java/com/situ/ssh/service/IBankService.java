package com.situ.ssh.service;

import com.situ.ssh.pojo.Bank;
import com.situ.ssh.util.PageBean;

public interface IBankService {

	void findAll(PageBean pageBean);

	void addBank(Bank model);

	void updateBank(Bank model);

	void deleteBank(String ids);

}
