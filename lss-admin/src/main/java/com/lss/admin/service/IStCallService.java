package com.lss.admin.service;

import java.util.List;

import com.lss.core.dto.FindStCallPage;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lss.core.dto.StCallDto;
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
public interface IStCallService {

	/**
	 * 
	 *
	 * 方法说明：添加员工电联统计信息
	 *
	 * @param stCallDto
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addStCall(StCallDto stCallDto) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：查找员工电联统计信息
	 *
	 * @param findStCall
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public StCallDto findStCall(StCallDto stCallDto) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询员工电联统计信息
	 *
	 * @param findStCallPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<StCallDto> findStCalls(FindStCallPage findStCallPage) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：修改员工电联统计信息
	 *
	 * @param updateStCall
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateStCall(StCallDto stCallDto) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：分页查询员工电联统计信息
	 *
	 * @param findStCallPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ReturnVo findStCallPage(FindStCallPage findStCallPage) throws LssException;

	/**
	 *  统计每日电联情况
	 * 
	 * @param batchNum
	 * @return
	 * @author lhy 2019年8月15日 v1.4
	 */
	public void batchAddDailyStCall(String batchNum) throws LssException;
	
	
	/**
	 * 
	 *
	 * 方法说明：分页查询员工今日电联统计信息
	 *
	 * @param findStCallPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.08.15
	 *
	 */
	public ReturnVo findTodayStCallPage(FindStCallPage findStCallPage, LoginAdmin admin) throws LssException;

	
}
