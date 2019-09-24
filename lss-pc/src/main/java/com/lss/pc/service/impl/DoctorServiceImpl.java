package com.lss.pc.service.impl;

import org.springframework.stereotype.Service;

import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.Clinic;
import com.lss.core.pojo.Doctor;
import com.lss.core.pojo.WorkTag;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.DoctorVo;
import com.lss.core.vo.pc.HomeParams;
import com.lss.pc.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Override
	public ReturnVo tags(WorkTag tag) {
		ReturnVo result = new ReturnVo();
		 
		result.setObj(MapperManager.workTagMapper.queryTags(tag));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo list(HomeParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		result.setTotal(MapperManager.doctorMapper.homeCount(params));
		result.setObj(MapperManager.doctorMapper.homeList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo details(Doctor params) {
		ReturnVo result = new ReturnVo();
		DoctorVo vo = MapperManager.doctorMapper
				.queryByID(params.getDoctorid());
		if (vo != null) {
			// 查询诊所
			if (vo.getClinicid() != null) {
				Clinic clinic = MapperManager.clinicMapper
						.selectByPrimaryKey(vo.getClinicid());
				if (clinic != null) {
					vo.setShortname(clinic.getShortname());
				}
			}
			// 查询职称
			if (vo.getTitleid() != null) {
				WorkTag tag = MapperManager.workTagMapper.selectByPrimaryKey(vo
						.getTitleid());
				if (tag != null) {
					vo.setJobtitle(tag.getTagname());
				}
			}
			// 查询科室
			if (vo.getKsid() != null) {
				WorkTag tag = MapperManager.workTagMapper.selectByPrimaryKey(vo
						.getKsid());
				if (tag != null) {
					vo.setDepartment(tag.getTagname());
				}
			}
			// 其他医生
			vo.setOtherlist(MapperManager.doctorMapper.otherList(params
					.getDoctorid()));
		}
		result.setObj(vo);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}
}
