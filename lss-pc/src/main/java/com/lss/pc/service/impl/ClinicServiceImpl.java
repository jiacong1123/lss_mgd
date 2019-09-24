package com.lss.pc.service.impl;

import org.springframework.stereotype.Service;

import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.Clinic;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.pc.ClinicVo;
import com.lss.pc.service.ClinicService;

@Service
public class ClinicServiceImpl implements ClinicService {

	@Override
	public ReturnVo list(Clinic params) {
		ReturnVo result = new ReturnVo();
		if ("".equals(params.getArea()))
			params.setArea(null);
		result.setObj(MapperManager.clinicMapper.homeClinicList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo details(Clinic params) {
		ReturnVo result = new ReturnVo();
		ClinicVo vo = MapperManager.clinicMapper
				.queryByID(params.getClinicid());
		if (vo != null) {
			// 查询门诊医生
			vo.setDoctors(MapperManager.doctorMapper.clinicDoctorList(params
					.getClinicid()));
		}
		result.setObj(vo);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

}
