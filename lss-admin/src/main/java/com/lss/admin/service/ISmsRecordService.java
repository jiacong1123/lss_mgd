package com.lss.admin.service;

import java.util.List;

import com.lss.core.dto.FindSmsRecordPage;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lss.core.dto.SmsRecordDto;
import com.lss.core.exception.LssException;
import com.lss.core.vo.ReturnVo;
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
public interface ISmsRecordService {
	
	/**
	 * 
	 *
	 * 方法说明：添加短信记录信息
	 *
	 * @param smsRecordDto
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addSmsRecord(SmsRecordDto smsRecordDto) throws LssException;
	
	/**
	 * 
	 *
	 * 方法说明：查找短信记录信息
	 *
	 * @param findSmsRecord
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public SmsRecordDto findSmsRecord(SmsRecordDto smsRecordDto) throws LssException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询短信记录信息
	 *
	 * @param findSmsRecordPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<SmsRecordDto>  findSmsRecords(FindSmsRecordPage findSmsRecordPage)throws LssException;

	/**
	 * 
	 *
	 * 方法说明：修改短信记录信息
	 *
	 * @param updateSmsRecord
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateSmsRecord(SmsRecordDto smsRecordDto)throws LssException;

	/**
	 * 
	 *
	 * 方法说明：分页查询短信记录信息
	 *
	 * @param findSmsRecordPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ReturnVo findSmsRecordPage(FindSmsRecordPage findSmsRecordPage) throws LssException;
	

}
