package com.lss.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lss.admin.service.ICallRecordService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.dao.ICallRecordDao;
import com.lss.core.dao.ISmsRecordDao;
import com.lss.core.dto.CallRecordDto;
import com.lss.core.dto.FindCallRecordPage;
import com.lss.core.dto.FindSmsRecordPage;
import com.lss.core.dto.SmsRecordDto;
import com.lss.core.emus.ProcessStatus;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.Admin;
import com.lss.core.pojo.CallRecord;
import com.lss.core.pojo.SmsRecord;
import com.lss.core.pojo.User;
import com.lss.core.pojo.WorkRecord;
import com.lss.core.util.AssertUtils;
import com.lss.core.util.DateUtils;
import com.lss.core.util.QiniuUtil;
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
public class CallRecordServiceImpl implements ICallRecordService { 

	/**
	 * 下载录音，间隔时间
	 */
	private static final int DOWN_RECORD_SECOND=3600;
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CallRecordServiceImpl.class);
	

	@Resource
	private ICallRecordDao callRecordDao;
	
	@Resource
	private ISmsRecordDao smsRecordDao;
	
	@Override
	public void addCallRecord(
			CallRecordDto callRecordDto) throws LssException {
		logger.debug("addCallRecord(AddCallRecord addCallRecord={}) - start", callRecordDto); 

		AssertUtils.notNull(callRecordDto);
		try {
			CallRecord callRecord = new CallRecord();
			//add数据录入
			callRecord.setId(callRecordDto.getId());
			callRecord.setRecordId(callRecordDto.getRecordId());
			callRecord.setType(callRecordDto.getType());
			callRecord.setTransferNo(callRecordDto.getTransferNo());
			callRecord.setShowNo(callRecordDto.getShowNo());
			callRecord.setLlResult(callRecordDto.getLlResult());
			callRecord.setStartTime(callRecordDto.getStartTime());
			callRecord.setEndTime(callRecordDto.getEndTime());
			callRecord.setDuration(callRecordDto.getDuration());
			callRecord.setRecordingUrl(callRecordDto.getRecordingUrl());
			callRecord.setCallInfo(callRecordDto.getCallInfo());
			callRecord.setEmpId(callRecordDto.getEmpId());
			callRecord.setEmpNo(callRecordDto.getEmpNo());
			callRecord.setEmpNoArea(callRecordDto.getEmpNoArea());
			callRecord.setEmpInfo(callRecordDto.getEmpInfo());
			callRecord.setCusId(callRecordDto.getCusId());
			callRecord.setCusNo(callRecordDto.getCusNo());
			callRecord.setCusNoArea(callRecordDto.getCusNoArea());
			callRecord.setCusInfo(callRecordDto.getCusInfo());
			callRecord.setProcessStatus(callRecordDto.getProcessStatus());
			callRecord.setAdminId(callRecordDto.getAdminId());
			callRecord.setAdminName(callRecordDto.getAdminName());
			callRecord.setAdminPhone(callRecordDto.getAdminPhone());
			callRecord.setUserId(callRecordDto.getUserId());
			callRecord.setUserName(callRecordDto.getUserName());
			callRecord.setWorkrecordId(callRecordDto.getWorkrecordId());
			callRecord.setOrderno(callRecordDto.getOrderno());
			callRecord.setLssRecordUrl(callRecordDto.getLssRecordUrl());
			callRecord.setCreateTime(new Date());
			callRecordDao.insertSelective(callRecord);
			callRecordDto.setId(callRecord.getId());
			logger.debug("addCallRecord(CallRecordDto) - end - return"); 
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增电话记录信息错误！",e);
			throw new LssException(ResponseCode.failure,"新增电话记录信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询电话记录信息
	 *
	 * @param findCallRecordPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<CallRecordDto>  findCallRecords(FindCallRecordPage findCallRecordPage)throws LssException{
		AssertUtils.notNull(findCallRecordPage);
		List<CallRecordDto> returnList=null;
		try {
			returnList = callRecordDao.findCallRecords(findCallRecordPage);
		} catch (Exception e) {
			logger.error("查找电话记录信息信息错误！", e);
			throw new LssException(ResponseCode.failure,"电话记录信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateCallRecord(
			CallRecordDto callRecordDto)
			throws LssException {
		logger.debug("updateCallRecord(CallRecordDto callRecordDto={}) - start", callRecordDto); //$NON-NLS-1$

		AssertUtils.notNull(callRecordDto);
		AssertUtils.notNullAndEmpty(callRecordDto.getId(),"id不能为空");
		try {
			CallRecord callRecord = new CallRecord();
			//update数据录入
			callRecord.setId(callRecordDto.getId());
			callRecord.setRecordId(callRecordDto.getRecordId());
			callRecord.setType(callRecordDto.getType());
			callRecord.setTransferNo(callRecordDto.getTransferNo());
			callRecord.setShowNo(callRecordDto.getShowNo());
			callRecord.setLlResult(callRecordDto.getLlResult());
			callRecord.setStartTime(callRecordDto.getStartTime());
			callRecord.setEndTime(callRecordDto.getEndTime());
			callRecord.setDuration(callRecordDto.getDuration());
			callRecord.setRecordingUrl(callRecordDto.getRecordingUrl());
			callRecord.setCallInfo(callRecordDto.getCallInfo());
			callRecord.setEmpId(callRecordDto.getEmpId());
			callRecord.setEmpNo(callRecordDto.getEmpNo());
			callRecord.setEmpNoArea(callRecordDto.getEmpNoArea());
			callRecord.setEmpInfo(callRecordDto.getEmpInfo());
			callRecord.setCusId(callRecordDto.getCusId());
			callRecord.setCusNo(callRecordDto.getCusNo());
			callRecord.setCusNoArea(callRecordDto.getCusNoArea());
			callRecord.setCusInfo(callRecordDto.getCusInfo());
			callRecord.setProcessStatus(callRecordDto.getProcessStatus());
			callRecord.setAdminId(callRecordDto.getAdminId());
			callRecord.setAdminName(callRecordDto.getAdminName());
			callRecord.setAdminPhone(callRecordDto.getAdminPhone());
			callRecord.setUserId(callRecordDto.getUserId());
			callRecord.setUserName(callRecordDto.getUserName());
			callRecord.setWorkrecordId(callRecordDto.getWorkrecordId());
			callRecord.setOrderno(callRecordDto.getOrderno());
			callRecord.setLssRecordUrl(callRecordDto.getLssRecordUrl());
			callRecord.setCreateTime(callRecordDto.getCreateTime());
			AssertUtils.notUpdateMoreThanOne(callRecordDao.updateByPrimaryKeySelective(callRecord));
			logger.debug("updateCallRecord(CallRecordDto) - end - return"); //$NON-NLS-1$
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("电话记录信息更新信息错误！",e);
			throw new LssException(ResponseCode.failure,"电话记录信息更新信息错误！",e);
		}
	}

	

	@Override
	public CallRecordDto findCallRecord(
			CallRecordDto callRecordDto) throws LssException {
		logger.debug("findCallRecord(FindCallRecord findCallRecord={}) - start", callRecordDto); //$NON-NLS-1$

		AssertUtils.notNull(callRecordDto);
		AssertUtils.notAllNull(callRecordDto.getId(),"id不能为空");
		try {
			CallRecord callRecord = callRecordDao.selectByPrimaryKey(callRecordDto.getId());
			if(callRecord == null){
				return null;
				//throw new LssException(ErrorCode.CALL_RECORD_NOT_EXIST_ERROR,"电话记录信息不存在");
			}
			CallRecordDto findCallRecordReturn = new CallRecordDto();
			//find数据录入
			findCallRecordReturn.setId(callRecord.getId());
			findCallRecordReturn.setRecordId(callRecord.getRecordId());
			findCallRecordReturn.setType(callRecord.getType());
			findCallRecordReturn.setTransferNo(callRecord.getTransferNo());
			findCallRecordReturn.setShowNo(callRecord.getShowNo());
			findCallRecordReturn.setLlResult(callRecord.getLlResult());
			findCallRecordReturn.setStartTime(callRecord.getStartTime());
			findCallRecordReturn.setEndTime(callRecord.getEndTime());
			findCallRecordReturn.setDuration(callRecord.getDuration());
			findCallRecordReturn.setRecordingUrl(callRecord.getRecordingUrl());
			findCallRecordReturn.setCallInfo(callRecord.getCallInfo());
			findCallRecordReturn.setEmpId(callRecord.getEmpId());
			findCallRecordReturn.setEmpNo(callRecord.getEmpNo());
			findCallRecordReturn.setEmpNoArea(callRecord.getEmpNoArea());
			findCallRecordReturn.setEmpInfo(callRecord.getEmpInfo());
			findCallRecordReturn.setCusId(callRecord.getCusId());
			findCallRecordReturn.setCusNo(callRecord.getCusNo());
			findCallRecordReturn.setCusNoArea(callRecord.getCusNoArea());
			findCallRecordReturn.setCusInfo(callRecord.getCusInfo());
			findCallRecordReturn.setProcessStatus(callRecord.getProcessStatus());
			findCallRecordReturn.setAdminId(callRecord.getAdminId());
			findCallRecordReturn.setAdminName(callRecord.getAdminName());
			findCallRecordReturn.setAdminPhone(callRecord.getAdminPhone());
			findCallRecordReturn.setUserId(callRecord.getUserId());
			findCallRecordReturn.setUserName(callRecord.getUserName());
			findCallRecordReturn.setWorkrecordId(callRecord.getWorkrecordId());
			findCallRecordReturn.setOrderno(callRecord.getOrderno());
			findCallRecordReturn.setLssRecordUrl(callRecord.getLssRecordUrl());
			findCallRecordReturn.setCreateTime(callRecord.getCreateTime());
			
			logger.debug("findCallRecord(CallRecordDto) - end - return value={}", findCallRecordReturn); //$NON-NLS-1$
			return findCallRecordReturn;
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找电话记录信息信息错误！",e);
			throw new LssException(ResponseCode.failure,"查找电话记录信息信息错误！",e);
		}


	}

	
	@Override
	public ReturnVo findCallRecordPage(
			FindCallRecordPage findCallRecordPage)
			throws LssException {
		logger.debug("findCallRecordPage(FindCallRecordPage findCallRecordPage={}) - start", findCallRecordPage); //$NON-NLS-1$

		AssertUtils.notNull(findCallRecordPage);
		List<CallRecordDto> returnList=null;
		int count = 0;
		try {
			returnList = callRecordDao.findCallRecordPage(findCallRecordPage);
			count = callRecordDao.findCallRecordPageCount(findCallRecordPage);
		}  catch (Exception e) {
			logger.error("电话记录信息不存在错误",e);
			throw new LssException(ResponseCode.failure,"电话记录信息不存在错误.！",e);
		}
		ReturnVo result = new ReturnVo();
		result.setPage(findCallRecordPage.getPage());
		result.setLimit(findCallRecordPage.getLimit());
		result.setObj(returnList);
		result.setTotal(count);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);

		logger.debug("findCallRecordPage(FindCallRecordPage) - end - return value={}", result); //$NON-NLS-1$
		return  result;
	}


	/* (non-Javadoc)
	 * @see com.lss.admin.service.ICallRecordService#processCallRecord()
	 */
	@Override
	public void processCallRecord(String batchNum) throws LssException {
		FindCallRecordPage findCallRecordPage = new FindCallRecordPage();
		/** 连连录音地址还需要一小段时间才能访问(最长几分钟, 和通话时长有关系, 因为要完成软编解码等音频处理和往前端服务器的拷贝, 是需要一点时间的)*/
		Date endDate=DateUtils.addSecond(new Date(), DOWN_RECORD_SECOND);
		findCallRecordPage.setEndDate(endDate);//查找指定时间之前，且未处理的录音
		findCallRecordPage.setProcessStatus(ProcessStatus.INIT.name());
		
		List<CallRecordDto> returnList=null;
		try {
			int count =callRecordDao.findCallRecordPageCount(findCallRecordPage);
			logger.info("批次{}:待处理录音数据共 {}条",batchNum,count);
			if(count>0) {
				//1.查出所有员工
			    Map<String, Admin> allAdmin= findAllAdmin();
			    //2.处理记录
				returnList = callRecordDao.findCallRecords(findCallRecordPage);
				if(returnList!=null) {
					for (Iterator<CallRecordDto> iterator = returnList.iterator(); iterator.hasNext();) {
						CallRecordDto callRecordDto = iterator.next();
						handleCallRecord(callRecordDto, allAdmin);
					}
				}
			}
		} catch (Exception e) {
			logger.error("查找电话记录信息信息错误！", e);
			throw new LssException(ResponseCode.failure,"电话记录信息不存在");
		}
	} 

	private Map<String, Admin> findAllAdmin(){
		List<Admin> allAdmin=MapperManager.adminMapper.selectAll();
		Map<String, Admin> admins=new HashMap<>();
		for (Admin admin : allAdmin) {
			admins.put(admin.getLoginame(), admin);
		}
		return admins;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	private void  handleCallRecord(CallRecordDto dto, Map<String, Admin> allAdmin) {
		CallRecord record=new CallRecord();
		record.setId(dto.getId());
		record.setProcessStatus(ProcessStatus.PROCESSED.name());
	
		String url = null;
		//1.上传录音到七牛
		if(StringUtils.isNotBlank(dto.getRecordingUrl())) {
			try {
				url = QiniuUtil.getInstant().uploadFromUrl(dto.getRecordingUrl());
				record.setLssRecordUrl(url);
			}catch (Exception e) {
				logger.error("上传七牛失败！", e);
			}
		}
		try {
			//2.修改员工信息
			Admin admin=allAdmin.get(dto.getEmpId());
			if(admin!=null) {
				record.setAdminId(admin.getAdminid());
				record.setAdminName(admin.getName());
			}
			//3.修改客户信息
			if(StringUtils.isNotBlank(dto.getCusId())) {
				Integer userId = Integer.parseInt(dto.getCusId());
				User user = MapperManager.userMapper.selectByPrimaryKey(userId);
				if(user!=null) {
					record.setUserId(user.getUserid());
					record.setUserName(user.getName());
				}
			}
			//4.将录音文件回写到工单跟踪记录中
			if(StringUtils.isNotBlank(dto.getCallInfo())&&"OutBound_Call".equals(dto.getType())) {
				Integer wId = Integer.parseInt(dto.getCallInfo());
				WorkRecord workRecord=new WorkRecord();
				workRecord.setRecordUrl(url);
				String llrt="";
				if("ANSWERED".equals(dto.getLlResult())) {
					llrt="通话成功";
				}else if("BUSY".equals(dto.getLlResult())) {
					llrt="被叫忙";
				}else if("NO_ANSWER".equals(dto.getLlResult())) {
					llrt="被叫无应答";
				}else if("REJECT".equals(dto.getLlResult())) {
					llrt="被叫拒接";
				}else if("HANGUP".equals(dto.getLlResult())) {
					llrt="主叫提前挂机";
				}else if("INVALID_NUMBER".equals(dto.getLlResult())) {
					llrt="空号, 连连后台使用信号异常";
				}else if("POWER_OFF".equals(dto.getLlResult())) {
					llrt="关机";
				}else if("UNAVAILABLE".equals(dto.getLlResult())) {
					llrt="暂时无法接听, 连连后台使用信号异常";
				}else if("SUSPEND".equals(dto.getLlResult())) {
					llrt="暂时无法接听, 连连后台使用信号异常";
				}else if("BLACK".equals(dto.getLlResult())) {
					llrt="暂时无法接听, 连连后台使用信号异常";
				}else if("OTHER".equals(dto.getLlResult())) {
					llrt="其他失败情形, 连连后台使用信号异常";
				}else {
					llrt=dto.getLlResult();
				}
//				workRecord.setContent("通话："+llrt);
				workRecord.setRemark(llrt);
				workRecord.setId(wId);
//				record.setOrderno(workRecord.getOrderno());
				record.setWorkrecordId(wId);
				MapperManager.workRecordMapper.updateByPrimaryKeySelective(workRecord);
			}//if end
		}catch (Exception e) {
			logger.error("操作失败！", e);
		}finally {
			record.setUpdateDate(new Date());
			//成功与否都记录为已处理
			callRecordDao.updateByPrimaryKeySelective(record);
		}
	}


	@Override
	public void processSmsRecord(String batchNum) throws LssException {
		FindSmsRecordPage param = new FindSmsRecordPage();
		/** 连连录音地址还需要一小段时间才能访问(最长几分钟, 和通话时长有关系, 因为要完成软编解码等音频处理和往前端服务器的拷贝, 是需要一点时间的)*/
		Date endDate = DateUtils.addSecond(new Date(), DOWN_RECORD_SECOND);
		param.setEndDate(endDate);//查找指定时间之前，且未处理的录音
		param.setProcessStatus(ProcessStatus.INIT.name());
		
		List<SmsRecordDto> returnList=null;
		try {
			int count =smsRecordDao.findSmsRecordPageCount(param);
			logger.info("批次{}:待处理短信数据共 {}条",batchNum,count);
			if(count>0) {
				//1.查出所有员工
			    Map<String, Admin> allAdmin= findAllAdmin();
			    //2.处理记录
				returnList = smsRecordDao.findSmsRecords(param);
				if(returnList!=null) {
					for (Iterator<SmsRecordDto> iterator = returnList.iterator(); iterator.hasNext();) {
						SmsRecordDto callRecordDto = iterator.next();
						handleSmsRecord(callRecordDto, allAdmin);
					}
				}
			}
		} catch (Exception e) {
			logger.error("查找短信记录信息信息错误！", e);
			throw new LssException(ResponseCode.failure,"短信记录信息不存在");
		}
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	private void  handleSmsRecord(SmsRecordDto dto, Map<String, Admin> allAdmin) {
		SmsRecord record=new SmsRecord();
		record.setId(dto.getId());
		record.setProcessStatus(ProcessStatus.PROCESSED.name());
		try { 
			//1.修改员工信息
			Admin admin=allAdmin.get(dto.getEmpId());
			if(admin!=null) {
				record.setAdminId(admin.getAdminid());
				record.setAdminName(admin.getName());
			}
			//2.修改客户信息
			if(StringUtils.isNotBlank(dto.getCusId())) {
				Integer userId = Integer.parseInt(dto.getCusId());
				User user = MapperManager.userMapper.selectByPrimaryKey(userId);
				if(user!=null) {
					record.setUserId(user.getUserid());
					record.setUserName(user.getName());
				}
			}
			
			//4.将短信发送结果回写到工单跟踪记录中
			if(StringUtils.isNotBlank(dto.getSmsInfo())&&"OUTBOUND".equals(dto.getType())) {
				Integer wId = Integer.parseInt(dto.getSmsInfo());
				WorkRecord workRecord=new WorkRecord();
				String llrt="";
				if("SENT".equals(dto.getLlResult())) {
					llrt="成功发送";
				}else if("INVALID_SHOW_NUMBER".equals(dto.getLlResult())) {
					llrt="显示号码不合法";
				}else if("INVALID_RECEIVER_NUMBER".equals(dto.getLlResult())) {
					llrt="接收号码非手机号";
				}else if("OTHER".equals(dto.getLlResult())) {
					llrt="其他失败情形, 连连后台使用信号异常";
				}else {
					llrt=dto.getLlResult();
				}
				workRecord.setRemark(llrt);
				workRecord.setId(wId);
//				record.setOrderno(workRecord.getOrderno());
				record.setWorkrecordId(wId);
				MapperManager.workRecordMapper.updateByPrimaryKeySelective(workRecord);
			}//if end
		}catch (Exception e) {
			logger.error("操作失败！", e);
		}finally {
			record.setUpdateDate(new Date());
			//成功与否都记录为已处理
			smsRecordDao.updateByPrimaryKeySelective(record);
		}
	}


	
	@Override
	public ReturnVo findUnReadlist(FindCallRecordPage param) throws LssException {
		logger.debug("findUnReadlist(FindCallRecordPage param = {}) - start",param);
		ReturnVo result = new ReturnVo();
		AssertUtils.notNull(param);
		List<CallRecordDto> list = null;
		int count = 0;
		try {
			list = callRecordDao.findUnReadlist(param);
			//填充工单号
			if(list.size()!=0) {
				for (CallRecordDto callRecordDto : list) {
					if(callRecordDto.getUserId()!=null) {
						String orderNo = MapperManager.workOrderMapper.findOrderNoByUserId(callRecordDto.getUserId());
						callRecordDto.setOrderno(orderNo);
					}
					callRecordDto.setName(callRecordDto.getUserName());
					callRecordDto.setPhone(callRecordDto.getCusNo());
				}
			}
			count = callRecordDao.findUnreadCount(param);
		} catch (Exception e) {
			logger.error("操作失败!",e);
			result.setResult(ResponseCode.failure);
			result.setMsg(ResponseCode.failureMsg);
			return result;
		}
		result.setPage(param.getPage());
		result.setLimit(param.getLimit());
		result.setObj(list);
		result.setTotal(count);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}


	
	@Override
	public ReturnVo findUnReadCount(FindCallRecordPage param) {
		logger.debug("findUnReadCount(FindCallRecordPage param = {}) - start",param);
		ReturnVo result = new ReturnVo();
		int count = 0;
		try {
			count = callRecordDao.findUnReadCount(param);
		} catch (Exception e) {
			logger.error("查询未处理未接来电数量错误!",e);
		}
		result.setObj(count);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}


	
	@Override
	public ReturnVo updateCallRecordStatus(FindCallRecordPage param) {
		logger.debug("updateCallRecordStatus(FindCallRecordPage param = {})-start");
		ReturnVo result = new ReturnVo();
		try {
			callRecordDao.updateCallRecordStatus(param);
		} catch (Exception e) {
			logger.error("更新通话记录状态错误!",e);
		}
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);
		return result;
	}

}
