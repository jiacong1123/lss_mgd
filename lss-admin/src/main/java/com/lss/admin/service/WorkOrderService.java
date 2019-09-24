package com.lss.admin.service;

import com.lss.core.pojo.WorkOrder;
import com.lss.core.pojo.WorkRecord;
import com.lss.core.pojo.WorkTag;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.WorkRecordVo;
import com.lss.core.vo.admin.params.UserOrderParams;
import com.lss.core.vo.admin.params.WorkOrderParams;

public interface WorkOrderService {
	/**
	 *  待关闭工单总量
	 * 
	 * @param params
	 * @param admin
	 * @return
	 */
	ReturnVo closeCount(WorkOrderParams params, LoginAdmin admin);
	
	/**
	 * 工单列表
	 * 
	 * @param params
	 * @param admin
	 * @return
	 */
	ReturnVo list(WorkOrderParams params, LoginAdmin admin);

	/**
	 * 标签
	 * 
	 * @param tag
	 * @return
	 */
	ReturnVo tags(WorkTag tag);

	/**
	 * 工单详情
	 * 
	 * @param orderno
	 * @return
	 */
	ReturnVo details(String orderno);

	/**
	 * 根据电话查询用户信息
	 * 
	 * @param phone
	 * @return
	 */
	ReturnVo userinfo(String phone,String noWxAlias);

	/**
	 * 新增/编辑工单
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo save(UserOrderParams params, LoginAdmin admin);

	/**
	 * 批量分配
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo batchassign(WorkOrderParams params, LoginAdmin admin);

	/**
	 * 关闭工单
	 * 
	 * @param order
	 * @return
	 */
	ReturnVo close(WorkOrder order, LoginAdmin admin);

	/**
	 * 回访跟进
	 * 
	 * @param order
	 * @return
	 */
	ReturnVo followup(WorkOrder order, LoginAdmin admin);

	/**
	 * 预约
	 * 
	 * @param order
	 * @param admin
	 * @return
	 */
	ReturnVo reserve(WorkOrder order, LoginAdmin admin);

	/**
	 * 添加工单记录
	 */
	Integer insertWorkRecord(String orderno, Integer adminid, String content,
			Integer talktime);

	/**
	 * 修改预约时间
	 * 
	 * @param order
	 * @return
	 */
	ReturnVo updatereserve(WorkOrder order);

	/**
	 * 转跟进
	 * 
	 * @param params
	 * @param admin
	 * @return
	 */
	ReturnVo turnfollowup(WorkRecord params, LoginAdmin admin);

	/**
	 * 已到店
	 * 
	 * @param order
	 * @param admin
	 * @return
	 */
	ReturnVo arrivals(WorkOrder order, LoginAdmin admin);

	/**
	 * 成交
	 * 
	 * @param order
	 * @param admin
	 * @return
	 */
	ReturnVo transaction(WorkOrder order, LoginAdmin admin);

	/**
	 * 激活
	 * 
	 * @param order
	 * @return
	 */
	ReturnVo activation(WorkOrder order, LoginAdmin admin);
	
	/**
	 * 微信聊天。
	 * @param params
	 * @param admin
	 * @return
	 * @author lhy 2019.05.14 
	 */
	ReturnVo wxChat(WorkRecordVo params, LoginAdmin admin);
	
	/**
	 * 删除工单。
	 * @param params
	 * @param admin
	 * @return
	 * @author lhy 2019.05.15 
	 */
	ReturnVo deleteWorkOrder(WorkOrder order, LoginAdmin admin);
	
	/**
	 * 批量转移
	 * 
	 * @param params
	 * @return
	 */
	ReturnVo batchTransfer(WorkOrderParams params, LoginAdmin admin);

	/**
	 *  系统自动关闭7天未跟进工单。
	 * 
	 * @param batchNum
	 * @return
	 * @author lhy 2019年8月12日 v1.4
	 */
	void autoCloseWorkOrder(String batchNum);

	/**
	 * 查询近三天待回访列表
	 * @param params
	 * @param loginAdmin
	 * @return
	 */
	ReturnVo returnList(WorkOrderParams params, LoginAdmin loginAdmin);

	/**
	 *  查询近三天待回访数量
	 * @param params
	 * @param loginAdmin
	 * @return
	 */
	ReturnVo returnCount(WorkOrderParams params, LoginAdmin loginAdmin);

	/**
	 * 更新待回访状态
	 * @param params
	 * @return
	 */
	ReturnVo updateIsReturn(WorkOrderParams params);

	/**
	 * 一小时内预约数量
	 * @param params
	 * @param loginAdmin
	 * @return
	 */
	ReturnVo reserveCount(WorkOrderParams params, LoginAdmin loginAdmin);
}
