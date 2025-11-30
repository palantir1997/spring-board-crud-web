package com.springboot.springmyboard.model;


public class memberVo {
	private int pkid;
	private String user_id;
	private String user_pw;
	private String name;
	private String regdate;
	
	public int getPkid() {
		return pkid;
	}
	
	public void setPkid(int pkid) {
		this.pkid = pkid;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_pw() {
		return user_pw;
	}
	
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "memberVo [pkid=" + pkid + ", user_id=" + user_id + ", user_pw=" + user_pw + ", name=" + name
				+ ", regdate=" + regdate + "]";
	}
}
