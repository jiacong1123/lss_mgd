package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.BagOrder;
import com.lss.core.vo.hyl.BagOrderVo;
import com.lss.core.vo.hyl.OrderParams;

public interface BagOrderMapper extends AbstractMapper<BagOrder, String> {

	/**
	 * 获取医生包订单数量
	 * 
	 * @return
	 */
	int getBagOrderCount(OrderParams params);

	/**
	 * 获取医生包订单列表
	 * 
	 * @param params
	 * @return
	 */
	List<BagOrder> getBagOrderList(OrderParams params);

	/**
	 * 支付回调修改状态
	 * 
	 * @param params
	 * @return
	 */
	int upPayStatus(BagOrder params);

	/**
	 * 查询未支付线下转账订单是否存在
	 * 
	 * @param order
	 * @return
	 */
	int queryOrder(BagOrder order);

	/**
	 * 后台查询订单数量
	 * 
	 * @param params
	 * @return
	 */
	int adminBagOrderCount(OrderParams params);

	/**
	 * 后台查询订单列表
	 * 
	 * @param params
	 * @return
	 */
	List<BagOrderVo> adminBagOrderList(OrderParams params);

	/**
	 * 线下转账确认支付
	 * 
	 * @param params
	 * @return
	 */
	int confirmPayment(BagOrder params);
}