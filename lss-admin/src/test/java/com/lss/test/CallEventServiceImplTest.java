package com.lss.test;

import java.util.ArrayList;
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
import com.lss.admin.service.ICallEventService;
import com.lss.core.dto.CallEventDto;
import com.lss.core.dto.FindCallEventPage;
import com.lss.core.exception.LssException;
import com.lss.core.util.HttpRequestHelper;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.phone.CallEventListVo;
import com.lss.core.vo.phone.CallEventVo;

 

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
public class CallEventServiceImplTest extends SpringTestCase{

	@Resource
	ICallEventService callEventService;



	/**
	 * 
	 *
	 * 方法说明：添加电话事件信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addCallEvent() throws LssException{
		CallEventDto callEventDto = new CallEventDto();
		//add数据录入
//		callEventDto.setId("Id");
		callEventDto.setRecordId("RecordId");
		callEventDto.setType("Type");
		callEventDto.setTime("Time");
		callEventDto.setTransferNo("TransferNo");
		callEventDto.setShowNo("ShowNo");
		callEventDto.setCallInfo("CallInfo");
		callEventDto.setEmpId("EmpId");
		callEventDto.setEmpNo("EmpNo");
		callEventDto.setEmpInfo("EmpInfo");
		callEventDto.setCusId("CusId");
		callEventDto.setCusNo("CusNo");
		callEventDto.setCusInfo("CusInfo");
		callEventDto.setCreateTime(new Date());
		
		callEventService.addCallEvent(callEventDto);
		
		System.out.println("新增成功：ID="+callEventDto.getId());
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改电话事件信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateCallEvent() throws LssException{
		CallEventDto callEventDto = new CallEventDto();
		//update数据录入
		callEventDto.setId(1);
		callEventDto.setRecordId("RecordId");
		callEventDto.setType("Type");
		callEventDto.setTime("Time");
		callEventDto.setTransferNo("TransferNo");
		callEventDto.setShowNo("ShowNo");
		callEventDto.setCallInfo("CallInfo");
		callEventDto.setEmpId("EmpId");
		callEventDto.setEmpNo("EmpNo");
		callEventDto.setEmpInfo("EmpInfo");
		callEventDto.setCusId("CusId");
		callEventDto.setCusNo("CusNo");
		callEventDto.setCusInfo("CusInfo");
		callEventDto.setCreateTime(new Date());

		callEventService.updateCallEvent(callEventDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找电话事件信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findCallEvent() throws LssException{
		CallEventDto findCallEvent = new CallEventDto();
		findCallEvent.setId(1);
		callEventService.findCallEvent(findCallEvent);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找电话事件信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findCallEventPage() throws LssException{
		FindCallEventPage findCallEventPage = new FindCallEventPage();
		ReturnVo page = callEventService.findCallEventPage(findCallEventPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找电话事件信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findCallEvents() throws LssException{
		FindCallEventPage findCallEventPage = new FindCallEventPage();
		List<CallEventDto> page = callEventService.findCallEvents(findCallEventPage);
		Assert.assertNotNull(page);
		
	}
	
	@Test
	public void tsCallEvent() {
		CallEventVo callEventDto = createCallEventVo(1);
		
		List<CallEventVo> records=new ArrayList<>();
		records.add(callEventDto);
		records.add(createCallEventVo(2));
		CallEventListVo listVo=new CallEventListVo();
		listVo.setToken("c93d174f9c434cb3982e74404f1d827b");
		listVo.setEvents(records);
		String reqJson=JSON.toJSONString(listVo);
		System.out.println("reqJson="+reqJson);
		
		String respStr= HttpRequestHelper.sendJsonPost("http://rw.kehuzhitongche.com/lss-admin/llyh/eventRecord", reqJson);
		System.out.println("respStr="+respStr);
	}
	
	private CallEventVo createCallEventVo(int i) {
		CallEventVo callEventDto = new CallEventVo();
		callEventDto.setRecordId("RecordId"+i);
		callEventDto.setType("DIALING_OUTBOUND");
		callEventDto.setTime("2019-05-09 11:59:33");
		callEventDto.setTransferNo("13011112222");
		callEventDto.setShowNo("15869696868");
		callEventDto.setCallInfo("CallInfo");
		callEventDto.setEmpId("admin");
		callEventDto.setEmpNo("18800001111");
		callEventDto.setEmpInfo("EmpInfo");
		callEventDto.setCusId("1091");
		callEventDto.setCusNo("13900001111");
		callEventDto.setCusInfo("CusInfo");
		
		return callEventDto;
	}
	
	
}
