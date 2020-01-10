package com.lss.core.pojo;

import java.io.Serializable;

public class UserDetailExeclVo implements Serializable {

	private String name;
	private String phone;
	private String tagname;
	private String tagname2;
	private String sourcedate;
	private String allottime;
	private String level;
	private String adminName;//所属人员
	
	
	
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public String getTagname2() {
		return tagname2;
	}
	public void setTagname2(String tagname2) {
		this.tagname2 = tagname2;
	}
	public String getSourcedate() {
		return sourcedate;
	}
	public void setSourcedate(String sourcedate) {
		this.sourcedate = sourcedate;
	}
	public String getAllottime() {
		return allottime;
	}
	public void setAllottime(String allottime) {
		this.allottime = allottime;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
