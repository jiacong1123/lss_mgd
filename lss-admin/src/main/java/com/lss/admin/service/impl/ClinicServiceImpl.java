package com.lss.admin.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.lss.admin.service.ClinicService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.Clinic;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.ClinicParams;

@Service
public class ClinicServiceImpl implements ClinicService {

	@Override
	public ReturnVo save(Clinic clinic) {
		ReturnVo result = new ReturnVo();
		String msg = checkClinic(clinic);
		if (!"".equals(msg)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(msg);
			return result;
		}
		int res = 0;
		if (clinic.getClinicid() == null
				|| clinic.getClinicid().intValue() <= 0) {
			// 添加
			clinic.setCreatetime(new Date());
			res = MapperManager.clinicMapper.insertSelective(clinic);
		} else {
			res = MapperManager.clinicMapper
					.updateByPrimaryKeySelective(clinic);
		}
		if (res == 1) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	/**
	 * 验证诊所信息
	 * 
	 * @return
	 */
	private String checkClinic(Clinic clinic) {
		if (clinic == null)
			return ResponseCode.parameterErrorMsg;
		if (ObjectUtil.isEmpty(clinic.getName()))
			return "请填写诊所名称";
		if (ObjectUtil.isEmpty(clinic.getImage()))
			return "请上传诊所图片";
		if (ObjectUtil.isEmpty(clinic.getShortname()))
			return "请填写诊所简称";
		if (ObjectUtil.isEmpty(clinic.getMainproject()))
			return "请填写主治项目";
		if (ObjectUtil.isEmpty(clinic.getTelephone()))
			return "请填写诊所电话";
		// if (ObjectUtil.isEmpty(clinic.getPrincipal()))
		// return "请填写负责人姓名";
		// if (ObjectUtil.isEmpty(clinic.getPhone()))
		// return "请填写负责人手机号";
		if (ObjectUtil.isEmpty(clinic.getProvince()))
			return "请选择省";
		if (ObjectUtil.isEmpty(clinic.getCity()))
			return "请选择市";
		if (ObjectUtil.isEmpty(clinic.getArea()))
			return "请选择区";
		if (ObjectUtil.isEmpty(clinic.getAddress()))
			return "请填写详细地址";
		if (ObjectUtil.isEmpty(clinic.getType()))
			return "请选择诊所类型";
		if (ObjectUtil.isEmpty(clinic.getMilieupicture()))
			return "请上传诊所环境图";
		if (ObjectUtil.isEmpty(clinic.getDevicepicture()))
			return "请上传诊所设备图";
		return "";
	}

	@Override
	public ReturnVo list(ClinicParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		result.setTotal(MapperManager.clinicMapper.clinicCount(params));
		result.setObj(MapperManager.clinicMapper.clinicList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo delete(Integer clinicid) {
		ReturnVo result = new ReturnVo();
		Clinic clinic = new Clinic();
		clinic.setClinicid(clinicid);
		clinic.setStatus(-1);// 逻辑删除
		if (MapperManager.clinicMapper.updateByPrimaryKeySelective(clinic) > 0) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	@Override
	public ReturnVo dropmenu() {
		ReturnVo result = new ReturnVo();
		result.setObj(MapperManager.clinicMapper.clinicMenuList());
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

}
