package com.lss.admin.service.impl;

import java.util.Date;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lss.admin.service.ICallEventService;
import com.lss.core.constant.ResponseCode;
import com.lss.core.dao.ICallEventDao;
import com.lss.core.dto.CallEventDto;
import com.lss.core.dto.FindCallEventPage;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.CallEvent;
import com.lss.core.util.AssertUtils;
import com.lss.core.vo.ReturnVo;
 
/**
 * 类说明：实现类
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
@Service
public class CallEventServiceImpl implements ICallEventService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CallEventServiceImpl.class);
	

	@Resource
	private ICallEventDao callEventDao;
	
	
	@Override
	public void addCallEvent(
			CallEventDto callEventDto) throws LssException {
		logger.debug("addCallEvent(AddCallEvent addCallEvent={}) - start", callEventDto); 

		AssertUtils.notNull(callEventDto);
		try {
			CallEvent callEvent = new CallEvent();
			//add数据录入
			callEvent.setId(callEventDto.getId());
			callEvent.setRecordId(callEventDto.getRecordId());
			callEvent.setType(callEventDto.getType());
			callEvent.setTime(callEventDto.getTime());
			callEvent.setTransferNo(callEventDto.getTransferNo());
			callEvent.setShowNo(callEventDto.getShowNo());
			callEvent.setCallInfo(callEventDto.getCallInfo());
			callEvent.setEmpId(callEventDto.getEmpId());
			callEvent.setEmpNo(callEventDto.getEmpNo());
			callEvent.setEmpInfo(callEventDto.getEmpInfo());
			callEvent.setCusId(callEventDto.getCusId());
			callEvent.setCusNo(callEventDto.getCusNo());
			callEvent.setCusInfo(callEventDto.getCusInfo());
			callEvent.setCreateTime(new Date());
			callEventDao.insertSelective(callEvent);
			callEventDto.setId(callEvent.getId());
			logger.debug("addCallEvent(CallEventDto) - end - return"); 
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增电话事件信息错误！",e);
			throw new LssException(ResponseCode.failure,"新增电话事件信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询电话事件信息
	 *
	 * @param findCallEventPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<CallEventDto>  findCallEvents(FindCallEventPage findCallEventPage)throws LssException{
		AssertUtils.notNull(findCallEventPage);
		List<CallEventDto> returnList=null;
		try {
			returnList = callEventDao.findCallEvents(findCallEventPage);
		} catch (Exception e) {
			logger.error("查找电话事件信息信息错误！", e);
			throw new LssException(ResponseCode.failure,"电话事件信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateCallEvent(
			CallEventDto callEventDto)
			throws LssException {
		logger.debug("updateCallEvent(CallEventDto callEventDto={}) - start", callEventDto); //$NON-NLS-1$

		AssertUtils.notNull(callEventDto);
		AssertUtils.notNullAndEmpty(callEventDto.getId(),"id不能为空");
		try {
			CallEvent callEvent = new CallEvent();
			//update数据录入
			callEvent.setId(callEventDto.getId());
			callEvent.setRecordId(callEventDto.getRecordId());
			callEvent.setType(callEventDto.getType());
			callEvent.setTime(callEventDto.getTime());
			callEvent.setTransferNo(callEventDto.getTransferNo());
			callEvent.setShowNo(callEventDto.getShowNo());
			callEvent.setCallInfo(callEventDto.getCallInfo());
			callEvent.setEmpId(callEventDto.getEmpId());
			callEvent.setEmpNo(callEventDto.getEmpNo());
			callEvent.setEmpInfo(callEventDto.getEmpInfo());
			callEvent.setCusId(callEventDto.getCusId());
			callEvent.setCusNo(callEventDto.getCusNo());
			callEvent.setCusInfo(callEventDto.getCusInfo());
			callEvent.setCreateTime(callEventDto.getCreateTime());
			AssertUtils.notUpdateMoreThanOne(callEventDao.updateByPrimaryKeySelective(callEvent));
			logger.debug("updateCallEvent(CallEventDto) - end - return"); //$NON-NLS-1$
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("电话事件信息更新信息错误！",e);
			throw new LssException(ResponseCode.failure,"电话事件信息更新信息错误！",e);
		}
	}

	

	@Override
	public CallEventDto findCallEvent(
			CallEventDto callEventDto) throws LssException {
		logger.debug("findCallEvent(FindCallEvent findCallEvent={}) - start", callEventDto); //$NON-NLS-1$

		AssertUtils.notNull(callEventDto);
		AssertUtils.notAllNull(callEventDto.getId(),"id不能为空");
		try {
			CallEvent callEvent = callEventDao.selectByPrimaryKey(callEventDto.getId());
			if(callEvent == null){
				return null;
				//throw new LssException(ErrorCode.CALL_EVENT_NOT_EXIST_ERROR,"电话事件信息不存在");
			}
			CallEventDto findCallEventReturn = new CallEventDto();
			//find数据录入
			findCallEventReturn.setId(callEvent.getId());
			findCallEventReturn.setRecordId(callEvent.getRecordId());
			findCallEventReturn.setType(callEvent.getType());
			findCallEventReturn.setTime(callEvent.getTime());
			findCallEventReturn.setTransferNo(callEvent.getTransferNo());
			findCallEventReturn.setShowNo(callEvent.getShowNo());
			findCallEventReturn.setCallInfo(callEvent.getCallInfo());
			findCallEventReturn.setEmpId(callEvent.getEmpId());
			findCallEventReturn.setEmpNo(callEvent.getEmpNo());
			findCallEventReturn.setEmpInfo(callEvent.getEmpInfo());
			findCallEventReturn.setCusId(callEvent.getCusId());
			findCallEventReturn.setCusNo(callEvent.getCusNo());
			findCallEventReturn.setCusInfo(callEvent.getCusInfo());
			findCallEventReturn.setCreateTime(callEvent.getCreateTime());
			
			logger.debug("findCallEvent(CallEventDto) - end - return value={}", findCallEventReturn); //$NON-NLS-1$
			return findCallEventReturn;
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找电话事件信息信息错误！",e);
			throw new LssException(ResponseCode.failure,"查找电话事件信息信息错误！",e);
		}


	}

	
	@Override
	public ReturnVo findCallEventPage(
			FindCallEventPage findCallEventPage)
			throws LssException {
		logger.debug("findCallEventPage(FindCallEventPage findCallEventPage={}) - start", findCallEventPage); //$NON-NLS-1$

		AssertUtils.notNull(findCallEventPage);
		List<CallEventDto> returnList=null;
		int count = 0;
		try {
			returnList = callEventDao.findCallEventPage(findCallEventPage);
			count = callEventDao.findCallEventPageCount(findCallEventPage);
		}  catch (Exception e) {
			logger.error("电话事件信息不存在错误",e);
			throw new LssException(ResponseCode.failure,"电话事件信息不存在错误.！",e);
		}
		ReturnVo result = new ReturnVo();
		result.setPage(findCallEventPage.getPage());
		result.setLimit(findCallEventPage.getLimit());
		result.setObj(returnList);
		result.setTotal(count);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);

		logger.debug("findCallEventPage(FindCallEventPage) - end - return value={}", result); //$NON-NLS-1$
		return  result;
	} 


}
