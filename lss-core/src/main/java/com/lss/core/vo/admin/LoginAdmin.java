package com.lss.core.vo.admin;

import java.util.List;

import com.lss.core.vo.ztc.ZtcImGuidMember;

public class LoginAdmin {
	/**
	 * 管理员id
	 */
	private Integer adminid;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 诊所id
	 */
	private Integer clinicid;

	/**
	 * 医生id
	 */
	private Integer doctorid;

	/**
	 * 是否是管理员
	 */
	private int isadmin = 0;

	/**
	 * 角色ids
	 */
	private List<Integer> roles;
	
	/** 登录名*/
	private String loginame;

	/** 客户直通车 IM 登录信息*/
	private ZtcImGuidMember guidMember;
	
	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getClinicid() {
		return clinicid;
	}

	public void setClinicid(Integer clinicid) {
		this.clinicid = clinicid;
	}

	public Integer getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}

	public int getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}

	public String getLoginame() {
		return loginame;
	}

	public void setLoginame(String loginame) {
		this.loginame = loginame;
	}

	public ZtcImGuidMember getGuidMember() {
		return guidMember;
	}

	public void setGuidMember(ZtcImGuidMember guidMember) {
		this.guidMember = guidMember;
	}
	
}
