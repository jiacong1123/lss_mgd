package com.lss.admin.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lss.core.dto.CallEventDto;
import com.lss.core.dto.FindCallEventPage;
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
 * CreateDate: 2019.02.19
 */
public interface ICallEventService {
	
	/**
	 * 
	 *
	 * 方法说明：添加电话事件信息
	 *
	 * @param callEventDto
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addCallEvent(CallEventDto callEventDto) throws LssException;
	
	/**
	 * 
	 *
	 * 方法说明：查找电话事件信息
	 *
	 * @param findCallEvent
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public CallEventDto findCallEvent(CallEventDto callEventDto) throws LssException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询电话事件信息
	 *
	 * @param findCallEventPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<CallEventDto>  findCallEvents(FindCallEventPage findCallEventPage)throws LssException;

	/**
	 * 
	 *
	 * 方法说明：修改电话事件信息
	 *
	 * @param updateCallEvent
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateCallEvent(CallEventDto callEventDto)throws LssException;

	/**
	 * 
	 *
	 * 方法说明：分页查询电话事件信息
	 *
	 * @param findCallEventPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ReturnVo findCallEventPage(FindCallEventPage findCallEventPage) throws LssException;
	

}
