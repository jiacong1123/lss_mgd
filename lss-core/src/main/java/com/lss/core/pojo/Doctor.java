package com.lss.core.pojo;

import java.util.Date;

public class Doctor {
	private Integer doctorid;

	private Integer clinicid;

	private String name;

	private String phone;

	private Integer sex;

	private Integer titleid;

	private String photo;

	private String goodat;

	private String synopsis;

	private Integer status;

	private Date createtime;

	private Integer ksid;

	public Integer getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getTitleid() {
		return titleid;
	}

	public void setTitleid(Integer titleid) {
		this.titleid = titleid;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo == null ? null : photo.trim();
	}

	public String getGoodat() {
		return goodat;
	}

	public void setGoodat(String goodat) {
		this.goodat = goodat == null ? null : goodat.trim();
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis == null ? null : synopsis.trim();
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

	public Integer getKsid() {
		return ksid;
	}

	public void setKsid(Integer ksid) {
		this.ksid = ksid;
	}
}