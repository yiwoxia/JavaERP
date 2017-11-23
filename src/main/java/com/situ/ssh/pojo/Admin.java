package com.situ.ssh.pojo;

public class Admin {
	private Integer id;
	private String name;
	private String password;
	//真实姓名
	private String trueName;
	//角色
	private String role;
	//所属部门
	private String department;
	public Admin() {
		super();
	}
	

	public Admin(Integer id, String name, String password, String trueName, String role, String department) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.trueName = trueName;
		this.role = role;
		this.department = department;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", password=" + password + ", trueName=" + trueName + ", role="
				+ role + ", department=" + department + "]";
	}


}