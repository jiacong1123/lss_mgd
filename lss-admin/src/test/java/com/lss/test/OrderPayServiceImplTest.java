package com.lss.test;

import java.util.Date;
import java.util.List;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.lss.admin.service.IOrderPayService;
import com.lss.core.dto.FindOrderPayPage;
import com.lss.core.dto.OrderPayDto;
import com.lss.core.exception.LssException;
import com.lss.core.vo.ReturnVo;

 
/**
 * 类说明：测试类
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
public class OrderPayServiceImplTest extends SpringTestCase{

	@Resource
	IOrderPayService orderPayService;



	/**
	 * 
	 *
	 * 方法说明：添加工单收费记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addOrderPay() throws LssException{
		OrderPayDto orderPayDto = new OrderPayDto();
		//add数据录入
//		orderPayDto.setId(1);
//		orderPayDto.setAdminid(1);
		orderPayDto.setOrderno("Orderno");
		orderPayDto.setReceivablesamt(0L);
		orderPayDto.setDebtamt(0L);
		orderPayDto.setAmount(0L);
		orderPayDto.setPayTime(new Date());
//		orderPayDto.setCreateDate(new Date());
//		orderPayDto.setUpdateDate(new Date());
		orderPayDto.setRemark("第一次登记收费");
//		orderPayDto.setFirstPay("Y");
		System.out.println(JSON.toJSON(orderPayDto));
//		orderPayService.addOrderPay(orderPayDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改工单收费记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateOrderPay() throws LssException{
		OrderPayDto orderPayDto = new OrderPayDto();
		//update数据录入
		orderPayDto.setId(1);
		orderPayDto.setAdminid(1);
		orderPayDto.setOrderno("Orderno");
		orderPayDto.setReceivablesamt(0L);
		orderPayDto.setDebtamt(0L);
		orderPayDto.setAmount(0L);
		orderPayDto.setPayTime(new Date());
		orderPayDto.setCreateDate(new Date());
		orderPayDto.setUpdateDate(new Date());
		orderPayDto.setRemark("Remark");
		orderPayDto.setFirstPay("FirstPay");

		orderPayService.updateOrderPay(orderPayDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找工单收费记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findOrderPay() throws LssException{
		OrderPayDto findOrderPay = new OrderPayDto();
		findOrderPay.setId(1);
		orderPayService.findOrderPay(findOrderPay);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找工单收费记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findOrderPayPage() throws LssException{
		FindOrderPayPage findOrderPayPage = new FindOrderPayPage();
		ReturnVo page = orderPayService.findOrderPayPage(findOrderPayPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找工单收费记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findOrderPays() throws LssException{
		FindOrderPayPage findOrderPayPage = new FindOrderPayPage();
		List<OrderPayDto> page = orderPayService.findOrderPays(findOrderPayPage);
		Assert.assertNotNull(page);
		
	}
	
}
