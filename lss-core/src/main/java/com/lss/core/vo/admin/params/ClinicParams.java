package com.lss.core.vo.admin.params;

import com.lss.core.vo.PageParams;

public class ClinicParams extends PageParams {
	private String name;

	private Integer type;

	private String province;

	private String city;

	private String area;

	private Integer status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = "".equals(name.trim()) ? null : name.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = "".equals(area) ? null : area;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
