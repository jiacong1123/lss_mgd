package com.lss.pc.service.impl;

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

import com.lss.core.constant.ResponseCode;
import com.lss.core.dao.IBaikeDao;
import com.lss.core.dto.BaikeDto;
import com.lss.core.dto.FindBaikePage;
import com.lss.core.exception.LssException;
import com.lss.core.pojo.Baike;
import com.lss.core.util.AssertUtils;
import com.lss.core.vo.ReturnVo;
import com.lss.pc.service.IBaikeService;

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
 *         CreateDate: 2019.06.27
 */
@Service
public class BaikeServiceImpl implements IBaikeService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(BaikeServiceImpl.class);

	@Resource
	private IBaikeDao baikeDao;

	@Override
	public BaikeDto findBaike(BaikeDto baikeDto) throws LssException {
		logger.debug("findBaike(FindBaike findBaike={}) - start", baikeDto); //$NON-NLS-1$

		AssertUtils.notNull(baikeDto);
		AssertUtils.notAllNull(baikeDto.getId(), "id不能为空");
		try {
			Baike baike = baikeDao.selectByPrimaryKey(baikeDto.getId());
			if (baike == null) {
				return null;
				// throw new LssException(ErrorCode.BAIKE_NOT_EXIST_ERROR,"口腔百科信息不存在");
			}
			BaikeDto findBaikeReturn = new BaikeDto();
			// find数据录入
			findBaikeReturn.setId(baike.getId());
			findBaikeReturn.setTitle(baike.getTitle());
			findBaikeReturn.setSubtitle(baike.getSubtitle());
			findBaikeReturn.setImage(baike.getImage());
			findBaikeReturn.setType(baike.getType());
			findBaikeReturn.setStatus(baike.getStatus());
			findBaikeReturn.setCreatetime(baike.getCreatetime());
			findBaikeReturn.setClickvolume(baike.getClickvolume());
			findBaikeReturn.setSmallIcon(baike.getSmallIcon());
			findBaikeReturn.setUrl(baike.getUrl());
			findBaikeReturn.setContent(baike.getContent());
			findBaikeReturn.setLables(baike.getLables());
			logger.debug("findBaike(BaikeDto) - end - return value={}", findBaikeReturn); //$NON-NLS-1$
			return findBaikeReturn;
		} catch (LssException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找口腔百科信息信息错误！", e);
			throw new LssException(ResponseCode.failure, "查找口腔百科信息信息错误！", e);
		}

	}

	@Override
	public ReturnVo findBaikePage(FindBaikePage findBaikePage) throws LssException {
		logger.debug("findBaikePage(FindBaikePage findBaikePage={}) - start", findBaikePage); //$NON-NLS-1$

		AssertUtils.notNull(findBaikePage);
		List<BaikeDto> returnList = null;
		int count = 0;
		try {
			returnList = baikeDao.findBaikePage(findBaikePage);
			count = baikeDao.findBaikePageCount(findBaikePage);
		} catch (Exception e) {
			logger.error("口腔百科信息不存在错误", e);
			throw new LssException(ResponseCode.failure, "口腔百科信息不存在错误.！", e);
		}
		ReturnVo result = new ReturnVo();
		result.setPage(findBaikePage.getPage());
		result.setLimit(findBaikePage.getLimit());
		result.setObj(returnList);
		result.setTotal(count);
		result.setResult(ResponseCode.success);
		result.setMsg(ResponseCode.successMsg);

		logger.debug("findBaikePage(FindBaikePage) - end - return value={}", result); //$NON-NLS-1$
		return result;
	}

}
