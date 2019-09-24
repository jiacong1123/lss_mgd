package com.lss.pc.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.core.pojo.Clinic;
import com.lss.core.vo.ReturnVo;
import com.lss.pc.base.BaseController;
import com.lss.pc.base.ServiceManager;

/**
 * 诊所 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("clinic")
public class ClinicController extends BaseController {

	/**
	 * 诊所列表
	 * 
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody Clinic params) {
		return ServiceManager.clinicService.list(params);
	}

	/**
	 * 诊所详情
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("details")
	public ReturnVo details(@RequestBody Clinic params) {
		return ServiceManager.clinicService.details(params);
	}
}
