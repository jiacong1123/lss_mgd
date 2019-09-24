package com.lss.admin.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lss.core.dto.OrderPayDto;
import com.lss.core.exception.LssException;
import com.lss.core.vo.ReturnVo;
import com.lss.core.dto.FindOrderPayPage;



import java.util.List;
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
 * CreateDate: 2019.02.19
 */
public interface IOrderPayService {
	
	/**
	 * 
	 *
	 * 方法说明：添加工单收费记录信息
	 *
	 * @param orderPayDto
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addOrderPay(OrderPayDto orderPayDto) throws LssException;
	
	/**
	 * 
	 *
	 * 方法说明：查找工单收费记录信息
	 *
	 * @param findOrderPay
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public OrderPayDto findOrderPay(OrderPayDto orderPayDto) throws LssException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询工单收费记录信息
	 *
	 * @param findOrderPayPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<OrderPayDto>  findOrderPays(FindOrderPayPage findOrderPayPage)throws LssException;

	/**
	 * 
	 *
	 * 方法说明：修改工单收费记录信息
	 *
	 * @param updateOrderPay
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateOrderPay(OrderPayDto orderPayDto)throws LssException;

	/**
	 * 
	 *
	 * 方法说明：分页查询工单收费记录信息
	 *
	 * @param findOrderPayPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ReturnVo findOrderPayPage(FindOrderPayPage findOrderPayPage) throws LssException;
	
	/**
	 * 
	 *
	 * 方法说明：工单登记收费
	 *
	 * @param orderPayDto
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.08.12
	 *
	 */
	public void workorderPay(OrderPayDto orderPayDto) throws LssException;
	
}
