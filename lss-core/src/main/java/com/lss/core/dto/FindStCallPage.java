package com.lss.core.dto;

import java.util.Date;
import java.util.List;

import com.lss.core.vo.PageParams;

public class FindStCallPage extends PageParams {
	/** 参数 */
	private Integer id;

	/** 开始日期 */
	private Date startDate;
	/** 结束日期 */
	private Date endDate;
	/** 结束日期 */
	private String stDate;
	/** 下级员工集合 */
	private List<Integer> adminids;
    /** 员工名称*/
    private String adminName;
    
	/** 开始日期 yyyy-MM-dd */
	private String startDateStr;
	/** 结束日期 yyyy-MM-dd */
	private String endDateStr;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStDate() {
		return stDate;
	}

	public void setStDate(String stDate) {
		this.stDate = stDate;
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

}
