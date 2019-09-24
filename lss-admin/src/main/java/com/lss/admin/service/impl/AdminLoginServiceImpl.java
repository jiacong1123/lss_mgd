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

import com.lss.admin.service.IAdminLoginService;
import com.lss.core.constant.ResponseCode;
import com.lss.core.dao.IAdminLoginDao;
import com.lss.core.dto.AdminLoginDto;
import com.lss.core.dto.FindAdminLoginPage;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.AdminLogin;
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
public class AdminLoginServiceImpl implements IAdminLoginService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(AdminLoginServiceImpl.class);
	

	@Resource
	private IAdminLoginDao adminLoginDao;
	
	
	@Override
	public void addAdminLogin(
			AdminLoginDto adminLoginDto) throws LssException {
		logger.debug("addAdminLogin(AddAdminLogin addAdminLogin={}) - start", adminLoginDto); 

		AssertUtils.notNull(adminLoginDto);
		try {
			AdminLogin adminLogin = new AdminLogin();
			//add数据录入
			adminLogin.setId(adminLoginDto.getId());
			adminLogin.setAdminid(adminLoginDto.getAdminid());
			adminLogin.setType(adminLoginDto.getType());
			adminLogin.setOpenid(adminLoginDto.getOpenid());
			adminLogin.setCreateDate(new Date());
			adminLogin.setUpdateDate(new Date());
			adminLoginDao.insertSelective(adminLogin);
			logger.debug("addAdminLogin(AdminLoginDto) - end - return"); 
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增管理员第三方绑定信息错误！",e);
			throw new LssException(ResponseCode.failure,"新增管理员第三方绑定信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询管理员第三方绑定信息
	 *
	 * @param findAdminLoginPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<AdminLoginDto>  findAdminLogins(FindAdminLoginPage findAdminLoginPage)throws LssException{
		AssertUtils.notNull(findAdminLoginPage);
		List<AdminLoginDto> returnList=null;
		try {
			returnList = adminLoginDao.findAdminLogins(findAdminLoginPage);
		} catch (Exception e) {
			logger.error("查找管理员第三方绑定信息错误！", e);
			throw new LssException(ResponseCode.failure,"管理员第三方绑定信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateAdminLogin(
			AdminLoginDto adminLoginDto)
			throws LssException {
		logger.debug("updateAdminLogin(AdminLoginDto adminLoginDto={}) - start", adminLoginDto); //$NON-NLS-1$

		AssertUtils.notNull(adminLoginDto);
		AssertUtils.notNullAndEmpty(adminLoginDto.getId(),"id不能为空");
		try {
			AdminLogin adminLogin = new AdminLogin();
			//update数据录入
			adminLogin.setId(adminLoginDto.getId());
			adminLogin.setAdminid(adminLoginDto.getAdminid());
			adminLogin.setType(adminLoginDto.getType());
			adminLogin.setOpenid(adminLoginDto.getOpenid());
//			adminLogin.setCreateDate(adminLoginDto.getCreateDate());
			adminLogin.setUpdateDate(new Date());
			AssertUtils.notUpdateMoreThanOne(adminLoginDao.updateByPrimaryKeySelective(adminLogin));
			logger.debug("updateAdminLogin(AdminLoginDto) - end - return"); //$NON-NLS-1$
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("管理员第三方绑定信息更新信息错误！",e);
			throw new LssException(ResponseCode.failure,"管理员第三方绑定信息更新信息错误！",e);
		}
	}

	

	@Override
	public AdminLoginDto findAdminLogin(
			AdminLoginDto adminLoginDto) throws LssException {
		logger.debug("findAdminLogin(FindAdminLogin findAdminLogin={}) - start", adminLoginDto); //$NON-NLS-1$

		AssertUtils.notNull(adminLoginDto);
		AssertUtils.notAllNull(adminLoginDto.getId(),"id不能为空");
		try {
			AdminLogin adminLogin = adminLoginDao.selectByPrimaryKey(adminLoginDto.getId());
			if(adminLogin == null){
				return null;
				//throw new LssException(ErrorCode.ADMIN_LOGIN_NOT_EXIST_ERROR,"管理员第三方绑定信息不存在");
			}
			AdminLoginDto findAdminLoginReturn = new AdminLoginDto();
			//find数据录入
			findAdminLoginReturn.setId(adminLogin.getId());
			findAdminLoginReturn.setAdminid(adminLogin.getAdminid());
			findAdminLoginReturn.setType(adminLogin.getType());
			findAdminLoginReturn.setOpenid(adminLogin.getOpenid());
			findAdminLoginReturn.setCreateDate(adminLogin.getCreateDate());
			findAdminLoginReturn.setUpdateDate(adminLogin.getUpdateDate());
			
			logger.debug("findAdminLogin(AdminLoginDto) - end - return value={}", findAdminLoginReturn); //$NON-NLS-1$
			return findAdminLoginReturn;
		}catch (LssException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找管理员第三方绑定信息错误！",e);
			throw new LssException(ResponseCode.failure,"查找管理员第三方绑定信息错误！",e);
		}


	}

	
	@Override
	public ReturnVo findAdminLoginPage(
			FindAdminLoginPage findAdminLoginPage)
			throws LssException {
		logger.debug("findAdminLoginPage(FindAdminLoginPage findAdminLoginPage={}) - start", findAdminLoginPage); //$NON-NLS-1$

		AssertUtils.notNull(findAdminLoginPage);
		List<AdminLoginDto> returnList=null;
		int count = 0;
		try {
			returnList = adminLoginDao.findAdminLoginPage(findAdminLoginPage);
			count = adminLoginDao.findAdminLoginPageCount(findAdminLoginPage);
		}  catch (Exception e) {
			logger.error("管理员第三方绑定信息不存在错误",e);
			throw new LssException(ResponseCode.failure,"管理员第三方绑定信息不存在错误.！",e);
		}
		ReturnVo result = new ReturnVo();
		result.setPage(findAdminLoginPage.getPage());
		result.setLimit(findAdminLoginPage.getLimit());
		result.setObj(returnList);
		result.setTotal(count);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg); 

		logger.debug("findAdminLoginPage(FindAdminLoginPage) - end - return value={}", result); //$NON-NLS-1$
		return  result;
	}


	@Override
	public AdminLoginDto findAdminLoginByOpenid(AdminLoginDto adminLoginDto) throws LssException {
		AssertUtils.notNull(adminLoginDto);
		AssertUtils.notNull(adminLoginDto.getOpenid(),"openId不能为空");
		AssertUtils.notNull(adminLoginDto.getType(),"第三方登录类型不能为空");
		AdminLoginDto adminLogin = null;
		List<AdminLoginDto> returnList = null;
		
		try {
			FindAdminLoginPage findAdminLoginPage=new FindAdminLoginPage();
			findAdminLoginPage.setType(adminLoginDto.getType());
			findAdminLoginPage.setOpenid(adminLoginDto.getOpenid());
			
			returnList = adminLoginDao.findAdminLogins(findAdminLoginPage);
			if(returnList!=null && returnList.size()>0) {
				adminLogin = returnList.get(0);
			}
		} catch (Exception e) {
			logger.error("查找管理员第三方绑定信息错误！", e);
			throw new LssException(ResponseCode.failure,"管理员第三方绑定信息不存在");
		}
		
		return adminLogin;
	
	} 


}
