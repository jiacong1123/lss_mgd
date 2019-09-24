package com.lss.core.dto;

import java.util.List;

import com.lss.core.vo.PageParams;

public class FindStOrderPage extends PageParams {
	/** 参数 */
	private Integer id;
	/** 开始月份日期 格式 yyyy-MM */
	private String startDateStr;
	/** 结束月份日期：格式 yyyy-MM */
	private String endDateStr;
	/** 下级员工集合 */
	private List<Integer> adminids;
	/** 员工名称 */
	private String adminName;
    /** 统计月份 格式 yyyy-MM */
    private String stDateStr;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public List<Integer> getAdminids() {
		return adminids;
	}

	public void setAdminids(List<Integer> adminids) {
		this.adminids = adminids;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getStDateStr() {
		return stDateStr;
	}

	public void setStDateStr(String stDateStr) {
		this.stDateStr = stDateStr;
	}

}
