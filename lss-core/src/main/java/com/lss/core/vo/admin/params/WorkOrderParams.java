package com.lss.core.vo.admin.params;

import java.util.Date;
import java.util.List;

import com.lss.core.vo.PageParams;

public class WorkOrderParams extends PageParams {
	// 前端传入参数
	private Integer status;
	private String name;
	private String phone;
	private String level;
	private Integer clinicid;
	private Date start;
	private Date end;
	
	
	// 权限参数
	private Integer adminid;
	private Integer doctorid;
	private String sort;
	private List<String> ordernos;
	/** 下级员工集合 */
	private List<Integer> adminids;
	/** 姓名或者电话查询 */
	private String searchKey;
	/** 来源开始日期 */
	private Date sourcedateStart;
	/** 来源结束日期 */
	private Date sourcedateEnd;
    /** 分配时间*/
    private Date allottime;
    /** 即将关闭的的时间 */
    private Date endCloseTime;
    
	/** 来源开始日期 */
	private String sourcedateStartStr;
	/** 来源结束日期 */
	private String sourcedateEndStr;
	/** 跟进时间开始日期 */
	private Date followupTimeStart;
	/** 跟进时间结束日期 */
	private Date followupTimeEnd;
	
	/** 分配时间-开始*/
    private Date allottimeStart;
    /** 分配时间-结束*/
    private Date allottimeEnd;
    
    /** 计划回访时间-开始*/
    private Date returndateStart;
    /** 计划回访时间-结束*/
    private Date returndateEnd;
    
    /** 回访状态:1-未回访;2-已回访*/
    private String isReturn;
    
    /** 预约时间*/
    private List<String> times;
    
    
    
    
	
	
	

	public List<String> getTimes() {
		return times;
	}

	public void setTimes(List<String> times) {
		this.times = times;
	}

	public String getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}

	public Date getReturndateStart() {
		return returndateStart;
	}

	public void setReturndateStart(Date returndateStart) {
		this.returndateStart = returndateStart;
	}

	public Date getReturndateEnd() {
		return returndateEnd;
	}

	public void setReturndateEnd(Date returndateEnd) {
		this.returndateEnd = returndateEnd;
	}

	public Date getAllottimeStart() {
		return allottimeStart;
	}

	public void setAllottimeStart(Date allottimeStart) {
		this.allottimeStart = allottimeStart;
	}

	public Date getAllottimeEnd() {
		return allottimeEnd;
	}

	public void setAllottimeEnd(Date allottimeEnd) {
		this.allottimeEnd = allottimeEnd;
	}

	public Date getFollowupTimeStart() {
		return followupTimeStart;
	}

	public void setFollowupTimeStart(Date followupTimeStart) {
		this.followupTimeStart = followupTimeStart;
	}

	public Date getFollowupTimeEnd() {
		return followupTimeEnd;
	}

	public void setFollowupTimeEnd(Date followupTimeEnd) {
		this.followupTimeEnd = followupTimeEnd;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = "".equals(name.trim()) ? null : name.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = "".equals(phone.trim()) ? null : phone.trim();
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = "".equals(level.trim()) ? null : level.trim();
	}

	public Integer getClinicid() {
		return clinicid;
	}

	public void setClinicid(Integer clinicid) {
		this.clinicid = clinicid;
	}

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public Integer getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public List<String> getOrdernos() {
		return ordernos;
	}

	public void setOrdernos(List<String> ordernos) {
		this.ordernos = ordernos;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public List<Integer> getAdminids() {
		return adminids;
	}

	public void setAdminids(List<Integer> adminids) {
		this.adminids = adminids;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Date getSourcedateStart() {
		return sourcedateStart;
	}

	public void setSourcedateStart(Date sourcedateStart) {
		this.sourcedateStart = sourcedateStart;
	}

	public Date getSourcedateEnd() {
		return sourcedateEnd;
	}

	public void setSourcedateEnd(Date sourcedateEnd) {
		this.sourcedateEnd = sourcedateEnd;
	}

	public Date getAllottime() {
		return allottime;
	}

	public void setAllottime(Date allottime) {
		this.allottime = allottime;
	}

	public Date getEndCloseTime() {
		return endCloseTime;
	}

	public void setEndCloseTime(Date endCloseTime) {
		this.endCloseTime = endCloseTime;
	}

	public String getSourcedateStartStr() {
		return sourcedateStartStr;
	}

	public void setSourcedateStartStr(String sourcedateStartStr) {
		this.sourcedateStartStr = sourcedateStartStr;
	}

	public String getSourcedateEndStr() {
		return sourcedateEndStr;
	}

	public void setSourcedateEndStr(String sourcedateEndStr) {
		this.sourcedateEndStr = sourcedateEndStr;
	}

	 
	
}
