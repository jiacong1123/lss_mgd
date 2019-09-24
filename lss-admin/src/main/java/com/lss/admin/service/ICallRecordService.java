package com.lss.admin.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lss.core.dto.CallRecordDto;
import com.lss.core.dto.FindCallRecordPage;
import com.lss.core.exception.LssException;
import com.lss.core.vo.ReturnVo;

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
 *         CreateDate: 2019.02.19
 */
public interface ICallRecordService {

	/**
	 * 
	 *
	 * 方法说明：添加电话记录信息
	 *
	 * @param callRecordDto
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addCallRecord(CallRecordDto callRecordDto) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：查找电话记录信息
	 *
	 * @param findCallRecord
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public CallRecordDto findCallRecord(CallRecordDto callRecordDto) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询电话记录信息
	 *
	 * @param findCallRecordPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<CallRecordDto> findCallRecords(FindCallRecordPage findCallRecordPage) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：修改电话记录信息
	 *
	 * @param updateCallRecord
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateCallRecord(CallRecordDto callRecordDto) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：分页查询电话记录信息
	 *
	 * @param findCallRecordPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ReturnVo findCallRecordPage(FindCallRecordPage findCallRecordPage) throws LssException;

	/**
	 *  处理录音。
	 *  <p>1.将录音上传到七牛服务器
	 *  <p>2.员工和客户数据冗余
	 * @throws LssException
	 */
	public void processCallRecord(String batchNum) throws LssException;
	
	/**
	 *  短信处理。
	 *  <p>1.员工和客户数据冗余
	 * @throws LssException
	 */
	public void processSmsRecord(String batchNum) throws LssException;

	/**
	 * 查询未接来电
	 * @param param
	 * @return
	 */
	public ReturnVo findUnReadlist(FindCallRecordPage param)throws LssException;

	/**
	 * 查询未处理未接来电数量
	 * @param param
	 * @return
	 */
	public ReturnVo findUnReadCount(FindCallRecordPage param);

	/**
	 * @param param
	 * @return 
	 */
	public ReturnVo updateCallRecordStatus(FindCallRecordPage param);
}
