package com.lss.core.vo.admin;

import java.util.List;

import com.lss.core.pojo.WorkOrder;

public class WorkOrderDetails extends WorkOrder {
	private String clinicname;
	private String project;
	private String adminname;
	private String doctorname;
	private UserVo user;
	private List<WorkRecordVo> records;

	public String getClinicname() {
		return clinicname;
	}

	public void setClinicname(String clinicname) {
		this.clinicname = clinicname;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getDoctorname() {
		return doctorname;
	}

	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public List<WorkRecordVo> getRecords() {
		return records;
	}

	public void setRecords(List<WorkRecordVo> records) {
		this.records = records;
	}
}
