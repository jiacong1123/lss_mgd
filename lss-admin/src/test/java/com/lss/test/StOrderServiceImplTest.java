package com.lss.test;

import java.math.BigDecimal;
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

import com.lss.admin.service.IStOrderService;
import com.lss.core.dto.FindStOrderPage;
import com.lss.core.dto.StOrderDto;
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
public class StOrderServiceImplTest extends SpringTestCase{

	@Resource
	IStOrderService stOrderService;



	/**
	 * 
	 *
	 * 方法说明：添加员工工单月成交统计信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addStOrder() throws LssException{
		StOrderDto stOrderDto = new StOrderDto();
		//add数据录入
		stOrderDto.setId(1);
		stOrderDto.setAdminid(1);
		stOrderDto.setAdminname("Adminname");
		stOrderDto.setStDate(new Date());
		stOrderDto.setAllotUserCount(1);
		stOrderDto.setReserveUserCount(1);
		stOrderDto.setHisReserveCount(2);
		stOrderDto.setAllReserveCount(3);
		stOrderDto.setMonReserveRate(BigDecimal.ONE);
		stOrderDto.setAllReserveRate(BigDecimal.ONE);
		stOrderDto.setBigDealCount(1);
		stOrderDto.setBigDealRate(BigDecimal.ONE);
		stOrderDto.setAllDealAmt(1000000L);
		stOrderDto.setCreateDate(new Date());
		stOrderDto.setUpdateDate(new Date());
		stOrderDto.setRemark("Remark");
		
		stOrderService.addStOrder(stOrderDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改员工工单月成交统计信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateStOrder() throws LssException{
		StOrderDto stOrderDto = new StOrderDto();
		//update数据录入
		stOrderDto.setId(1);
		stOrderDto.setAdminid(1);
		stOrderDto.setAdminname("Adminname");
		stOrderDto.setStDate(new Date());
		stOrderDto.setAllotUserCount(1);
		stOrderDto.setReserveUserCount(1);
		stOrderDto.setHisReserveCount(2);
		stOrderDto.setAllReserveCount(3);
		stOrderDto.setMonReserveRate(BigDecimal.ONE);
		stOrderDto.setAllReserveRate(BigDecimal.ONE);
		stOrderDto.setBigDealCount(1);
		stOrderDto.setBigDealRate(BigDecimal.ONE);
		stOrderDto.setAllDealAmt(1000000L);
		stOrderDto.setCreateDate(new Date());
		stOrderDto.setUpdateDate(new Date());
		stOrderDto.setRemark("Remark");

		stOrderService.updateStOrder(stOrderDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找员工工单月成交统计信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findStOrder() throws LssException{
		StOrderDto findStOrder = new StOrderDto();
		findStOrder.setId(1);
		stOrderService.findStOrder(findStOrder);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找员工工单月成交统计信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findStOrderPage() throws LssException{
		FindStOrderPage findStOrderPage = new FindStOrderPage();
		ReturnVo page = stOrderService.findStOrderPage(findStOrderPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找员工工单月成交统计信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findStOrders() throws LssException{
		FindStOrderPage findStOrderPage = new FindStOrderPage();
		List<StOrderDto> page = stOrderService.findStOrders(findStOrderPage);
		Assert.assertNotNull(page);
		
	}
	
}
