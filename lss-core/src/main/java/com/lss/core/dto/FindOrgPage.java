package com.lss.core.dto;

import com.lss.core.vo.PageParams;

public class FindOrgPage extends PageParams {
	/** 参数 */
	private Integer id;
	
	/** 上级机构 */
	private Integer parentId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	
}
