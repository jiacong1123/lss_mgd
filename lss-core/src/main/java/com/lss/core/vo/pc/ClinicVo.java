package com.lss.core.vo.pc;

import java.util.List;

import com.lss.core.pojo.Clinic;

public class ClinicVo extends Clinic {
	private List<DoctorListVo> doctors;

	public List<DoctorListVo> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<DoctorListVo> doctors) {
		this.doctors = doctors;
	}
}
