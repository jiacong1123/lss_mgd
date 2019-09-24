package com.lss.core.emus;

/**
 * 
 * WorkTag 表 Type说明
 * 
 * CreateDate: 2018年4月12日
 */
public enum WorkTagType {

	/** 预约项目 */
	yyxm(1),

	/** 工单来源 */
	gdly(2),

	/** 职称 */
	zhicheng(3),

	/** 医生科室 */
	ysks(4),

	/** 好乐美请求url */
	hlm_url(5),
	
	/** 直通车请求url */
	ztc_url(6),
	
	/** 工单标签 */
	label(7);

	private Integer type;

	WorkTagType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
