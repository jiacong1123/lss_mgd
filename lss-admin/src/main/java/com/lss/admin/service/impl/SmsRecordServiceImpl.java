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

import com.lss.admin.service.ISmsRecordService;
import com.lss.core.constant.ResponseCode;
import com.lss.core.dao.ISmsRecordDao;
import com.lss.core.dto.FindSmsRecordPage;
import com.lss.core.dto.SmsRecordDto;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.SmsRecord;
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
public class SmsRecordServiceImpl implements ISmsRecordService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(SmsRecordServiceImpl.class);
	

	@Resource
	private ISmsRecordDao smsRecordDao;
	
	
	@Override
	public void addSmsRecord(
			SmsRecordDto smsRecordDto) throws LssException {
		logger.debug("addSmsRecord(AddSmsRecord addSmsRecord={}) - start", smsRecordDto); 

		AssertUtils.notNull(smsRecordDto);
		try {
			SmsRecord smsRecord = new SmsRecord();
			//add数据录入
			smsRecord.setId(smsRecordDto.getId());
			smsRecord.setRecordId(smsRecordDto.getRecordId());
			smsRecord.setType(smsRecordDto.getType());
			smsRecord.setTransferNo(smsRecordDto.getTransferNo());
			smsRecord.setShowNo(smsRecordDto.getShowNo());
			smsRecord.setLlResult(smsRecordDto.getLlResult());
			smsRecord.setTransferTime(smsRecordDto.getTransferTime());
			smsRecord.setContent(smsRecordDto.getContent());
			smsRecord.setSmsInfo(smsRecordDto.getSmsInfo());
			smsRecord.setEmpId(smsRecordDto.getEmpId());
			smsRecord.setEmpNo(smsRecordDto.getEmpNo());
			smsRecord.setEmpNoArea(smsRecordDto.getEmpNoArea());
			smsRecord.setEmpInfo(smsRecordDto.getEmpInfo());
			smsRecord.setCusId(smsRecordDto.getCusId());
			smsRecord.setCusNo(smsRecordDto.getCusNo());
			smsRecord.setCusNoArea(smsRecordDto.getCusNoArea());
			smsRecord.setCusInfo(smsRecordDto.getCusInfo());
			smsRecord.setProcessStatus(smsRecordDto.getProcessStatus());
			smsRecord.setAdminId(smsRecordDto.getAdminId());
			smsRecord.setAdminName(smsRecordDto.getAdminName());
			smsRecord.setAdminPhone(smsRecordDto.getAdminPhone());
			smsRecord.setUserId(smsRecordDto.getUserId());
			smsRecord.setUserName(smsRecordDto.getUserName());
			smsRecord.setWorkrecordId(smsRecordDto.getWorkrecordId());
			smsRecord.setOrderno(smsRecordDto.getOrderno());
			smsRecord.setLssRecordUrl(smsRecordDto.getLssRecordUrl());
			smsRecord.setCreateTime(new Date());
			smsRecordDao.insertSelective(smsRecord);
			smsRecordDto.setId(smsRecord.getId());
			logger.debug("addSmsRecord(SmsRecordDto) - end - return"); 
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增短信记录信息错误！",e);
			throw new LssException(ResponseCode.failure,"新增短信记录信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询短信记录信息
	 *
	 * @param findSmsRecordPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<SmsRecordDto>  findSmsRecords(FindSmsRecordPage findSmsRecordPage)throws LssException{
		AssertUtils.notNull(findSmsRecordPage);
		List<SmsRecordDto> returnList=null;
		try {
			returnList = smsRecordDao.findSmsRecords(findSmsRecordPage);
		} catch (Exception e) {
			logger.error("查找短信记录信息信息错误！", e);
			throw new LssException(ResponseCode.failure,"短信记录信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateSmsRecord(
			SmsRecordDto smsRecordDto)
			throws LssException {
		logger.debug("updateSmsRecord(SmsRecordDto smsRecordDto={}) - start", smsRecordDto); //$NON-NLS-1$

		AssertUtils.notNull(smsRecordDto);
		AssertUtils.notNullAndEmpty(smsRecordDto.getId(),"id不能为空");
		try {
			SmsRecord smsRecord = new SmsRecord();
			//update数据录入
			smsRecord.setId(smsRecordDto.getId());
			smsRecord.setRecordId(smsRecordDto.getRecordId());
			smsRecord.setType(smsRecordDto.getType());
			smsRecord.setTransferNo(smsRecordDto.getTransferNo());
			smsRecord.setShowNo(smsRecordDto.getShowNo());
			smsRecord.setLlResult(smsRecordDto.getLlResult());
			smsRecord.setTransferTime(smsRecordDto.getTransferTime());
			smsRecord.setContent(smsRecordDto.getContent());
			smsRecord.setSmsInfo(smsRecordDto.getSmsInfo());
			smsRecord.setEmpId(smsRecordDto.getEmpId());
			smsRecord.setEmpNo(smsRecordDto.getEmpNo());
			smsRecord.setEmpNoArea(smsRecordDto.getEmpNoArea());
			smsRecord.setEmpInfo(smsRecordDto.getEmpInfo());
			smsRecord.setCusId(smsRecordDto.getCusId());
			smsRecord.setCusNo(smsRecordDto.getCusNo());
			smsRecord.setCusNoArea(smsRecordDto.getCusNoArea());
			smsRecord.setCusInfo(smsRecordDto.getCusInfo());
			smsRecord.setProcessStatus(smsRecordDto.getProcessStatus());
			smsRecord.setAdminId(smsRecordDto.getAdminId());
			smsRecord.setAdminName(smsRecordDto.getAdminName());
			smsRecord.setAdminPhone(smsRecordDto.getAdminPhone());
			smsRecord.setUserId(smsRecordDto.getUserId());
			smsRecord.setUserName(smsRecordDto.getUserName());
			smsRecord.setWorkrecordId(smsRecordDto.getWorkrecordId());
			smsRecord.setOrderno(smsRecordDto.getOrderno());
			smsRecord.setLssRecordUrl(smsRecordDto.getLssRecordUrl());
			smsRecord.setCreateTime(smsRecordDto.getCreateTime());
			AssertUtils.notUpdateMoreThanOne(smsRecordDao.updateByPrimaryKeySelective(smsRecord));
			logger.debug("updateSmsRecord(SmsRecordDto) - end - return"); //$NON-NLS-1$
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("短信记录信息更新信息错误！",e);
			throw new LssException(ResponseCode.failure,"短信记录信息更新信息错误！",e);
		}
	}

	

	@Override
	public SmsRecordDto findSmsRecord(
			SmsRecordDto smsRecordDto) throws LssException {
		logger.debug("findSmsRecord(FindSmsRecord findSmsRecord={}) - start", smsRecordDto); //$NON-NLS-1$

		AssertUtils.notNull(smsRecordDto);
		AssertUtils.notAllNull(smsRecordDto.getId(),"id不能为空");
		try {
			SmsRecord smsRecord = smsRecordDao.selectByPrimaryKey(smsRecordDto.getId());
			if(smsRecord == null){
				return null;
				//throw new LssException(ErrorCode.SMS_RECORD_NOT_EXIST_ERROR,"短信记录信息不存在");
			}
			SmsRecordDto findSmsRecordReturn = new SmsRecordDto();
			//find数据录入
			findSmsRecordReturn.setId(smsRecord.getId());
			findSmsRecordReturn.setRecordId(smsRecord.getRecordId());
			findSmsRecordReturn.setType(smsRecord.getType());
			findSmsRecordReturn.setTransferNo(smsRecord.getTransferNo());
			findSmsRecordReturn.setShowNo(smsRecord.getShowNo());
			findSmsRecordReturn.setLlResult(smsRecord.getLlResult());
			findSmsRecordReturn.setTransferTime(smsRecord.getTransferTime());
			findSmsRecordReturn.setContent(smsRecord.getContent());
			findSmsRecordReturn.setSmsInfo(smsRecord.getSmsInfo());
			findSmsRecordReturn.setEmpId(smsRecord.getEmpId());
			findSmsRecordReturn.setEmpNo(smsRecord.getEmpNo());
			findSmsRecordReturn.setEmpNoArea(smsRecord.getEmpNoArea());
			findSmsRecordReturn.setEmpInfo(smsRecord.getEmpInfo());
			findSmsRecordReturn.setCusId(smsRecord.getCusId());
			findSmsRecordReturn.setCusNo(smsRecord.getCusNo());
			findSmsRecordReturn.setCusNoArea(smsRecord.getCusNoArea());
			findSmsRecordReturn.setCusInfo(smsRecord.getCusInfo());
			findSmsRecordReturn.setProcessStatus(smsRecord.getProcessStatus());
			findSmsRecordReturn.setAdminId(smsRecord.getAdminId());
			findSmsRecordReturn.setAdminName(smsRecord.getAdminName());
			findSmsRecordReturn.setAdminPhone(smsRecord.getAdminPhone());
			findSmsRecordReturn.setUserId(smsRecord.getUserId());
			findSmsRecordReturn.setUserName(smsRecord.getUserName());
			findSmsRecordReturn.setWorkrecordId(smsRecord.getWorkrecordId());
			findSmsRecordReturn.setOrderno(smsRecord.getOrderno());
			findSmsRecordReturn.setLssRecordUrl(smsRecord.getLssRecordUrl());
			findSmsRecordReturn.setCreateTime(smsRecord.getCreateTime());
			
			logger.debug("findSmsRecord(SmsRecordDto) - end - return value={}", findSmsRecordReturn); //$NON-NLS-1$
			return findSmsRecordReturn;
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找短信记录信息信息错误！",e);
			throw new LssException(ResponseCode.failure,"查找短信记录信息信息错误！",e);
		}


	}

	
	@Override
	public ReturnVo findSmsRecordPage(
			FindSmsRecordPage findSmsRecordPage)
			throws LssException {
		logger.debug("findSmsRecordPage(FindSmsRecordPage findSmsRecordPage={}) - start", findSmsRecordPage); //$NON-NLS-1$

		AssertUtils.notNull(findSmsRecordPage);
		List<SmsRecordDto> returnList=null;
		int count = 0;
		try {
			returnList = smsRecordDao.findSmsRecordPage(findSmsRecordPage);
			count = smsRecordDao.findSmsRecordPageCount(findSmsRecordPage);
		}  catch (Exception e) {
			logger.error("短信记录信息不存在错误",e);
			throw new LssException(ResponseCode.failure,"短信记录信息不存在错误.！",e);
		}
		ReturnVo result = new ReturnVo();
		result.setPage(findSmsRecordPage.getPage());
		result.setLimit(findSmsRecordPage.getLimit());
		result.setObj(returnList);
		result.setTotal(count);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg); 

		logger.debug("findSmsRecordPage(FindSmsRecordPage) - end - return value={}", result); //$NON-NLS-1$
		return  result;
	} 


}
