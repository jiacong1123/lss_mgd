package com.lss.core.dao;

import java.util.List;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.WorkTag;

public interface WorkTagMapper extends AbstractMapper<WorkTag, Integer> {
	/**
	 * 查询标签列表
	 * 
	 * @param type
	 * @return
	 */
	List<WorkTag> queryTags(WorkTag tag);

	/**
	 * 查询来源
	 * 
	 * @param tag
	 * @return
	 */
	WorkTag getWorkTag(WorkTag tag);

	/**
	 * 根据类型查询
	 * 
	 * @param type
	 * @return
	 */
	WorkTag queryByType(Integer type);
}