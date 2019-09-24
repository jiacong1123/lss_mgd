package com.lss.applets.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.applets.base.BaseController;
import com.lss.applets.base.ServiceManager;
import com.lss.core.pojo.Activity;
import com.lss.core.pojo.Product;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.ClinicParams;
import com.lss.core.vo.admin.params.DoctorParams;
import com.lss.core.vo.admin.params.ScienceParams;

/**
 * 首页 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("home")
public class HomeController extends BaseController {

	/**
	 * 医生列表
	 * 
	 * @return
	 */
	@RequestMapping("doctorlist")
	public ReturnVo doctorlist(@RequestBody DoctorParams params) {
		return ServiceManager.homeService.doctorlist(params);
	}

	/**
	 * 诊所列表
	 * 
	 * @return
	 */
	@RequestMapping("cliniclist")
	public ReturnVo cliniclist(@RequestBody ClinicParams params) {
		return ServiceManager.homeService.cliniclist(params);
	}

	/**
	 * 科普列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("sciencelist")
	public ReturnVo sciencelist(@RequestBody ScienceParams params) {
		return ServiceManager.homeService.sciencelist(params);
	}

	/**
	 * 活动列表
	 * 
	 * @return
	 */
	@RequestMapping("activitylist")
	public ReturnVo activitylist(@RequestBody ScienceParams params) {
		return ServiceManager.homeService.activitylist(params);
	}

	/**
	 * 活动详情
	 * 
	 * @return
	 */
	@RequestMapping("activitydetails")
	public ReturnVo activitydetails(@RequestBody Activity params) {
		return ServiceManager.homeService.activitydetails(params);
	}

	/**
	 * 产品列表
	 * 
	 * @return
	 */
	@RequestMapping("productlist")
	public ReturnVo productlist(@RequestBody Product params) {
		return ServiceManager.homeService.productlist(params);
	}

	/**
	 * 产品详情
	 * 
	 * @return
	 */
	@RequestMapping("productdetails")
	public ReturnVo productdetails(@RequestBody Product params) {
		return ServiceManager.homeService.productdetails(params);
	}
}
