package com.lss.test;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lss.admin.service.IAdminLoginService;
import com.lss.core.dto.AdminLoginDto;
import com.lss.core.dto.FindAdminLoginPage;
import com.lss.core.exception.LssException;
import com.lss.core.vo.ReturnVo;

import java.util.Date;
import java.util.List;

 
/**
 * 类说明：测试类
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
public class AdminLoginServiceImplTest extends SpringTestCase{

	@Resource
	IAdminLoginService adminLoginService;



	/**
	 * 
	 *
	 * 方法说明：添加管理员第三方绑定信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addAdminLogin() throws LssException{
		AdminLoginDto adminLoginDto = new AdminLoginDto();
		//add数据录入
//		adminLoginDto.setId(1);
		adminLoginDto.setAdminid(1);
		adminLoginDto.setType("Type");
		adminLoginDto.setOpenid("Openid");
		adminLoginDto.setCreateDate(new Date());
		adminLoginDto.setUpdateDate(new Date());
		
		adminLoginService.addAdminLogin(adminLoginDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改管理员第三方绑定信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateAdminLogin() throws LssException{
		AdminLoginDto adminLoginDto = new AdminLoginDto();
		//update数据录入
		adminLoginDto.setId(1);
		adminLoginDto.setAdminid(1);
		adminLoginDto.setType("Type");
		adminLoginDto.setOpenid("Openid");
		adminLoginDto.setCreateDate(new Date());
		adminLoginDto.setUpdateDate(new Date());

		adminLoginService.updateAdminLogin(adminLoginDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找管理员第三方绑定信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findAdminLogin() throws LssException{
		AdminLoginDto findAdminLogin = new AdminLoginDto();
		findAdminLogin.setId(1);
		adminLoginService.findAdminLogin(findAdminLogin);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找管理员第三方绑定信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findAdminLoginPage() throws LssException{
		FindAdminLoginPage findAdminLoginPage = new FindAdminLoginPage();
		ReturnVo page = adminLoginService.findAdminLoginPage(findAdminLoginPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找管理员第三方绑定信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findAdminLogins() throws LssException{
		FindAdminLoginPage findAdminLoginPage = new FindAdminLoginPage();
		List<AdminLoginDto> page = adminLoginService.findAdminLogins(findAdminLoginPage);
		Assert.assertNotNull(page);
		
	}
	
}
