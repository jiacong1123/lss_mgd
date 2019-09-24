package com.lss.core.vo.admin;

import java.util.List;

import com.lss.core.pojo.Admin;
import com.lss.core.pojo.Role;

public class AdminListVo extends Admin {

	private String clinicname;

	private List<Role> roles;

	public String getClinicname() {
		return clinicname;
	}

	public void setClinicname(String clinicname) {
		this.clinicname = clinicname;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
