package com.lss.core.dto;

import com.lss.core.vo.PageParams;

public class FindBaikePage extends PageParams {
	/** 参数 */
	private Integer id;

	private Integer notStatus;
	/** 类型（1.口腔百科） */
	private Integer type;
	/** 状态 1启用 0禁用 -1删除 */
	private Integer status;
    /** 标签*/
    private String lables;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNotStatus() {
		return notStatus;
	}

	public void setNotStatus(Integer notStatus) {
		this.notStatus = notStatus;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLables() {
		return lables;
	}

	public void setLables(String lables) {
		this.lables = lables;
	}

}
