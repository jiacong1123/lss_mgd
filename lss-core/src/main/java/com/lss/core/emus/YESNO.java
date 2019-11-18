package com.lss.core.emus;

/**
 * 
 * 
 * 类说明：性别
 *   
 * CreateDate: 2018年4月12日
 */
public enum YESNO {
	
	/** 男 */
	Y("是"),
	
	/** 女 */
	N("否");
	
	private String name;
	
	YESNO(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
