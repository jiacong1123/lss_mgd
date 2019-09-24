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
import com.lss.admin.service.ICallRecordService;
import com.lss.core.dto.CallRecordDto;
import com.lss.core.dto.FindCallRecordPage;
import com.lss.core.exception.LssException;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.phone.CallRecordListVo;
import com.lss.core.vo.phone.CallRecordVo;

 

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
public class CallRecordServiceImplTest extends SpringTestCase{

	@Resource
	ICallRecordService callRecordService;



	/**
	 * 
	 *
	 * 方法说明：添加电话记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addCallRecord() throws LssException{
		CallRecordDto callRecordDto = new CallRecordDto();
		//add数据录入
		callRecordDto.setId(1);
		callRecordDto.setRecordId("RecordId");
		callRecordDto.setType("Type");
		callRecordDto.setTransferNo("TransferNo");
		callRecordDto.setShowNo("ShowNo");
		callRecordDto.setLlResult("LlResult");
		callRecordDto.setStartTime("StartTime");
		callRecordDto.setEndTime("EndTime");
		callRecordDto.setDuration(1);
		callRecordDto.setRecordingUrl("RecordingUrl");
		callRecordDto.setCallInfo("CallInfo");
		callRecordDto.setEmpId("EmpId");
		callRecordDto.setEmpNo("EmpNo");
		callRecordDto.setEmpNoArea("EmpNoArea");
		callRecordDto.setEmpInfo("EmpInfo");
		callRecordDto.setCusId("CusId");
		callRecordDto.setCusNo("CusNo");
		callRecordDto.setCusNoArea("CusNoArea");
		callRecordDto.setCusInfo("CusInfo");
		callRecordDto.setProcessStatus("init");
		callRecordDto.setAdminId(1);
		callRecordDto.setAdminName("AdminName");
		callRecordDto.setAdminPhone("AdminPhone");
		callRecordDto.setUserId(1);
		callRecordDto.setUserName("UserName");
		callRecordDto.setWorkrecordId(1);
		callRecordDto.setOrderno("Orderno");
		callRecordDto.setLssRecordUrl("LssRecordUrl");
		callRecordDto.setCreateTime(new Date());
		
		callRecordService.addCallRecord(callRecordDto);
		System.out.println("新增成功：ID="+callRecordDto.getId());
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改电话记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateCallRecord() throws LssException{
		CallRecordDto callRecordDto = new CallRecordDto();
		//update数据录入
		callRecordDto.setId(1);
		callRecordDto.setRecordId("RecordId");
		callRecordDto.setType("Type");
		callRecordDto.setTransferNo("TransferNo");
		callRecordDto.setShowNo("ShowNo");
		callRecordDto.setLlResult("LlResult");
		callRecordDto.setStartTime("StartTime");
		callRecordDto.setEndTime("EndTime");
		callRecordDto.setDuration(1);
		callRecordDto.setRecordingUrl("RecordingUrl");
		callRecordDto.setCallInfo("CallInfo");
		callRecordDto.setEmpId("EmpId");
		callRecordDto.setEmpNo("EmpNo");
		callRecordDto.setEmpNoArea("EmpNoArea");
		callRecordDto.setEmpInfo("EmpInfo");
		callRecordDto.setCusId("CusId");
		callRecordDto.setCusNo("CusNo");
		callRecordDto.setCusNoArea("CusNoArea");
		callRecordDto.setCusInfo("CusInfo");
		callRecordDto.setProcessStatus("ProcessStatus");
		callRecordDto.setAdminId(1);
		callRecordDto.setAdminName("AdminName");
		callRecordDto.setAdminPhone("AdminPhone");
		callRecordDto.setUserId(1);
		callRecordDto.setUserName("UserName");
		callRecordDto.setWorkrecordId(1);
		callRecordDto.setOrderno("Orderno");
		callRecordDto.setLssRecordUrl("LssRecordUrl");
		callRecordDto.setCreateTime(new Date());

		callRecordService.updateCallRecord(callRecordDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找电话记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findCallRecord() throws LssException{
		CallRecordDto findCallRecord = new CallRecordDto();
		findCallRecord.setId(1);
		callRecordService.findCallRecord(findCallRecord);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找电话记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findCallRecordPage() throws LssException{
		FindCallRecordPage findCallRecordPage = new FindCallRecordPage();
		ReturnVo page = callRecordService.findCallRecordPage(findCallRecordPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找电话记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findCallRecords() throws LssException{
		FindCallRecordPage findCallRecordPage = new FindCallRecordPage();
		List<CallRecordDto> page = callRecordService.findCallRecords(findCallRecordPage);
		Assert.assertNotNull(page);
		
	}
	
	@Test
	public void tsCallRecords() {
		CallRecordVo callRecordDto = new CallRecordVo();
		callRecordDto.setRecordId("RecordId");
		callRecordDto.setType("Type");
		callRecordDto.setTransferNo("TransferNo");
		callRecordDto.setShowNo("ShowNo");
		callRecordDto.setResult("LlResult");
		callRecordDto.setStartTime("StartTime");
		callRecordDto.setEndTime("EndTime");
		callRecordDto.setDuration(1);
		callRecordDto.setRecordingUrl("RecordingUrl");
		callRecordDto.setCallInfo("CallInfo");
		callRecordDto.setEmpId("EmpId");
		callRecordDto.setEmpNo("EmpNo");
		callRecordDto.setEmpNoArea("EmpNoArea");
		callRecordDto.setEmpInfo("EmpInfo");
		callRecordDto.setCusId("CusId");
		callRecordDto.setCusNo("CusNo");
		callRecordDto.setCusNoArea("CusNoArea");
		callRecordDto.setCusInfo("CusInfo");
		
		List<CallRecordVo> records=new ArrayList<>();
		records.add(callRecordDto);
		CallRecordListVo listVo=new CallRecordListVo();
		listVo.setToken("c93d174f9c434cb3982e74404f1d827b");
		listVo.setRecords(records);
		
		System.out.println(JSON.toJSONString(listVo));
		
	}
	
}
