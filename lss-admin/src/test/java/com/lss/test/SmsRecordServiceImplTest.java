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
import com.lss.admin.service.ISmsRecordService;
import com.lss.core.dto.FindSmsRecordPage;
import com.lss.core.dto.SmsRecordDto;
import com.lss.core.exception.LssException;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.phone.CallRecordVo;
import com.lss.core.vo.phone.LlyhSmsRecordListVo;
import com.lss.core.vo.phone.LlyhSmsRecordVo;


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
public class SmsRecordServiceImplTest extends SpringTestCase{

	@Resource
	ISmsRecordService smsRecordService;



	/**
	 * 
	 *
	 * 方法说明：添加短信记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addSmsRecord() throws LssException{
		SmsRecordDto smsRecordDto = new SmsRecordDto();
		//add数据录入
		smsRecordDto.setId(1);
		smsRecordDto.setRecordId("RecordId");
		smsRecordDto.setType("Type");
		smsRecordDto.setTransferNo("TransferNo");
		smsRecordDto.setShowNo("ShowNo");
		smsRecordDto.setLlResult("LlResult");
		smsRecordDto.setTransferTime("TransferTime");
		smsRecordDto.setContent("Content");
		smsRecordDto.setSmsInfo("SmsInfo");
		smsRecordDto.setEmpId("EmpId");
		smsRecordDto.setEmpNo("EmpNo");
		smsRecordDto.setEmpNoArea("EmpNoArea");
		smsRecordDto.setEmpInfo("EmpInfo");
		smsRecordDto.setCusId("CusId");
		smsRecordDto.setCusNo("CusNo");
		smsRecordDto.setCusNoArea("CusNoArea");
		smsRecordDto.setCusInfo("CusInfo");
		smsRecordDto.setProcessStatus("PROCESSED");
		smsRecordDto.setAdminId(1);
		smsRecordDto.setAdminName("AdminName");
		smsRecordDto.setAdminPhone("AdminPhone");
		smsRecordDto.setUserId(1);
		smsRecordDto.setUserName("UserName");
		smsRecordDto.setWorkrecordId(1);
		smsRecordDto.setOrderno("Orderno");
		smsRecordDto.setLssRecordUrl("LssRecordUrl");
		smsRecordDto.setCreateTime(new Date());
		
		smsRecordService.addSmsRecord(smsRecordDto);
		System.out.println("新增成功：ID="+smsRecordDto.getId());
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改短信记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateSmsRecord() throws LssException{
		SmsRecordDto smsRecordDto = new SmsRecordDto();
		//update数据录入
		smsRecordDto.setId(1);
		smsRecordDto.setRecordId("RecordId");
		smsRecordDto.setType("Type");
		smsRecordDto.setTransferNo("TransferNo");
		smsRecordDto.setShowNo("ShowNo");
		smsRecordDto.setLlResult("LlResult");
		smsRecordDto.setTransferTime("TransferTime");
		smsRecordDto.setContent("Content");
		smsRecordDto.setSmsInfo("SmsInfo");
		smsRecordDto.setEmpId("EmpId");
		smsRecordDto.setEmpNo("EmpNo");
		smsRecordDto.setEmpNoArea("EmpNoArea");
		smsRecordDto.setEmpInfo("EmpInfo");
		smsRecordDto.setCusId("CusId");
		smsRecordDto.setCusNo("CusNo");
		smsRecordDto.setCusNoArea("CusNoArea");
		smsRecordDto.setCusInfo("CusInfo");
		smsRecordDto.setProcessStatus("ProcessStatus");
		smsRecordDto.setAdminId(1);
		smsRecordDto.setAdminName("AdminName");
		smsRecordDto.setAdminPhone("AdminPhone");
		smsRecordDto.setUserId(1);
		smsRecordDto.setUserName("UserName");
		smsRecordDto.setWorkrecordId(2);
		smsRecordDto.setOrderno("Orderno");
		smsRecordDto.setLssRecordUrl("LssRecordUrl");
		smsRecordDto.setCreateTime(new Date());

		smsRecordService.updateSmsRecord(smsRecordDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找短信记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findSmsRecord() throws LssException{
		SmsRecordDto findSmsRecord = new SmsRecordDto();
		findSmsRecord.setId(1);
		smsRecordService.findSmsRecord(findSmsRecord);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找短信记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findSmsRecordPage() throws LssException{
		FindSmsRecordPage findSmsRecordPage = new FindSmsRecordPage();
		ReturnVo page = smsRecordService.findSmsRecordPage(findSmsRecordPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找短信记录信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findSmsRecords() throws LssException{
		FindSmsRecordPage findSmsRecordPage = new FindSmsRecordPage();
		List<SmsRecordDto> page = smsRecordService.findSmsRecords(findSmsRecordPage);
		Assert.assertNotNull(page);
		
	}
	
	@Test
	public void tsSmsRecords() {
		LlyhSmsRecordVo smsRecordDto = new LlyhSmsRecordVo();
		smsRecordDto.setRecordId("RecordId2");
		smsRecordDto.setType("OUTBOUND");
		smsRecordDto.setTransferNo("TransferNo");
		smsRecordDto.setShowNo("ShowNo");
		smsRecordDto.setResult("SENT");
		smsRecordDto.setTransferTime("2019-05-09");
		smsRecordDto.setContent("你回哈哈哈哈哈哈哈或或或");
		smsRecordDto.setSmsInfo("SmsInfo");
		smsRecordDto.setEmpId("admin");
		smsRecordDto.setEmpNo("18800001111");
		smsRecordDto.setEmpNoArea("EmpNoArea");
		smsRecordDto.setEmpInfo("EmpInfo");
		smsRecordDto.setCusId("1091");
		smsRecordDto.setCusNo("13900001111");
		smsRecordDto.setCusNoArea("CusNoArea");
		smsRecordDto.setCusInfo("CusInfo");
		
		List<LlyhSmsRecordVo> records=new ArrayList<>();
		records.add(smsRecordDto);
		LlyhSmsRecordListVo listVo=new LlyhSmsRecordListVo();
		listVo.setToken("c93d174f9c434cb3982e74404f1d827b");
		listVo.setRecords(records);
		
		System.out.println(JSON.toJSONString(listVo));
		
	}
	
}
