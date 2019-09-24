package com.lss.core.vo.admin.params;

import java.util.List;

public class RoleParams {
	private Integer adminid;

	private List<Integer> roleids;

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public List<Integer> getRoleids() {
		return roleids;
	}

	public void setRoleids(List<Integer> roleids) {
		this.roleids = roleids;
	}
}
