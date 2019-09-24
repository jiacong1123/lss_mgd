package com.lss.test;

import java.util.Date;
import java.util.List;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lss.admin.service.IOrgService;
import com.lss.core.dto.FindOrgPage;
import com.lss.core.dto.OrgDto;
import com.lss.core.exception.LssException;
import com.lss.core.vo.ReturnVo;

 
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
public class OrgServiceImplTest extends SpringTestCase{

	@Resource
	IOrgService orgService;



	/**
	 * 
	 *
	 * 方法说明：添加机构组织信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addOrg() throws LssException{
		OrgDto orgDto = new OrgDto();
		//add数据录入
//		orgDto.setId(1);
		orgDto.setOrgName("深圳乐莎莎科技有限公司");
		orgDto.setParentId(0);
		orgDto.setParentIds(",0,");
		orgDto.setGrade(null);
		orgDto.setRemark("顶级机构");
		orgDto.setIndexNo(0);
		orgDto.setCreateDate(new Date());
		orgDto.setUpdateDate(new Date());
		
		orgService.addOrg(orgDto);
		System.out.println("新增成功：ID="+orgDto.getId());
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改机构组织信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateOrg() throws LssException{
		OrgDto orgDto = new OrgDto();
		//update数据录入
		orgDto.setId(1);
		orgDto.setOrgName("OrgName");
		orgDto.setParentId(1);
		orgDto.setParentIds("ParentIds");
		orgDto.setGrade("Grade");
		orgDto.setRemark("Remark");
		orgDto.setIndexNo(1);
		orgDto.setCreateDate(new Date());
		orgDto.setUpdateDate(new Date());

		orgService.updateOrg(orgDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找机构组织信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findOrg() throws LssException{
		OrgDto findOrg = new OrgDto();
		findOrg.setId(1);
		orgService.findOrg(findOrg);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找机构组织信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findOrgPage() throws LssException{
		FindOrgPage findOrgPage = new FindOrgPage();
		ReturnVo page = orgService.findOrgPage(findOrgPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找机构组织信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findOrgs() throws LssException{
		FindOrgPage findOrgPage = new FindOrgPage();
		List<OrgDto> page = orgService.findOrgs(findOrgPage);
		Assert.assertNotNull(page);
		
	}
	
}
