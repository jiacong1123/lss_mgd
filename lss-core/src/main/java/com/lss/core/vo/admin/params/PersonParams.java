package com.lss.core.vo.admin.params;

import java.util.List;

public class PersonParams {
	private Integer roleid;//角色ID
	private Integer clinicid;//诊所ID
	private Integer orgid;//机构ID
	private List<Integer> adminids;
	/**
	 * 管理员id
	 */
	private Integer adminid;
	
	

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public List<Integer> getAdminids() {
		return adminids;
	}

	public void setAdminids(List<Integer> adminids) {
		this.adminids = adminids;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getClinicid() {
		return clinicid;
	}

	public void setClinicid(Integer clinicid) {
		this.clinicid = clinicid;
	}
}
