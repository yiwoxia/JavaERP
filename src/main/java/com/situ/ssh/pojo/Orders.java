package com.situ.ssh.pojo;

import java.util.Date;

public class Orders {


	private Integer id;
	//订单编号
	private String order_no;
	//下单者
	private String user;
	//待付款者
	private String payer;
	//总金额
	private Double payment;
	//订单状态 0-已取消  10-未付款 20-已完成 
	private Integer status;
	//订单完成时间
	private Date time;
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(Integer id, String order_no, String user, String payer, Double payment, Integer status, Date time) {
		super();
		this.id = id;
		this.order_no = order_no;
		this.user = user;
		this.payer = payer;
		this.payment = payment;
		this.status = status;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", order_no=" + order_no + ", user=" + user + ", payer=" + payer + ", payment="
				+ payment + ", status=" + status + ", time=" + time + "]";
	}

	

}
