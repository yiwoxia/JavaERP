package com.situ.ssh.pojo;

public class Products {

	private Integer id;
	//产品编号
	private String product_id;
	//产品名称
	private String name;
	//所存仓库的id
	private Integer store_id;
	//规格
	private String specification;
	//型号
	private String type;
	//产地
	private String place;
	//数量
	private Integer amount;
	//单价
	private Double price;

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Products(Integer id, String product_id, String name, Integer store_id, String specification, String type,
			String place, Integer amount, Double price) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.name = name;
		this.store_id = store_id;
		this.specification = specification;
		this.type = type;
		this.place = place;
		this.amount = amount;
		this.price = price;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Products [id=" + id + ", product_id=" + product_id + ", name=" + name + ", store_id=" + store_id
				+ ", specification=" + specification + ", type=" + type + ", place=" + place + ", amount=" + amount
				+ ", price=" + price + "]";
	}
	
	

}
