package com.lss.core.vo.admin.params;

import java.util.Date;

import com.lss.core.vo.PageParams;

public class ClueParams extends PageParams {

	private String orderno;

	private String province;
	private String city;
	private String area;
	private String usertype;
	private String usertypename;

	private Date reservedate;
	private String reservetime;
	private String worknotes;

	private String code;
	private String clinicName;
	private String isShop;

	private String name;
	private String phone;
	private int type;
	private String status;

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = "".equals(orderno) ? null : orderno;
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

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = "".equals(usertype) ? null : usertype;
	}

	public String getUsertypename() {
		return usertypename;
	}

	public void setUsertypename(String usertypename) {
		this.usertypename = "".equals(usertypename) ? null : usertypename;
	}

	public Date getReservedate() {
		return reservedate;
	}

	public void setReservedate(Date reservedate) {
		this.reservedate = reservedate;
	}

	public String getReservetime() {
		return reservetime;
	}

	public void setReservetime(String reservetime) {
		this.reservetime = "".equals(reservetime) ? null : reservetime;
	}

	public String getWorknotes() {
		return worknotes;
	}

	public void setWorknotes(String worknotes) {
		this.worknotes = "".equals(worknotes) ? null : worknotes;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = "".equals(code) ? null : code;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = "".equals(clinicName) ? null : clinicName;
	}

	public String getIsShop() {
		return isShop;
	}

	public void setIsShop(String isShop) {
		this.isShop = "".equals(isShop) ? null : isShop;
	}

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
