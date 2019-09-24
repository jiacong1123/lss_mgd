package com.lss.admin.service;

import java.util.List;

import com.lss.core.dto.FindStOrderPage;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lss.core.dto.StOrderDto;
import com.lss.core.exception.LssException;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;

/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 *         CreateDate: 2019.02.19
 */
public interface IStOrderService {

	/**
	 * 
	 *
	 * 方法说明：添加员工工单月成交统计信息
	 *
	 * @param stOrderDto
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addStOrder(StOrderDto stOrderDto) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：查找员工工单月成交统计信息
	 *
	 * @param findStOrder
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public StOrderDto findStOrder(StOrderDto stOrderDto) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询员工工单月成交统计信息
	 *
	 * @param findStOrderPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<StOrderDto> findStOrders(FindStOrderPage findStOrderPage) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：修改员工工单月成交统计信息
	 *
	 * @param updateStOrder
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateStOrder(StOrderDto stOrderDto) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：分页查询员工工单月成交统计信息
	 *
	 * @param findStOrderPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ReturnVo findStOrderPage(FindStOrderPage findStOrderPage) throws LssException;

	/**
	 *  统计员工工单月成交信息.
	 * 
	 * @param batchNum
	 * @return
	 * @author lhy 2019年8月16日 v1.4
	 */
	public void batchAddMonthStOrder(String batchNum) throws LssException;
	
	/**
	 * 统计当月员工工单月成交信息
	 * 
	 * @return
	 * @throws LssException
	 */
	public ReturnVo findStOrderCurrMonthPage(FindStOrderPage findStOrderPage, LoginAdmin admin) throws LssException;
	
}
