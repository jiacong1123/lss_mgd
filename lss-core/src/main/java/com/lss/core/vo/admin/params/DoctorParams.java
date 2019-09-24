package com.lss.core.vo.admin.params;

import com.lss.core.vo.PageParams;

public class DoctorParams extends PageParams {
	private String name;

	private Integer clinicid;

	private Integer titleid;
	
	private Integer status;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = "".equals(name) ? null : name;
	}

	public Integer getClinicid() {
		return clinicid;
	}

	public void setClinicid(Integer clinicid) {
		this.clinicid = clinicid;
	}

	public Integer getTitleid() {
		return titleid;
	}

	public void setTitleid(Integer titleid) {
		this.titleid = titleid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
