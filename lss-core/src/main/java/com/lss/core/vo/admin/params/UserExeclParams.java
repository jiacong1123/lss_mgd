package com.lss.core.vo.admin.params;

public class UserExeclParams {
	private String name;
	private String phone;
	private String province;
	private String city;
	private Integer sourceid;
	private String start;
	private String end;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = "".equals(name) ? null : name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = "".equals(phone) ? null : phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = "".equals(province) ? null : province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = "".equals(city) ? null : city;
	}

	public Integer getSourceid() {
		return sourceid;
	}

	public void setSourceid(Integer sourceid) {
		this.sourceid = sourceid;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = "".equals(start) ? null : start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = "".equals(end) ? null : end;
	}
}
