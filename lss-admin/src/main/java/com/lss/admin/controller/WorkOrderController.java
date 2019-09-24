package com.lss.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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
import com.lss.core.pojo.WorkOrder;
import com.lss.core.pojo.WorkRecord;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.WorkRecordVo;
import com.lss.core.vo.admin.params.UserOrderParams;
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
	 * 最近三天待回访列表
	 * 
	 * @return
	 */
	@RequestMapping("returnCount")
	public ReturnVo returnCount(@RequestBody WorkOrderParams params) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat newSFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//查询最近三天未回访的工单数量
			params.setIsReturn("1");
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
		//更新状态为已回访
		WorkOrderParams params = new WorkOrderParams();
		params .setIsReturn("2");
		List<String> ordernos = new ArrayList<String>();
		ordernos.add(order.getOrderno());
		params.setOrdernos(ordernos );
		ServiceManager.workOrderService.updateIsReturn(params);
		return ServiceManager.workOrderService.close(order, loginAdmin);
	}

	/**
	 * 回访跟进
	 * 
	 * @param order
	 * @return
	 */
	@RequestMapping("followup")
	public ReturnVo followup(@RequestBody WorkOrder order) {
		//更新状态为已回访
		WorkOrderParams params = new WorkOrderParams();
		params .setIsReturn("2");
		List<String> ordernos = new ArrayList<String>();
		ordernos.add(order.getOrderno());
		params.setOrdernos(ordernos );
		ServiceManager.workOrderService.updateIsReturn(params);
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
	
	
}
