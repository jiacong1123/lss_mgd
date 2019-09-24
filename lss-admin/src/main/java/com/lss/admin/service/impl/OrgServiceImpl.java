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

import com.lss.admin.service.IOrgService;
import com.lss.core.constant.ResponseCode;
import com.lss.core.dao.IOrgDao;
import com.lss.core.dto.FindOrgPage;
import com.lss.core.dto.OrgDto;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.Org;
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
public class OrgServiceImpl implements IOrgService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(OrgServiceImpl.class);
	

	@Resource
	private IOrgDao orgDao;
	
	
	@Override
	public void addOrg(
			OrgDto orgDto) throws LssException {
		logger.debug("addOrg(AddOrg addOrg={}) - start", orgDto); 

		AssertUtils.notNull(orgDto);
		try {
			Org org = new Org();
			//add数据录入
			org.setId(orgDto.getId());
			org.setOrgName(orgDto.getOrgName());
			if (null != orgDto.getParentId() && null == orgDto.getParentIds()) {
				Org parentOrg = orgDao.selectByPrimaryKey(orgDto.getParentId());
				if (parentOrg != null) {
					org.setParentId(orgDto.getParentId());
					org.setParentIds(parentOrg.getParentIds() + parentOrg.getId() + ",");
				}else {
					throw new LssException(ResponseCode.failure,"上级机构不存在！");
				}
			}else {
				org.setParentId(orgDto.getParentId());
				org.setParentIds(orgDto.getParentIds());
			}
			
			org.setGrade(orgDto.getGrade());
			org.setRemark(orgDto.getRemark());
			if (null != orgDto.getIndexNo()) {
				org.setIndexNo(orgDto.getIndexNo());
			}else {
				org.setIndexNo(0);
			}
			org.setCreateDate(orgDto.getCreateDate());
//			org.setUpdateDate(orgDto.getUpdateDate());
			org.setCreateDate(new Date());
			orgDao.insertSelective(org);
			orgDto.setId(org.getId());
			logger.debug("addOrg(OrgDto) - end - return"); 
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增机构组织信息错误！",e);
			throw new LssException(ResponseCode.failure,"新增机构组织信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询机构组织信息
	 *
	 * @param findOrgPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<OrgDto>  findOrgs(FindOrgPage findOrgPage)throws LssException{
		AssertUtils.notNull(findOrgPage);
		List<OrgDto> returnList=null;
		try {
			returnList = orgDao.findOrgs(findOrgPage);
		} catch (Exception e) {
			logger.error("查找机构组织信息信息错误！", e);
			throw new LssException(ResponseCode.failure,"机构组织信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateOrg(
			OrgDto orgDto)
			throws LssException {
		logger.debug("updateOrg(OrgDto orgDto={}) - start", orgDto); //$NON-NLS-1$

		AssertUtils.notNull(orgDto);
		AssertUtils.notNullAndEmpty(orgDto.getId(),"id不能为空");
		try {
			Org org = new Org();
			//update数据录入
			org.setId(orgDto.getId());
			org.setOrgName(orgDto.getOrgName());
			
			if (null != orgDto.getParentId() && null == orgDto.getParentIds()) {
				Org findOrg = orgDao.selectByPrimaryKey(orgDto.getId());
				if (findOrg.getParentId() != orgDto.getParentId()) {//修改上级机构才操作
					Org parentOrg = orgDao.selectByPrimaryKey(orgDto.getParentId());
					if (parentOrg != null) {
						org.setParentId(orgDto.getParentId());
						org.setParentIds(parentOrg.getParentIds() + parentOrg.getId() + ",");
					} else {
						throw new LssException(ResponseCode.failure, "上级机构不存在！");
					}
				}
			}else {
				org.setParentId(orgDto.getParentId());
				org.setParentIds(orgDto.getParentIds());
			}
			org.setGrade(orgDto.getGrade());
			org.setRemark(orgDto.getRemark());
			org.setIndexNo(orgDto.getIndexNo());
//			org.setCreateDate(orgDto.getCreateDate());
			org.setUpdateDate(new Date());
			AssertUtils.notUpdateMoreThanOne(orgDao.updateByPrimaryKeySelective(org));
			logger.debug("updateOrg(OrgDto) - end - return"); //$NON-NLS-1$
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("机构组织信息更新信息错误！",e);
			throw new LssException(ResponseCode.failure,"机构组织信息更新信息错误！",e);
		}
	}

	

	@Override
	public OrgDto findOrg(
			OrgDto orgDto) throws LssException {
		logger.debug("findOrg(FindOrg findOrg={}) - start", orgDto); //$NON-NLS-1$

		AssertUtils.notNull(orgDto);
		AssertUtils.notAllNull(orgDto.getId(),"id不能为空");
		try {
			Org org = orgDao.selectByPrimaryKey(orgDto.getId());
			if(org == null){
				return null;
				//throw new LssException(ErrorCode.ORG_NOT_EXIST_ERROR,"机构组织信息不存在");
			}
			OrgDto findOrgReturn = new OrgDto();
			//find数据录入
			findOrgReturn.setId(org.getId());
			findOrgReturn.setOrgName(org.getOrgName());
			findOrgReturn.setParentId(org.getParentId());
			findOrgReturn.setParentIds(org.getParentIds());
			findOrgReturn.setGrade(org.getGrade());
			findOrgReturn.setRemark(org.getRemark());
			findOrgReturn.setIndexNo(org.getIndexNo());
			findOrgReturn.setCreateDate(org.getCreateDate());
			findOrgReturn.setUpdateDate(org.getUpdateDate());
			
			logger.debug("findOrg(OrgDto) - end - return value={}", findOrgReturn); //$NON-NLS-1$
			return findOrgReturn;
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找机构组织信息信息错误！",e);
			throw new LssException(ResponseCode.failure,"查找机构组织信息信息错误！",e);
		}


	}

	
	@Override
	public ReturnVo findOrgPage(
			FindOrgPage findOrgPage)
			throws LssException {
		logger.debug("findOrgPage(FindOrgPage findOrgPage={}) - start", findOrgPage); //$NON-NLS-1$

		AssertUtils.notNull(findOrgPage);
		List<OrgDto> returnList=null;
		int count = 0;
		try {
			returnList = orgDao.findOrgPage(findOrgPage);
			count = orgDao.findOrgPageCount(findOrgPage);
		}  catch (Exception e) {
			logger.error("机构组织信息不存在错误",e);
			throw new LssException(ResponseCode.failure,"机构组织信息不存在错误.！",e);
		}
		ReturnVo result = new ReturnVo();
		result.setPage(findOrgPage.getPage());
		result.setLimit(findOrgPage.getLimit());
		result.setObj(returnList);
		result.setTotal(count);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg); 

		logger.debug("findOrgPage(FindOrgPage) - end - return value={}", result); //$NON-NLS-1$
		return  result;
	} 


}
