package com.lss.pc.service;

import com.lss.core.pojo.Doctor;
import com.lss.core.pojo.WorkTag;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.pc.HomeParams;

public interface DoctorService {
	/**
	 * 标签
	 * 
	 * @param tag
	 * @return
	 */
	ReturnVo tags(WorkTag tag);

	/**
	 * 医生列表
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo list(HomeParams params);
	
	/**
	 * 医生详情
	 * @param params
	 * @return
	 */
	ReturnVo details(Doctor params);
}
