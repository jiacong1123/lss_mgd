/**
 * 
 */
package com.lss.admin.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.lss.admin.base.ServiceManager;
import com.lss.admin.service.PhoneService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.LlyhConstant;
import com.lss.core.constant.ResponseCode;
import com.lss.core.dao.ICallEventDao;
import com.lss.core.dao.ICallRecordDao;
import com.lss.core.dao.ISmsRecordDao;
import com.lss.core.emus.ProcessStatus;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.Admin;
import com.lss.core.pojo.CallEvent;
import com.lss.core.pojo.CallRecord;
import com.lss.core.pojo.SmsRecord;
import com.lss.core.pojo.User;
import com.lss.core.pojo.WorkOrder;
import com.lss.core.pojo.WorkRecord;
import com.lss.core.util.HttpRequestHelper;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.UserVo;
import com.lss.core.vo.admin.WorkOrderVo;
import com.lss.core.vo.phone.CallEventListVo;
import com.lss.core.vo.phone.CallEventVo;
import com.lss.core.vo.phone.CallRecordListVo;
import com.lss.core.vo.phone.CallRecordVo;
import com.lss.core.vo.phone.LlyhBindResultVo;
import com.lss.core.vo.phone.LlyhBindVo;
import com.lss.core.vo.phone.LlyhResultVo;
import com.lss.core.vo.phone.LlyhSmsRecordListVo;
import com.lss.core.vo.phone.LlyhSmsRecordVo;


/**
 * 
 * 
 * 类说明：连连推送的内容处理。 <br/>
 * 电话相关记录。
 * 
 * 
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月6日
 */
@Service
public class PhoneServiceImpl implements PhoneService {
	private static final Logger log = LoggerFactory.getLogger(PhoneServiceImpl.class);

	@Resource
	private ICallRecordDao callRecordDao;
	
	@Resource
	private ICallEventDao callEventDao;
	
	@Resource
	private ISmsRecordDao smsRecordDao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lss.admin.service.PhoneService#saveCallRecord(com.lss.core.vo.phone.
	 * CallRecordListVo)
	 */
	@Override
	public LlyhResultVo saveCallRecord(CallRecordListVo recordListVo) {
		if(!LlyhConstant.LSS_TOKEN.equals(recordListVo.getToken())) {
			throw new LssException(ResponseCode.llyhUnkowToken, ResponseCode.llyhUnkowTokenMsg);
		}
		
		List<CallRecordVo> records=recordListVo.getRecords();
		if(null==records) {
			throw new LssException(ResponseCode.llyhParameterError, ResponseCode.llyhParameterErrorMsg);
		}
		for (Iterator<CallRecordVo> iterator = records.iterator(); iterator.hasNext();) {
			CallRecordVo callRecordDto = iterator.next();
	 
			CallRecord callRecord = new CallRecord();
			//add数据录入
//			callRecord.setId(callRecordDto.getId());
			callRecord.setRecordId(callRecordDto.getRecordId());
			callRecord.setType(callRecordDto.getType());
			callRecord.setTransferNo(callRecordDto.getTransferNo());
			callRecord.setShowNo(callRecordDto.getShowNo());
			callRecord.setLlResult(callRecordDto.getResult());
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
			callRecord.setProcessStatus(ProcessStatus.INIT.name());
//			callRecord.setAdminId(callRecordDto.getAdminId());
//			callRecord.setAdminName(callRecordDto.getAdminName());
//			callRecord.setAdminPhone(callRecordDto.getAdminPhone());
//			callRecord.setUserId(callRecordDto.getUserId());
//			callRecord.setUserName(callRecordDto.getUserName());
//			callRecord.setWorkrecordId(callRecordDto.getWorkrecordId());
//			callRecord.setOrderno(callRecordDto.getOrderno());
//			callRecord.setLssRecordUrl(callRecordDto.getLssRecordUrl());
			callRecord.setCreateTime(new Date());
			callRecordDao.insertSelective(callRecord);
		}
		return LlyhResultVo.success();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lss.admin.service.PhoneService#saveSmsRecord(com.lss.core.vo.phone.
	 * LlyhSmsRecordListVo)
	 */
	@Override
	public LlyhResultVo saveSmsRecord(LlyhSmsRecordListVo smsRecordListVo) {
		if(!LlyhConstant.LSS_TOKEN.equals(smsRecordListVo.getToken())) {
			throw new LssException(ResponseCode.llyhUnkowToken, ResponseCode.llyhUnkowTokenMsg);
		}
		List<LlyhSmsRecordVo> records = smsRecordListVo.getRecords();
		if (null == records) {
			throw new LssException(ResponseCode.llyhParameterError, ResponseCode.llyhParameterErrorMsg);
		}
		for (Iterator<LlyhSmsRecordVo> iterator = records.iterator(); iterator.hasNext();) {
			LlyhSmsRecordVo smsRecordDto = iterator.next();
			
			SmsRecord smsRecord = new SmsRecord();
			//add数据录入
//			smsRecord.setId(smsRecordDto.getId());
			smsRecord.setRecordId(smsRecordDto.getRecordId());
			smsRecord.setType(smsRecordDto.getType());
			smsRecord.setTransferNo(smsRecordDto.getTransferNo());
			smsRecord.setShowNo(smsRecordDto.getShowNo());
			smsRecord.setLlResult(smsRecordDto.getResult());
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
			smsRecord.setProcessStatus(ProcessStatus.INIT.name());
//			smsRecord.setAdminId(smsRecordDto.getAdminId());
//			smsRecord.setAdminName(smsRecordDto.getAdminName());
//			smsRecord.setAdminPhone(smsRecordDto.getAdminPhone());
//			smsRecord.setUserId(smsRecordDto.getUserId());
//			smsRecord.setUserName(smsRecordDto.getUserName());
//			smsRecord.setWorkrecordId(smsRecordDto.getWorkrecordId());
//			smsRecord.setOrderno(smsRecordDto.getOrderno());
//			smsRecord.setLssRecordUrl(smsRecordDto.getLssRecordUrl());
			smsRecord.setCreateTime(new Date());
			smsRecordDao.insertSelective(smsRecord);
		}
		return LlyhResultVo.success();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lss.admin.service.PhoneService#saveEventRecord(com.lss.core.vo.phone.
	 * CallEventListVo)
	 */
	@Override
	public LlyhResultVo saveEventRecord(CallEventListVo callEventListVo) {
		if(!LlyhConstant.LSS_TOKEN.equals(callEventListVo.getToken())) {
			throw new LssException(ResponseCode.llyhUnkowToken, ResponseCode.llyhUnkowTokenMsg);
		}
		List<CallEventVo> records = callEventListVo.getEvents();
		if(null==records) {
			throw new LssException(ResponseCode.llyhParameterError, ResponseCode.llyhParameterErrorMsg);
		}
		for (Iterator<CallEventVo> iterator = records.iterator(); iterator.hasNext();) {
			CallEventVo callEventDto = iterator.next();
		
			CallEvent callEvent = new CallEvent();
			//add数据录入
//			callEvent.setId(callEventDto.getId());
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
		}
		return LlyhResultVo.success();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lss.admin.service.PhoneService#phoneBind(com.lss.core.vo.phone.
	 * LlyhBindVo)
	 */
	@Override
	public ReturnVo phoneBind(WorkOrderVo order, LoginAdmin loginAdmin) {
		ReturnVo result = new ReturnVo();
		if (ObjectUtil.isEmpty(order.getOrderno())) {
			if(ObjectUtil.isEmpty(order.getPhone())) {
				throw new LssException(ResponseCode.parameterError, "工单编号及手机号为空");
			}else {//号码来电，电销员再回拨
				//1.首先查看客户是否存在
				UserVo user = MapperManager.userMapper.queryByPhone(order.getPhone());
				
				Admin admin= MapperManager.adminMapper.selectByPrimaryKey(loginAdmin.getAdminid());
				LlyhBindVo bindVo = new LlyhBindVo();
				bindVo.setCallerId(loginAdmin.getLoginame());
				bindVo.setToken(LlyhConstant.ACCESS_TOKEN);
				
				if(ObjectUtil.isEmpty(admin.getCallerNos())) {
					throw new LssException(ResponseCode.failure, "没有绑定话机号码");
				}
				if(ObjectUtil.isEmpty(admin.getTransferNo())) {
					bindVo.setType("PER_PERSON");//不指定小号
				}else {
					bindVo.setType("PER_PERSON_ASSIGNED");
					bindVo.setTransferNo(admin.getTransferNo());//指定小号
				}
				String[] cStrings = admin.getCallerNos().split(",");//
				bindVo.setCallerNos(cStrings);
				if(user!=null) {//非陌生用户
					bindVo.setCalledId(user.getUserid().toString());//用户ID
				}
				bindVo.setAgentContact(admin.getNoWx());
				//2.客户信息
				bindVo.setCalledNo(order.getPhone());//前端入参 phone
//				bindVo.setPayload(workRecordId.toString());//没有工单则 跟进记录ID 为空
				//3.绑定
				String reqJson = JSON.toJSONString(bindVo);
				log.info("phoneBind 外呼号码绑定 bindVo={}", reqJson);
				String resultJson = HttpRequestHelper.sendJsonPost(LlyhConstant.BIND_URL, reqJson);
				log.info("phoneBind 外呼号码绑定结果 resultJson={}", resultJson);
		
				LlyhBindResultVo resultVo = JSON.parseObject(resultJson, LlyhBindResultVo.class);
		
				if (0 == resultVo.getCode().intValue()) {
					result.setResult(ResponseCode.success);
					result.setMsg(ResponseCode.successMsg);
					result.setObj(resultVo.getNumber());
				} else {
					result.setResult(ResponseCode.failure);
					result.setMsg(resultVo.getMessage());
				}
				return result;
			}
		}else {//工单中拨打电话 
			//1.工单
			WorkOrder workOrder = MapperManager.workOrderMapper.selectByPrimaryKey(order.getOrderno());
			Admin admin= MapperManager.adminMapper.selectByPrimaryKey(loginAdmin.getAdminid());
			
			LlyhBindVo bindVo = new LlyhBindVo();
			bindVo.setCallerId(loginAdmin.getLoginame());
			bindVo.setToken(LlyhConstant.ACCESS_TOKEN);
			
			if(ObjectUtil.isEmpty(admin.getCallerNos())) {
				throw new LssException(ResponseCode.failure, "没有绑定话机号码");
			}
			if(ObjectUtil.isEmpty(admin.getTransferNo())) {
				bindVo.setType("PER_PERSON");//不指定小号
			}else {
				bindVo.setType("PER_PERSON_ASSIGNED");
				bindVo.setTransferNo(admin.getTransferNo());//指定小号
			}
			String[] cStrings = admin.getCallerNos().split(",");//
			bindVo.setCallerNos(cStrings);
			bindVo.setCalledId(workOrder.getUserid().toString());//用户ID
			bindVo.setAgentContact(admin.getNoWx());
			//2.客户信息
			User user = MapperManager.userMapper.selectByPrimaryKey(workOrder.getUserid());
			bindVo.setCalledNo(user.getPhone());
			Integer workRecordId = ServiceManager.workOrderService.insertWorkRecord(order.getOrderno(),
					loginAdmin.getAdminid(), "通话" , null);
			bindVo.setPayload(workRecordId.toString());//跟进记录ID
	
			//3.绑定
			String reqJson = JSON.toJSONString(bindVo);
			log.info("phoneBind 外呼号码绑定 bindVo={}", reqJson);
			String resultJson = HttpRequestHelper.sendJsonPost(LlyhConstant.BIND_URL, reqJson);
			log.info("phoneBind 外呼号码绑定结果 resultJson={}", resultJson);
	
			LlyhBindResultVo resultVo = JSON.parseObject(resultJson, LlyhBindResultVo.class);
	
			if (0 == resultVo.getCode().intValue()) {
				result.setResult(ResponseCode.success);
				result.setMsg(ResponseCode.successMsg);
				result.setObj(resultVo.getNumber());
			} else {
				result.setResult(ResponseCode.failure);
				result.setMsg(resultVo.getMessage());
				//4.失败则记录失败原因
				WorkRecord workRecord=new WorkRecord();
				workRecord.setId(workRecordId);
				workRecord.setStatus(0);
				workRecord.setRemark(resultVo.getMessage());
				MapperManager.workRecordMapper.updateByPrimaryKeySelective(workRecord);
			}
			return result;
		}
	}

}
