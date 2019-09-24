package com.lss.core.dto;

import com.lss.core.vo.PageParams;

public class FindOrderPayPage extends PageParams {
	/** 参数 */
	private Integer id;
	/** 工单号 */
	private String orderno;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

}
