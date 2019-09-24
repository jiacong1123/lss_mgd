package com.lss.applets.service;

import com.lss.core.pojo.Activity;
import com.lss.core.pojo.Product;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.ClinicParams;
import com.lss.core.vo.admin.params.DoctorParams;
import com.lss.core.vo.admin.params.ScienceParams;

public interface HomeService {
	/**
	 * 医生列表
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo doctorlist(DoctorParams params);
	
	/**
	 * 诊所列表
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo cliniclist(ClinicParams params);
	
	/**
	 * 科普列表
	 * @param params
	 * @return
	 */
	ReturnVo sciencelist(ScienceParams params);
	
	/**
	 * 活动列表
	 * @param params
	 * @return
	 */
	ReturnVo activitylist(ScienceParams params);
	
	/** 
	 * 活动详情
	 * @param params
	 * @return
	 */
	ReturnVo activitydetails(Activity params);
	
	/**
	 * 产品列表
	 * @param params
	 * @return
	 */
	ReturnVo productlist(Product params);
	
	/**
	 * 产品详情
	 * @param params
	 * @return
	 */
	ReturnVo productdetails(Product params);
}
