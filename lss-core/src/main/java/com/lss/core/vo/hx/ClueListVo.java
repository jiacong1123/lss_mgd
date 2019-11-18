package com.lss.core.vo.hx;

import java.util.Date;

import com.lss.core.pojo.WorkOrder;

public class ClueListVo extends WorkOrder {
	private String name;
	private String phone;
	private String province;
	private String city;
	private String area;
	private String project;
	private String source;
	private Integer sex;
	private Date sourcedate;
	private Integer age;

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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getSourcedate() {
		return sourcedate;
	}

	public void setSourcedate(Date sourcedate) {
		this.sourcedate = sourcedate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
