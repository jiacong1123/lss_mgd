package com.lss.core.vo.admin.params;

public class UserExeclParams {
	private String name;
	private String phone;
	private String province;
	private String city;
	private String sourceid;
	private String sourceid2;
	private String start;
	private String end;
	/** 状态   0未分配 10 新分配  1待跟进  2已预约 3已到店 4已完成 5已关闭 10 新分配*/
	private String orderstatus;
	
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
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	public String getSourceid2() {
		return sourceid2;
	}
	public void setSourceid2(String sourceid2) {
		this.sourceid2 = sourceid2;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	
	

	
}
