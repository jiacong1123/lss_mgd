package com.lss.admin.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.lss.admin.service.DoctorService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.Doctor;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.params.DoctorParams;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Override
	public ReturnVo save(Doctor doctor) {
		ReturnVo result = new ReturnVo();
		String msg = checkDoctor(doctor);
		if (!"".equals(msg)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(msg);
			return result;
		}
		int res = 0;
		if (doctor.getDoctorid() == null
				|| doctor.getDoctorid().intValue() <= 0) {
			// 添加
			doctor.setCreatetime(new Date());
			res = MapperManager.doctorMapper.insertSelective(doctor);
		} else {
			res = MapperManager.doctorMapper
					.updateByPrimaryKeySelective(doctor);
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
	 * 验证医生信息
	 * 
	 * @param doctor
	 * @return
	 */
	private String checkDoctor(Doctor doctor) {
		if (doctor == null)
			return ResponseCode.parameterErrorMsg;
		if (ObjectUtil.isEmpty(doctor.getClinicid()))
			return "请选择诊所";
		if (ObjectUtil.isEmpty(doctor.getName()))
			return "请填写姓名";
		// if (ObjectUtil.isEmpty(doctor.getPhone()))
		// return "请填写电话";
		if (ObjectUtil.isEmpty(doctor.getSex()))
			return "请选择性别";
		if (ObjectUtil.isEmpty(doctor.getTitleid()))
			return "请选择职称";
		if (ObjectUtil.isEmpty(doctor.getPhoto()))
			return "请上传头像";
		if (ObjectUtil.isEmpty(doctor.getGoodat()))
			return "请填写擅长";
		if (ObjectUtil.isEmpty(doctor.getSynopsis()))
			return "请填写简介";
		return "";
	}

	@Override
	public ReturnVo list(DoctorParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		result.setTotal(MapperManager.doctorMapper.doctorCount(params));
		result.setObj(MapperManager.doctorMapper.doctorList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo delete(Integer doctorid) {
		ReturnVo result = new ReturnVo();
		Doctor doctor = new Doctor();
		doctor.setDoctorid(doctorid);
		doctor.setStatus(-1);// 逻辑删除
		if (MapperManager.doctorMapper.updateByPrimaryKeySelective(doctor) > 0) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	@Override
	public ReturnVo dropmenu(Doctor doctor) {
		ReturnVo result = new ReturnVo();
		if (doctor.getClinicid() == null) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		result.setObj(MapperManager.doctorMapper.getDoctorMenus(doctor
				.getClinicid()));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}
}
