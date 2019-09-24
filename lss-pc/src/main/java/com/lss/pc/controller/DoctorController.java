package com.lss.pc.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.core.pojo.Doctor;
import com.lss.core.pojo.WorkTag;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.pc.HomeParams;
import com.lss.pc.base.BaseController;
import com.lss.pc.base.ServiceManager;

/**
 * 医生 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("doctor")
public class DoctorController extends BaseController {

	/**
	 * 标签
	 * 
	 * @return
	 */
	@RequestMapping("tags")
	public ReturnVo tags(@RequestBody WorkTag tag) {
		return ServiceManager.doctorService.tags(tag);
	}

	/**
	 * 医生列表
	 * 
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody HomeParams params) {
		return ServiceManager.doctorService.list(params);
	}

	/**
	 * 医生详情
	 * 
	 * @return
	 */
	@RequestMapping("details")
	public ReturnVo details(@RequestBody Doctor params) {
		return ServiceManager.doctorService.details(params);
	}
}
