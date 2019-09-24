package com.lss.core.vo.admin;

import java.util.List;

import com.lss.core.pojo.Doctor;
import com.lss.core.vo.pc.DoctorListVo;

public class DoctorVo extends Doctor {
	private String shortname;

	private String jobtitle;

	private String department;

	private List<DoctorListVo> otherlist;
	
	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<DoctorListVo> getOtherlist() {
		return otherlist;
	}

	public void setOtherlist(List<DoctorListVo> otherlist) {
		this.otherlist = otherlist;
	}
}
