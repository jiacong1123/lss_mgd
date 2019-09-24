package com.lss.admin.service.impl;

import java.util.ArrayList;
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

import com.lss.admin.service.IStCallService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.dao.IStCallDao;
import com.lss.core.dto.FindStCallPage;
import com.lss.core.dto.StCallDto;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.Admin;
import com.lss.core.pojo.StCall;
import com.lss.core.util.AssertUtils;
import com.lss.core.util.DateUtils;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
 
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
public class StCallServiceImpl implements IStCallService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(StCallServiceImpl.class);
	

	@Resource
	private IStCallDao stCallDao;
	
	
	@Override
	public void addStCall(StCallDto stCallDto) throws LssException {
		logger.debug("addStCall(AddStCall addStCall={}) - start", stCallDto); 

		AssertUtils.notNull(stCallDto);
		try {
			StCall stCall = new StCall();
			//add数据录入
			stCall.setId(stCallDto.getId());
			stCall.setAdminid(stCallDto.getAdminid());
			stCall.setAdminname(stCallDto.getAdminname());
			stCall.setStDate(stCallDto.getStDate());
			stCall.setCallCount(stCallDto.getCallCount());
			stCall.setDuration(stCallDto.getDuration());
			stCall.setAvgDuration(stCallDto.getAvgDuration());
			stCall.setCreateDate(stCallDto.getCreateDate());
			stCall.setUpdateDate(stCallDto.getUpdateDate());
			stCall.setRemark(stCallDto.getRemark());
			stCallDao.insertSelective(stCall);
			logger.debug("addStCall(StCallDto) - end - return"); 
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增员工电联统计信息错误！",e);
			throw new LssException(ResponseCode.failure,"新增员工电联统计信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询员工电联统计信息
	 *
	 * @param findStCallPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<StCallDto>  findStCalls(FindStCallPage findStCallPage)throws LssException{
		AssertUtils.notNull(findStCallPage);
		List<StCallDto> returnList=null;
		try {
			returnList = stCallDao.findStCalls(findStCallPage);
		} catch (Exception e) {
			logger.error("查找员工电联统计信息信息错误！", e);
			throw new LssException(ResponseCode.failure,"员工电联统计信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateStCall(
			StCallDto stCallDto)
			throws LssException {
		logger.debug("updateStCall(StCallDto stCallDto={}) - start", stCallDto); //$NON-NLS-1$

		AssertUtils.notNull(stCallDto);
		AssertUtils.notNullAndEmpty(stCallDto.getId(),"id不能为空");
		try {
			StCall stCall = new StCall();
			//update数据录入
			stCall.setId(stCallDto.getId());
			stCall.setAdminid(stCallDto.getAdminid());
			stCall.setAdminname(stCallDto.getAdminname());
			stCall.setStDate(stCallDto.getStDate());
			stCall.setCallCount(stCallDto.getCallCount());
			stCall.setDuration(stCallDto.getDuration());
			stCall.setAvgDuration(stCallDto.getAvgDuration());
			stCall.setCreateDate(stCallDto.getCreateDate());
			stCall.setUpdateDate(stCallDto.getUpdateDate());
			stCall.setRemark(stCallDto.getRemark());
			AssertUtils.notUpdateMoreThanOne(stCallDao.updateByPrimaryKeySelective(stCall));
			logger.debug("updateStCall(StCallDto) - end - return"); //$NON-NLS-1$
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("员工电联统计信息更新信息错误！",e);
			throw new LssException(ResponseCode.failure,"员工电联统计信息更新信息错误！",e);
		}
	}

	

	@Override
	public StCallDto findStCall(
			StCallDto stCallDto) throws LssException {
		logger.debug("findStCall(FindStCall findStCall={}) - start", stCallDto); //$NON-NLS-1$

		AssertUtils.notNull(stCallDto);
		AssertUtils.notAllNull(stCallDto.getId(),"id不能为空");
		try {
			StCall stCall = stCallDao.selectByPrimaryKey(stCallDto.getId());
			if(stCall == null){
				return null;
				//throw new LssException(ErrorCode.ST_CALL_NOT_EXIST_ERROR,"员工电联统计信息不存在");
			}
			StCallDto findStCallReturn = new StCallDto();
			//find数据录入
			findStCallReturn.setId(stCall.getId());
			findStCallReturn.setAdminid(stCall.getAdminid());
			findStCallReturn.setAdminname(stCall.getAdminname());
			findStCallReturn.setStDate(stCall.getStDate());
			findStCallReturn.setCallCount(stCall.getCallCount());
			findStCallReturn.setDuration(stCall.getDuration());
			findStCallReturn.setAvgDuration(stCall.getAvgDuration());
			findStCallReturn.setCreateDate(stCall.getCreateDate());
			findStCallReturn.setUpdateDate(stCall.getUpdateDate());
			findStCallReturn.setRemark(stCall.getRemark());
			
			logger.debug("findStCall(StCallDto) - end - return value={}", findStCallReturn); //$NON-NLS-1$
			return findStCallReturn;
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找员工电联统计信息信息错误！",e);
			throw new LssException(ResponseCode.failure,"查找员工电联统计信息信息错误！",e);
		}


	}

	
	@Override
	public ReturnVo findStCallPage(
			FindStCallPage findStCallPage)
			throws LssException {
		logger.debug("findStCallPage(FindStCallPage findStCallPage={}) - start", findStCallPage); //$NON-NLS-1$

		AssertUtils.notNull(findStCallPage);
		List<StCallDto> returnList=null;
		int count = 0;
		try {
			returnList = stCallDao.findStCallPage(findStCallPage);
			count = stCallDao.findStCallPageCount(findStCallPage);
		}  catch (Exception e) {
			logger.error("员工电联统计信息不存在错误",e);
			throw new LssException(ResponseCode.failure,"员工电联统计信息不存在错误.！",e);
		}
		ReturnVo result = new ReturnVo();
		result.setPage(findStCallPage.getPage());
		result.setLimit(findStCallPage.getLimit());
		result.setObj(returnList);
		result.setTotal(count);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg); 
		
		logger.debug("findStCallPage(FindStCallPage) - end - return value={}", result); //$NON-NLS-1$
		return  result;
	}


	@Override
	public void batchAddDailyStCall(String batchNum) throws LssException {
		logger.info("批次{},1.开始统计每日电联数据....", batchNum);
		Date yesterday = DateUtils.addDays(new Date(), -1);
		String stDate = DateUtils.formatdateFormatDB(yesterday);
		int logNum = 0;
		logNum = stCallDao.batchAddDailyStCall(stDate);

		logger.info("批次{},2.统计每日电联数据操作已完成！统计日期:{},新增数据:{}",batchNum,stDate,logNum);
	} 

	@Override
	public ReturnVo findTodayStCallPage(FindStCallPage findStCallPage, LoginAdmin admin)
			throws LssException {
		logger.debug("findStCallPage(FindStCallPage findStCallPage={}) - start", findStCallPage); //$NON-NLS-1$
		AssertUtils.notNull(findStCallPage);
		// 查询条件
		if (ObjectUtil.isEmpty(admin.getRoles())) {
			 throw new LssException(ResponseCode.failure, "还未分配角色");
		}
	
		List<StCallDto> returnList=null;
		int count = 0;
		try {
			//如果是 电销组长查小组成员的，是电销员则只查自己的
			if (admin.getRoles().contains(9)) {// 电销组长
				Admin findAdmin = MapperManager.adminMapper.selectByPrimaryKey(admin.getAdminid());
				if (findAdmin.getOrgId() != null) {
					// 下属及同组员工
					List<Integer> adminids = MapperManager.adminMapper.selectByOrgId(findAdmin.getOrgId());
					if (adminids.size() == 0) {// 没下属，则给一个不存在的账号 避免拿了所有员工数据
						adminids.add(-1);
					}
					findStCallPage.setAdminids(adminids);
				} else {
					throw new LssException(ResponseCode.parameterError, "电销组长未分配组织");
				}
			} else if (admin.getRoles().contains(3)) {//电销员
				List<Integer> adminids =new ArrayList<Integer>();
				adminids.add(admin.getAdminid());
				findStCallPage.setAdminids(adminids);
			}
			String stDateToday = DateUtils.formatdateFormatDB(new Date());
			findStCallPage.setStDate(stDateToday); //统计今天的
			
			returnList = stCallDao.findTodayStCallPage(findStCallPage);
			count = stCallDao.findTodayStCallCount(findStCallPage);
		}  catch (Exception e) {
			logger.error("员工电联统计信息不存在错误",e);
			throw new LssException(ResponseCode.failure,"员工电联统计信息不存在错误.！",e);
		}
		ReturnVo result = new ReturnVo();
		result.setPage(findStCallPage.getPage());
		result.setLimit(findStCallPage.getLimit());
		result.setObj(returnList);
		result.setTotal(count);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg); 
		
		logger.debug("findStCallPage(FindStCallPage) - end - return value={}", result); //$NON-NLS-1$
		return  result;
	}

	
}
