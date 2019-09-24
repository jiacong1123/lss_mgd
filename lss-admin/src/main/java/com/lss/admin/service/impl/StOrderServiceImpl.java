package com.lss.admin.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lss.admin.service.IStOrderService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.dao.IStOrderDao;
import com.lss.core.dto.FindStOrderPage;
import com.lss.core.dto.StOrderDto;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.Admin;
import com.lss.core.pojo.StOrder;
import com.lss.core.util.AssertUtils;
import com.lss.core.util.DateUtils;
import com.lss.core.util.ObjectUtil;
import com.lss.core.vo.ReturnVo;
import com.lss.core.vo.admin.LoginAdmin;
import com.lss.core.vo.admin.params.AdminParams;

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
 *         CreateDate: 2019.02.19
 */
@Service
public class StOrderServiceImpl implements IStOrderService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(StOrderServiceImpl.class);

	@Resource
	private IStOrderDao stOrderDao;

	@Override
	public void addStOrder(StOrderDto stOrderDto) throws LssException {
		logger.debug("addStOrder(AddStOrder addStOrder={}) - start", stOrderDto);

		AssertUtils.notNull(stOrderDto);
		try {
			StOrder stOrder = new StOrder();
			// add数据录入
			stOrder.setId(stOrderDto.getId());
			stOrder.setAdminid(stOrderDto.getAdminid());
			stOrder.setAdminname(stOrderDto.getAdminname());
			stOrder.setStDate(stOrderDto.getStDate());
			stOrder.setAllotUserCount(stOrderDto.getAllotUserCount());
			stOrder.setReserveUserCount(stOrderDto.getReserveUserCount());
			stOrder.setHisReserveCount(stOrderDto.getHisReserveCount());
			stOrder.setAllReserveCount(stOrderDto.getAllReserveCount());
			stOrder.setMonReserveRate(stOrderDto.getMonReserveRate());
			stOrder.setAllReserveRate(stOrderDto.getAllReserveRate());
			stOrder.setBigDealCount(stOrderDto.getBigDealCount());
			stOrder.setBigDealRate(stOrderDto.getBigDealRate());
			stOrder.setAllDealAmt(stOrderDto.getAllDealAmt());
			stOrder.setCreateDate(stOrderDto.getCreateDate());
			stOrder.setUpdateDate(stOrderDto.getUpdateDate());
			stOrder.setRemark(stOrderDto.getRemark());
			stOrderDao.insertSelective(stOrder);
			logger.debug("addStOrder(StOrderDto) - end - return");
		} catch (LssException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增员工工单月成交统计信息错误！", e);
			throw new LssException(ResponseCode.failure, "新增员工工单月成交统计信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询员工工单月成交统计信息
	 *
	 * @param findStOrderPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<StOrderDto> findStOrders(FindStOrderPage findStOrderPage) throws LssException {
		AssertUtils.notNull(findStOrderPage);
		List<StOrderDto> returnList = null;
		try {
			returnList = stOrderDao.findStOrders(findStOrderPage);
		} catch (Exception e) {
			logger.error("查找员工工单月成交统计信息信息错误！", e);
			throw new LssException(ResponseCode.failure, "员工工单月成交统计信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateStOrder(StOrderDto stOrderDto) throws LssException {
		logger.debug("updateStOrder(StOrderDto stOrderDto={}) - start", stOrderDto); //$NON-NLS-1$

		AssertUtils.notNull(stOrderDto);
		AssertUtils.notNullAndEmpty(stOrderDto.getId(), "id不能为空");
		try {
			StOrder stOrder = new StOrder();
			// update数据录入
			stOrder.setId(stOrderDto.getId());
			stOrder.setAdminid(stOrderDto.getAdminid());
			stOrder.setAdminname(stOrderDto.getAdminname());
			stOrder.setStDate(stOrderDto.getStDate());
			stOrder.setAllotUserCount(stOrderDto.getAllotUserCount());
			stOrder.setReserveUserCount(stOrderDto.getReserveUserCount());
			stOrder.setHisReserveCount(stOrderDto.getHisReserveCount());
			stOrder.setAllReserveCount(stOrderDto.getAllReserveCount());
			stOrder.setMonReserveRate(stOrderDto.getMonReserveRate());
			stOrder.setAllReserveRate(stOrderDto.getAllReserveRate());
			stOrder.setBigDealCount(stOrderDto.getBigDealCount());
			stOrder.setBigDealRate(stOrderDto.getBigDealRate());
			stOrder.setAllDealAmt(stOrderDto.getAllDealAmt());
			stOrder.setCreateDate(stOrderDto.getCreateDate());
			stOrder.setUpdateDate(stOrderDto.getUpdateDate());
			stOrder.setRemark(stOrderDto.getRemark());
			AssertUtils.notUpdateMoreThanOne(stOrderDao.updateByPrimaryKeySelective(stOrder));
			logger.debug("updateStOrder(StOrderDto) - end - return"); //$NON-NLS-1$
		} catch (LssException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("员工工单月成交统计信息更新信息错误！", e);
			throw new LssException(ResponseCode.failure, "员工工单月成交统计信息更新信息错误！", e);
		}
	}

	@Override
	public StOrderDto findStOrder(StOrderDto stOrderDto) throws LssException {
		logger.debug("findStOrder(FindStOrder findStOrder={}) - start", stOrderDto); //$NON-NLS-1$

		AssertUtils.notNull(stOrderDto);
		AssertUtils.notAllNull(stOrderDto.getId(), "id不能为空");
		try {
			StOrder stOrder = stOrderDao.selectByPrimaryKey(stOrderDto.getId());
			if (stOrder == null) {
				return null;
				// throw new LssException(ErrorCode.ST_ORDER_NOT_EXIST_ERROR,"员工工单月成交统计信息不存在");
			}
			StOrderDto findStOrderReturn = new StOrderDto();
			// find数据录入
			findStOrderReturn.setId(stOrder.getId());
			findStOrderReturn.setAdminid(stOrder.getAdminid());
			findStOrderReturn.setAdminname(stOrder.getAdminname());
			findStOrderReturn.setStDate(stOrder.getStDate());
			findStOrderReturn.setAllotUserCount(stOrder.getAllotUserCount());
			findStOrderReturn.setReserveUserCount(stOrder.getReserveUserCount());
			findStOrderReturn.setHisReserveCount(stOrder.getHisReserveCount());
			findStOrderReturn.setAllReserveCount(stOrder.getAllReserveCount());
			findStOrderReturn.setMonReserveRate(stOrder.getMonReserveRate());
			findStOrderReturn.setAllReserveRate(stOrder.getAllReserveRate());
			findStOrderReturn.setBigDealCount(stOrder.getBigDealCount());
			findStOrderReturn.setBigDealRate(stOrder.getBigDealRate());
			findStOrderReturn.setAllDealAmt(stOrder.getAllDealAmt());
			findStOrderReturn.setCreateDate(stOrder.getCreateDate());
			findStOrderReturn.setUpdateDate(stOrder.getUpdateDate());
			findStOrderReturn.setRemark(stOrder.getRemark());

			logger.debug("findStOrder(StOrderDto) - end - return value={}", findStOrderReturn); //$NON-NLS-1$
			return findStOrderReturn;
		} catch (LssException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找员工工单月成交统计信息信息错误！", e);
			throw new LssException(ResponseCode.failure, "查找员工工单月成交统计信息信息错误！", e);
		}

	}

	@Override
	public ReturnVo findStOrderPage(FindStOrderPage findStOrderPage) throws LssException {
		logger.debug("findStOrderPage(FindStOrderPage findStOrderPage={}) - start", findStOrderPage); //$NON-NLS-1$

		AssertUtils.notNull(findStOrderPage);
		List<StOrderDto> returnList = null;
		int count = 0;
		try {
			returnList = stOrderDao.findStOrderPage(findStOrderPage);
			count = stOrderDao.findStOrderPageCount(findStOrderPage);
		} catch (Exception e) {
			logger.error("员工工单月成交统计信息不存在错误", e);
			throw new LssException(ResponseCode.failure, "员工工单月成交统计信息不存在错误.！", e);
		}
		ReturnVo result = new ReturnVo();
		result.setPage(findStOrderPage.getPage());
		result.setLimit(findStOrderPage.getLimit());
		result.setObj(returnList);
		result.setTotal(count);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);

		logger.debug("findStOrderPage(FindStOrderPage) - end - return value={}", result); //$NON-NLS-1$
		return result;
	}

	@Override
	public void batchAddMonthStOrder(String batchNum) throws LssException {
		logger.info("批次{},1.开始统计员工工单月成交信息....", batchNum);
		Date preMonth = DateUtils.addMonths(new Date(), -1);
		String stDateStr = DateUtils.formatdateMonthFormatDB(preMonth);
		int logNum = 0;
		StOrderDto record = new StOrderDto();
		record.setStDateStr(stDateStr);
		record.setStDate(DateUtils.parseDateFormatDB(stDateStr + "01"));
		//0 当月电销人员 先给所有电销员 初始一条成交统计数据
		logNum = stOrderDao.batchAddStOrder(record);
		// 1.月客户数统计=当月分配的客户数 当月分配
//		logNum = stOrderDao.batchAddMonthAllotUserCount(record); 使用方案一 
		if (logNum > 0) {// 有新增的数据
			// 1.新客到店数=当月到店的客户
			stOrderDao.batchAddMonthAllotUserCount2(record);
			// 2.新客到店数=当月到店的客户
			stOrderDao.batchAddMonthReserveUserCount(record);
			// 3.总到店数（累计到店）
			stOrderDao.batchAddMonthAllReserveCount(record);
			// 4.历史到店数=累计到店-当月到店
			stOrderDao.batchAddMonthHisReserveCount(record);
			// 5.新客到店率=新客到店数÷月客户数
			stOrderDao.batchAddMonthMonReserveRate(record);
			// 6.综合到店率=总到店数÷月客户数（本月分配的客户）
			stOrderDao.batchAddMonthAllReserveRate(record);
			// 7.大项（正畸1、种植2、修复140）+实付金额大于1000+状态（已成交）
			stOrderDao.batchAddMonthBigDealCount(record);
			// 8.（大项）成交率 =大项目成交数÷总到店数
			stOrderDao.batchAddMonthBigDealRate(record);
			// 9.业绩完成=状态（已完成）+全部项目实收金额（大项目+其他）
			stOrderDao.batchAddMonthAllDealAmt(record);
		}
		logger.info("批次{},2.统计员工工单月成交信息操作已完成！统计日期:{},新增数据:{}", batchNum, stDateStr, logNum);
	}

	@Override
	public ReturnVo findStOrderCurrMonthPage(FindStOrderPage findStOrderPage, LoginAdmin admin) throws LssException {
		logger.debug("findStCallPage(FindStOrderPage findStOrderPage={}) - start", findStOrderPage); //$NON-NLS-1$
		AssertUtils.notNull(findStOrderPage);
		// 查询条件
		if (ObjectUtil.isEmpty(admin.getRoles())) {
			 throw new LssException(ResponseCode.failure, "还未分配角色");
		}
	
		List<StOrderDto> returnList=null;
		int count = 0;
		try {
			//零：查统计的电销员（如果是 电销组长查小组成员的，是电销员则只查自己的）
			List<Integer> adminids =null;//查询电销员ID
			if (admin.getRoles().contains(9)) {// 电销组长
				Admin findAdmin = MapperManager.adminMapper.selectByPrimaryKey(admin.getAdminid());
				if (findAdmin.getOrgId() != null) {
					// 下属及同组员工
					adminids = MapperManager.adminMapper.selectByOrgId(findAdmin.getOrgId());
					if (adminids.size() == 0) {// 没下属，则给一个不存在的账号 避免拿了所有员工数据
						adminids.add(-1);
					}
//					findStOrderPage.setAdminids(adminids);
				} else {
					throw new LssException(ResponseCode.parameterError, "电销组长未分配组织");
				}
			} else if (admin.getRoles().contains(3)) {//电销员
				adminids =new ArrayList<Integer>();
				adminids.add(admin.getAdminid());
//				findStOrderPage.setAdminids(adminids);
			}
		
			//零.0： 查出员工
			AdminParams params = new AdminParams();
			params.setAdminids(adminids);
			params.setAdminName(findStOrderPage.getAdminName());
			params.setPage(findStOrderPage.getPage()); 
			params.setLimit(findStOrderPage.getLimit());
			
			List<Admin> adminList = MapperManager.adminMapper.findAdminList(params);
			count = MapperManager.adminMapper.findAdminListCount(params);
			
			if(count>0) {//有相应的电销才查
				// 一 .返回的数据组装，各统计值默认设置0
				returnList=new ArrayList<StOrderDto>();
				List<Integer> findAdminIds=new ArrayList<Integer>();//当页查询的管理员
				for (Admin ele : adminList) {
					findAdminIds.add(ele.getAdminid());
					StOrderDto stOrder=new StOrderDto();
					stOrder.setAdminid(ele.getAdminid());
					stOrder.setAdminname(ele.getName());
				    /** 1.月客户数（当月分配的客户数）*/
					stOrder.setAllotUserCount(0);
				    /** 2.新到店数（当月到店的客户）*/
					stOrder.setReserveUserCount(0);
				    /** 3.历史到店数（累计到店-当月到店）*/
					stOrder.setHisReserveCount(0);
				    /** 4.总到店数:累计到店（新客到店数+历史到店数）*/
					stOrder.setAllReserveCount(0);
				    /** 5.新客到店率（新客到店数÷月客户数）*/
					stOrder.setMonReserveRate(BigDecimal.ZERO);
				    /** 6.综合到店率（总到店数÷月客户数（本月分配的客户））*/
					stOrder.setAllReserveRate(BigDecimal.ZERO);
				    /** 7.大项成交数（大项（正畸1、种植2、修复140）+实付金额大于1000+状态（已成交））*/
					stOrder.setBigDealCount(0);
				    /** 8.成交率（大项）（大项目成交数÷总到店数）*/
					stOrder.setBigDealRate(BigDecimal.ZERO);
				    /** 9.业绩完成（分为单位）（状态（已完成）+全部项目实收金额（大项目+其他））*/
					stOrder.setAllDealAmt(0L);
					returnList.add(stOrder);
				}
				
				// 二： 从DB实时查出当月的统计数据
				String stDateMonth = DateUtils.formatdateMonthFormatYYYY_MM(new Date());
				findStOrderPage.setStDateStr(stDateMonth); //统计这个月的
				findStOrderPage.setAdminids(findAdminIds); //指定电销员的
				//1.月客户数（当月分配的客户数）
				List<StOrderDto> aucList = stOrderDao.findMonthAllotUserCount(findStOrderPage);
				Map<Integer, StOrderDto> aucMap = aucList.stream().collect(Collectors.toMap(StOrderDto::getAdminid, (p) -> p));
				//2.新客到店数 (当月分配，当月到店的客户)
				List<StOrderDto> rucList = stOrderDao.findMonthReserveUserCount(findStOrderPage);
				Map<Integer, StOrderDto> rucMap = rucList.stream().collect(Collectors.toMap(StOrderDto::getAdminid, (p) -> p));
				//3.总到店数 （本月累计到店（新客到店数+历史到店数））
				List<StOrderDto> arcList = stOrderDao.findMonthAllReserveCount(findStOrderPage);
				Map<Integer, StOrderDto> arcMap = arcList.stream().collect(Collectors.toMap(StOrderDto::getAdminid, (p) -> p));
				//7.大项（正畸1、种植2、修复140）+实付金额大于1000+状态（已成交）
				List<StOrderDto> bdcList = stOrderDao.findMonthBigDealCount(findStOrderPage);
				Map<Integer, StOrderDto> bdcMap = bdcList.stream().collect(Collectors.toMap(StOrderDto::getAdminid, (p) -> p));
				//9.业绩完成=状态（已完成）+全部项目实收金额（大项目+其他）
				List<StOrderDto> adaList = stOrderDao.findMonthAllDealAmt(findStOrderPage);
				Map<Integer, StOrderDto> adaMap = adaList.stream().collect(Collectors.toMap(StOrderDto::getAdminid, (p) -> p));
				
				//三：设置到返回的数据中
				for (StOrderDto stOrderDto : returnList) {
					stOrderDto.setStDateStr(stDateMonth); 
					//1.月客户数（当月分配的客户数）
					if(aucMap!=null && aucMap.get(stOrderDto.getAdminid())!=null) {
						stOrderDto.setAllotUserCount(aucMap.get(stOrderDto.getAdminid()).getAllotUserCount());
					}
					//2.新客到店数 （当月分配，当月到店的客户）
					if(rucMap!=null && rucMap.get(stOrderDto.getAdminid())!=null) {
						stOrderDto.setReserveUserCount(rucMap.get(stOrderDto.getAdminid()).getReserveUserCount());
					}
					//3.总到店数 （本月累计到店（新客到店数+历史到店数））
					if(arcMap!=null && arcMap.get(stOrderDto.getAdminid())!=null) {
						stOrderDto.setAllReserveCount(arcMap.get(stOrderDto.getAdminid()).getAllReserveCount());
					}
					//7.大项 
					if(bdcMap!=null && bdcMap.get(stOrderDto.getAdminid())!=null) {
						stOrderDto.setBigDealCount(bdcMap.get(stOrderDto.getAdminid()).getBigDealCount());
					}
					//9.业绩完成
					if(adaMap!=null && adaMap.get(stOrderDto.getAdminid())!=null) {
						stOrderDto.setAllDealAmt(adaMap.get(stOrderDto.getAdminid()).getAllDealAmt());
					}
					/** 4.历史到店数（累计到店-当月到店） （当月之前分配，本月到店）*/
					stOrderDto.setHisReserveCount(stOrderDto.getAllReserveCount()-stOrderDto.getReserveUserCount());
					/** 5.新客到店率（新客到店数÷月客户数）*/
					if(stOrderDto.getAllotUserCount()>0) {
						stOrderDto.setMonReserveRate(new BigDecimal(stOrderDto.getReserveUserCount()).divide(new BigDecimal(stOrderDto.getAllotUserCount()), 4, RoundingMode.HALF_UP));
					    /** 6.综合到店率（总到店数÷月客户数（本月分配的客户））*/
						stOrderDto.setAllReserveRate(new BigDecimal(stOrderDto.getAllReserveCount()).divide(new BigDecimal(stOrderDto.getAllotUserCount()), 4, RoundingMode.HALF_UP));
					}
				    /** 8.成交率（大项）（大项目成交数÷总到店数）*/
					if(stOrderDto.getAllReserveCount()>0) {
						stOrderDto.setBigDealRate(new BigDecimal(stOrderDto.getBigDealCount()).divide(new BigDecimal(stOrderDto.getAllReserveCount()), 4, RoundingMode.HALF_UP));
					}
				}
			
			}
			
		}  catch (Exception e) {
			logger.error("员工单月成交信息不存在错误",e);
			throw new LssException(ResponseCode.failure,"员工单月成交信息不存在错误.！",e);
		}
		ReturnVo result = new ReturnVo();
		result.setPage(findStOrderPage.getPage());
		result.setLimit(findStOrderPage.getLimit());
		result.setObj(returnList);
		result.setTotal(count);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg); 
		
		logger.debug("findStCallPage(FindStCallPage) - end - return value={}", result); //$NON-NLS-1$
		return  result;
	}

}
