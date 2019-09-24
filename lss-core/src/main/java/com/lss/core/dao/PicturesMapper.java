package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.Pictures;

public interface PicturesMapper extends AbstractMapper<Pictures, Integer> {

	/**
	 * 获取产品图片
	 * 
	 * @param pid
	 * @return
	 */
	List<Pictures> getList(Integer pid);

	/**
	 * 删除产品图片
	 * 
	 * @param pid
	 * @return
	 */
	int deleteByPid(Integer pid);
}