package com.lss.admin.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.core.pojo.Clinic;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.ClinicParams;

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
	 * 添加/编辑诊所
	 * 
	 * @return
	 */
	@RequestMapping("save")
	public ReturnVo save(@RequestBody Clinic clinic) {
		return ServiceManager.clinicService.save(clinic);
	}

	/**
	 * 诊所列表
	 * 
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody ClinicParams params) {
		return ServiceManager.clinicService.list(params);
	}

	/**
	 * 删除诊所
	 * 
	 * @return
	 */
	@RequestMapping("delete")
	public ReturnVo delete(@RequestBody Clinic clinic) {
		return ServiceManager.clinicService.delete(clinic.getClinicid());
	}

	/**
	 * 诊所菜单
	 * 
	 * @return
	 */
	@RequestMapping("dropmenu")
	public ReturnVo dropmenu() {
		return ServiceManager.clinicService.dropmenu();
	}
}
