package com.lss.admin.service;

import java.util.List;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lss.core.dto.AdminLoginDto;
import com.lss.core.dto.FindAdminLoginPage;
import com.lss.core.exception.LssException;
import com.lss.core.vo.ReturnVo;
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
public interface IAdminLoginService {
	
	/**
	 * 
	 *
	 * 方法说明：添加管理员第三方绑定信息
	 *
	 * @param adminLoginDto
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addAdminLogin(AdminLoginDto adminLoginDto) throws LssException;
	
	/**
	 * 
	 *
	 * 方法说明：查找管理员第三方绑定信息
	 *
	 * @param findAdminLogin
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public AdminLoginDto findAdminLogin(AdminLoginDto adminLoginDto) throws LssException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询管理员第三方绑定信息
	 *
	 * @param findAdminLoginPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<AdminLoginDto>  findAdminLogins(FindAdminLoginPage findAdminLoginPage)throws LssException;

	/**
	 * 
	 *
	 * 方法说明：修改管理员第三方绑定信息
	 *
	 * @param updateAdminLogin
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateAdminLogin(AdminLoginDto adminLoginDto)throws LssException;

	/**
	 * 
	 *
	 * 方法说明：分页查询管理员第三方绑定信息
	 *
	 * @param findAdminLoginPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ReturnVo findAdminLoginPage(FindAdminLoginPage findAdminLoginPage) throws LssException;
	
	/**
	 * 
	 *
	 * 方法说明：查找管理员第三方绑定信息
	 * @param type 第三方类型 非空
	 * @param openid 第三方openid 非空
	 * @param adminLoginDto
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019年6月19日
	 *
	 */
	public AdminLoginDto findAdminLoginByOpenid(AdminLoginDto adminLoginDto) throws LssException;
}
