package com.lss.admin.service;

import java.util.List;

import com.lss.core.dto.FindOrgPage;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lss.core.dto.OrgDto;
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
public interface IOrgService {
	
	/**rootid*/
	public static final Integer ROOT_ID = 0;

	/**
	 * 
	 *
	 * 方法说明：添加机构组织信息
	 *
	 * @param orgDto
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addOrg(OrgDto orgDto) throws LssException;
	
	/**
	 * 
	 *
	 * 方法说明：查找机构组织信息
	 *
	 * @param findOrg
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public OrgDto findOrg(OrgDto orgDto) throws LssException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询机构组织信息
	 *
	 * @param findOrgPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<OrgDto>  findOrgs(FindOrgPage findOrgPage)throws LssException;

	/**
	 * 
	 *
	 * 方法说明：修改机构组织信息
	 *
	 * @param updateOrg
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateOrg(OrgDto orgDto)throws LssException;

	/**
	 * 
	 *
	 * 方法说明：分页查询机构组织信息
	 *
	 * @param findOrgPage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ReturnVo findOrgPage(FindOrgPage findOrgPage) throws LssException;
	

}
