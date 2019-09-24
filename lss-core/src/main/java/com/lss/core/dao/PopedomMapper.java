package com.lss.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lss.core.base.AbstractMapper;
import com.lss.core.pojo.Popedom;
import com.lss.core.vo.admin.PopedomVo;

public interface PopedomMapper extends AbstractMapper<Popedom, Integer> {
	/**
	 * 权限列表
	 * 
	 * @return
	 */
	List<PopedomVo> popedomList(@Param("roleids") List<Integer> roleids);
}
