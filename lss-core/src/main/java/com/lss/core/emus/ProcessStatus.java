package com.lss.core.emus;

/**
 * 
 * 话单 出来状态
 * 
 * CreateDate: 2019.06.19
 */
public enum ProcessStatus {

	/** 待处理 */
	INIT("待处理"),

	/** 已处理 */
	PROCESSED("已处理");

	private String name;

	ProcessStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

 
}
