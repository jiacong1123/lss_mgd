package com.lss.pc.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.pojo.Banner;
import com.lss.core.pojo.Clinic;
import com.lss.core.pojo.Doctor;
import com.lss.core.pojo.News;
import com.lss.core.pojo.User;
import com.lss.core.pojo.WorkOrder;
import com.lss.core.pojo.WorkTag;
import com.lss.core.util.FormatUtil;
import com.lss.core.util.GenerateOrderno;
import com.lss.core.util.MD5;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.UserVo;
import com.lss.core.vo.applets.UserInfoParams;
import com.lss.core.vo.pc.HomeParams;
import com.lss.pc.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {

	@Override
	public ReturnVo banner(Banner params) {
		ReturnVo result = new ReturnVo();
		result.setObj(MapperManager.bannerMapper.homeList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo news(HomeParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		result.setTotal(MapperManager.newsMapper.homeCount(params));
		result.setObj(MapperManager.newsMapper.homeList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo clinicinfo() {
		ReturnVo result = new ReturnVo();
		Map<String, Object> map = new HashMap<String, Object>();
		// 目前默认查询一鸣口腔
		Clinic clinic = MapperManager.clinicMapper.selectByPrimaryKey(1);
		String[] milieupicture = null;
		if (ObjectUtil.isNotEmpty(clinic.getMilieupicture())) {
			milieupicture = clinic.getMilieupicture().split(",");
		}
		String[] devicepicture = null;
		if (ObjectUtil.isNotEmpty(clinic.getDevicepicture())) {
			devicepicture = clinic.getDevicepicture().split(",");
		}
		String[] doctorpicture = null;
		// 查询诊所医生
		List<Doctor> doctors = MapperManager.doctorMapper.getDoctorsList(1);
		if (ObjectUtil.isNotEmpty(doctors)) {
			doctorpicture = new String[doctors.size()];
			for (int i = 0; i < doctors.size(); i++) {
				doctorpicture[i] = doctors.get(i).getPhoto();
			}
		}
		map.put("milieupicture", milieupicture);
		map.put("devicepicture", devicepicture);
		map.put("doctorpicture", doctorpicture);
		result.setObj(map);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo newsdetails(News params) {
		ReturnVo result = new ReturnVo();
		result.setObj(MapperManager.newsMapper.selectByPrimaryKey(params
				.getId()));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public ReturnVo formsubmit(UserInfoParams params) {
		ReturnVo result = new ReturnVo();
		String msg = checkUserInfo(params);
		if (!"".equals(msg)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(msg);
			return result;
		}
		// 查询用户信息
		UserVo user = MapperManager.userMapper.queryByPhone(params.getPhone());
		if (user == null) {
			Date dt = new Date();
			// 插入用户信息
			User model = new User();
			model.setPhone(params.getPhone());
			model.setPassword(MD5.encrypt("123456"));
			model.setName(params.getName());
			model.setSex(params.getSex());
			// 查询来源
			WorkTag tagPar = new WorkTag();
			tagPar.setType(2);
			tagPar.setTagname("官网");
			WorkTag tag = MapperManager.workTagMapper.getWorkTag(tagPar);
			if (tag != null)
				model.setSourceid(tag.getTagid());
			model.setSourcedate(dt);
			model.setCreatetime(dt);
			if (MapperManager.userMapper.insertSelective(model) > 0) {
				// 插入工单
				WorkOrder order = new WorkOrder();
				String orderno = GenerateOrderno.getInstance().GenerateOrder();
				order.setOrderno(orderno);
				order.setUserid(model.getUserid());
				order.setCreatetime(dt);
				order.setProjectid(params.getProjectid());
				order.setWorknotes(params.getNotes());
				order.setReservedate(params.getReservedate());
				order.setPid(params.getPid());
				MapperManager.workOrderMapper.insertSelective(order);
			}
		}
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	/**
	 * 校验用户信息
	 * 
	 * @param params
	 * @return
	 */
	private String checkUserInfo(UserInfoParams params) {
		if (ObjectUtil.isEmpty(params.getPhone()))
			return "请填写手机号";
		else {
			if (!FormatUtil.matchMobile(params.getPhone()))
				return "手机格式错误";
		}
		if (ObjectUtil.isEmpty(params.getName()))
			return "请填写姓名";
		return "";
	}
}
