package com.situ.ssh.pojo;

public class Store {

	private Integer id;
	private String name;
	//所在位置
	private String location;
	//总容量
	private String amount;
	//负责人
	private String person;
	
	public Store() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", location=" + location + ", amount=" + amount + ", person="
				+ person + "]";
	}
	
	
}
