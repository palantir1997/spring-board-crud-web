package com.springboot.springmyboard.model;


public class boardVo {
	private int pkid;
	private int fkmember;
	private String title;
	private String content;
	private int viewcount;
	private String regdate;
	private String name;
	private String img_path;
	
	public int getPkid() {
		return pkid;
	}
	
	public void setPkid(int pkid) {
		this.pkid = pkid;
	}
	
	public int getFkmember() {
		return fkmember;
	}
	
	public void setFkmember(int fkmember) {
		this.fkmember = fkmember;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getViewcount() {
		return viewcount;
	}
	
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	
	public String getRegdate() {
		return regdate;
	}
	
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getImg_path() { return img_path; }
	public void setImg_path(String img_path) { this.img_path = img_path; }

	@Override
	public String toString() {
		return "boardVo [pkid=" + pkid + ", fkmember=" + fkmember + ", title=" + title + ", content=" + content
				+ ", viewcount=" + viewcount + ", regdate=" + regdate + ", name=" + name + "]";
	}
}
