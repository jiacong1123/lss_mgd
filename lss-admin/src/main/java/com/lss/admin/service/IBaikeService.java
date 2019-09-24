package com.lss.admin.service;

import java.util.List;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lss.core.dto.BaikeDto;
import com.lss.core.dto.FindBaikePage;
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
 *         CreateDate: 2019.02.19
 */
public interface IBaikeService {

	/**
	 * 
	 *
	 * 方法说明：添加口腔百科信息
	 *
	 * @param baikeDto
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-06-27
	 *
	 */
	public void addBaike(BaikeDto baikeDto) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：查找口腔百科信息
	 *
	 * @param findBaike
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-06-27
	 *
	 */
	public BaikeDto findBaike(BaikeDto baikeDto) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询口腔百科信息
	 *
	 * @param findBaikePage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-06-27
	 *
	 */
	public List<BaikeDto> findBaikes(FindBaikePage findBaikePage) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：修改口腔百科信息
	 *
	 * @param updateBaike
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-06-27
	 *
	 */
	public void updateBaike(BaikeDto baikeDto) throws LssException;

	/**
	 * 
	 *
	 * 方法说明：分页查询口腔百科信息
	 *
	 * @param findBaikePage
	 * @return
	 * @throws LssException
	 *
	 * @author lhy CreateDate: 2019-06-27
	 *
	 */
	public ReturnVo findBaikePage(FindBaikePage findBaikePage) throws LssException;

}
