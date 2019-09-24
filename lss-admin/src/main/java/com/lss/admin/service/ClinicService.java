package com.lss.admin.service;

import com.lss.core.pojo.Clinic;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.ClinicParams;

public interface ClinicService {
	/**
	 * 添加/编辑诊所
	 * 
	 * @param clinic
	 * @return
	 */
	ReturnVo save(Clinic clinic);

	/**
	 * 诊所列表
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo list(ClinicParams params);

	/**
	 * 删除诊所
	 * 
	 * @param clinicid
	 * @return
	 */
	ReturnVo delete(Integer clinicid);
	
	/**
	 * 诊所菜单
	 * @return
	 */
	ReturnVo dropmenu();
}
