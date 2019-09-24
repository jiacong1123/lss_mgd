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

import com.lss.admin.service.IStCallService;
import com.lss.core.dto.FindStCallPage;
import com.lss.core.dto.StCallDto;
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
 *         CreateDate: 2019.02.19
 */
public class StCallServiceImplTest extends SpringTestCase {

	@Resource
	IStCallService stCallService;

	/**
	 * 
	 *
	 * 方法说明：添加员工电联统计信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addStCall() throws LssException {
		StCallDto stCallDto = new StCallDto();
		// add数据录入
		stCallDto.setId(1);
		stCallDto.setAdminid(1);
		stCallDto.setAdminname("Adminname");
		stCallDto.setStDate(new Date());
		stCallDto.setCallCount(1);
		stCallDto.setDuration(1);
		stCallDto.setAvgDuration(1);
		stCallDto.setCreateDate(new Date());
		stCallDto.setUpdateDate(new Date());
		stCallDto.setRemark("Remark");

		stCallService.addStCall(stCallDto);

	}

	/**
	 * 
	 *
	 * 方法说明：修改员工电联统计信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateStCall() throws LssException {
		StCallDto stCallDto = new StCallDto();
		// update数据录入
		stCallDto.setId(1);
		stCallDto.setAdminid(1);
		stCallDto.setAdminname("Adminname");
		stCallDto.setStDate(new Date());
		stCallDto.setCallCount(1);
		stCallDto.setDuration(1);
		stCallDto.setAvgDuration(1);
		stCallDto.setCreateDate(new Date());
		stCallDto.setUpdateDate(new Date());
		stCallDto.setRemark("Remark");

		stCallService.updateStCall(stCallDto);

	}

	/**
	 * 
	 *
	 * 方法说明：查找员工电联统计信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findStCall() throws LssException {
		StCallDto findStCall = new StCallDto();
		findStCall.setId(1);
		stCallService.findStCall(findStCall);
	}

	/**
	 * 
	 *
	 * 方法说明：查找员工电联统计信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findStCallPage() throws LssException {
		FindStCallPage findStCallPage = new FindStCallPage();
		ReturnVo page = stCallService.findStCallPage(findStCallPage);
		Assert.assertNotNull(page);

	}

	/**
	 * 
	 *
	 * 方法说明：查找员工电联统计信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findStCalls() throws LssException {
		FindStCallPage findStCallPage = new FindStCallPage();
		List<StCallDto> page = stCallService.findStCalls(findStCallPage);
		Assert.assertNotNull(page);

	}

}
