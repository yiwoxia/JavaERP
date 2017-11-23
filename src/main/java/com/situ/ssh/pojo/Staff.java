package com.situ.ssh.pojo;

import java.util.Date;

public class Staff {
	
	/**编号*/
	private Integer id;
	/**ID*/
	private Integer num;
	/**员工账号*/
	private String account;
	/**员工密码*/
	private String password;
	/**员工角色*/
	private Integer role;
	/**员工真实姓名*/
	private String name;
	/**手机号*/
	private Integer phone;
	/**QQ*/
	private Integer qq;
	/**微信*/
	private String wechat;
	/**注册时间*/
	private Date date;
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Staff(Integer id, Integer num, String account, String password, Integer role, String name, Integer phone,
			Integer qq, String wechat, Date date) {
		super();
		this.id = id;
		this.num = num;
		this.account = account;
		this.password = password;
		this.role = role;
		this.name = name;
		this.phone = phone;
		this.qq = qq;
		this.wechat = wechat;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Integer getQq() {
		return qq;
	}

	public void setQq(Integer qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", num=" + num + ", account=" + account + ", password=" + password + ", role=" + role
				+ ", name=" + name + ", phone=" + phone + ", qq=" + qq + ", wechat=" + wechat + ", date=" + date + "]";
	}

}
