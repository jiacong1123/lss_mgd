package com.lss.core.dao;

import java.util.List;

import com.lss.core.dto.FindStOrderPage;
import com.lss.core.dto.StOrderDto;
import com.lss.core.pojo.StOrder;

public interface IStOrderDao {
	int deleteByPrimaryKey(Integer id);

	int insert(StOrder record);

	int insertSelective(StOrder record);

	StOrder selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(StOrder record);

	int updateByPrimaryKey(StOrder record);

	List<StOrderDto> findStOrders(FindStOrderPage findStOrderPage);

	List<StOrderDto> findStOrderPage(FindStOrderPage findStOrderPage);

	int findStOrderPageCount(FindStOrderPage findStOrderPage);
	
	/** 方案二 1.月客户数统计，只统计了有分配的电销员 */
	int batchAddMonthAllotUserCount(StOrderDto record);

	/** 方案一 0 当月电销人员 先给所有电销员 初始一条成交统计数据 */
	int batchAddStOrder(StOrderDto record); 
	/** 方案一 1.更新月客户数统计 */
	int batchAddMonthAllotUserCount2(StOrderDto record);
	
	// 2.新客到店数
	int batchAddMonthReserveUserCount(StOrderDto record);

	// 3.总到店数
	int batchAddMonthAllReserveCount(StOrderDto record);

	// 4.历史到店数
	int batchAddMonthHisReserveCount(StOrderDto record);

	// 5.新客到店率=新客到店数÷月客户数
	int batchAddMonthMonReserveRate(StOrderDto record);

	// 6.综合到店率=总到店数÷月客户数（本月分配的客户）
	int batchAddMonthAllReserveRate(StOrderDto record);

	// 7.大项（正畸1、种植2、修复140）+实付金额大于1000+状态（已成交）
	int batchAddMonthBigDealCount(StOrderDto record);

	// 8.（大项）成交率 =大项目成交数÷总到店数
	int batchAddMonthBigDealRate(StOrderDto record);

	// 9.业绩完成=状态（已完成）+全部项目实收金额（大项目+其他）
	int batchAddMonthAllDealAmt(StOrderDto record);

	
	//1.月客户数统计 查询
	List<StOrderDto> findMonthAllotUserCount(FindStOrderPage findStOrderPage);
	//2.新客到店数 查询
	List<StOrderDto> findMonthReserveUserCount(FindStOrderPage findStOrderPage);
	//3.总到店数
	List<StOrderDto> findMonthAllReserveCount(FindStOrderPage findStOrderPage);
	//7.大项（正畸1、种植2、修复140）+实付金额大于1000+状态（已成交）
	List<StOrderDto> findMonthBigDealCount(FindStOrderPage findStOrderPage);
	//9.业绩完成=状态（已完成）+全部项目实收金额（大项目+其他）
	List<StOrderDto> findMonthAllDealAmt(FindStOrderPage findStOrderPage);
	
}