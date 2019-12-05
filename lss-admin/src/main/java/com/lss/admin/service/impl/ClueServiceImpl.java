package com.lss.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lss.admin.service.ClueService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.emus.Sex;
import com.lss.core.emus.WorkTagType;
import com.lss.core.pojo.Admin;
import com.lss.core.pojo.User;
import com.lss.core.pojo.WorkOrder;
import com.lss.core.pojo.WorkRecord;
import com.lss.core.pojo.WorkTag;
import com.lss.core.util.DateUtils;
import com.lss.core.util.EntityUtils;
import com.lss.core.util.HttpUtil;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.params.ClueParams;
import com.lss.core.vo.hx.ClueListVo;
import com.lss.core.vo.hx.HxClue;

@Service
public class ClueServiceImpl implements ClueService {

	private static final Logger log = LoggerFactory
			.getLogger(ClueServiceImpl.class);

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public ReturnVo create(ClueParams params) throws Exception {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(params.getOrderno())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("工单号不能为空");
			return result;
		}
		if (ObjectUtil.isEmpty(params.getProvince(), params.getCity(),
				params.getArea())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("请选择地区");
			return result;
		}
		if (ObjectUtil.isEmpty(params.getUsertype(), params.getUsertypename())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("请选择用户类型");
			return result;
		}
		WorkOrder order = MapperManager.workOrderMapper
				.selectByPrimaryKey(params.getOrderno());
		User user = MapperManager.userMapper.selectByPrimaryKey(order
				.getUserid());
		// 验证工单是否已是线索
		if (order.getIsclue().intValue() == 1) {
			result.setResult(ResponseCode.failure);
			result.setMsg("工单已是线索");
			return result;
		}
		// 修改地区
		user.setProvince(params.getProvince());
		user.setCity(params.getCity());
		user.setArea(params.getArea());
		int res = MapperManager.userMapper.updateByPrimaryKeySelective(user);
		if (res <= 0)
			throw new Exception();
		// 修改工单
		order.setUsertype(params.getUsertype());
		order.setUsertypename(params.getUsertypename());
		order.setReservedate(params.getReservedate());
		order.setReservetime(params.getReservetime());
		order.setWorknotes(params.getWorknotes());
		order.setIsclue(1);// 是线索
		order.setCluestatus(0);// 可抢
		res = MapperManager.workOrderMapper.updateByPrimaryKeySelective(order);
		if (res <= 0)
			throw new Exception();
		// 同步换新系统
		HxClue clue = new HxClue();
		clue.setName(user.getName());
		clue.setPhone(user.getPhone());
		if (user.getSex() == null) {
			clue.setSex(Sex.UNKNOWN.toString());
		} else if (user.getSex() == 1) {
			clue.setSex(Sex.MALE.toString());
		} else if (user.getSex() == 2) {
			clue.setSex(Sex.FEMALE.toString());
		}
		clue.setAge(user.getAge());
		clue.setProvince(user.getProvince());
		clue.setCity(user.getCity());
		clue.setArea(user.getArea());
		// 查询来源
		if (user.getSourceid() != null) {
			WorkTag tag = MapperManager.workTagMapper.selectByPrimaryKey(user
					.getSourceid());
			if (tag != null)
				clue.setSource(tag.getTagname());
		}
		clue.setUserType(order.getUsertype());
		clue.setOrderNo(order.getOrderno());
		// 查询项目名称
		if (order.getProjectid() != null) {
			WorkTag tag = MapperManager.workTagMapper.selectByPrimaryKey(order
					.getProjectid());
			if (tag != null)
				clue.setProject(tag.getTagname());
		}
		if (order.getReservedate() != null)
			clue.setReserveDate(DateUtils.formatdateFormat(order
					.getReservedate()));
		clue.setReserveTime(order.getReservetime());
		// 查询根据人员
		Admin admin = MapperManager.adminMapper.selectByPrimaryKey(order
				.getAdminid());
		if (admin != null)
			clue.setFollowName(admin.getName());
		clue.setWishLevel(order.getLevel());
		clue.setComplaint(order.getComplaint());
		clue.setRemark(order.getWorknotes());
		// TODO 发送请求
		Map<String, String> map = EntityUtils.entityToMap(clue);
		// 获取好乐美请求url
		WorkTag tag = MapperManager.workTagMapper
				.queryByType(WorkTagType.hlm_url.getType());
		String json = HttpUtil.post(tag.getTagname() + "hx/createClue.do", map);
		if (ObjectUtil.isEmpty(json)) {
			throw new Exception();
		}
		JSONObject obj = JSONObject.parseObject(json);
		if (!obj.getBooleanValue("result")) {
			throw new Exception();
		}
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;

	}

	@Override
	public ReturnVo dispatch(ClueParams params) throws Exception {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(params.getOrderno(), params.getCode(),
				params.getClinicName(), params.getIsShop())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("参数错误");
			return result;
		}
		WorkOrder order = MapperManager.workOrderMapper
				.selectByPrimaryKey(params.getOrderno());
		if (order.getCluestatus().intValue() > 0) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("工单已被接诊");
			return result;
		}
		// 修改工单数据
		order.setOrdertype(2);// 派单
		if ("YES".equals(params.getIsShop())) {
			order.setCluestatus(2);// 冻结中
		} else {
			order.setCluestatus(1);// 已被抢
		}
		order.setClinicname(params.getClinicName());
		order.setVisitingtime(new Date());
		int res = MapperManager.workOrderMapper
				.updateByPrimaryKeySelective(order);
		if (res <= 0)
			throw new Exception();
		// TODO 派单
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderno", params.getOrderno());
		map.put("serverCode", params.getCode());
		// 获取好乐美请求url
		WorkTag tag = MapperManager.workTagMapper
				.queryByType(WorkTagType.hlm_url.getType());
		String json = HttpUtil.post(tag.getTagname() + "hx/visiting.do", map);
		if (ObjectUtil.isEmpty(json)) {
			throw new Exception();
		}
		JSONObject obj = JSONObject.parseObject(json);
		if (!obj.getBooleanValue("result")) {
			throw new Exception();
		}
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo list(ClueParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		result.setTotal(MapperManager.workOrderMapper.getClueCount(params));
		result.setObj(MapperManager.workOrderMapper.getClueList(params));
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public ReturnVo turnfollowup(WorkRecord params, LoginAdmin admin)
			throws Exception {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(params.getOrderno())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		if (ObjectUtil.isEmpty(params.getContent())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("请填写沟通内容");
			return result;
		}
		if (MapperManager.workOrderMapper.clueTurnFollowUp(params.getOrderno()) > 0) {
			Date now=new Date();
			// 添加跟进记录
			params.setContent("【线索转跟进】" + params.getContent());
			params.setAdminid(admin.getAdminid());
			params.setCreatetime(now);
			MapperManager.workRecordMapper.insertSelective(params);
			
			//1.最近跟进时间及操作 v1.4 2019.08.12 
			WorkOrder updateWorkOrder=new WorkOrder();
			updateWorkOrder.setOrderno(params.getOrderno());
			updateWorkOrder.setFollowuptime(now);
			updateWorkOrder.setFollowupremarks(params.getContent());
			MapperManager.workOrderMapper.updateByPrimaryKeySelective(updateWorkOrder);
			

			// TODO 换新取消线索
			Map<String, String> map = new HashMap<String, String>();
			map.put("orderno", params.getOrderno());
			WorkTag tag = MapperManager.workTagMapper
					.queryByType(WorkTagType.hlm_url.getType());
			String json = HttpUtil.post(tag.getTagname() + "hx/upstatus.do",
					map);
			if (ObjectUtil.isEmpty(json)) {
				throw new Exception();
			}
			JSONObject obj = JSONObject.parseObject(json);
			if (!obj.getBooleanValue("result")) {
				throw new Exception();
			}

			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public ReturnVo edit(ClueListVo vo) throws Exception {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(vo.getOrderno())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		WorkOrder order = MapperManager.workOrderMapper.selectByPrimaryKey(vo
				.getOrderno());
		if (order.getCluestatus().intValue() > 0) {
			result.setResult(ResponseCode.failure);
			result.setMsg("线索已被接诊，不能编辑！");
			return result;
		}
		// 修改工单表
		if (ObjectUtil.isNotEmpty(vo.getProjectid()))
			order.setProjectid(vo.getProjectid());
		if (ObjectUtil.isNotEmpty(vo.getReservedate()))
			order.setReservedate(vo.getReservedate());
		if (ObjectUtil.isNotEmpty(vo.getReservetime()))
			order.setReservetime(vo.getReservetime());
		if (ObjectUtil.isNotEmpty(vo.getUsertype()))
			order.setUsertype(vo.getUsertype());
		if (ObjectUtil.isNotEmpty(vo.getUsertypename()))
			order.setUsertypename(vo.getUsertypename());
		if (ObjectUtil.isNotEmpty(vo.getLevel()))
			order.setLevel(vo.getLevel());
		if (ObjectUtil.isNotEmpty(vo.getComplaint()))
			order.setComplaint(vo.getComplaint());
		if (ObjectUtil.isNotEmpty(vo.getWorknotes()))
			order.setWorknotes(vo.getWorknotes());
		int res = MapperManager.workOrderMapper
				.updateByPrimaryKeySelective(order);
		if (res <= 0)
			throw new Exception();
		User user = MapperManager.userMapper.selectByPrimaryKey(order
				.getUserid());
		user.setProvince(vo.getProvince());
		user.setCity(vo.getCity());
		user.setArea(vo.getArea());
		res = MapperManager.userMapper.updateByPrimaryKeySelective(user);
		if (res <= 0)
			throw new Exception();
		// TODO 编辑线索
		HxClue clue = new HxClue();
		clue.setName(user.getName());
		clue.setPhone(user.getPhone());
		if (user.getSex() == null) {
			clue.setSex(Sex.UNKNOWN.toString());
		} else if (user.getSex() == 1) {
			clue.setSex(Sex.MALE.toString());
		} else if (user.getSex() == 2) {
			clue.setSex(Sex.FEMALE.toString());
		}
		clue.setAge(user.getAge());
		clue.setProvince(user.getProvince());
		clue.setCity(user.getCity());
		clue.setArea(user.getArea());
		// 查询来源
		if (user.getSourceid() != null) {
			WorkTag tag = MapperManager.workTagMapper.selectByPrimaryKey(user
					.getSourceid());
			if (tag != null)
				clue.setSource(tag.getTagname());
		}
		clue.setUserType(order.getUsertype());
		clue.setOrderNo(order.getOrderno());
		// 查询项目名称
		if (order.getProjectid() != null) {
			WorkTag tag = MapperManager.workTagMapper.selectByPrimaryKey(order
					.getProjectid());
			if (tag != null)
				clue.setProject(tag.getTagname());
		}
		clue.setReserveDate(DateUtils.formatdateFormat(order.getReservedate()));
		clue.setReserveTime(order.getReservetime());
		// 查询根据人员
		Admin admin = MapperManager.adminMapper.selectByPrimaryKey(order
				.getAdminid());
		if (admin != null)
			clue.setFollowName(admin.getName());
		clue.setWishLevel(order.getLevel());
		clue.setComplaint(order.getComplaint());
		clue.setRemark(order.getWorknotes());
		// TODO 发送请求
		Map<String, String> map = EntityUtils.entityToMap(clue);
		WorkTag tag = MapperManager.workTagMapper
				.queryByType(WorkTagType.hlm_url.getType());
		String json = HttpUtil.post(tag.getTagname() + "hx/createClue.do", map);
		if (ObjectUtil.isEmpty(json)) {
			throw new Exception();
		}
		JSONObject obj = JSONObject.parseObject(json);
		if (!obj.getBooleanValue("result")) {
			throw new Exception();
		}

		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public ReturnVo operation(ClueParams params) throws Exception {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(params.getOrderno(), params.getStatus())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		WorkOrder order = MapperManager.workOrderMapper
				.selectByPrimaryKey(params.getOrderno());
		int res = 0;
		String status = "";
		if ("confirm".equals(params.getStatus().trim())) {
			// 确认到店
			order.setCluestatus(1);// 已被抢
			res = MapperManager.workOrderMapper
					.updateByPrimaryKeySelective(order);
			status = "OK";
		} else {
			// 取消到店
			res = MapperManager.workOrderMapper.clueCancelShop(params
					.getOrderno());
			status = "CANCEL";
		}
		if (res <= 0) {
			throw new Exception();
		}
		// TODO 换新 确认/取消到店
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderno", params.getOrderno());
		map.put("status", status);
		WorkTag tag = MapperManager.workTagMapper
				.queryByType(WorkTagType.hlm_url.getType());
		String json = HttpUtil.post(tag.getTagname() + "hx/confirmorcancel.do",
				map);
		if (ObjectUtil.isEmpty(json)) {
			throw new Exception();
		}
		JSONObject obj = JSONObject.parseObject(json);
		if (!obj.getBooleanValue("result")) {
			throw new Exception();
		}
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo visiting(WorkOrder params) {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(params.getOrderno(), params.getClinicname())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		WorkOrder order = MapperManager.workOrderMapper
				.selectByPrimaryKey(params.getOrderno());
		if (order.getCluestatus().intValue() > 0) {
			result.setResult(ResponseCode.failure);
			result.setMsg("工单线索已被接诊");
			return result;
		}
		order.setCluestatus(params.getCluestatus());
		order.setOrdertype(1);// 接诊
		order.setClinicname(params.getClinicname());
		order.setVisitingtime(new Date());
		if (MapperManager.workOrderMapper.updateByPrimaryKeySelective(order) > 0) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	@Override
	public ReturnVo toshop(String orderno) {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(orderno)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		WorkOrder order = MapperManager.workOrderMapper
				.selectByPrimaryKey(orderno);
		if (order.getCluestatus().intValue() != 2) {
			result.setResult(ResponseCode.failure);
			result.setMsg("工单线索非冻结状态");
			return result;
		}
		order.setCluestatus(1);
		if (MapperManager.workOrderMapper.updateByPrimaryKeySelective(order) > 0) {
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}
}
