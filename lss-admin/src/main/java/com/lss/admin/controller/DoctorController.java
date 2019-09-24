package com.lss.admin.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.pojo.Doctor;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.DoctorParams;

/**
 * 医生管理
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("doctor")
public class DoctorController extends BaseController {

	/**
	 * 添加/编辑医生
	 * 
	 * @return
	 */
	@RequestMapping("save")
	public ReturnVo save(@RequestBody Doctor doctor) {
		return ServiceManager.doctorService.save(doctor);
	}

	/**
	 * 医生列表
	 * 
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody DoctorParams params) {
		return ServiceManager.doctorService.list(params);
	}

	/**
	 * 删除医生
	 * 
	 * @return
	 */
	@RequestMapping("delete")
	public ReturnVo delete(@RequestBody Doctor doctor) {
		return ServiceManager.doctorService.delete(doctor.getDoctorid());
	}

	/**
	 * 医生菜单
	 * 
	 * @return
	 */
	@RequestMapping("dropmenu")
	public ReturnVo dropmenu(@RequestBody Doctor doctor) {
		return ServiceManager.doctorService.dropmenu(doctor);
	}

}
