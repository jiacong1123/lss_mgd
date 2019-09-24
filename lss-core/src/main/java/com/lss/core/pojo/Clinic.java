package com.lss.core.pojo;

import java.util.Date;

public class Clinic {
	private Integer clinicid;

	private String name;

	private String image;

	private String shortname;

	private String mainproject;

	private String telephone;

	private String principal;

	private String phone;

	private String province;

	private String city;

	private String area;

	private String address;

	private Integer type;

	private String description;

	private Integer status;

	private Date createtime;

	private String milieupicture;

	private String devicepicture;

	public Integer getClinicid() {
		return clinicid;
	}

	public void setClinicid(Integer clinicid) {
		this.clinicid = clinicid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image == null ? null : image.trim();
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname == null ? null : shortname.trim();
	}

	public String getMainproject() {
		return mainproject;
	}

	public void setMainproject(String mainproject) {
		this.mainproject = mainproject == null ? null : mainproject.trim();
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone == null ? null : telephone.trim();
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal == null ? null : principal.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getMilieupicture() {
		return milieupicture;
	}

	public void setMilieupicture(String milieupicture) {
		this.milieupicture = milieupicture;
	}

	public String getDevicepicture() {
		return devicepicture;
	}

	public void setDevicepicture(String devicepicture) {
		this.devicepicture = devicepicture;
	}
}