package com.lss.core.vo.admin.params;

import java.util.Date;

import com.lss.core.vo.PageParams;

public class UserParams extends PageParams {
	private String name;
	private String phone;
	private String province;
	private String city;
	private Integer sourceid;
	private Date start;
	private Date end;
	
	/** 状态   0未分配 10 新分配  1待跟进  2已预约 3已到店 4已完成 5已关闭 10 新分配*/
	private Integer orderstatus;
	/** 所属销售 */
	private Integer adminid;

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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Integer getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(Integer orderstatus) {
		this.orderstatus = orderstatus;
	}

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}
	
	
}
