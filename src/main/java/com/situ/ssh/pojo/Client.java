package com.situ.ssh.pojo;

public class Client {

	private Integer id;
	private String name;
	private String level;
	private String zfb;
	private String weixin;
	private String salesperso;
	private String phone;
	private String address;
	private String id_card;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(Integer id, String name, String level, String zfb, String weixin, String salesperso, String phone,
			String address, String id_card) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.zfb = zfb;
		this.weixin = weixin;
		this.salesperso = salesperso;
		this.phone = phone;
		this.address = address;
		this.id_card = id_card;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getZfb() {
		return zfb;
	}

	public void setZfb(String zfb) {
		this.zfb = zfb;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getSalesperso() {
		return salesperso;
	}

	public void setSalesperso(String salesperso) {
		this.salesperso = salesperso;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", level=" + level + ", zfb=" + zfb + ", weixin=" + weixin
				+ ", salesperso=" + salesperso + ", phone=" + phone + ", address=" + address + ", id_card=" + id_card
				+ "]";
	}

}
