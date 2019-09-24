package com.lss.pc.base;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lss.pc.service.ClinicService;
import com.lss.pc.service.DoctorService;
import com.lss.pc.service.HomeService;

/**
 * service 代理工厂
 * 
 * @author MMHEART
 *
 */
@Component
public class ServiceManager {
	public static HomeService homeService;

	public static DoctorService doctorService;

	public static ClinicService clinicService;

	public HomeService getHomeService() {
		return homeService;
	}

	@Resource
	public void setHomeService(HomeService homeService) {
		ServiceManager.homeService = homeService;
	}

	public DoctorService getDoctorService() {
		return doctorService;
	}

	@Resource
	public void setDoctorService(DoctorService doctorService) {
		ServiceManager.doctorService = doctorService;
	}

	public ClinicService getClinicService() {
		return clinicService;
	}

	@Resource
	public void setClinicService(ClinicService clinicService) {
		ServiceManager.clinicService = clinicService;
	}
}
