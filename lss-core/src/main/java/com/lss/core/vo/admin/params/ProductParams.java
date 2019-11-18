package com.lss.core.vo.admin.params;

import com.lss.core.vo.PageParams;

public class ProductParams extends PageParams {
	private String title;
	private Integer classid;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = "".equals(title) ? null : title;
	}

	public Integer getClassid() {
		return classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}
}
