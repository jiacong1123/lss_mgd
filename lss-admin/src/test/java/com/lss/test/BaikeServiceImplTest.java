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

import com.lss.admin.service.IBaikeService;
import com.lss.core.dto.BaikeDto;
import com.lss.core.dto.FindBaikePage;
import com.lss.core.exception.LssException;
import com.lss.core.vo.ReturnVo;

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
public class BaikeServiceImplTest extends SpringTestCase{

	@Resource
	IBaikeService baikeService;



	/**
	 * 
	 *
	 * 方法说明：添加口腔百科信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addBaike() throws LssException{
		BaikeDto baikeDto = new BaikeDto();
		//add数据录入
		baikeDto.setTitle("Title");
		baikeDto.setSubtitle("Subtitle");
		baikeDto.setImage("Image");
		baikeDto.setType(1);
		baikeDto.setStatus(1);
//		baikeDto.setCreatetime("Createtime");
		baikeDto.setClickvolume(0);
		baikeDto.setSmallIcon("SmallIcon");
		baikeDto.setUrl("Url");
		baikeDto.setContent("Content");
		
		baikeService.addBaike(baikeDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改口腔百科信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateBaike() throws LssException{
		BaikeDto baikeDto = new BaikeDto();
		//update数据录入
		baikeDto.setId(1);
		baikeDto.setTitle("Title");
		baikeDto.setSubtitle("Subtitle");
		baikeDto.setImage("Image");
		baikeDto.setType(1);
		baikeDto.setStatus(1);
		baikeDto.setClickvolume(2);
		baikeDto.setSmallIcon("SmallIcon");
		baikeDto.setUrl("Url");
		baikeDto.setContent("Content");

		baikeService.updateBaike(baikeDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找口腔百科信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBaike() throws LssException{
		BaikeDto findBaike = new BaikeDto();
		findBaike.setId(1);
		baikeService.findBaike(findBaike);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找口腔百科信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBaikePage() throws LssException{
		FindBaikePage findBaikePage = new FindBaikePage();
		ReturnVo page = baikeService.findBaikePage(findBaikePage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找口腔百科信息
	 *
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBaikes() throws LssException{
		FindBaikePage findBaikePage = new FindBaikePage();
		List<BaikeDto> page = baikeService.findBaikes(findBaikePage);
		Assert.assertNotNull(page);
		
	}
	
}
