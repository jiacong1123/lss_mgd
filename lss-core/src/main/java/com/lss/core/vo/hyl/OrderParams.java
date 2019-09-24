package com.lss.core.vo.hyl;

import com.lss.core.vo.PageParams;

public class OrderParams extends PageParams {
	private Integer doctorid;
	private String phone;

	public Integer getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = "".equals(phone) ? null : phone;
	}
}
