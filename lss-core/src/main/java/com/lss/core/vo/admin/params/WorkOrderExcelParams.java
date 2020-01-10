package com.lss.core.vo.admin.params;

public class WorkOrderExcelParams {

	// 来源id
	private String isAll;
	// 来源id
	private String sourceid;
	// 来源id
	private String sourceid2;
	// 意愿
	private String level;
	/** 来源开始日期 */
	private String sourcedateStart;
	/** 来源结束日期 */
	private String sourcedateEnd;

	/** 分配时间-开始 */
	private String allottimeStart;
	/** 分配时间-结束 */
	private String allottimeEnd;

	
	public String getIsAll() {
		return isAll;
	}

	public void setIsAll(String isAll) {
		this.isAll = isAll;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public String getSourceid2() {
		return sourceid2;
	}

	public void setSourceid2(String sourceid2) {
		this.sourceid2 = sourceid2;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSourcedateStart() {
		return sourcedateStart;
	}

	public void setSourcedateStart(String sourcedateStart) {
		this.sourcedateStart = sourcedateStart;
	}

	public String getSourcedateEnd() {
		return sourcedateEnd;
	}

	public void setSourcedateEnd(String sourcedateEnd) {
		this.sourcedateEnd = sourcedateEnd;
	}

	public String getAllottimeStart() {
		return allottimeStart;
	}

	public void setAllottimeStart(String allottimeStart) {
		this.allottimeStart = allottimeStart;
	}

	public String getAllottimeEnd() {
		return allottimeEnd;
	}

	public void setAllottimeEnd(String allottimeEnd) {
		this.allottimeEnd = allottimeEnd;
	}

}
