package com.situ.ssh.pojo;

import java.io.Serializable;
import java.util.Date;

public class Bank implements Serializable {

	private Integer id;
	//付款单位
	private String payer;
	//经手人
	private String person;
	//付款账户
	private String account;
	//付款账户名
	private String accountName;
	//金额
	private Double money;
	//备注
	private String remark;
	//下单时间
	private Date time;
	public Bank(Integer id, String payer, String person, String account, String accountName, Double money,
			String remark, Date time) {
		super();
		this.id = id;
		this.payer = payer;
		this.person = person;
		this.account = account;
		this.accountName = accountName;
		this.money = money;
		this.remark = remark;
		this.time = time;
	}
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Bank [id=" + id + ", payer=" + payer + ", person=" + person + ", account=" + account + ", accountName="
				+ accountName + ", money=" + money + ", remark=" + remark + ", time=" + time + "]";
	}
	
}
