package com.lss.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lss.admin.base.ServiceManager;
import com.lss.admin.service.UserService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.Admin;
import com.lss.core.pojo.User;
import com.lss.core.pojo.WorkOrder;
import com.lss.core.pojo.WorkTag;
import com.lss.core.util.DateUtils;
import com.lss.core.util.ExportExcel;
import com.lss.core.util.FormatUtil;
import com.lss.core.util.GenerateOrderno;
import com.lss.core.util.MD5;
import com.lss.core.util.ObjectUtil;
import com.lss.core.util.POIExcelUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.UserExeclVo;
import com.lss.core.vo.admin.UserVo;
import com.lss.core.vo.admin.params.UserExeclParams;
import com.lss.core.vo.admin.params.UserParams;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public ReturnVo save(User user) {
		ReturnVo result = new ReturnVo();
		int res = 0;
		if (user.getUserid() == null || user.getUserid().intValue() <= 0) {
			String msg = checkUser(user);
			if (!"".equals(msg)) {
				result.setResult(ResponseCode.parameterError);
				result.setMsg(msg);
				return result;
			}
			// 添加时默认添加一条工单信息
			if (MapperManager.userMapper.checkPhone(user.getPhone()) > 0) {
				result.setResult(ResponseCode.failure);
				result.setMsg("手机号已存在");
				return result;
			}
			Date dt = new Date();
			user.setPassword(MD5.encrypt("123456"));
			user.setCreatetime(dt);
			res = MapperManager.userMapper.insertSelective(user);
			// 默认添加
			WorkOrder order = new WorkOrder();
			order.setOrderno(GenerateOrderno.getInstance().GenerateOrder());
			order.setUserid(user.getUserid());
			order.setCreatetime(dt);
			order.setWorknotes(user.getNotes());
			res = MapperManager.workOrderMapper.insertSelective(order);
		} else {
			user.setPhone(null);
			user.setPassword(null);
			user.setStatus(null);
			user.setCreatetime(null);
			// 修改
			res = MapperManager.userMapper.updateByPrimaryKeySelective(user);
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
	 * 验证用户信息
	 * 
	 * @return
	 */
	@Override
	public String checkUser(User user) {
		if (user == null)
			return ResponseCode.parameterErrorMsg;
		if (ObjectUtil.isEmpty(user.getPhone()))
			return "手机号不能为空";
		else {
			if (!FormatUtil.matchMobile(user.getPhone()))
				return "手机号格式错误";
		}
		if (ObjectUtil.isEmpty(user.getName()))
			return "请填写客户姓名";
		if (ObjectUtil.isEmpty(user.getSourceid()))
			return "请选择客户来源";
		if (ObjectUtil.isEmpty(user.getSourcedate()))
			return "请填写来源日期";
		if (ObjectUtil.isNotEmpty(user.getSourceid2())&&ObjectUtil.isEmpty(user.getSourcename2()))
			return "客户二级来源名称不能为空";//二级来源ID非空，则名称必须非空
		return "";
	}

	@Override
	public ReturnVo list(UserParams params) {
		ReturnVo result = new ReturnVo();
		result.setPage(params.getPage());
		result.setLimit(params.getLimit());
		if (params.getStart() != null) {
			params.setStart(DateUtils.getDateDay(params.getStart()));
		}
		if (params.getEnd() != null) {
			params.setEnd(DateUtils.addDate(
					DateUtils.getDateDay(params.getEnd()), 1));
		}
		List<UserVo> list = MapperManager.userMapper.userList(params);
//		if (ObjectUtil.isNotEmpty(list)) {//隐藏手机号 2019.06.14
//			for(UserVo user : list) {
//				user.setPhone(FormatUtil.formatMobile(user.getPhone()));
//			}
//		}
		result.setTotal(MapperManager.userMapper.userCount(params));
		result.setObj(list);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

	@Override
	public ReturnVo batchadd(MultipartFile file, LoginAdmin loginAdmin)
			throws Exception {
		ReturnVo result = new ReturnVo();
		List<List<String>> list;
		try {
			list = POIExcelUtil.readExcel(file.getInputStream(), "xls", 0);
		} catch (Exception e) {
			log.error("解析excel失败", e);
			result.setResult(ResponseCode.failure);
			result.setMsg("解析excel失败");
			return result;
		}
		if (ObjectUtil.isEmpty(list)) {
			result.setResult(ResponseCode.parameterError);
			result.setMsg("模板客户信息为空");
			return result;
		}
		// 预约项目
		WorkTag tagParam=new WorkTag();
		tagParam.setType(1);
		List<WorkTag> projectlist = MapperManager.workTagMapper.queryTags(tagParam);// 查询预约项目列表
		// 查询来源信息
		tagParam.setType(2);
		List<WorkTag> sourcelist = MapperManager.workTagMapper.queryTags(tagParam);// 查询来源列表
		int success = 0, failure = 0, repeat = 0;
		List<String> failurePhones = new ArrayList<String>();
		List<String> repeatPhones = new ArrayList<String>();
		// 循环客户信息
		for (int i = 0; i < list.size(); i++) {
			List<String> temp = list.get(i);
			String  phone=temp.get(1).trim();
			String name=temp.get(0).trim();
			int res = 0;
			try {
				res = ServiceManager.userService.insertUser(temp, sourcelist,
						projectlist, loginAdmin);
				switch (res) {
				case 0:
					failure++;
					failurePhones.add(phone);
					break;
				case 1:
					success++;
					break;
				case 2:
					repeat++;
					repeatPhones.add(phone);
					break;
				}
			} catch (Exception e) {
				failure++;
				failurePhones.add(phone);
				log.error("导入失败：客户{},手机号{}失败!",name,phone);
				log.error("导入失败", e);
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("批量导入成功" + success + "条！");
		if (repeat > 0) {
			sb.append("重复客户信息" + repeat + "条！");
			sb.append("重复电话：" + repeatPhones.toString());
		}
		if (failure > 0) {
			sb.append("失败" + failure + "条！");
			sb.append("失败电话：" + failurePhones.toString());
		}
		result.setResult(ResponseCode.success);
		result.setMsg(sb.toString());
		log.info("导入完成，结果:{}", result.getMsg());
		return result;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public int insertUser(List<String> temp, List<WorkTag> sourcelist,
			List<WorkTag> projectlist, LoginAdmin loginAdmin) {
		Date dt = new Date();
		// 插入用户信息
		User user = new User();
		user.setName(temp.get(0).trim());// 客户姓名
		String phone = temp.get(1).trim();// 客户电话
		if ("".equals(phone)) {
			log.info("导入失败：客户{},手机号{}为空!",user.getName(),phone);
			return 0;
		}
		// 验证手机号 
		if (MapperManager.userMapper.checkPhone(phone) > 0) {
			if (temp.size() >= 8) {//看有没有用分配人员
				String customerService = temp.get(7).trim();// 跟进客服
				if (!"".equals(customerService)) {
					Admin admin = MapperManager.adminMapper
							.queryByName(customerService);
					if (admin != null) {//分配人员非空，则检测客户否有未分配工单
						//检测 该手机号客户是否已分配，未分配则进行分配，已分配则提示 重复
						UserVo findUser = MapperManager.userMapper.queryByPhone(phone);
						// 用户信息存在。。查询是否有待分配的工单
						WorkOrder findOrder = MapperManager.workOrderMapper.queryWorkOrder(findUser.getUserid());
						if (findOrder == null) { 
							//没有工单。不管，逻辑照旧
							log.info("导入失败：客户{},手机号{}重复!",user.getName(),phone);
							return 2;
						}else if(0==findOrder.getStatus()) {//待分配，则进行分配
							WorkOrder updateOrder=new WorkOrder();
							updateOrder.setAdminid(admin.getAdminid());
							//FixBug:1510导入客户信息，部分渠道没有分配日期
							updateOrder.setAllottime(new Date());
							updateOrder.setOrderno(findOrder.getOrderno());
							updateOrder.setStatus(1);
							if (temp.size() >= 9) {
								updateOrder.setWorknotes(temp.get(8).trim());
							}
							String project = temp.get(6).trim();// 预约项目
							if (!"".equals(project)) {
								for (WorkTag tag : projectlist) {
									if (tag.getTagname().equals(project)) {
										updateOrder.setProjectid(tag.getTagid());
										break;
									}
								}
							}
							MapperManager.workOrderMapper.updateByPrimaryKeySelective(updateOrder);
							ServiceManager.workOrderService.insertWorkRecord(findOrder.getOrderno(), loginAdmin.getAdminid(), "分配",
									null);
							return 1;
						}else {//工单非未分配状态，提示重复
							log.info("导入失败：客户{},手机号{}重复!",user.getName(),phone);
							return 2;
						}
					}else {
						//分配人员没查到，没有分配人员 ，直接提示重复
						log.info("导入失败：客户{},手机号{}重复!",user.getName(),phone);
						return 2;
					}
				}else {
					//没有分配人员 ，直接提示重复
					log.info("导入失败：客户{},手机号{}重复!",user.getName(),phone);
					return 2;
				}
			}else {//没有分配人员 ，直接提示重复
				log.info("导入失败：客户{},手机号{}重复!",user.getName(),phone);
				return 2;
			}
		}else {
			user.setPhone(phone);
			String sex = temp.get(2).trim();// 性别
			if ("男".equals(sex))
				user.setSex(1);
			else if ("女".equals(sex))
				user.setSex(2);
			String age = temp.get(3).trim();// 年龄
			if (!"".equals(age)) {
				try {
					user.setAge(Integer.parseInt(age));
				} catch (Exception e) {
				}
			}
			String source = temp.get(4).trim();// 来源
			if (!"".equals(source)) {
				for (WorkTag tag : sourcelist) {
					if (tag.getTagname().equals(source)) {
						user.setSourceid(tag.getTagid());
						break;
					}
				}
			}
			// 来源日期格式
			String sourceDate = temp.get(5).trim();// 来源日期
			if (ObjectUtil.isNotEmpty(sourceDate)) {
				log.error("来源日期{}" ,sourceDate);
				user.setSourcedate(DateUtils.parseDate(sourceDate));
	//			user.setSourcedate(new Date(sourceDate));
			}
			user.setPassword(MD5.encrypt("123456"));
			if (temp.size() >= 9) {
				user.setNotes(temp.get(8).trim());// 备注
			}
			user.setCreatetime(dt);
			MapperManager.userMapper.insertSelective(user);
			// 插入工单信息
			String orderno = GenerateOrderno.getInstance().GenerateOrder();
			WorkOrder order = new WorkOrder();
			order.setOrderno(orderno);
			order.setUserid(user.getUserid());
			order.setCreatetime(dt);
			order.setWorknotes(user.getNotes());
			String project = temp.get(6).trim();// 预约项目
			if (!"".equals(project)) {
				for (WorkTag tag : projectlist) {
					if (tag.getTagname().equals(project)) {
						order.setProjectid(tag.getTagid());
						break;
					}
				}
			}
			if (temp.size() >= 8) {
				String customerService = temp.get(7).trim();// 跟进客服
				if (!"".equals(customerService)) {
					Admin admin = MapperManager.adminMapper
							.queryByName(customerService);
					if (admin != null)
						order.setAdminid(admin.getAdminid());
				}
			}
			String content = "新增";
			// 如果已分配人员 状态设置为待跟进
			if (order.getAdminid() != null) {
				//2019-09-25 v1.4.4 导入时如果有所属人员则设置分配日期
				order.setAllottime(new Date());
				order.setStatus(1);
				content += "分配";
			}
			MapperManager.workOrderMapper.insertSelective(order);
			ServiceManager.workOrderService.insertWorkRecord(orderno,
					loginAdmin.getAdminid(), content, null);
			return 1;
		}
		
	}

	@Override
	public void batchexport(UserExeclParams params, HttpServletResponse response) {
		try {
			if (params == null)
				params = new UserExeclParams();
			if (ObjectUtil.isNotEmpty(params.getEnd())) {
				params.setEnd(DateUtils.formatdateFormat(DateUtils.addDate(
						DateUtils.parseDate(params.getEnd()), 1)));
			}
			List<UserExeclVo> list = MapperManager.userMapper
					.exportExcel(params);
			
			if (ObjectUtil.isNotEmpty(list)) {
//				for(UserExeclVo user : list) {隐藏手机号 2019.06.14
//					user.setPhone(FormatUtil.formatMobile(user.getPhone()));
//				}
				ExportExcel<UserExeclVo> ee = new ExportExcel<UserExeclVo>();
				String[] headers = { "电话", "姓名", "年龄", "创建时间", "一级来源","二级来源","客户意向", "所属人员",
						"来源url", "用户备注", };
				String fileName = "客户信息列表";
				ee.exportExcel(headers, list, fileName, response);
			}
		} catch (Exception e) {
			log.error("批量导出客户信息失败：" + e.toString());
		}
	}

	@Override
	public ReturnVo deleteUser(User user, LoginAdmin admin) {
		if (ObjectUtil.isEmpty(user.getUserid())) {
			throw new LssException(ResponseCode.parameterError, "客户编号为空");
		}
		log.info("删除客户：userid：{},删除人：{}-{}",user.getUserid(),admin.getAdminid(),admin.getName());
		int i= MapperManager.userMapper.deleteByPrimaryKey(user.getUserid());
		WorkOrder params=new WorkOrder();
		params.setUserid(user.getUserid());
		int oi=MapperManager.workOrderMapper.deleteOrderByUser(params);
		log.info("删除结果：userid{}:{},该用户工单数：{}",user.getUserid(),i,oi);
		return ReturnVo.success(null);
	}

	@Override
	public void updateUser(User user) {
		if (ObjectUtil.isEmpty(user.getUserid())) {
			throw new LssException(ResponseCode.parameterError, "客户编号为空");
		}
		int i= MapperManager.userMapper.updateByPrimaryKeySelective(user);
		log.info("修改结果：userid{}:{}",user.getUserid(),i);
	}

	@Override
	public UserVo findUserByPhone(String phone) {
		if (ObjectUtil.isEmpty(phone)) {
			throw new LssException(ResponseCode.parameterError, "手机号码为空");
		}
		return MapperManager.userMapper.queryByPhone(phone);
	}
	
	
	
}
