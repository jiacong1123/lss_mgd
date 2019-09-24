package com.lss.admin.service;

import com.lss.core.pojo.Doctor;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.DoctorParams;

public interface DoctorService {

	/**
	 * 添加/编辑医生
	 * 
	 * @param doctor
	 * @return
	 */
	ReturnVo save(Doctor doctor);

	/**
	 * 医生列表
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo list(DoctorParams params);

	/**
	 * 删除医生
	 * 
	 * @param doctor
	 * @return
	 */
	ReturnVo delete(Integer doctorid);

	/**
	 * 医生菜单
	 * 
	 * @param doctor
	 * @return
	 */
	ReturnVo dropmenu(Doctor doctor);
}
