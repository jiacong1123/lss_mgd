package com.lss.pc.service;

import com.lss.core.pojo.Clinic;
import com.lss.core.vo.ReturnVo;

public interface ClinicService {

	/**
	 * 诊所列表
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo list(Clinic params);

	/**
	 * 诊所详情
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo details(Clinic params);
}
