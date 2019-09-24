package com.lss.core.dto;

import java.io.Serializable;
import java.util.Date;

public class StCallExportDto implements Serializable {

	/** 统计日期 */
	private String stDate;
	/** 登记人姓名 */
	private String adminname;

	/** 通话次数 */
	private Integer callCount;

	/** 通话时长 */
	private Integer duration;

	/** 平均时长 */
	private Integer avgDuration;

	
	public String getStDate() {
		return stDate;
	}

	public void setStDate(String stDate) {
		this.stDate = stDate;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public Integer getCallCount() {
		return callCount;
	}

	public void setCallCount(Integer callCount) {
		this.callCount = callCount;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getAvgDuration() {
		return avgDuration;
	}

	public void setAvgDuration(Integer avgDuration) {
		this.avgDuration = avgDuration;
	}

}
