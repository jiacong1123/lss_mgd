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

import com.lss.admin.service.IOrderPayService;
import com.lss.core.base.MapperManager;
import com.lss.core.constant.ResponseCode;
import com.lss.core.dao.IOrderPayDao;
import com.lss.core.dto.FindOrderPayPage;
import com.lss.core.dto.OrderPayDto;
import com.lss.core.emus.YESNO;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.OrderPay;
import com.lss.core.pojo.WorkOrder;
import com.lss.core.util.AssertUtils;
import com.lss.core.util.ObjectUtil;
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
 * CreateDate: 2019.08.12
 */
@Service
public class OrderPayServiceImpl implements IOrderPayService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(OrderPayServiceImpl.class);
	

	@Resource
	private IOrderPayDao orderPayDao;
	
	
	@Override
	public void addOrderPay(
			OrderPayDto orderPayDto) throws LssException {
		logger.debug("addOrderPay(AddOrderPay addOrderPay={}) - start", orderPayDto); 

		AssertUtils.notNull(orderPayDto);
		try {
			OrderPay orderPay = new OrderPay();
			//add数据录入
//			orderPay.setId(orderPayDto.getId());
			orderPay.setAdminid(orderPayDto.getAdminid());
			orderPay.setOrderno(orderPayDto.getOrderno());
			orderPay.setReceivablesamt(orderPayDto.getReceivablesamt());
			orderPay.setDebtamt(orderPayDto.getDebtamt());
			orderPay.setAmount(orderPayDto.getAmount());
			orderPay.setPayTime(orderPayDto.getPayTime());
			orderPay.setCreateDate(new Date());
			orderPay.setUpdateDate(new Date());
			orderPay.setRemark(orderPayDto.getRemark());
			orderPay.setFirstPay(orderPayDto.getFirstPay());
			orderPay.setAdminname(orderPayDto.getAdminname());
			orderPayDao.insertSelective(orderPay);
			logger.debug("addOrderPay(OrderPayDto) - end - return"); 
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增工单收费记录信息错误！",e);
			throw new LssException(ResponseCode.failure,"新增工单收费记录信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询工单收费记录信息
	 *
	 * @param findOrderPayPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<OrderPayDto>  findOrderPays(FindOrderPayPage findOrderPayPage)throws LssException{
		AssertUtils.notNull(findOrderPayPage);
		List<OrderPayDto> returnList=null;
		try {
			returnList = orderPayDao.findOrderPays(findOrderPayPage);
		} catch (Exception e) {
			logger.error("查找工单收费记录信息信息错误！", e);
			throw new LssException(ResponseCode.failure,"工单收费记录信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateOrderPay(
			OrderPayDto orderPayDto)
			throws LssException {
		logger.debug("updateOrderPay(OrderPayDto orderPayDto={}) - start", orderPayDto); //$NON-NLS-1$

		AssertUtils.notNull(orderPayDto);
		AssertUtils.notNullAndEmpty(orderPayDto.getId(),"id不能为空");
		try {
			OrderPay orderPay = new OrderPay();
			//update数据录入
			orderPay.setId(orderPayDto.getId());
			orderPay.setAdminid(orderPayDto.getAdminid());
			orderPay.setOrderno(orderPayDto.getOrderno());
			orderPay.setReceivablesamt(orderPayDto.getReceivablesamt());
			orderPay.setDebtamt(orderPayDto.getDebtamt());
			orderPay.setAmount(orderPayDto.getAmount());
			orderPay.setPayTime(orderPayDto.getPayTime());
//			orderPay.setCreateDate(orderPayDto.getCreateDate());
			orderPay.setUpdateDate(new Date());
			orderPay.setRemark(orderPayDto.getRemark());
			orderPay.setFirstPay(orderPayDto.getFirstPay());
			orderPay.setAdminname(orderPayDto.getAdminname());
			AssertUtils.notUpdateMoreThanOne(orderPayDao.updateByPrimaryKeySelective(orderPay));
			logger.debug("updateOrderPay(OrderPayDto) - end - return"); //$NON-NLS-1$
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("工单收费记录信息更新信息错误！",e);
			throw new LssException(ResponseCode.failure,"工单收费记录信息更新信息错误！",e);
		}
	}

	

	@Override
	public OrderPayDto findOrderPay(
			OrderPayDto orderPayDto) throws LssException {
		logger.debug("findOrderPay(FindOrderPay findOrderPay={}) - start", orderPayDto); //$NON-NLS-1$

		AssertUtils.notNull(orderPayDto);
		AssertUtils.notAllNull(orderPayDto.getId(),"id不能为空");
		try {
			OrderPay orderPay = orderPayDao.selectByPrimaryKey(orderPayDto.getId());
			if(orderPay == null){
				return null;
				//throw new LssException(ErrorCode.ORDER_PAY_NOT_EXIST_ERROR,"工单收费记录信息不存在");
			}
			OrderPayDto findOrderPayReturn = new OrderPayDto();
			//find数据录入
			findOrderPayReturn.setId(orderPay.getId());
			findOrderPayReturn.setAdminid(orderPay.getAdminid());
			findOrderPayReturn.setOrderno(orderPay.getOrderno());
			findOrderPayReturn.setReceivablesamt(orderPay.getReceivablesamt());
			findOrderPayReturn.setDebtamt(orderPay.getDebtamt());
			findOrderPayReturn.setAmount(orderPay.getAmount());
			findOrderPayReturn.setPayTime(orderPay.getPayTime());
			findOrderPayReturn.setCreateDate(orderPay.getCreateDate());
			findOrderPayReturn.setUpdateDate(orderPay.getUpdateDate());
			findOrderPayReturn.setRemark(orderPay.getRemark());
			findOrderPayReturn.setFirstPay(orderPay.getFirstPay());
			findOrderPayReturn.setAdminname(orderPay.getAdminname());
			logger.debug("findOrderPay(OrderPayDto) - end - return value={}", findOrderPayReturn); //$NON-NLS-1$
			return findOrderPayReturn;
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找工单收费记录信息信息错误！",e);
			throw new LssException(ResponseCode.failure,"查找工单收费记录信息信息错误！",e);
		}


	}

	
	@Override
	public ReturnVo findOrderPayPage(
			FindOrderPayPage findOrderPayPage)
			throws LssException {
		logger.debug("findOrderPayPage(FindOrderPayPage findOrderPayPage={}) - start", findOrderPayPage); //$NON-NLS-1$

		AssertUtils.notNull(findOrderPayPage);
		List<OrderPayDto> returnList=null;
		int count = 0;
		try {
			returnList = orderPayDao.findOrderPayPage(findOrderPayPage);
			count = orderPayDao.findOrderPayPageCount(findOrderPayPage);
		}  catch (Exception e) {
			logger.error("工单收费记录信息不存在错误",e);
			throw new LssException(ResponseCode.failure,"工单收费记录信息不存在错误.！",e);
		}
		ReturnVo result = new ReturnVo();
		result.setPage(findOrderPayPage.getPage());
		result.setLimit(findOrderPayPage.getLimit());
		result.setObj(returnList);
		result.setTotal(count);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg); 


		logger.debug("findOrderPayPage(FindOrderPayPage) - end - return value={}", result); //$NON-NLS-1$
		return  result;
	}


	@Override
	public void workorderPay(OrderPayDto params) throws LssException {
		if(ObjectUtil.isEmpty(params.getAmount())) {
			throw new LssException(ResponseCode.parameterError, "收费金额为空");
		}
		if(ObjectUtil.isEmpty(params.getReceivablesamt())) {
			throw new LssException(ResponseCode.parameterError, "应收金额为空");
		}
		if(ObjectUtil.isEmpty(params.getReceivablesamt())) {
			throw new LssException(ResponseCode.parameterError, "欠收金额为空");
		}
		if(ObjectUtil.isEmpty(params.getPayTime())) {
			throw new LssException(ResponseCode.parameterError, "收费时间为空");
		}
		if(ObjectUtil.isEmpty(params.getOrderno())) {
			throw new LssException(ResponseCode.parameterError, "工单号为空");
		}
		//1 .找到工单 计算
		WorkOrder findWorkOrder = MapperManager.workOrderMapper.selectByPrimaryKey(params.getOrderno());
		WorkOrder updateWorkOrder = new WorkOrder();
		updateWorkOrder.setOrderno(params.getOrderno());
		if (findWorkOrder.getReceivablesamt() != null && findWorkOrder.getAmount() != null) {//应收金额非空则代表不是第一次登记收费
			updateWorkOrder.setDebtamt(params.getDebtamt());//欠款更新为最新欠款
			updateWorkOrder.setAmount(params.getAmount() + findWorkOrder.getAmount());//工单累计收费
//			findWorkOrder.setReceivablesamt(params.getReceivablesamt());应收不变
			params.setFirstPay(YESNO.N.name());
		}else {//第一次登记收费
			updateWorkOrder.setAmount(params.getAmount());//收费
			updateWorkOrder.setDebtamt(params.getDebtamt());//欠费
			updateWorkOrder.setReceivablesamt(params.getReceivablesamt());//应收等于第一次登记时费用
			params.setFirstPay(YESNO.Y.name());
			updateWorkOrder.setPayTime(params.getPayTime());
		}
		MapperManager.workOrderMapper.updateByPrimaryKeySelective(updateWorkOrder);
		
		//2.添加记录
		OrderPay orderPay = new OrderPay();
		orderPay.setAdminid(params.getAdminid());
		orderPay.setOrderno(params.getOrderno());
		orderPay.setReceivablesamt(params.getReceivablesamt());
		orderPay.setDebtamt(params.getDebtamt());
		orderPay.setAmount(params.getAmount());
		orderPay.setPayTime(params.getPayTime());
		orderPay.setCreateDate(new Date());
		orderPay.setUpdateDate(new Date());
		orderPay.setRemark(params.getRemark());
		orderPay.setFirstPay(params.getFirstPay());
		orderPay.setAdminname(params.getAdminname());
		orderPayDao.insertSelective(orderPay);
	} 


}
