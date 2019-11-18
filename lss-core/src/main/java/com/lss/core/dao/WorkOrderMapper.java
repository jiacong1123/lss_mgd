package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.WorkOrder;
import com.lss.core.pojo.WorkRecord;
import com.lss.core.vo.admin.WorkOrderDetails;
import com.lss.core.vo.admin.WorkOrderVo;
import com.lss.core.vo.admin.params.ClueParams;
import com.lss.core.vo.admin.params.WorkOrderParams;
import com.lss.core.vo.hx.ClueListVo;

public interface WorkOrderMapper extends AbstractMapper<WorkOrder, String> {
	/**
	 * 工单数量
	 * 
	 * @param params
	 * @return
	 */
	Integer workOrderCount(WorkOrderParams params);

	/**
	 * 工单列表
	 * 
	 * @param params
	 * @return
	 */
	List<WorkOrderVo> workOrderList(WorkOrderParams params);

	/**
	 * 工单详情
	 * 
	 * @param orderno
	 * @return
	 */
	WorkOrderDetails queryDetails(String orderno);

	/**
	 * 根据用户id查询未完成的工单
	 * 
	 * @param userid
	 * @return
	 */
	WorkOrder queryWorkOrder(Integer userid);

	/**
	 * 批量分配工单
	 * 
	 * @param params
	 * @return
	 */
	int batchassignWorkOrder(WorkOrderParams params);

	/**
	 * 关闭工单
	 * 
	 * @param order
	 * @return
	 */
	int closeWorkOrder(WorkOrder order);

	/**
	 * 跟进工单
	 * 
	 * @param order
	 * @return
	 */
	int followupWorkOrder(WorkOrder order);

	/**
	 * 预约工单
	 * 
	 * @param order
	 * @return
	 */
	int reserveWorkOrder(WorkOrder order);

	/**
	 * 修改预约
	 * 
	 * @param order
	 * @return
	 */
	int updateReserve(WorkOrder order);

	/**
	 * 转跟进
	 * 
	 * @param params
	 * @return
	 */
	int turnFollowUp(WorkRecord params);

	/**
	 * 已到店
	 * 
	 * @param order
	 * @return
	 */
	int arrivals(WorkOrder order);

	/**
	 * 成交
	 * 
	 * @param order
	 * @return
	 */
	int transaction(WorkOrder order);

	/**
	 * 激活工单
	 * 
	 * @param order
	 * @return
	 */
	int activation(WorkOrder order);

	/**
	 * 获取线索数量
	 * 
	 * @param params
	 * @return
	 */
	int getClueCount(ClueParams params);

	/**
	 * 获取线索列表
	 * 
	 * @param params
	 * @return
	 */
	List<ClueListVo> getClueList(ClueParams params);
	
	/**
	 * 线索转跟进
	 * 
	 * @param params
	 * @return
	 */
	int clueTurnFollowUp(String orderno);
	
	/**
	 * 线索取消到店
	 * @param orderno
	 * @return
	 */
	int clueCancelShop(String orderno);
	
	/**
	 * 删除已关闭工单
	 * 
	 * @param params
	 * @return
	 * @author lhy 2019.05.16
	 */
	int deleteOrder(WorkOrder params);
	
	/**
	 * 批量转移所属人员。
	 * 
	 * @param params
	 * @return
	 */
	int batchTransfer(WorkOrderParams params);
	
	
	/**
	 * 系统自动关闭7天未跟进工单。
	 * 
	 * @param params
	 * @return
	 * @author lhy 2019年8月12日 v1.4
	 */
	int autoCloseWorkOrder(WorkOrderParams params);
	
	/**
	 * 删除用戶的工单
	 * 
	 * @param params
	 * @return
	 * @author lhy 2019年8月12日
	 */
	int deleteOrderByUser(WorkOrder params);

	/**
	 * 通过手机号码查询工单号
	 * @param cusNo
	 * @return
	 */
	String findOrderNo(String phone);

	/**
	 * 更新待回访状态
	 * @param params
	 */
	void updateIsReturn(WorkOrderParams params);

	/**
	 * 通过userId查询最近的一条工单号
	 * @param userId
	 * @return
	 */
	String findOrderNoByUserId(Integer userId);

	/**
	 * 查询近一小时内的预约数量
	 * @param params
	 * @return
	 */
	Integer reserveCount(WorkOrderParams params);

	/**
	 * 更新提醒状态
	 * @param params
	 */
	void updateIsRemindStatus(WorkOrderParams params);

	/**
	 * @param orderno
	 * @return
	 */
	WorkOrder findWordOrderByOrderNo(String orderno);

	List<Integer> findAdminIds(WorkOrderParams params);

	void offerWorkOrder(WorkOrderParams params);

	int offerToMeCount(WorkOrderParams params);

	List<WorkOrderVo> offerToMe(WorkOrderParams params);

	int offerFromMeCount(WorkOrderParams params);

	List<WorkOrder> offerFromMe(WorkOrderParams params);

	void cancleOffer(WorkOrderParams params);

}