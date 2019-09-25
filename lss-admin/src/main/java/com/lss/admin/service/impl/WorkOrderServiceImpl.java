package com.lss.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lss.admin.base.ServiceManager;
import com.lss.admin.service.IOrderPayService;
import com.lss.admin.service.IOrgService;
import com.lss.admin.service.WorkOrderService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.constant.SystemConstant;
import com.lss.core.dto.OrderPayDto;
import com.lss.core.dto.OrgDto;
import com.lss.core.emus.WorkTagType;
import com.lss.core.emus.YESNO;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.Admin;
import com.lss.core.pojo.Clinic;
import com.lss.core.pojo.User;
import com.lss.core.pojo.WorkOrder;
import com.lss.core.pojo.WorkRecord;
import com.lss.core.pojo.WorkTag;
import com.lss.core.util.DateUtils;
import com.lss.core.util.GenerateOrderno;
import com.lss.core.util.HttpUtil;
import com.lss.core.util.MD5;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.UserVo;
import com.lss.core.vo.admin.WorkOrderDetails;
import com.lss.core.vo.admin.WorkOrderVo;
import com.lss.core.vo.admin.WorkRecordVo;
import com.lss.core.vo.admin.params.UserOrderParams;
import com.lss.core.vo.admin.params.WorkOrderParams;
import com.lss.core.vo.ztc.ZtcPersonMember;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {
	
	private static final Logger log = LoggerFactory.getLogger(WorkOrderServiceImpl.class);
	
	@Resource
	private IOrderPayService orderPayService;
	@Resource
	private IOrgService orgService; 
	
	
	@Override
	public ReturnVo closeCount(WorkOrderParams params, LoginAdmin admin) { 
		return list(params, admin,true);
	}

	@Override
	public ReturnVo list(WorkOrderParams params, LoginAdmin admin) {
		return list(params, admin,false);
	}
	/**
	 * 
	 * @param params 参数
	 * @param admin 当前登录人
	 * @param onlyCount 是否只查总数 true：只查总数，false：查列表
	 * @return
	 */
	public ReturnVo list(WorkOrderParams params, LoginAdmin admin,boolean onlyCount) {
		if(StringUtils.isNotEmpty(params.getSourcedateStartStr())) {
			params.setSourcedateStart(DateUtils.parseDate(params.getSourcedateStartStr()));
		}
		if(StringUtils.isNotEmpty(params.getSourcedateEndStr())) {
			params.setSourcedateEnd(DateUtils.parseDate(params.getSourcedateEndStr()));	
		}
		//status 状态 0未分配 1待跟进 2已预约 3已到店 4已完成 5已关闭 10新分配 99全部 98待回收列表
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		// 查询条件
		if (ObjectUtil.isEmpty(admin.getRoles())) {
			result.setResult(ResponseCode.failure);
			result.setMsg("还未分配角色");
			return result;
		}
		if(params.getStatus()==98) {//待调库（回收）列表
			Date now = DateUtils.getDateDay(new Date());
			Date nextDay = DateUtils.addDate(now, SystemConstant.WORK_ORDER_WILL_CLOSE_DAY);
			params.setEndCloseTime(nextDay);
		}
		// 其他状态要加相应的查询条件
//		if (params.getStatus() != 0 && params.getStatus() != 5) {//关闭的也只看本人的 2019.08.12 v1.4
		if (params.getStatus() != 0) {
			if (admin.getRoles().contains(3) && !admin.getRoles().contains(9)) {
				if(null==params.getAdminid()) {
					params.setAdminid(admin.getAdminid());
				}
				
				// 关闭的，电销员要看到自己和组长的 2019.08.29
				if(params.getStatus() == 5 && !admin.getRoles().contains(9)) {
					Admin findAdmin= MapperManager.adminMapper.selectByPrimaryKey(admin.getAdminid());
					if (findAdmin.getOrgId() != null) {
						params.setAdminid(null);
						
						OrgDto orgParam=new OrgDto();
						orgParam.setId(findAdmin.getOrgId());
						OrgDto findOrg = orgService.findOrg(orgParam);
						//上级及同组员工 
						List<Integer> adminids = MapperManager.adminMapper.selectByOrg(findOrg);
						if(adminids.size()==0) {//没人，则给一个不存在的账号 避免拿了所有员工数据
							adminids.add(-1); 
						}
						if(null==params.getAdminid()) {
							params.setAdminids(adminids);
						}
					}else {
						throw new LssException(ResponseCode.parameterError, "电销员未分配组织");
					}
				}//// 关闭的，电销员要看到自己和组长的 2019.08.29 end
			}
			
			
			if (admin.getRoles().contains(4) || admin.getRoles().contains(5)
					|| admin.getRoles().contains(6)) {
				params.setClinicid(admin.getClinicid());
			}
			if (params.getStatus() == 3 || params.getStatus() == 4) {
				if (admin.getRoles().contains(6))
					params.setDoctorid(admin.getAdminid());
			}
			
			if(admin.getRoles().contains(9)) {//电销组长 ，看到自己和下属的
				Admin findAdmin= MapperManager.adminMapper.selectByPrimaryKey(admin.getAdminid());
				if (findAdmin.getOrgId() != null) {
					//如果没有用所属人查询就查小组所有成员
					if(params.getAdminid()==null) {
						//下属及同组员工 
						List<Integer> adminids = MapperManager.adminMapper.selectByOrgId(findAdmin.getOrgId());
						if(adminids.size()==0) {//没下属，则给一个不存在的账号 避免拿了所有员工数据
							adminids.add(-1); 
						}
						params.setAdminids(adminids);
					}
				}else {
					throw new LssException(ResponseCode.parameterError, "电销组长未分配组织");
				}
			}
			
			//同时拥有电销组长,电销员两个角色
			if(admin.getRoles().contains(9) && admin.getRoles().contains(3)) {
				Admin findAdmin= MapperManager.adminMapper.selectByPrimaryKey(admin.getAdminid());
				if (findAdmin.getOrgId() != null) {
					//如果没有用所属人查询就查小组所有成员
					if(params.getAdminid()==null) {
						//下属及同组员工 
						List<Integer> adminids = MapperManager.adminMapper.selectByOrgId(findAdmin.getOrgId());
						if(adminids.size()==0) {//没下属，则给一个不存在的账号 避免拿了所有员工数据
							adminids.add(-1); 
						}
						params.setAdminids(adminids);
					}
				}else {
					throw new LssException(ResponseCode.parameterError, "电销组长未分配组织");
				}
			}
			
		}
		if (params.getStart() != null) {
			params.setStart(DateUtils.getDateDay(params.getStart()));
		}
		if (params.getEnd() != null) {
			params.setEnd(DateUtils.getDateDay(params.getEnd()));
		}
		
		/*
		 * if (params.getFollowupTimeStart() != null) {
		 * params.setFollowupTimeStart(DateUtils.getDateDay(params.getFollowupTimeStart(
		 * ))); } if (params.getFollowupTimeEnd() != null) {
		 * params.setFollowupTimeEnd(DateUtils.getDateDay(params.getFollowupTimeEnd()));
		 * }
		 */
		if (params.getSourcedateEnd() != null) {//包含查詢的當天的 24小時内的數據
			params.setSourcedateEnd(DateUtils.addDate(params.getSourcedateEnd(), 1));
		}
		String sort = "";
		// 排序
		if (params.getStatus() == 1 || params.getStatus() == 10) {
			sort = " a.createtime ASC ";
		} else if (params.getStatus() == 2 || params.getStatus() == 3) {
			sort = " a.reservedate ASC ";
		} else if (params.getStatus() ==98 ) {
			sort = " a.followuptime ASC "; //掉库日期根据跟进日期升序排序
		} else {
			sort = " a.createtime DESC ";
		}
		params.setSort(sort);
		result.setTotal(MapperManager.workOrderMapper.workOrderCount(params));//查总数
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		if(!onlyCount) {//查列表
			List<WorkOrderVo> data=MapperManager.workOrderMapper.workOrderList(params);
			result.setObj(data);
			//客户直通车微信信息 2019.05.13
			if (data!=null && data.size()>0 && admin.getGuidMember() != null && params.getStatus() != 0
	//				&& params.getStatus() != 5 //关闭的也只看本人的 2019.08.12 v1.4
			) {
				
				if (admin.getRoles().contains(3)) {
					//1.组装 请求参数
					List<String> mobiles=new ArrayList<>();
					for (WorkOrderVo ele : data) {
						mobiles.add(ele.getPhone());
					}
					Map<String, String> map = new HashMap<String, String>();
					map.put("memberNoGm", admin.getGuidMember().getMemberNoGuid());
					map.put("merchantNo", admin.getGuidMember().getMemberNoMerchant());
					map.put("mobiles",  StringUtils.strip(mobiles.toString(),"[]"));
					WorkTag tag = MapperManager.workTagMapper
							.queryByType(WorkTagType.ztc_url.getType());
					String json = null;
					try {
						//2.从直通车查好友
						json = HttpUtil.post(tag.getTagname() + "/api-os/lss/gd/pmlist.do",
								map);
						if (ObjectUtil.isEmpty(json)) {
							throw new Exception("微信查询失败");
						}
						JSONObject obj = JSONObject.parseObject(json);
						if (!obj.getBooleanValue("result")) {
							throw new Exception("微信查询失败");
						}
						//3.查完后组装到返回的list
						List<ZtcPersonMember> pMember= JSON.parseArray(obj.getString("returnObject"), ZtcPersonMember.class);
						//key=phone,value=微信好友（一个手机号可能对应多个好友）
						Map<String, List<ZtcPersonMember>> ztcPmMap=new HashMap<String, List<ZtcPersonMember>>();
						for (ZtcPersonMember ztcPersonMember : pMember) {
							if(ztcPmMap.containsKey(ztcPersonMember.getMobile())) {
								ztcPmMap.get(ztcPersonMember.getMobile()).add(ztcPersonMember);
							}else {
								List<ZtcPersonMember> d2=new ArrayList<>();
								d2.add(ztcPersonMember);
								ztcPmMap.put(ztcPersonMember.getMobile(), d2);
							}
						}
						//组装到工单list中
						for (WorkOrderVo ele : data) {
							ele.setPersonMembers(ztcPmMap.get(ele.getPhone()));
						}
						
						log.info("rt:{}",pMember);
					} catch (Exception e) {
						log.error("直通车查好友失败,json={}",json);
						log.error("直通车查好友失败!",e);
					}
					
				}
			}//客户直通车微信信息 end
		}
		return result;
	}

	@Override
	public ReturnVo tags(WorkTag tag) {
		ReturnVo result = new ReturnVo();
		List<WorkTag> datas=MapperManager.workTagMapper.queryTags(tag);
		if(WorkTagType.label.getType().equals(tag.getType()) && tag.getParentid()==null) {
			//所有级别查出来了,组装子集
			List<WorkTag> rtTags = getOneGradeTag(datas);
			result.setObj(rtTags);
		}else {
		 result.setObj(datas);
		}
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	private List<WorkTag>  getOneGradeTag(List<WorkTag> datas){
		List<WorkTag> rtTags = new ArrayList<WorkTag>();
		for (Iterator<WorkTag> iterator = datas.iterator(); iterator.hasNext();) {
			WorkTag ele = iterator.next();
			if (ele.getParentid() == null && 1 == ele.getGrade()) {
				rtTags.add(ele);
			}
		}
		for (WorkTag ele : rtTags) {
			ele.setChild(getTagByParentId(datas, ele.getTagid()));
		}
		return rtTags;
	}
	
	private List<WorkTag> getTagByParentId(List<WorkTag> tags,Integer parentId){
		List<WorkTag> data = new ArrayList<>();
		for (Iterator<WorkTag> iterator = tags.iterator(); iterator.hasNext();) {
			WorkTag ele = iterator.next();
			if(ele.getParentid()==parentId) {
				data.add(ele);
			}
		}
		return data;
	}
	
	
	@Override
	public ReturnVo details(String orderno) {
		ReturnVo result = new ReturnVo();
		WorkOrderDetails vo = MapperManager.workOrderMapper
				.queryDetails(orderno);
		if (vo != null) {
			if (vo.getClinicid() != null) {
				Clinic clinic = MapperManager.clinicMapper
						.selectByPrimaryKey(vo.getClinicid());
				vo.setClinicname(clinic.getShortname());
			}
			if (vo.getProjectid() != null) {
				WorkTag tag = MapperManager.workTagMapper.selectByPrimaryKey(vo
						.getProjectid());
				vo.setProject(tag.getTagname());
			}
			if (vo.getAdminid() != null) {
				Admin admin = MapperManager.adminMapper.selectByPrimaryKey(vo
						.getAdminid());
				vo.setAdminname(admin.getName());
			}
			if (vo.getDoctorid() != null) {
				Admin admin = MapperManager.adminMapper.selectByPrimaryKey(vo
						.getDoctorid());
				vo.setDoctorname(admin.getName());
			}
			vo.setUser(MapperManager.userMapper.queryUser(vo.getUserid()));
			// 查询跟进记录
			vo.setRecords(MapperManager.workRecordMapper.getRecordList(orderno));

		}
		result.setObj(vo);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo userinfo(String phone,String noWxAlias) {
		ReturnVo result = new ReturnVo();
		UserVo user = MapperManager.userMapper.queryByPhone(phone);
		if (user == null && StringUtils.isNotEmpty(noWxAlias)) {//手机号未找到用户则根据微信号找
			user = MapperManager.userMapper.queryByNoWxAlias(noWxAlias);
		}
		if (user != null) {
			// 用户信息存在。。查询是否有未完成的工单
			WorkOrder order = MapperManager.workOrderMapper.queryWorkOrder(user
					.getUserid());
			if (order != null) {//当前手机号码已存在，工单状态：{1}，所属销售{1}，不能新增工单。
				WorkOrderVo userOrder=new WorkOrderVo();
				userOrder.setOrderno(order.getOrderno());
				userOrder.setAdminid(order.getAdminid());
//				userOrder.setAdminname(adminname);
				userOrder.setStatus(order.getStatus());
				userOrder.setFollowup(order.getFollowup());
				
				String msg = workOrderExistMsg(order,userOrder);
				user.setMsg(msg);
				user.setOrderno(order.getOrderno());
				user.setOrder(userOrder);
			}
		}
		result.setObj(user);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	/**
	 * 工单存在时的提示语。
	 * @param order
	 * @return
	 */
	private String workOrderExistMsg(WorkOrder order,WorkOrderVo userOrder) {
		StringBuilder sb = new StringBuilder();
		sb.append("当前手机号码已存在，工单状态：");
		switch (order.getStatus()) {
		case 0:
			sb.append("待分配");
			break;
		case 1:{
			if(0==order.getFollowup().intValue()) {
				sb.append("新分配");
			}else {
				sb.append("待跟进");
			}
			break;
		}
		case 2:
			sb.append("已预约");
			break;
		case 3:
			sb.append("已到店");
			break;
		case 5:
			sb.append("已关闭");
			break;
		}
		if(null!=order.getAdminid()) {
			Admin admin=MapperManager.adminMapper.selectByPrimaryKey(order.getAdminid());
			if(admin!=null) {
				sb.append("，所属销售");
				sb.append(admin.getName());
				if(userOrder!=null) {
					userOrder.setAdminname(admin.getName());
				}
			}
		}
		sb.append("，不能新增工单。");
		return sb.toString();
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public ReturnVo save(UserOrderParams params, LoginAdmin admin) {
		ReturnVo result = new ReturnVo();
		// 验证用户信息
		String msg = ServiceManager.userService.checkUser(params.getUser());
		if (!"".equals(msg)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(msg);
			return result;
		}
		WorkOrder order = params.getOrder();
		if (order == null)
			order = new WorkOrder();
		if (params.getUser().getUserid() != null
				&& ObjectUtil.isEmpty(order.getOrderno())) {
			// 验证用户是否有未完成的工单
			WorkOrder userOrder = MapperManager.workOrderMapper
					.queryWorkOrder(params.getUser().getUserid());
			if (userOrder != null) {
				result.setResult(ResponseCode.failure);
				result.setMsg(workOrderExistMsg(userOrder,null));
				return result;
			}
		}
		Date dt = new Date();
		// 添加/编辑用户信息
		if (params.getUser().getUserid() == null) {
			// 新增用户
			if (MapperManager.userMapper
					.checkPhone(params.getUser().getPhone()) > 0) {
				result.setResult(ResponseCode.failure);
				result.setMsg("手机号已存在");
				return result;
			}
			//微信不为空，则检测微信是否存在 lhy 2019年6月20日
			if (StringUtils.isNotEmpty(params.getUser().getNoWxAlias())) {
				if (MapperManager.userMapper.checkNoWxAlias(params.getUser().getNoWxAlias()) > 0) {
					result.setResult(ResponseCode.failure);
					result.setMsg("微信号已存在");
					return result;
				}
			}
			params.getUser().setPassword(MD5.encrypt("123456"));
			params.getUser().setCreatetime(dt);
			MapperManager.userMapper.insertSelective(params.getUser());
		} else {
			//修改微信则检测微信是否重复 lhy 2019年6月20日
			if(StringUtils.isNotEmpty(params.getUser().getNoWxAlias())) {
				User findUser=MapperManager.userMapper.queryByNoWxAlias(params.getUser().getNoWxAlias());
				if (findUser != null && !findUser.getUserid().equals(params.getUser().getUserid())) {
					result.setResult(ResponseCode.failure);
					result.setMsg("微信号已存在");
					return result;
				}
			}
			// 修改用户
			params.getUser().setPhone(null);
			params.getUser().setPassword(null);
			params.getUser().setStatus(null);
			params.getUser().setCreatetime(null);
			MapperManager.userMapper.updateByPrimaryKeySelective(params
					.getUser());
		}
		// 判断是否是预约状态
		if (order.getClinicid() != null && order.getReservedate() != null
				&& ObjectUtil.isNotEmpty(order.getReservetime())) {
			order.setStatus(2);// 已预约
		} else if (order.getAdminid() != null) {
			order.setStatus(1);// 已分配人员
			//order.setAllottime(new Date());//最新分配时间 2019.08.12 v1.4
		}
		// 添加/编辑工单
		if (ObjectUtil.isEmpty(order.getOrderno())) {
			// 新增工单
			String orderno = GenerateOrderno.getInstance().GenerateOrder();
			order.setOrderno(orderno);
			order.setUserid(params.getUser().getUserid());
			order.setCreatetime(dt);
			MapperManager.workOrderMapper.insertSelective(order);
			String content = "新增" + (order.getAdminid() != null ? "分配" : "");
			insertWorkRecord(orderno, admin.getAdminid(), content, null);
		} else {
			WorkOrder temp = MapperManager.workOrderMapper
					.selectByPrimaryKey(order.getOrderno());
			// 修改工单
			order.setUserid(null);
			order.setStatus(null);
			order.setCreatetime(null);
			MapperManager.workOrderMapper.updateByPrimaryKeySelective(order);
			if (temp.getStatus() == 0 && order.getAdminid() != null) {
				insertWorkRecord(order.getOrderno(), admin.getAdminid(), "分配",
						null);
			}
		}
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public ReturnVo batchassign(WorkOrderParams params, LoginAdmin admin) {
		ReturnVo result = new ReturnVo();
		if (params.getAdminid() == null) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("请选择分配人员");
			return result;
		}
		if (ObjectUtil.isEmpty(params.getOrdernos())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("请选择分配工单");
			return result;
		}
		params.setAllottime(new Date());//最新分配时间 2019.08.12 v1.4
		if (MapperManager.workOrderMapper.batchassignWorkOrder(params) > 0) {
			// 插入工单记录
			for (String orderno : params.getOrdernos()) {
				insertWorkRecord(orderno, admin.getAdminid(), "分配", null);
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
	public ReturnVo close(WorkOrder order, LoginAdmin admin) {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(order.getOrderno())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		if (ObjectUtil.isEmpty(order.getClosereason())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("请填写关闭原因");
			return result;
		}
		/*	WorkOrder workOrder = MapperManager.workOrderMapper
				.selectByPrimaryKey(order.getOrderno());
		
		 * if (!admin.getAdminid().equals(workOrder.getAdminid())) {
		 * result.setResult(ResponseCode.failure);
		 * result.setMsg("非法操作，您不能关闭该工单！"); return result; }
		 */
		WorkOrder updateOrder=new WorkOrder();
		updateOrder.setOrderno(order.getOrderno());
		updateOrder.setClosereason(admin.getName()+":"+order.getClosereason());
		if (MapperManager.workOrderMapper.closeWorkOrder(updateOrder) > 0) {
			// 添加记录
			insertWorkRecord(order.getOrderno(), admin.getAdminid(),
					"关闭工单！关闭原因：" + (null==order.getClosereason()?"":order.getClosereason()), order.getFollowup());
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
	public ReturnVo followup(WorkOrder order, LoginAdmin admin) {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(order.getOrderno())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		if (ObjectUtil.isEmpty(order.getComplaint())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("请填写沟通内容");
			return result;
		}
		/*WorkOrder workOrder = MapperManager.workOrderMapper
				.selectByPrimaryKey(order.getOrderno());
		/*
		 * if (!admin.getAdminid().equals(workOrder.getAdminid())) {
		 * result.setResult(ResponseCode.failure);
		 * result.setMsg("非法操作，您不能跟进该工单！"); return result; }
		 */
		order.setDoctorid(0);
		if (MapperManager.workOrderMapper.followupWorkOrder(order) > 0) {
			// 添加记录
			insertWorkRecord(order.getOrderno(), admin.getAdminid(),
					order.getComplaint(), order.getFollowup());
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
	public ReturnVo reserve(WorkOrder order, LoginAdmin admin) {
		ReturnVo result = new ReturnVo();
		String msg = checkReserve(order);
		if (!"".equals(msg)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(msg);
			return result;
		}
		/*WorkOrder workOrder = MapperManager.workOrderMapper
				.selectByPrimaryKey(order.getOrderno());
		/*
		 * if (!admin.getAdminid().equals(workOrder.getAdminid())) {
		 * result.setResult(ResponseCode.failure);
		 * result.setMsg("非法操作，您不能预约该工单！"); return result; }
		 */
		if (MapperManager.workOrderMapper.reserveWorkOrder(order) > 0) {
			insertWorkRecord(order.getOrderno(), admin.getAdminid(), "预约", null);
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	private String checkReserve(WorkOrder order) {
		if (ObjectUtil.isEmpty(order.getOrderno()))
			return ResponseCode.parameterErrorMsg;
		if (order.getClinicid() == null)
			return "请选择诊所";
		if (order.getReservedate() == null)
			return "请选择预约日期";
		if (ObjectUtil.isEmpty(order.getReservetime()))
			return "请选择预约时间";
		return "";
	}

	/**
	 * 插入工单记录
	 */
	@Override
	public Integer insertWorkRecord(String orderno, Integer adminid,
			String content, Integer talktime) {
		Date now= new Date();
		WorkRecord record = new WorkRecord();
		record.setOrderno(orderno);
		record.setAdminid(adminid);
		record.setContent(content);
		record.setTalktime(talktime);
		record.setCreatetime(now);
		MapperManager.workRecordMapper.insertSelective(record);
		
		//1.最近跟进时间及操作 v1.4 2019.08.12 
		WorkOrder updateWorkOrder=new WorkOrder();
		updateWorkOrder.setOrderno(orderno);
		updateWorkOrder.setFollowuptime(now);
		updateWorkOrder.setFollowupremarks(content);
		MapperManager.workOrderMapper.updateByPrimaryKeySelective(updateWorkOrder);
		return record.getId();
	}

	@Override
	public ReturnVo updatereserve(WorkOrder order) {
		ReturnVo result = new ReturnVo();
		String msg = checkReserve(order);
		if (!"".equals(msg)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(msg);
			return result;
		}
		if (MapperManager.workOrderMapper.updateReserve(order) > 0) {
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
	public ReturnVo turnfollowup(WorkRecord params, LoginAdmin admin) {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(params.getOrderno())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		if (ObjectUtil.isEmpty(params.getContent())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("请填写原因");
			return result;
		}
		if (MapperManager.workOrderMapper.turnFollowUp(params) > 0) {
			Date now=new Date();
			params.setContent("转跟进！原因：" + params.getContent());
			params.setAdminid(admin.getAdminid());
			params.setCreatetime(now);
			MapperManager.workRecordMapper.insertSelective(params);
			
			//1.最近跟进时间及操作 v1.4 2019.08.12 
			WorkOrder updateWorkOrder=new WorkOrder();
			updateWorkOrder.setOrderno(params.getOrderno());
			updateWorkOrder.setFollowuptime(now);
			updateWorkOrder.setFollowupremarks(params.getContent());
			MapperManager.workOrderMapper.updateByPrimaryKeySelective(updateWorkOrder);
			
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
	public ReturnVo arrivals(WorkOrder order, LoginAdmin admin) {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(order.getOrderno())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		if (ObjectUtil.isEmpty(order.getDoctorid())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("请选择医生");
			return result;
		}
		order.setArrivaltime(new Date());
		if (MapperManager.workOrderMapper.arrivals(order) > 0) {
			insertWorkRecord(order.getOrderno(), admin.getAdminid(), "已到店",
					null);
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
	public ReturnVo transaction(WorkOrder order, LoginAdmin admin) {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(order.getOrderno())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		if (order.getProjectid() == null) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("请选择成交项目");
			return result;
		}
		if (MapperManager.workOrderMapper.transaction(order) > 0) {
			//跟进记录
			insertWorkRecord(order.getOrderno(), admin.getAdminid(), "已成交！"
					+(null==order.getWorknotes()?"":order.getWorknotes()), null);
			//收费记录 2019.08.12 v1.4
			OrderPayDto orderpay =new OrderPayDto();
			orderpay.setAdminid(admin.getAdminid());
			orderpay.setAmount(order.getAmount());
			orderpay.setReceivablesamt(order.getReceivablesamt());
			orderpay.setDebtamt(order.getDebtamt());
			orderpay.setPayTime(order.getPayTime());
			orderpay.setFirstPay(YESNO.Y.name());
			orderpay.setOrderno(order.getOrderno());
			orderpay.setAdminname(admin.getName());
			orderPayService.addOrderPay(orderpay);
			
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
	public ReturnVo activation(WorkOrder order, LoginAdmin admin) {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(order.getOrderno())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg(ResponseCode.parameterErrorMsg);
			return result;
		}
		// 如果激活的角色是电销，直接挂在他的名下
		if (admin.getRoles().contains(3)) {
			order.setAdminid(admin.getAdminid());
		}
		if (MapperManager.workOrderMapper.activation(order) > 0) {
			insertWorkRecord(order.getOrderno(), admin.getAdminid(), "激活工单",
					null);
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} else {
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	@Override
	public ReturnVo wxChat(WorkRecordVo params, LoginAdmin admin) {
		if (ObjectUtil.isEmpty(params.getOrderno())) {
			throw new LssException(ResponseCode.parameterError, "工单编号为空");
		}
		if (ObjectUtil.isEmpty(params.getShopWx())) {
			throw new LssException(ResponseCode.parameterError, "终端微信为空");
		}
		if (ObjectUtil.isEmpty(params.getMemberNo())) {
			throw new LssException(ResponseCode.parameterError, "客户编号为空");
		}
		if (ObjectUtil.isEmpty(params.getRemark2())) {//存贮ZtcPersonMember的json信息，用于查看聊天详情参数
			throw new LssException(ResponseCode.parameterError, "客户微信信息为空");
		}
		//同一个工单、客户编号、终端编号 一天只记录一次聊天记录
		String key = params.getShopWx() + ":" + params.getMemberNo();
		int count=0;//检测今天是否已聊天
		WorkRecordVo queryCountParam=new WorkRecordVo();
		queryCountParam.setOrderno(params.getOrderno());
		queryCountParam.setRemark(key);
		Date now = DateUtils.getDateDay(new Date());
		Date nextDay = DateUtils.addDate(now, 1);
		queryCountParam.setStart(now);//含
		queryCountParam.setEnd(nextDay);//不包含
		count = MapperManager.workRecordMapper.getRecordListCount(queryCountParam);
		if(count>0) {
			ReturnVo.success(null);
		}else {
			Date nowTime=new Date();
			WorkRecord workRecord = new WorkRecord();
			workRecord.setOrderno(params.getOrderno());
			workRecord.setRemark2(params.getRemark2());
			workRecord.setContent("微信聊天");
			workRecord.setRemark(key);
			workRecord.setCreatetime(nowTime);
			workRecord.setAdminid(admin.getAdminid());
			MapperManager.workRecordMapper.insertSelective(workRecord);
			
			//1.最近跟进时间及操作 v1.4 2019.08.12 
			WorkOrder updateWorkOrder=new WorkOrder();
			updateWorkOrder.setOrderno(params.getOrderno());
			updateWorkOrder.setFollowuptime(nowTime);
			updateWorkOrder.setFollowupremarks(workRecord.getContent());
			MapperManager.workOrderMapper.updateByPrimaryKeySelective(updateWorkOrder);
		}
		return ReturnVo.success(null);
		
	}

	@Override
	public ReturnVo deleteWorkOrder(WorkOrder order, LoginAdmin admin) {
		log.info("删除工单：orderno:{},删除人：{}-{}" + order.getOrderno(), admin.getAdminid(), admin.getName());
		if (ObjectUtil.isEmpty(order.getOrderno())) {
			throw new LssException(ResponseCode.parameterError, "工单编号为空");
		}
		int i=MapperManager.workOrderMapper.deleteOrder(order);
		ReturnVo result=null;
		if (i > 0) {
			// 添加记录
			insertWorkRecord(order.getOrderno(), admin.getAdminid(),
					"删除工单！"+admin.getName(), order.getFollowup());
			result=ReturnVo.success(null);
		} else {
			result=ReturnVo.failure(ResponseCode.failure,ResponseCode.failureMsg);
		}
		return result;
	}
	
	
	@Override
	public ReturnVo batchTransfer(WorkOrderParams params, LoginAdmin admin) {
		ReturnVo result = new ReturnVo();
		if (params.getAdminid() == null) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("请选择接受人");
			return result;
		}
		if (ObjectUtil.isEmpty(params.getOrdernos())) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("请选择分配工单");
			return result;
		}
		
		//params.setAllottime(new Date());//最新分配时间 2019.08.12 v1.4
		if (MapperManager.workOrderMapper.batchTransfer(params) > 0) {
			// 插入工单记录
			for (String orderno : params.getOrdernos()) {
				insertWorkRecord(orderno, admin.getAdminid(), "转让", null);
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
	public void autoCloseWorkOrder(String batchNum) {
//		掉库包含的工单状态：新分配、待跟进、已预约、已到店
		Date now = DateUtils.getDateDay(new Date());
		Date nextDay = DateUtils.addDate(now, SystemConstant.WORK_ORDER_CLOSE_DAY);
		WorkOrderParams params =new WorkOrderParams();
		params.setEndCloseTime(nextDay);
		try {
			log.info("批次{}:关闭截止日期 {} ",batchNum, DateUtils.formatDateTime(nextDay));
			// 第一步：7天未跟进的工单关闭前记录其跟进记录
			int cnt1 = MapperManager.workRecordMapper.autoCloseWorkOrderRecord(params);
			int cnt2 = 0;
			if(cnt1>0) {//有需要关闭的工单
				// 第二步：7天未跟进的工单关闭
				cnt2 = MapperManager.workOrderMapper.autoCloseWorkOrder(params);
			}
			log.info("批次{}:共记录关闭跟进数据 {}条，关闭{}条工单", batchNum, cnt1, cnt2);
		} catch (Exception e) {
			log.error("系统关闭工单异常！", e);
			throw new LssException(ResponseCode.failure,"系统关闭工单异常！");
		}
	}

	
	@Override
	public ReturnVo returnList(WorkOrderParams params, LoginAdmin loginAdmin) {
		log.debug("returnList(WorkOrderParams params = {}, LoginAdmin loginAdmin = {})",params,loginAdmin);
		//status 状态 0未分配 1待跟进 2已预约 3已到店 4已完成 5已关闭 10新分配 99全部 98待回收列表
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		// 查询条件
		if (ObjectUtil.isEmpty(loginAdmin.getRoles())) {
			result.setResult(ResponseCode.failure);
			result.setMsg("还未分配角色");
			return result;
		}
		//查询状态不能为空,为空默认查待跟进
		if(params.getStatus()==null) {
			params.setStatus(1);
		}

		//电销只能看自己的	
		if (loginAdmin.getRoles().contains(3)) {
			if(null==params.getAdminid()) {
				params.setAdminid(loginAdmin.getAdminid());
			}
		}	
		if (loginAdmin.getRoles().contains(4) || loginAdmin.getRoles().contains(5)
				|| loginAdmin.getRoles().contains(6)) {
			params.setClinicid(loginAdmin.getClinicid());
		}
		
		//电销组长 ，看到自己和下属的
		if(loginAdmin.getRoles().contains(9)) {
			Admin findAdmin= MapperManager.adminMapper.selectByPrimaryKey(loginAdmin.getAdminid());
			if (findAdmin.getOrgId() != null) {
				//下属及同组员工 
				List<Integer> adminids = MapperManager.adminMapper.selectByOrgId(findAdmin.getOrgId());
				if(adminids.size()==0) {//没下属，则给一个不存在的账号 避免拿了所有员工数据
					adminids.add(-1); 
				}
				params.setAdminids(adminids);
				params.setAdminid(null);
			}else {
				throw new LssException(ResponseCode.parameterError, "电销组长未分配组织");
			}
		}
		
		// 排序
		String sort = "a.returndate DESC";
		params.setSort(sort);
		result.setTotal(MapperManager.workOrderMapper.workOrderCount(params));//查总数
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		List<WorkOrderVo> data=MapperManager.workOrderMapper.workOrderList(params);
		result.setObj(data);
		return result;
	}

	
	@Override
	public ReturnVo returnCount(WorkOrderParams params, LoginAdmin loginAdmin) {
		log.debug("returnCount(WorkOrderParams params = {}, LoginAdmin loginAdmin = {})",params,loginAdmin);
		//status 状态 0未分配 1待跟进 2已预约 3已到店 4已完成 5已关闭 10新分配 99全部 98待回收列表
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		// 查询条件
		if (ObjectUtil.isEmpty(loginAdmin.getRoles())) {
			result.setResult(ResponseCode.failure);
			result.setMsg("还未分配角色");
			return result;
		}
		//查询状态不能为空,为空默认查待跟进
		if(params.getStatus()==null) {
			params.setStatus(1);
		}

		//电销只能看自己的	
		if (loginAdmin.getRoles().contains(3)) {
			if(null==params.getAdminid()) {
				params.setAdminid(loginAdmin.getAdminid());
			}
		}	
		if (loginAdmin.getRoles().contains(4) || loginAdmin.getRoles().contains(5)
				|| loginAdmin.getRoles().contains(6)) {
			params.setClinicid(loginAdmin.getClinicid());
		}
		
		//电销组长 ，看到自己和下属的
		if(loginAdmin.getRoles().contains(9)) {
			Admin findAdmin= MapperManager.adminMapper.selectByPrimaryKey(loginAdmin.getAdminid());
			if (findAdmin.getOrgId() != null) {
				//下属及同组员工 
				List<Integer> adminids = MapperManager.adminMapper.selectByOrgId(findAdmin.getOrgId());
				if(adminids.size()==0) {//没下属，则给一个不存在的账号 避免拿了所有员工数据
					adminids.add(-1); 
				}
				params.setAdminids(adminids);
				params.setAdminid(null);
			}else {
				throw new LssException(ResponseCode.parameterError, "电销组长未分配组织");
			}
		}
		
		result.setTotal(MapperManager.workOrderMapper.workOrderCount(params));//查总数
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	
	@Override
	public ReturnVo updateIsReturn(WorkOrderParams params) {
		log.debug("updateIsReturn(WorkOrderParams params = {}) -start",params);
		ReturnVo result = new ReturnVo();
		try {
			MapperManager.workOrderMapper.updateIsReturn(params);
			result.setResult(ResponseCode.success);
			result.setMsg(ResponseCode.successMsg);
		} catch (Exception e) {
			log.error("更新待回访状态失败!");
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
		}
		return result;
	}

	
	@Override
	public ReturnVo reserveCount(WorkOrderParams params, LoginAdmin loginAdmin) {
		log.debug("reserveCount(WorkOrderParams params, LoginAdmin loginAdmin) -start",params,loginAdmin);
		ReturnVo result = new ReturnVo();
		if(null == params.getStart()) {
			params.setStart(new Date());
		}
		//if()
		try {
			//先获取预约的时间段
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(params.getStart());
			int hours = calendar.get(Calendar.HOUR_OF_DAY);
			System.out.println(hours);
			String timeStart = "";
			String timeEnd = "";
			List<String> times = new ArrayList<>();
			switch (hours) {
			case 9:
				timeStart = "9:00~9:30";
				timeEnd = "9:30~10:00";
				times.add(timeStart);
				times.add(timeEnd);
				break;
				
			case 10:
				timeStart = "10:00~10:30";
				timeEnd = "10:00~11:00";
				times.add(timeStart);
				times.add(timeEnd);			
				break;
			
			case 11:
				timeStart = "11:00~11:30";
				timeEnd = "11:30~12:00";
				times.add(timeStart);
				times.add(timeEnd);	
				break;
			
			case 12:
				timeStart = "12:00~12:30";
				timeEnd = "12:30~13:00";
				times.add(timeStart);
				times.add(timeEnd);	
				break;
			
			case 13:
				timeStart = "13:00~13:30";
				timeEnd = "13:30~14:00";
				times.add(timeStart);
				times.add(timeEnd);	
				break;
			
			case 14:
				timeStart = "14:00~14:30";
				timeEnd = "14:30~15:00";
				times.add(timeStart);
				times.add(timeEnd);
				break;
			
			case 15:
				timeStart = "15:00~15:30";
				timeEnd = "15:30~16:00";
				times.add(timeStart);
				times.add(timeEnd);
				break;
			
			case 16:
				timeStart = "16:00~16:30";
				timeEnd = "16:30~17:00";
				times.add(timeStart);
				times.add(timeEnd);
				break;
			
			case 17:
				timeStart = "17:00~17:30";
				timeEnd = "17:30~18:00";
				times.add(timeStart);
				times.add(timeEnd);
				break;
			
			case 18:
				timeStart = "18:00~18:30";
				timeEnd = "18:30~19:00";
				times.add(timeStart);
				times.add(timeEnd);
				break;
			
			case 19:
				timeStart = "19:00~19:30";
				timeEnd = "19:30~20:00";
				times.add(timeStart);
				times.add(timeEnd);
				break;
			
			case 20:
				timeStart = "20:00~20:30";
				timeEnd = "20:30~21:00";
				times.add(timeStart);
				times.add(timeEnd);
				break;
				
			case 21:
				timeStart = "21:00~21:30";
				timeEnd = "21:30~22:00";
				times.add(timeStart);
				times.add(timeEnd);
				break;

			default:
				break;
			}
			
			params.setTimes(times);
			
			//再获取预约日期
			SimpleDateFormat sft  = new SimpleDateFormat("yyyy-MM-dd");
			String start = sft.format(params.getStart());
			String end = sft.format(params.getStart());
			params.setStart(sft.parse(start));
			params.setEnd(sft.parse(end));
			
			
		} catch (Exception e) {
			log.error("时间转换错误!",e);
			result.setResult(ResponseCode.failure);
			result.setMsg("时间转换错误!");
			return result;
		}
		
		if(loginAdmin.getRoles().contains(3)&&!loginAdmin.getRoles().contains(9)) {
			if(null == params.getAdminid()) {
				params.setAdminid(loginAdmin.getAdminid());
			}
		}
		
		if(loginAdmin.getRoles().contains(9)) {//电销组长 ，看到自己和下属的
			Admin findAdmin= MapperManager.adminMapper.selectByPrimaryKey(loginAdmin.getAdminid());
			if (findAdmin.getOrgId() != null) {
				//如果没有用所属人查询就查小组所有成员
				if(params.getAdminid()==null) {
					//下属及同组员工 
					List<Integer> adminids = MapperManager.adminMapper.selectByOrgId(findAdmin.getOrgId());
					if(adminids.size()==0) {//没下属，则给一个不存在的账号 避免拿了所有员工数据
						adminids.add(-1); 
					}
					params.setAdminids(adminids);
				}
			}else {
				throw new LssException(ResponseCode.parameterError, "电销组长未分配组织");
			}
		}
		
		//同时拥有电销组长,电销员两个角色
		if(loginAdmin.getRoles().contains(9) && loginAdmin.getRoles().contains(3)) {
			Admin findAdmin= MapperManager.adminMapper.selectByPrimaryKey(loginAdmin.getAdminid());
			if (findAdmin.getOrgId() != null) {
				//如果没有用所属人查询就查小组所有成员
				if(params.getAdminid()==null) {
					//下属及同组员工 
					List<Integer> adminids = MapperManager.adminMapper.selectByOrgId(findAdmin.getOrgId());
					if(adminids.size()==0) {//没下属，则给一个不存在的账号 避免拿了所有员工数据
						adminids.add(-1); 
					}
					params.setAdminids(adminids);
				}
			}else {
				throw new LssException(ResponseCode.parameterError, "电销组长未分配组织");
			}
		}
		result.setTotal(MapperManager.workOrderMapper.reserveCount(params));//查总数
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		
		
		
		return result;
	}
 
	
}
