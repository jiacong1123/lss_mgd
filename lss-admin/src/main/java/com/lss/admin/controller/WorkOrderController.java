package com.lss.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lss.admin.base.BaseController;
import com.lss.admin.base.ServiceManager;
import com.lss.admin.service.IOrderPayService;
import com.lss.core.constant.ResponseCode;
import com.lss.core.dto.FindOrderPayPage;
import com.lss.core.dto.OrderPayDto;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.User;
import com.lss.core.pojo.UserArriavl;
import com.lss.core.pojo.UserCountExeclVo;
import com.lss.core.pojo.UserDetailExeclVo;
import com.lss.core.pojo.UserSource;
import com.lss.core.pojo.UserSourceExeclVo;
import com.lss.core.pojo.WorkOrder;
import com.lss.core.pojo.WorkRecord;
import com.lss.core.util.ExportExcel;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.WorkRecordVo;
import com.lss.core.vo.admin.params.UserArriavlExcelParam;
import com.lss.core.vo.admin.params.UserArriavlParam;
import com.lss.core.vo.admin.params.UserOrderParams;
import com.lss.core.vo.admin.params.WorkOrderExcelParams;
import com.lss.core.vo.admin.params.WorkOrderParams;

/**
 * 工单 controller
 * 
 * @author bobo
 *
 */
@RestController
@RequestMapping("workorder")
public class WorkOrderController extends BaseController {
	@Resource
	private IOrderPayService orderPayService;
	
	/**
	 * 待关闭工单总量
	 * 
	 * @return
	 */
	@RequestMapping("close/count")
	public ReturnVo closeCount(@RequestBody WorkOrderParams params) {
		return ServiceManager.workOrderService.closeCount(params, loginAdmin);
	}
	
	/**
	 * 一小时内预约总量
	 * 
	 * @return
	 */
	@RequestMapping("reserveCount")
	public ReturnVo reserveCount(@RequestBody WorkOrderParams params) {
		return ServiceManager.workOrderService.reserveCount(params, loginAdmin);
	}
	
	/**
	 * 工单列表
	 * 
	 * @return
	 */
	@RequestMapping("list")
	public ReturnVo list(@RequestBody WorkOrderParams params) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat newSFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if(null != params.getAllottimeStart()) {
				String format = sFormat.format(params.getAllottimeStart())+" 00:00:00";
				params.setAllottimeStart(newSFormat.parse(format));
			}
			if(null != params.getAllottimeEnd()) {
				String format = sFormat.format(params.getAllottimeEnd())+" 23:59:59";
				params.setAllottimeEnd(newSFormat.parse(format));
			}
			if(null != params.getFollowupTimeStart()) {
				String format = sFormat.format(params.getFollowupTimeStart())+" 00:00:00";
				params.setFollowupTimeStart(newSFormat.parse(format));
			}
			if(null != params.getFollowupTimeEnd()) {
				String format = sFormat.format(params.getFollowupTimeEnd())+" 23:59:59";
				params.setFollowupTimeEnd(newSFormat.parse(format));
			}
		} catch (Exception e) {
			ReturnVo vo = new ReturnVo();
			vo.setResult(ResponseCode.error);
			vo.setMsg("时间转换出错!");
			return vo;
		}
		
		return ServiceManager.workOrderService.list(params, loginAdmin);
	}
	
	/**
	 * 最近三天待回访列表
	 * 
	 * @return
	 */
	@RequestMapping("returnList")
	public ReturnVo returnList(@RequestBody WorkOrderParams params) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat newSFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//如果回访时间为空,默认取当前日期
			if(null == params.getReturndateEnd()) {
				params.setReturndateEnd(new Date());
			}
			if(null != params.getReturndateEnd()) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(params.getReturndateEnd());
				calendar.add(calendar.DAY_OF_MONTH, -3);
				String start = sFormat.format(calendar.getTime())+" 00:00:00";
				params.setReturndateStart(newSFormat.parse(start));
			}
			if(null != params.getReturndateEnd()) {
				String end = sFormat.format(params.getReturndateEnd())+" 23:59:59";
				params.setReturndateEnd(newSFormat.parse(end));
			}
		} catch (Exception e) {
			ReturnVo vo = new ReturnVo();
			vo.setResult(ResponseCode.error);
			vo.setMsg("时间转换出错!");
			return vo;
		}
		
		return ServiceManager.workOrderService.returnList(params, loginAdmin);
		
	}
	
	/**
	   * 查询十分钟内待回访数量
	 * 
	 * @return
	 */
	@RequestMapping("returnCount")
	public ReturnVo returnCount(@RequestBody WorkOrderParams params) {
		try {
			params.setIsReturn("1");
			//如果回访时间为空,默认取当前日期
			if(null == params.getReturndateStart()) {
				params.setReturndateStart(new Date());
			}
			//2019-11-08 新需求:查询十分钟内待回访数量,以弹窗形式提醒
			Calendar ca = Calendar.getInstance();
			ca.setTime(params.getReturndateStart());
			ca.add(Calendar.MINUTE, 10);
			params.setReturndateEnd(ca.getTime());
		} catch (Exception e) {
			ReturnVo vo = new ReturnVo();
			vo.setResult(ResponseCode.error);
			vo.setMsg("时间转换出错!");
			return vo;
		}
		return ServiceManager.workOrderService.returnCount(params, loginAdmin);
		
	}
	
	/**
	 * 最近三天待回访列表
	 * 
	 * @return
	 */
	@RequestMapping("updateIsReturn")
	public ReturnVo updateIsReturn(@RequestBody WorkOrderParams params) {
		//更新状态为已回访
		params.setIsReturn("2");
		return ServiceManager.workOrderService.updateIsReturn(params);
	}
	

	/**
	 * 工单详情
	 * 
	 * @return
	 */
	@RequestMapping("details")
	public ReturnVo details(@RequestBody WorkOrder order) {
		return ServiceManager.workOrderService.details(order.getOrderno());
	}

	/**
	 * 查询用户信息
	 * 
	 * @return
	 */
	@RequestMapping("userinfo")
	public ReturnVo userinfo(@RequestBody User user) {
		return ServiceManager.workOrderService.userinfo(user.getPhone(),user.getNoWxAlias());
	}

	/**
	 * 新增/编辑工单
	 * 
	 * @return
	 */
	@RequestMapping("save")
	public ReturnVo save(@RequestBody UserOrderParams params) {
		return ServiceManager.workOrderService.save(params, loginAdmin);
	}

	/**
	 * 批量分配
	 * 
	 * @return
	 */
	@RequestMapping("batchassign")
	public ReturnVo batchassign(@RequestBody WorkOrderParams params) {
		return ServiceManager.workOrderService.batchassign(params, loginAdmin);
	}

	/**
	 * 关闭工单
	 * 
	 * @return
	 */
	@RequestMapping("close")
	public ReturnVo close(@RequestBody WorkOrder order) {
		ReturnVo result = new ReturnVo();
		result.setResult(ResponseCode.parameterError);
		result.setMsg(ResponseCode.parameterErrorMsg);
		//更新状态为已回访
		if(null != order) {
			WorkOrderParams params = new WorkOrderParams();
			params.setIsReturn("2");
			List<String> ordernos = new ArrayList<String>();
			String[] ordernoArr = order.getOrderno().split(",");
			Collections.addAll(ordernos, ordernoArr);
			params.setOrdernos(ordernos);
			ServiceManager.workOrderService.updateIsReturn(params);
			//关闭工单时取消工单共享状态
			ServiceManager.workOrderService.cancleOffer(params,loginAdmin);
			return ServiceManager.workOrderService.close(order, loginAdmin);
		}else {
			return result;
		}
	}

	/**
	 * 回访跟进
	 * 
	 * @param order
	 * @return
	 */
	@RequestMapping("followup")
	public ReturnVo followup(@RequestBody WorkOrder order) {
		//处理待跟进工单时更新状态为已回访
		WorkOrder orderVo = ServiceManager.workOrderService.findWordOrderByOrderNo(order.getOrderno());
		if(orderVo!=null&&orderVo.getFollowup()>0) {
			WorkOrderParams params = new WorkOrderParams();
			//如果用户修改推迟回访时间,则更新状态为待回访
			if(order.getReturndate()!=null&&orderVo.getReturndate()!=null
					&&order.getReturndate().after(orderVo.getReturndate())) {
				params.setIsReturn("1");
			}
			List<String> ordernos = new ArrayList<String>();
			ordernos.add(order.getOrderno());
			params.setOrdernos(ordernos );
			ServiceManager.workOrderService.updateIsReturn(params);
		}
		
		return ServiceManager.workOrderService.followup(order, loginAdmin);
	}

	/**
	 * 预约
	 * 
	 * @return
	 */
	@RequestMapping("reserve")
	public ReturnVo reserve(@RequestBody WorkOrder order) {
		//更新状态为已回访
		WorkOrderParams params = new WorkOrderParams();
		params .setIsReturn("2");
		List<String> ordernos = new ArrayList<String>();
		ordernos.add(order.getOrderno());
		params.setOrdernos(ordernos );
		ServiceManager.workOrderService.updateIsReturn(params);
		return ServiceManager.workOrderService.reserve(order, loginAdmin);
	}

	/**
	 * 修改预约
	 * 
	 * @return
	 */
	@RequestMapping("updatereserve")
	public ReturnVo updatereserve(@RequestBody WorkOrder order) {
		return ServiceManager.workOrderService.updatereserve(order);
	}

	/**
	 * 转跟进
	 * 
	 * @return
	 */
	@RequestMapping("turnfollowup")
	public ReturnVo turnfollowup(@RequestBody WorkRecord params) {
		return ServiceManager.workOrderService.turnfollowup(params, loginAdmin);
	}

	/**
	 * 已到店
	 * 
	 * @return
	 */
	@RequestMapping("arrivals")
	public ReturnVo arrivals(@RequestBody WorkOrder order) {
		return ServiceManager.workOrderService.arrivals(order, loginAdmin);
	}

	/**
	 * 成交
	 * 
	 * @return
	 */
	@RequestMapping("transaction")
	public ReturnVo transaction(@RequestBody WorkOrder order) {
		return ServiceManager.workOrderService.transaction(order, loginAdmin);
	}

	/**
	 * 激活
	 * 
	 * @param order
	 * @return
	 */
	@RequestMapping("activation")
	public ReturnVo activation(@RequestBody WorkOrder order) {
		return ServiceManager.workOrderService.activation(order, loginAdmin);
	}
	
	/**
	 * 微信聊天
	 * @param params
	 * @return
	 * @author lhy 2019.05.14
	 */
	@RequestMapping("/wxChat")
	public ReturnVo wxChat(@RequestBody WorkRecordVo params) {
		return ServiceManager.workOrderService.wxChat(params, loginAdmin);
	}
	
	/**
	 * 删除工单
	 * @param params
	 * @return
	 * @author lhy 2019.05.15
	 */
	@RequestMapping("/remove")
	public ReturnVo remove(@RequestBody WorkOrder order) {
		return ServiceManager.workOrderService.deleteWorkOrder(order, loginAdmin);
	}
	
	
	/**
	 * 批量转移所属人员。
	 * 
	 * @return
	 * @author lhy 2019.05.16
	 */
	@RequestMapping("/batchTransfer")
	public ReturnVo batchTransfer(@RequestBody WorkOrderParams params) {
		return ServiceManager.workOrderService.batchTransfer(params, loginAdmin);
	}
	
	/**
	 * 修改用户标签
	 * 
	 * @return
	 * @author lhy 2019.08.07
	 */
	@RequestMapping("lable/edit")
	public ReturnVo editLable(@RequestBody User params) {
		User updateUser=new User();
		updateUser.setUserid(params.getUserid());
		updateUser.setLablenames(params.getLablenames());
		updateUser.setLableremarks(params.getLableremarks());
		
		ServiceManager.userService.updateUser(updateUser);
		
		return ReturnVo.success(null);
	}
	
	/**
	 * 工单收费登记。
	 * @param params
	 * @return
	 * @author lhy 2019.08.12 v1.4
	 */
	@RequestMapping("pay/save")
	public ReturnVo addOrderPay(@RequestBody OrderPayDto params) {
		params.setAdminid(loginAdmin.getAdminid());
		params.setAdminname(loginAdmin.getName());
		
		orderPayService.workorderPay(params);
		return ReturnVo.success(null);
	}
	
	/**
	 * 工单收费登记 列表。
	 * @param params
	 * @return
	 * @author lhy 2019.08.12 v1.4
	 */
	@RequestMapping("pay/list")
	public ReturnVo findOrderPayList(@RequestBody FindOrderPayPage params) {
		if(ObjectUtil.isEmpty(params.getOrderno())) {
			throw new LssException(ResponseCode.parameterError, "工单号为空");
		}
		ReturnVo returnVo = orderPayService.findOrderPayPage(params);
		
		return returnVo;
	}
	
	/**
	 * 批量共享工单
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/offer")
	public ReturnVo offer(@RequestBody WorkOrderParams params) {
		if(ObjectUtil.isEmpty(params.getAdminids())) {
			throw new LssException(ResponseCode.parameterError, "请选择要共享的电销用户!");
		}
		if(ObjectUtil.isEmpty(params.getOrdernos())) {
			throw new LssException(ResponseCode.parameterError, "工单号不能为空!");
		}
		return ServiceManager.workOrderService.offerOrder(params, loginAdmin);
	}
	
	
	/**
	 * 共享给我的
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/offerToMe")
	public ReturnVo offerToMe(@RequestBody WorkOrderParams params) {
		if(null==params.getToMe()) {
			params.setToMe(loginAdmin.getAdminid().toString());
		}
		return ServiceManager.workOrderService.offerToMe(params);
	}
	
	/**
	 * 我共享的
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/offerByMe")
	public ReturnVo offerByMe(@RequestBody WorkOrderParams params) {
		if(null==params.getFromMe()) {
			params.setFromMe(loginAdmin.getAdminid().toString());
		}
		return ServiceManager.workOrderService.offerFromMe(params);
	}
	
	/**
	 * 取消共享
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/cancleOffer")
	public ReturnVo cancleOffer(@RequestBody WorkOrderParams params) {
		if(null==params.getOrdernos()) {
			throw new LssException(ResponseCode.parameterError, "请选择要取消共享的工单!");
		}
		return ServiceManager.workOrderService.cancleOffer(params,loginAdmin);
	}
	
	/**
	 * 用户统计
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/userCount")
	public ReturnVo userCount(@RequestBody WorkOrderParams params) {
		return ServiceManager.workOrderService.userCount(params);
	}
	
	/**
	 * 用户意愿统计
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/levelCount")
	public ReturnVo levelCount(@RequestBody WorkOrderParams params) {
		return ServiceManager.workOrderService.levelCount(params);
	}
	
	
	/**
	 * 导出用户统计
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/exportUserCount")
	public void exportUserCount(WorkOrderExcelParams workOrderExcelParams) {
		if(StringUtils.isNotEmpty(workOrderExcelParams.getAllottimeStart())) {
			workOrderExcelParams.setAllottimeStart(workOrderExcelParams.getAllottimeStart() + " 00:00:00");
		}
		if(StringUtils.isNotEmpty(workOrderExcelParams.getAllottimeEnd())) {
			workOrderExcelParams.setAllottimeEnd(workOrderExcelParams.getAllottimeEnd() + " 23:59:59");
		}
		ReturnVo returnVo = ServiceManager.workOrderService.exportUserCount(workOrderExcelParams);
		
		List<UserCountExeclVo> list = (List<UserCountExeclVo>) returnVo.getObj();
		ExportExcel<UserCountExeclVo> ee = new ExportExcel<UserCountExeclVo>();
		String[] headers = {"一级来源","二级来源", "来源日期", "分配日期", "意愿", "数量", "占比"};
		String fileName = "客户表单有效性统计";
		ee.exportExcel(headers, list, fileName, response);
	}
	
	/**
	 * 导出用户统计明细
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/exportUserDetail")
	public void exportUserDetail(WorkOrderExcelParams workOrderExcelParams) {
		if(StringUtils.isNotEmpty(workOrderExcelParams.getAllottimeStart())) {
			workOrderExcelParams.setAllottimeStart(workOrderExcelParams.getAllottimeStart() + " 00:00:00");
		}
		if(StringUtils.isNotEmpty(workOrderExcelParams.getAllottimeEnd())) {
			workOrderExcelParams.setAllottimeEnd(workOrderExcelParams.getAllottimeEnd() + " 23:59:59");
		}
		ReturnVo returnVo = ServiceManager.workOrderService.exportUserDetail(workOrderExcelParams);
		
		List<UserDetailExeclVo> list = (List<UserDetailExeclVo>) returnVo.getObj();
		ExportExcel<UserDetailExeclVo> ee = new ExportExcel<UserDetailExeclVo>();
		String[] headers = {"姓名","手机号","一级来源","二级来源", "来源日期", "分配日期", "意愿", "所属人员"};
		String fileName = "客户表单有效性明细";
		ee.exportExcel(headers, list, fileName, response);
	}
	
	/**
	 * 客户来源统计
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/userSource")
	public ReturnVo userSource(@RequestBody WorkOrderParams params) {
		ReturnVo returnVo = ServiceManager.workOrderService.userSource(params);
		return returnVo;
	}
	
	/**
	 * 客户来源总计统计
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/userSourceTotal")
	public ReturnVo userSourceTotal(@RequestBody WorkOrderParams params) {
		ReturnVo returnVo = ServiceManager.workOrderService.userSourceTotal(params);
		return returnVo;
	}
	
	/**
	 * 导出用户统计明细
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/exportUserSource")
	public void exportUserSource(WorkOrderExcelParams workOrderExcelParams) {
		if(StringUtils.isNotEmpty(workOrderExcelParams.getSourcedateStart())) {
			workOrderExcelParams.setSourcedateStart(workOrderExcelParams.getSourcedateStart() + " 00:00:00");
		}
		if(StringUtils.isNotEmpty(workOrderExcelParams.getSourcedateEnd())) {
			workOrderExcelParams.setSourcedateEnd(workOrderExcelParams.getSourcedateEnd() + " 23:59:59");
		}
		ReturnVo returnVo = ServiceManager.workOrderService.exportUserSource(workOrderExcelParams);
		
		List<UserSource> list = (List<UserSource>) returnVo.getObj();
		ExportExcel<UserSource> ee = new ExportExcel<UserSource>();
		String[] headers = {"一级来源","二级来源", "客户总数", "接通数", "接通率", "预约数","预约率","接通预约率","到店数","到店率","接通到店率",
				"成交","成交率","接通成交率"};
		String fileName = "客户来源统计";
		ee.exportExcel(headers, list, fileName, response);
	}
	
	
	/**
	 * 到店客户统计
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/userArrival")
	public ReturnVo userArrival(@RequestBody UserArriavlParam params) {
		ReturnVo returnVo = ServiceManager.workOrderService.userArrival(params);
		return returnVo;
	}
	
	/**
	 * 导出到店用户明细
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/exportUserArrival")
	public void exportUserArrival(UserArriavlExcelParam userArriavlExcelParam) {
		
		ReturnVo returnVo = ServiceManager.workOrderService.exportUserArrival(userArriavlExcelParam);
		
		List<UserArriavl> list = (List<UserArriavl>) returnVo.getObj();
		ExportExcel<UserArriavl> ee = new ExportExcel<UserArriavl>();
		String[] headers = {"姓名","手机号", "性别", "年龄", "地区", "一级来源","二级来源","意向项目","成交项目","来源日期","到店日期",
				"成交日期"};
		String fileName = "到店客户统计";
		ee.exportExcel(headers, list, fileName, response);
	}
	
	/**
	 * 客户到店性别统计
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/userArrivalSex")
	public ReturnVo userArrivalSex(@RequestBody UserArriavlParam params) {
		ReturnVo returnVo = ServiceManager.workOrderService.userArrivalSex(params);
		return returnVo;
	}
	
	/**
	 * 客户到店意向统计
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/userArrivalProject")
	public ReturnVo userArrivalProject(@RequestBody UserArriavlParam params) {
		ReturnVo returnVo = ServiceManager.workOrderService.userArrivalProject(params);
		return returnVo;
	}
	
	/**
	 * 客户到店年龄统计
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/userArrivalAge")
	public ReturnVo userArrivalAge(@RequestBody UserArriavlParam params) {
		ReturnVo returnVo = ServiceManager.workOrderService.userArrivalAge(params);
		return returnVo;
	}
	
}
