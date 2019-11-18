package com.lss.core.vo.admin;

public class UserExeclVo {

	private String phone;
	private String name;
	private String age;
	private String strtime;
	private String source;
	 /** 2级来源名称*/
    private String sourcename2;
	 /** 意愿等级*/
    private String level;
	private String admin;
	private String url;
	private String notes;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getStrtime() {
		return strtime;
	}

	public void setStrtime(String strtime) {
		this.strtime = strtime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourcename2() {
		return sourcename2;
	}

	public void setSourcename2(String sourcename2) {
		this.sourcename2 = sourcename2;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
}
