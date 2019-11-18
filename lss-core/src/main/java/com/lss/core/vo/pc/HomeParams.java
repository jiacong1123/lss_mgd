package com.lss.core.vo.pc;

import com.lss.core.vo.PageParams;

public class HomeParams extends PageParams {
	private Integer type;
	private Integer ksid;
	private String title;
	private Integer notType;
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getKsid() {
		return ksid;
	}

	public void setKsid(Integer ksid) {
		this.ksid = ksid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = "".equals(title) ? null : title;
	}

	public Integer getNotType() {
		return notType;
	}

	public void setNotType(Integer notType) {
		this.notType = notType;
	}
	
	
}
